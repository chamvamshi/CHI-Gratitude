package com.example.chigratitude;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerImageAdapter extends RecyclerView.Adapter<RecyclerImageAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ImageModel> imageModelArrayList;

    public RecyclerImageAdapter(Context context, ArrayList<ImageModel> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
    }

    @NonNull
    @Override
    public RecyclerImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.single_image_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerImageAdapter.ViewHolder holder, int position) {
        Log.d("abcd", "onBindViewHolder: imageView: " + imageModelArrayList);
        Glide.with(context)
                .load(imageModelArrayList.get(position).getImageurl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        Log.d("problem", "getItemCount: imageView"+ imageModelArrayList.size());
        return imageModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);


        }
    }
}
