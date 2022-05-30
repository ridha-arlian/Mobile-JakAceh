package com.example.jakaceh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.jakaceh.databinding.ActivityWisataDetailBinding;

public class WisataDetailActivity extends AppCompatActivity {

    private ActivityWisataDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWisataDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Wisata wisata = Daftar.wisata.get(getIntent().getIntExtra("position", 0));

        binding.judul.setText(wisata.judul);
        binding.gambar.setImageDrawable(ContextCompat.getDrawable(this, wisata.gambar));
        binding.detail.setText(wisata.detailSpec);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}