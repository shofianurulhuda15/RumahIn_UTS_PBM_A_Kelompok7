package com.example.rumahin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rumahin.adapter.HouseAdapter;
import com.example.rumahin.model.House;
import com.example.rumahin.util.DataDummy;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText etSearch;
    private RecyclerView rvResults;
    private HouseAdapter houseAdapter;
    private BottomNavigationView bottomNavigationView;
    private List<House> allHouses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Inisialisasi view
        etSearch = findViewById(R.id.et_search);
        rvResults = findViewById(R.id.rv_search_results);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Setup RecyclerView
        rvResults.setLayoutManager(new GridLayoutManager(this, 2));
        houseAdapter = new HouseAdapter(this);
        rvResults.setAdapter(houseAdapter);

        // Load semua data rumah
        allHouses = DataDummy.generateDummyHouses();
        houseAdapter.setHouseList(allHouses);

        // Text input listener untuk pencarian
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                houseAdapter.filterByQuery(s.toString());
            }
        });

        // Atur navigasi bawah
        bottomNavigationView.setSelectedItemId(R.id.nav_home); // Tandai yang aktif

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

    // Optional: handle tombol back agar tetap elegan
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
