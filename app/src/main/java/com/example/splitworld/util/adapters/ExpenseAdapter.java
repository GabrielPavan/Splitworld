package com.example.splitworld.util.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitworld.R;
import com.example.splitworld.database.model.MemberModel;
import com.example.splitworld.database.model.TransactionsBetweenMembersHeadersModel;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private Context context;
    private List<TransactionsBetweenMembersHeadersModel> expenses;
    private OnExpenseDeleteListener onExpenseDeleteListener;
    private OnExpenseEditListener onExpenseEditListener;
    public interface OnExpenseDeleteListener {
        void OnExpenseDelete(TransactionsBetweenMembersHeadersModel expense, int position);
    }
    public interface OnExpenseAddListener {
        void OnExpenseAdd(TransactionsBetweenMembersHeadersModel expense);
    }
    public interface OnExpenseEditListener {
        void OnExpenseEdit(TransactionsBetweenMembersHeadersModel expense);
    }

    public ExpenseAdapter(Context context,
                          List<TransactionsBetweenMembersHeadersModel> expenses,
                          OnExpenseDeleteListener expenseDeleteListener,
                          OnExpenseEditListener expenseEditListener) {
        this.context = context;
        this.expenses = expenses;
        this.onExpenseDeleteListener = expenseDeleteListener;
        this.onExpenseEditListener = expenseEditListener;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_item, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        TransactionsBetweenMembersHeadersModel expense = expenses.get(position);
        holder.ExpenseType.setText(expense.getExpense_type());
        holder.ExpenseValue.setText(String.valueOf(expense.getExpense_total_value()));
        holder.ExpenseDescription.setText(expense.getExpense_description());
        holder.ExpensePayer.setText(expense.getPayed_by_name());

        holder.itemView.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Edit expense: " + expense.getExpense_type())
                    .setMessage("Would you like to edit or remove this expense?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        onExpenseDeleteListener.OnExpenseDelete(expense, position);
                    })
                    .setNegativeButton("Edit", ((dialog, which) -> {
                        onExpenseEditListener.OnExpenseEdit(expense);
                    }))
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView ExpenseType;
        TextView ExpenseDescription;
        TextView ExpensePayer;
        TextView ExpenseValue;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            ExpenseType= itemView.findViewById(R.id.ExpenseTitle);
            ExpenseDescription = itemView.findViewById(R.id.ExpenseDescription);
            ExpensePayer = itemView.findViewById(R.id.ExpensePayer);
            ExpenseValue = itemView.findViewById(R.id.ExpenseValue);
        }
    }
}
