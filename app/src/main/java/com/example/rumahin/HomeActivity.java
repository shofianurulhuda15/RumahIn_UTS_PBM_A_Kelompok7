package com.example.rumahin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rumahin.adapter.CategoryAdapter;
import com.example.rumahin.adapter.HouseAdapter;
import com.example.rumahin.model.House;
import com.example.rumahin.util.DataDummy;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClickListener {

    private RecyclerView rvCategories, rvHouses;
    private CategoryAdapter categoryAdapter;
    private HouseAdapter houseAdapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("RumahIn");

        // Setup bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    return true;
                } else if (itemId == R.id.nav_favorite) {
                    // Untuk front-end saja, belum diimplementasikan
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_post) {
                    Intent intent = new Intent(HomeActivity.this, PostHouseActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        // Setup RecyclerView for Categories
        rvCategories = findViewById(R.id.rv_categories);
        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(this, this);
        rvCategories.setAdapter(categoryAdapter);

        // Setup RecyclerView for Houses
        rvHouses = findViewById(R.id.rv_houses);
        rvHouses.setLayoutManager(new GridLayoutManager(this, 2));
        houseAdapter = new HouseAdapter(this);
        rvHouses.setAdapter(houseAdapter);

        // Load data
        loadData();
    }

    private void loadData() {
        // Load categories
        List<String> categories = DataDummy.generateCategories();
        categoryAdapter.setCategoryList(categories);

        // Load houses
        List<House> houses = DataDummy.generateDummyHouses();
        houseAdapter.setHouseList(houses);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        });

        return true;
    }


    @Override
    public void onCategoryClick(String category) {
        houseAdapter.filterByCategory(category);
    }
}