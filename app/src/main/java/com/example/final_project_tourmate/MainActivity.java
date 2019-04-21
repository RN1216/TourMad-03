package com.example.final_project_tourmate;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.final_project_tourmate.Package.RegisterData;
import com.example.final_project_tourmate.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String email, password;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();



    }

    public void loginUser(View view) {
        String email = binding.emailEditText.getText().toString();
        String password = binding.passwordEditText.getText().toString();
        logIn(new RegisterData(email, password));
        firebaseAuth .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            user = firebaseAuth.getCurrentUser();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.statusTV.setText(e.getLocalizedMessage());
            }
        });
    }

    private void logIn(RegisterData registerData) {

        if (registerData.getEmail().isEmpty() || !registerData.getEmail().contains("@") || !registerData.getEmail().endsWith(".com")) {
            Toast.makeText(MainActivity.this, "Please enter a valied email addrss.", Toast.LENGTH_SHORT).show();
        } else if (registerData.getPassword().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter a password.", Toast.LENGTH_SHORT).show();
        } else if (registerData.getPassword().length() < 6) {
            Toast.makeText(MainActivity.this, "Please enter 6 digit password.", Toast.LENGTH_SHORT).show();
        } else {
            //sing in code
            // Initialize Firebase Auth
            firebaseAuth.signInWithEmailAndPassword(registerData.getEmail(), registerData.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //Toast.makeText(SingInActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Create_Event_Tour_Activity.class));


                    } else {
                        Toast.makeText(MainActivity.this, "Please SingUp.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void signUp(View view) {
        startActivity(new Intent(MainActivity.this, RegisterUser.class));
    }
}
