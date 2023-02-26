package com.example.chigratitude;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity {

    private  FirebaseAuth auth;
    private EditText name,email,password;
    private Button signupbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


    auth = FirebaseAuth.getInstance();
    name = findViewById(R.id.name);
    email = findViewById(R.id.email);
    password = findViewById(R.id.password);
    signupbtn = findViewById(R.id.signupbtn);

    signupbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String etname = name.getText().toString().trim();
            String etemail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (etname.isEmpty()){
                name.setError("name should not be empty");
            }
            if (etemail.isEmpty()){
                email.setError("email should not be empty");
            }
            if (pass.isEmpty()){
                password.setError("password should not be empty");
            }
        }
    });




    }
}