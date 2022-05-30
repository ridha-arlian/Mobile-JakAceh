package com.example.jakaceh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.jakaceh.databinding.ActivityWisataBinding;
import com.google.android.material.navigation.NavigationView;

public class WisataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityWisataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWisataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

//        binding.mainSidebar.setOnClickListener(l -> {
//            binding.drawerLayout.openDrawer(GravityCompat.START);
//        });

        binding.navView.bringToFront();
        binding.navView.setNavigationItemSelectedListener(this);

        binding.mainRecycler.setAdapter(new WisataAdapter(this));
        binding.mainRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        binding.navView.setCheckedItem(R.id.nav_none);
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                Intent intentHome = new Intent(this,HomeActivity.class);
                startActivity(intentHome);
                break;

            case R.id.nav_profil:
                Intent intentProfile = new Intent(this,Profile.class);
                startActivity(intentProfile);
                break;
            case R.id.nav_about:
                Intent intentAbout = new Intent(this,About.class);
                startActivity(intentAbout);
                break;
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}