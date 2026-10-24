package com.voicegov.ai.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.voicegov.ai.R;
import com.voicegov.ai.models.Exam;
import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {
    private List<Exam> exams;
    private OnExamClickListener listener;

    public interface OnExamClickListener {
        void onExamClick(Exam exam);
    }

    public ExamAdapter(List<Exam> exams, OnExamClickListener listener) {
        this.exams = exams;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exam exam = exams.get(position);
        holder.title.setText(exam.getTitle());
        holder.date.setText(exam.getDate());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onExamClick(exam);
        });
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvExamTitle);
            date = itemView.findViewById(R.id.tvExamDate);
        }
    }
}