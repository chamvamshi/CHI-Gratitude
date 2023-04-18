package com.example.chigratitude;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
    private final int GALLERY_REQ_CODE = 1000;
    ActivityResultLauncher<String> launcher;
    private RecyclerView recyclerView;
    private ArrayList<ImageModel> imageModelArrayList;
    private RecyclerImageAdapter recyclerImageAdapter;
    DatabaseReference database;
    FirebaseStorage storage;
    ImageView imageView;
    ImageModel image;
    NavigationView deleteoption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(GalleryActivity.this, 2));
        recyclerView.setHasFixedSize(true);

        imageModelArrayList = new ArrayList<>();


        FloatingActionButton fabbtn = findViewById(R.id.fab);

        database = FirebaseDatabase.getInstance().getReference().child("image");
        storage = FirebaseStorage.getInstance();


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAll();
                imageModelArrayList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    image = new ImageModel();
                    image.setImageurl(snapshot1.getValue(String.class));
                    imageModelArrayList.add(image);
                }
                recyclerImageAdapter = new RecyclerImageAdapter(getApplicationContext(), imageModelArrayList);
                recyclerView.setAdapter(recyclerImageAdapter);
                recyclerImageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GalleryActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        image.setImageURI(result);
                        final StorageReference reference = storage.getReference()
                                .child("image").child(String.valueOf(System.currentTimeMillis()));
                        reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                          // String document = taskSnapshot.;
                                            String url = task.getResult().toString();
                                            database.push()
                                                    .setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {

                                                                Toast.makeText(GalleryActivity.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                                                            } else {
                                                                Toast.makeText(GalleryActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        } else {
                                            task.getException().printStackTrace();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });

        fabbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
            }
        });




    }

    private void clearAll() {
        if(imageModelArrayList != null){
            imageModelArrayList.clear();

            if(recyclerImageAdapter != null){
                recyclerImageAdapter.notifyDataSetChanged();
            }
            imageModelArrayList = new ArrayList<>();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == GALLERY_REQ_CODE) {

                image.setImageURI(data.getData());

            }
        }
    }




}