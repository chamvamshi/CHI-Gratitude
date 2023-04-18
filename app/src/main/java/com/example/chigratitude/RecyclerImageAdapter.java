package com.example.chigratitude;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecyclerImageAdapter extends RecyclerView.Adapter<RecyclerImageAdapter.ViewHolder> {
    //    NavigationView deleteoption;
    FirebaseStorage storage;

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
        String imageUrl = imageModelArrayList.get(position).getImageurl();

        Glide.with(context)
                .load(imageUrl)
                .into(holder.imageView);

        holder.deleteBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                storage = FirebaseStorage.getInstance();
                StorageReference reference = storage.getReferenceFromUrl(imageUrl);
                reference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageFullActivity.class);
                intent.putExtra("image", imageModelArrayList.get(position).getImageurl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView, deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
        }
    }
}
