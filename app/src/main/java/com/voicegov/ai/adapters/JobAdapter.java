package com.voicegov.ai.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.voicegov.ai.R;
import com.voicegov.ai.models.Job;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    private List<Job> jobs;

    public JobAdapter(List<Job> jobs) {
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.title.setText(job.getTitle());
        holder.org.setText(job.getOrganization());
        holder.date.setText(job.getLastDate());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, org, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvJobTitle);
            org = itemView.findViewById(R.id.tvJobOrg);
            date = itemView.findViewById(R.id.tvJobDate);
        }
    }
}