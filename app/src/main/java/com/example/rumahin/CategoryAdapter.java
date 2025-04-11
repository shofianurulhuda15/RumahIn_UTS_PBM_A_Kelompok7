package com.example.rumahin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rumahin.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<String> categoryList;
    private Context context;
    private int selectedPosition = 0;
    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(String category);
    }

    public CategoryAdapter(Context context, OnCategoryClickListener listener) {
        this.context = context;
        this.categoryList = new ArrayList<>();
        this.listener = listener;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String category = categoryList.get(position);
        holder.tvCategory.setText(category);

        // Change background color based on selection
        if (position == selectedPosition) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.tvCategory.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorLightGray));
            holder.tvCategory.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelected = selectedPosition;
                selectedPosition = holder.getAdapterPosition();

                notifyItemChanged(previousSelected);
                notifyItemChanged(selectedPosition);

                listener.onCategoryClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            tvCategory = itemView.findViewById(R.id.tv_category);
        }
    }
}