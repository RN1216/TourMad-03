package com.example.final_project_tourmate;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_project_tourmate.Package.TourInfo;
import com.example.final_project_tourmate.databinding.ActivityCreateEventTourBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Create_Event_Tour_Activity extends AppCompatActivity {

    private ActivityCreateEventTourBinding binding;
    private SimpleDateFormat dateSDF = new SimpleDateFormat("dd/MM/yyyy");
    private EditText tourNameET, tourDescriptionET, startDateET, endDateET, budgetET;
    private Button saveTourInfoBtn;

    private String uid, tourName, tourDescription;
    private long startDate, endDate;
    private double budget;
    private FirebaseAuth firebaseAuth;
    // Write to the database
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView( this, R.layout.activity_create__event__tour_);
       firebaseAuth = FirebaseAuth.getInstance();

       binding.startDateAddTourET.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               pickStartDate();
           }
       });

       binding.endDateAddTourET.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               pickEndDate();
           }
       });

       binding.saveTourInfoAddNewTourBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               tourName = binding.tourNameET.getText().toString();
               tourDescription = binding.tourDescriptionET.getText().toString();
               budget = Double.valueOf(binding.tourBudgetET.getText().toString());

               if (tourName.isEmpty()) {
                   Toast.makeText(getApplicationContext(), "Please enter your tour name.", Toast.LENGTH_SHORT).show();
               } else if (tourDescription.isEmpty()) {
                   Toast.makeText(getApplicationContext(), "Please enter something about your tour.", Toast.LENGTH_SHORT).show();
               } else if (budget == 0) {
                   Toast.makeText(getApplicationContext(), "Please enter your budget.", Toast.LENGTH_SHORT).show();
               } else if (startDate == 0 || endDate == 0) {
                   Toast.makeText(getApplicationContext(), "Please enter your tour date range.", Toast.LENGTH_SHORT).show();
               } else {
                   saveTourInfo(new TourInfo(tourName, tourDescription, startDate, endDate, budget));
               }
           }
       });

    }

    private void pickEndDate() {

        DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String selectedDate1 = day + "/" + month + "/" + year;
                Date date1 = new Date();
                try {
                    date1 = dateSDF.parse(selectedDate1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                endDate = date1.getTime();
                binding.endDateAddTourET.setText(selectedDate1);

            }
        };
        Calendar calendar1 = Calendar.getInstance();
        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH);
        int day = calendar1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog1 = new DatePickerDialog(Create_Event_Tour_Activity.this, dateSetListener1, year, month, day);
        datePickerDialog1.show();

    }

    private void pickStartDate() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String selectedDate = day + "/" + month + "/" + year;
                Date date = new Date();
                try {
                    date = dateSDF.parse(selectedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                startDate = date.getTime();
                binding.startDateAddTourET.setText(selectedDate);

            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(Create_Event_Tour_Activity.this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    public void saveTourInfo(TourInfo tourInfo) {

        String user = firebaseAuth.getCurrentUser().getUid();
        // Write to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tourUser").child(user);
        uid = myRef.push().getKey();
        tourInfo.setTourUid(uid);
        myRef.child("event").child(uid).setValue(tourInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Create_Event_Tour_Activity.this, "Tour info saved successfully.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Create_Event_Tour_Activity.this, TourDetailInfoActivity.class));
                    finish();
                } else {
                    Toast.makeText(Create_Event_Tour_Activity.this, "Tour info not save successfully.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
