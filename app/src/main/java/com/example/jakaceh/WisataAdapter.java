package com.example.jakaceh;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jakaceh.databinding.ItemWisataBinding;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.MainViewHolder> {

    private Context context;
    public WisataAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWisataBinding binding = ItemWisataBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.binding.itemMainImage.setImageDrawable(ContextCompat.getDrawable(context, Daftar.wisata.get(position).gambar));
        holder.binding.itemMainRating.setRating(Daftar.wisata.get(position).rating);
        holder.binding.itemMainRatingValue.setText(String.valueOf(Daftar.wisata.get(position).rating));
        holder.binding.itemMainTitle.setText(Daftar.wisata.get(position).judul);
        holder.binding.itemMainDetail.setText(Daftar.wisata.get(position).detail);
        holder.binding.itemMainMore.setOnClickListener(l -> {
            Intent intent = new Intent(context, WisataDetailActivity.class);
            intent.putExtra("position", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return Daftar.wisata.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        public ItemWisataBinding binding;
        public MainViewHolder(ItemWisataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
