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
import com.example.final_project_tourmate.databinding.ActivityRegisterUserBinding;
import com.google.android.gms.common.data.DataBufferUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {
    private ActivityRegisterUserBinding binding;
    private String uid,name, email, password, reTypePassword, cellNumber;
    private Double cellNumberIn;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this, R.layout.activity_register_user);
       firebaseAuth = FirebaseAuth.getInstance();

        final Intent intent = getIntent();
    }

    public void registerUser(View view) {
        name = binding.nameET.getText().toString();
        email = binding.emailET.getText().toString();
        password = binding.passwordET.getText().toString().trim();
        reTypePassword = binding.rePasswordET.getText().toString().trim();
        cellNumber = binding.phnET.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(RegisterUser.this, "Please enter your name.", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty() || !email.contains("@")) {
            Toast.makeText(RegisterUser.this, "Please enter a valied email addrss.", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(RegisterUser.this, "Please enter password.", Toast.LENGTH_SHORT).show();
        } else if (!password.contains(reTypePassword)) {
            Toast.makeText(RegisterUser.this, "Password do not match.", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(RegisterUser.this, "Please enter 6 digit password.", Toast.LENGTH_SHORT).show();
        } else if (cellNumber.isEmpty() || cellNumber.length() < 11) {
            Toast.makeText(RegisterUser.this, "Please enter 11 digit number with out contry code.", Toast.LENGTH_SHORT).show();
        } else {
            createAccount(email, password);
            //Toast.makeText(Main2Activity.this, "You enter :"+name+" "+email+" "+password+" "+cellNumber, Toast.LENGTH_SHORT).show();
        }


    }

    private void createAccount(final String email, String password) {
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            user = firebaseAuth.getCurrentUser();
                            uid= task.getResult().getUser().getUid();
                            saveRegisterUser(new RegisterData(uid,name,email, Double.valueOf(cellNumber)));

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    private void saveRegisterUser(RegisterData registerData) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tourUser");

        myRef.child(uid).setValue(registerData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterUser.this, "Register is successful.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterUser.this, Create_Event_Tour_Activity.class));

                }

            }
        });
    }
}
