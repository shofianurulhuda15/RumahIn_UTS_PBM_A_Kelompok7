package com.example.rumahin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.rumahin.model.House;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private House house;
    private ImageView ivHouse, ivFavorite;
    private TextView tvName, tvAddress, tvPrice, tvRooms, tvBathrooms, tvArea, tvDescription, tvOwnerName, tvOwnerPhone, tvCategory;
    private Button btnContact;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detail Hunian");
        }

        // Initialize views
        ivHouse = findViewById(R.id.iv_house);
        ivFavorite = findViewById(R.id.iv_favorite);
        tvName = findViewById(R.id.tv_name);
        tvAddress = findViewById(R.id.tv_address);
        tvPrice = findViewById(R.id.tv_price);
        tvRooms = findViewById(R.id.tv_rooms);
        tvBathrooms = findViewById(R.id.tv_bathrooms);
        tvArea = findViewById(R.id.tv_area);
        tvDescription = findViewById(R.id.tv_description);
        tvOwnerName = findViewById(R.id.tv_owner_name);
        tvOwnerPhone = findViewById(R.id.tv_owner_phone);
        tvCategory = findViewById(R.id.tv_category);
        btnContact = findViewById(R.id.btn_contact);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Get data from intent
        if (getIntent().hasExtra("house")) {
            house = (House) getIntent().getSerializableExtra("house");
            populateData();
        } else {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Set bottom navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_favorite) {
//                startActivity(new Intent(this, FavoriteActivity.class));
//                finish();
                return true;
            } else if (id == R.id.nav_post) {
                startActivity(new Intent(this, PostHouseActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    private void populateData() {
        // Format harga
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedPrice = currencyFormat.format(house.getPrice()).replace("Rp", "Rp ").replace(",00", "");

        tvName.setText(house.getName());
        tvAddress.setText(house.getAddress());
        tvPrice.setText(formattedPrice + " / tahun");
        tvRooms.setText(house.getRooms() + " Kamar Tidur");
        tvBathrooms.setText(house.getBathrooms() + " Kamar Mandi");
        tvArea.setText(house.getArea() + " m²");
        tvDescription.setText(house.getDescription());
        tvOwnerName.setText("Nama Pemilik: " + house.getOwnerName());
        tvOwnerPhone.setText("Nomor HP: " + house.getOwnerPhone());
        tvCategory.setText("Kategori: " + house.getCategory());

        // Load image
        Glide.with(this)
                .load(house.getImageUrl())
                .placeholder(R.drawable.sample_house)
                .error(R.drawable.sample_house)
                .centerCrop()
                .into(ivHouse);

        // Set favorite icon
        updateFavoriteIcon();

        // Favorite toggle
        ivFavorite.setOnClickListener(v -> {
            house.setFavorite(!house.isFavorite());
            updateFavoriteIcon();
            String message = house.isFavorite() ? "Ditambahkan ke favorit" : "Dihapus dari favorit";
            Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
        });

        // Hubungi pemilik
        btnContact.setOnClickListener(v -> {
            String phoneNumber = house.getOwnerPhone();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        });
    }

    private void updateFavoriteIcon() {
        if (house.isFavorite()) {
            ivFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite));
        } else {
            ivFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
