package com.example.splitworld.util.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitworld.R;
import com.example.splitworld.database.model.MemberModel;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    private List<MemberModel> memberList;

    public MemberAdapter(List<MemberModel> memberList) {
        this.memberList = memberList;
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
        holder.textViewTotalBorrowed.setText("Borrowed: " + member.getTotal_loan());
        holder.textViewTotalPaid.setText("Paid: " + member.getTotal_paid());
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
            textViewTotalBorrowed = itemView.findViewById(R.id.textViewTotalBorrowed);
            textViewTotalPaid = itemView.findViewById(R.id.textViewTotalPaid);
        }
    }
}
