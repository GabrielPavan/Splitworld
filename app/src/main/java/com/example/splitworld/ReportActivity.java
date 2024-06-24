package com.example.splitworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.JsonWriter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitworld.api.API;
import com.example.splitworld.api.model.Response;
import com.example.splitworld.api.model.Travel;
import com.example.splitworld.api.model.TravelAirTicketCost;
import com.example.splitworld.api.model.TravelGasCost;
import com.example.splitworld.api.model.TravelHostingCost;
import com.example.splitworld.api.model.TravelMealCost;
import com.example.splitworld.api.model.TravelOtherCost;
import com.example.splitworld.database.dao.MemberDAO;
import com.example.splitworld.database.dao.TransactionsBetweenMembersHeadersDAO;
import com.example.splitworld.database.model.MemberModel;
import com.example.splitworld.database.model.TransactionsBetweenMembersHeadersModel;
import com.example.splitworld.util.SharedKey;
import com.example.splitworld.util.adapters.MemberAdapter;

import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;

public class ReportActivity extends AppCompatActivity {
    private MemberAdapter memberAdapter;
    private MemberDAO memberDAO;
    private TransactionsBetweenMembersHeadersDAO transactionsBetweenMembersHeadersDAO;
    private List<MemberModel> memberList;
    private TextView textViewTotalTravelers, textViewTotalCost, textViewCostPerPerson;
    private double totalAmount = 0.0;
    private Button buttonBack, buttonEndTrip;

    Travel travel = new Travel();
    TravelGasCost travelGasCost = new TravelGasCost();
    TravelAirTicketCost travelAirTicketCost = new TravelAirTicketCost();
    TravelHostingCost travelHostingCost = new TravelHostingCost();
    TravelMealCost travelMealCost = new TravelMealCost();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        textViewTotalTravelers = findViewById(R.id.textViewTotalTravelers);
        textViewTotalCost = findViewById(R.id.textViewTotalCost);
        textViewCostPerPerson = findViewById(R.id.textViewCostPerPerson);
        buttonBack = findViewById(R.id.buttonBack);
        buttonEndTrip = findViewById(R.id.buttonEndTrip);

        memberDAO = new MemberDAO(this);
        transactionsBetweenMembersHeadersDAO = new TransactionsBetweenMembersHeadersDAO(this);

        memberList = memberDAO.findAll();
        updateTotalSpent(memberList);
        memberList = memberDAO.findAll();

        RecyclerView recyclerViewMembers = findViewById(R.id.recyclerViewMembers);
        recyclerViewMembers.setLayoutManager(new LinearLayoutManager(this));

        memberAdapter = new MemberAdapter(this, memberList);
        recyclerViewMembers.setAdapter(memberAdapter);

        textViewTotalTravelers.setText("Total Travelers: " + memberList.size());
        setupTotal(transactionsBetweenMembersHeadersDAO.findAll());
        textViewCostPerPerson.setText(String.format("Average cost per Member: %.2f", totalAmount/memberList.size()));

        buttonBack.setOnClickListener(v -> {
            startActivity(new Intent(ReportActivity.this, MainActivity.class));
            finish();
        });
        buttonEndTrip.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Save Travel and Restart");
            builder.setMessage("The information will be sent to our database and removed from your device.");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ReportActivity.this);
                    SharedPreferences.Editor edit = preferences.edit();

                    travel.setLocal(preferences.getString(SharedKey.KEY_DESTINY, "error"));
                    travel.setDuracaoViagem(1);
                    travel.setCustoTotalViagem(totalAmount);
                    travel.setCustoPorPessoa(totalAmount/memberList.size());
                    travel.setTotalViajantes(memberList.size());
                    travel.setIdConta(117166);

                    travelGasCost.setId(117166);
                    travelGasCost.setCustoMedioLitro(1);
                    travelGasCost.setMediaKMLitro(1);
                    travelGasCost.setCustoMedioLitro(1);
                    travelGasCost.setTotalVeiculos(1);

                    travel.setGasolina(travelGasCost);

                    travelAirTicketCost.setId(117166);
                    travelAirTicketCost.setCustoPessoa(
                            transactionsBetweenMembersHeadersDAO.findAll().stream()
                            .filter(t -> "Air Ticket".equals(t.getExpense_description()))
                            .mapToDouble(TransactionsBetweenMembersHeadersModel::getExpense_total_value)
                            .sum() / memberList.size());
                    travelAirTicketCost.setCustoAluguelVeiculo(0);

                    travel.setAereo(travelAirTicketCost);

                    List<TravelOtherCost> travelOtherCosts =
                            transactionsBetweenMembersHeadersDAO.findAll().stream()
                            .filter(t -> "Other".equals(t.getExpense_description()) || "Entertainment".equals(t.getExpense_description()))
                            .map(t -> new TravelOtherCost(t))
                            .collect(Collectors.toList());

                    travel.setListaEntretenimento(travelOtherCosts);

                    travelHostingCost.setId(117166);
                    travelHostingCost.setTotalNoite(
                            transactionsBetweenMembersHeadersDAO.findAll().stream()
                            .filter(t -> "Other".equals(t.getExpense_description()) || "Entertainment".equals(t.getExpense_description()))
                            .map(t -> new TravelOtherCost(t))
                            .collect(Collectors.toList()).size());
                    travelHostingCost.setCustoMedioNoite(
                            transactionsBetweenMembersHeadersDAO.findAll().stream()
                            .filter(t -> "Hosting".equals(t.getExpense_description()))
                            .mapToDouble(TransactionsBetweenMembersHeadersModel::getExpense_total_value)
                            .sum());
                    travelHostingCost.setTotalQuartos(0);

                    travel.setHospedagem(travelHostingCost);

                    travelMealCost.setId(117166);
                    travelMealCost.setCustoRefeicao(
                            transactionsBetweenMembersHeadersDAO.findAll().stream()
                                    .filter(t -> "Food".equals(t.getExpense_description()))
                                    .mapToDouble(TransactionsBetweenMembersHeadersModel::getExpense_total_value)
                                    .sum()
                    );
                    travelMealCost.setRefeicoesDia(0);
                    travel.setRefeicao(travelMealCost);
                    SendToAPI();

                    edit.putString(SharedKey.KEY_DESTINY, "");
                    edit.apply();
                    transactionsBetweenMembersHeadersDAO.deleteAll();
                    startActivity(new Intent(ReportActivity.this, MainActivity.class));
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(ReportActivity.this, MainActivity.class));
                    finish();
                }
            });
            builder.show();
        });

    }
    private void setupTotal(List<TransactionsBetweenMembersHeadersModel> total){
        totalAmount = 0.0;
        for (TransactionsBetweenMembersHeadersModel transaction : total) {
            totalAmount += transaction.getExpense_total_value();
        }
        textViewTotalCost.setText(String.format("Total Trip Cost: $%.2f", totalAmount));
    }

    private void updateTotalSpent(List<MemberModel> memberList){
        for (MemberModel member : memberList) {
            double totalAmount = transactionsBetweenMembersHeadersDAO.getTotalTransactionsByMemberId(member.getId());
            memberDAO.updateTotalPaid(member.getId(), totalAmount);
        }
    }
    private void SendToAPI(){
        API.postTravel(travel, new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response != null && response.isSuccessful()) {
                    Response r = response.body();
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(ReportActivity.this);
                    builder2.setMessage("Sucesso " + r.getMensagem() + "\n\n" + travel.toString());
                    builder2.create().show();
                }
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                AlertDialog.Builder builder1= new AlertDialog.Builder(ReportActivity.this);
                builder1.setMessage("Test" + t.getMessage());
                builder1.create().show();
            }
        });
    }

}