package com.example.chigratitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private EditText nameEt;
    private Button signinbtn;
    private EditText readData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        nameEt = findViewById(R.id.name);
        signinbtn = findViewById(R.id.signInbtn);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String username = nameEt.getText().toString();
                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter the name",Toast.LENGTH_SHORT).show();
                } else {
                    readData(username);
                }
            }
        });

    }

    private void readData(String username) {
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("users");
        ref.child(username).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                           String  email = String.valueOf(dataSnapshot.child("email"));
                          String   name = String.valueOf(dataSnapshot.child("name"));

                          Intent openMainActivity = new Intent(SignInActivity.this,MainActivity2.class);
                          startActivity(openMainActivity);
                        }else{
                            Toast.makeText(getApplicationContext(),"user doesn't exist",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                    }
                });
}
}
