package com.example.final_project_tourmate;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.final_project_tourmate.Package.TourInfo;
import com.example.final_project_tourmate.databinding.ActivityTourDetailInfoBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TourDetailInfoActivity extends AppCompatActivity {

    private ActivityTourDetailInfoBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private TourRecyclerViewAdapter tourRecyclerViewAdapter;
    private ArrayList<TourInfo> dataList;
    private String user;

    private long startDate, endDate;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateSDF = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_tour_detail_info);

        dataList = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        user = firebaseAuth.getCurrentUser().getUid();
        userRef = database.getReference("tourUser").child(user).child("event");

        getDataFromDatabase();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        tourRecyclerViewAdapter = new TourRecyclerViewAdapter(dataList, getApplicationContext());
        binding.recyclerView.setAdapter(tourRecyclerViewAdapter);

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


            }
        };
        Calendar calendar1 = Calendar.getInstance();
        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH);
        int day = calendar1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog1 = new DatePickerDialog(TourDetailInfoActivity.this, dateSetListener1, year, month, day);
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


            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(TourDetailInfoActivity.this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void getDataFromDatabase() {

        userRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataList.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        TourInfo allData = data.getValue(TourInfo.class);
                        dataList.add(allData);
                        tourRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TourDetailInfoActivity.this, "Can not read from DataBase.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
