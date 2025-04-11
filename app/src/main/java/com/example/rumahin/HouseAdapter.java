package com.example.rumahin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rumahin.DetailActivity;
import com.example.rumahin.R;
import com.example.rumahin.model.House;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.HouseViewHolder> {

    private List<House> houseList;
    private List<House> filteredList;
    private Context context;

    public HouseAdapter(Context context) {
        this.context = context;
        this.houseList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
    }

    public void setHouseList(List<House> houseList) {
        this.houseList = houseList;
        this.filteredList = new ArrayList<>(houseList);
        notifyDataSetChanged();
    }

    public void filterByCategory(String category) {
        filteredList.clear();
        if (category.equals("Semua")) {
            filteredList.addAll(houseList);
        } else {
            for (House house : houseList) {
                if (house.getCategory().equals(category)) {
                    filteredList.add(house);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filterByQuery(String query) {
        filteredList.clear();
        if (query == null || query.isEmpty()) {
            filteredList.addAll(houseList);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (House house : houseList) {
                if (house.getName().toLowerCase().contains(lowerCaseQuery) ||
                        house.getAddress().toLowerCase().contains(lowerCaseQuery) ||
                        house.getCategory().toLowerCase().contains(lowerCaseQuery)) {
                    filteredList.add(house);
                }
            }
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_house, parent, false);
        return new HouseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        House house = filteredList.get(position);

        // Format harga
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedPrice = currencyFormat.format(house.getPrice()).replace("Rp", "Rp ").replace(",00", "");

        holder.tvName.setText(house.getName());
        holder.tvAddress.setText(house.getAddress());
        holder.tvPrice.setText(formattedPrice + " / tahun");
        holder.tvRooms.setText(house.getRooms() + " Kamar");
        holder.tvCategory.setText(house.getCategory());

        // Load image
        Glide.with(context)
                .load(house.getImageUrl())
                .placeholder(R.drawable.sample_house)
                .error(R.drawable.sample_house)
                .centerCrop()
                .into(holder.ivHouse);

        // Set favorite icon
        if (house.isFavorite()) {
            holder.ivFavorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite));
        } else {
            holder.ivFavorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border));
        }

        // Set click listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("house", house);
                context.startActivity(intent);
            }
        });

        // Set favorite click listener
        holder.ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                house.setFavorite(!house.isFavorite());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class HouseViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView ivHouse, ivFavorite;
        TextView tvName, tvAddress, tvPrice, tvRooms, tvCategory;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            ivHouse = itemView.findViewById(R.id.iv_house);
            ivFavorite = itemView.findViewById(R.id.iv_favorite);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvRooms = itemView.findViewById(R.id.tv_rooms);
            tvCategory = itemView.findViewById(R.id.tv_category);
        }
    }


}