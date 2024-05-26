package com.example.splitworld;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private ListView marksListView;
    private Button fab;
    private HashMap<String, List<String>> marks;
    private ArrayAdapter<String> adapter;
    private List<String> markList;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        marksListView = findViewById(R.id.marksListView);
        fab = findViewById(R.id.fab);

        marks = new HashMap<>();
        markList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, markList);
        marksListView.setAdapter(adapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                loadMarksForSelectedDate();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDate != null) {
                    showAddMarkDialog();
                } else {
                    Toast.makeText(CalendarActivity.this, "Please select a date first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void loadMarksForSelectedDate() {
        markList.clear();
        if (marks.containsKey(selectedDate)) {
            markList.addAll(marks.get(selectedDate));
        }
        adapter.notifyDataSetChanged();
    }

    private void showAddMarkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_mark, null);
        builder.setView(dialogView);

        final EditText markEditText = dialogView.findViewById(R.id.markEditText);

        builder.setTitle("Add Mark")
                .setPositiveButton("Add", (dialog, which) -> {
                    String mark = markEditText.getText().toString().trim();
                    if (!mark.isEmpty()) {
                        addMark(selectedDate, mark);
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void addMark(String date, String mark) {
        if (!marks.containsKey(date)) {
            marks.put(date, new ArrayList<>());
        }
        marks.get(date).add(mark);
        loadMarksForSelectedDate();
    }
}