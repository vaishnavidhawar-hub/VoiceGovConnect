package com.voicegov.ai.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.voicegov.ai.R;
import com.voicegov.ai.models.Scheme;
import java.util.List;

public class SchemeAdapter extends RecyclerView.Adapter<SchemeAdapter.ViewHolder> {
    private List<Scheme> schemes;
    private OnSchemeClickListener listener;

    public interface OnSchemeClickListener {
        void onSchemeClick(Scheme scheme);
    }

    public SchemeAdapter(List<Scheme> schemes, OnSchemeClickListener listener) {
        this.schemes = schemes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scheme_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Scheme scheme = schemes.get(position);
        holder.tvTitle.setText(scheme.title);
        holder.tvCategory.setText(scheme.description);
        holder.itemView.setOnClickListener(v -> listener.onSchemeClick(scheme));
    }

    @Override
    public int getItemCount() {
        return schemes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}