package com.voicegov.ai.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.voicegov.ai.R;
import com.voicegov.ai.models.Scholarship;
import java.util.List;

public class ScholarshipAdapter extends RecyclerView.Adapter<ScholarshipAdapter.ViewHolder> {
    private List<Scholarship> scholarships;

    public ScholarshipAdapter(List<Scholarship> scholarships) {
        this.scholarships = scholarships;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scholarship_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Scholarship scholarship = scholarships.get(position);
        holder.title.setText(scholarship.getTitle());
        holder.amount.setText(scholarship.getAmount());
        holder.deadline.setText(scholarship.getDeadline());
    }

    @Override
    public int getItemCount() {
        return scholarships.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, amount, deadline;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvScholarshipTitle);
            amount = itemView.findViewById(R.id.tvScholarshipAmount);
            deadline = itemView.findViewById(R.id.tvScholarshipDeadline);
        }
    }
}