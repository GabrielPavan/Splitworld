package com.example.splitworld.util.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitworld.MemberListActivity;
import com.example.splitworld.R;
import com.example.splitworld.database.model.MemberModel;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    public interface OnMemberDeleteListener {
        void onMemberDelete(MemberModel member, int position);
    }
    public interface OnMemberAddListener {
        void onMemberAdd(MemberModel member);
    }

    private List<MemberModel> memberList;
    private Context context;
    private OnMemberDeleteListener deleteListener;
    private OnMemberAddListener addListener;

    public MemberAdapter(Context context, List<MemberModel> memberList, OnMemberDeleteListener deleteListener, OnMemberAddListener addListener) {
        this.context = context;
        this.memberList = memberList;
        this.deleteListener = deleteListener;
        this.addListener = addListener;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_item, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        MemberModel member = memberList.get(position);
        holder.textViewMemberName.setText(member.getName());
        holder.textViewTotalPaid.setText("Total spent: " + member.getTotal_paid());

        holder.itemView.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Member")
                    .setMessage("Are you sure you want to delete " + member.getName() + "?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if(member.getTotal_paid() > 0){
                            Toast.makeText(context, "This member has transactions that cannot be deleted", Toast.LENGTH_LONG).show();
                            return;
                        }
                        deleteListener.onMemberDelete(member, position);
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder {
        ImageView iconMember;
        TextView textViewMemberName;
        TextView textViewTotalBorrowed;
        TextView textViewTotalPaid;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            iconMember = itemView.findViewById(R.id.iconMember);
            textViewMemberName = itemView.findViewById(R.id.textViewMemberName);
            textViewTotalPaid = itemView.findViewById(R.id.textViewTotalPaid);
        }
    }
}
