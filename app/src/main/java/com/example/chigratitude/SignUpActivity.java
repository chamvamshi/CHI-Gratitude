package com.example.chigratitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    private EditText nameEt, emailEt, passwordEt;
    private Button signupbtn;
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        login = findViewById(R.id.signin);
        nameEt = findViewById(R.id.name);
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);
        signupbtn = findViewById(R.id.signupbtn);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("users");
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, MainActivity2.class));
//                String name = nameEt.getText().toString().trim();
//                String email = emailEt.getText().toString().trim();
//                String pass = passwordEt.getText().toString().trim();
//
//                if (name.isEmpty()) {
//                    nameEt.setError("name should not be empty");
//                    return;
//                }
//                if (email.isEmpty()) {
//                    emailEt.setError("email should not be empty");
//                    return;
//                }
//                if (pass.isEmpty()) {
//                    passwordEt.setError("password should not be empty");
//                    return;
//                }
//                User user = new User(name, email, pass);
//
//                ref.child(name).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(SignUpActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
//                            Intent signupIntent = new Intent(SignUpActivity.this, MainActivity2.class);
//                            startActivity(signupIntent);
//                            finish();
//                        } else {
//                            Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                        }
//                        finish();
//                    }
//                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openActivity = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(openActivity);
            }
        });


    }
}