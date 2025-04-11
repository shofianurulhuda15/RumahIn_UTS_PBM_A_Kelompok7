package com.example.rumahin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PostHouseActivity extends AppCompatActivity {

    private EditText etName, etAddress, etDescription, etPrice, etRooms, etBathrooms, etArea, etImageUrl, etOwnerName, etOwnerPhone;
    private Spinner spinnerCategory;
    private Button btnPost;
    private BottomNavigationView bottomNavigationView; // Tambahkan ini

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_house);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Posting Hunian"); // Optional: set judul
        }

        etName = findViewById(R.id.et_name);
        etAddress = findViewById(R.id.et_address);
        etDescription = findViewById(R.id.et_description);
        etPrice = findViewById(R.id.et_price);
        etRooms = findViewById(R.id.et_rooms);
        etBathrooms = findViewById(R.id.et_bathrooms);
        etArea = findViewById(R.id.et_area);
        etImageUrl = findViewById(R.id.et_image_url);
        etOwnerName = findViewById(R.id.et_owner_name);
        etOwnerPhone = findViewById(R.id.et_owner_phone);
        spinnerCategory = findViewById(R.id.spinner_category);
        btnPost = findViewById(R.id.btn_post);
        bottomNavigationView = findViewById(R.id.bottom_navigation); // Inisialisasi

        // Setup dropdown kategori
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.category_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        btnPost.setOnClickListener(v -> {
            Toast.makeText(this, "Rumah berhasil diposting (dummy)", Toast.LENGTH_SHORT).show();
        });

        // Bottom navigation logic
        bottomNavigationView.setOnItemSelectedListener(item -> {
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
                // Di sini kita sudah di halaman ini, jadi tidak perlu berpindah
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });

        // Highlight tab saat ini
        bottomNavigationView.setSelectedItemId(R.id.nav_post);
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
