package com.example.final_project_tourmate;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.final_project_tourmate.Package.TourInfo;
import com.example.final_project_tourmate.databinding.ActivityEventListBinding;
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

public class EventLIstActivity extends AppCompatActivity implements TourRecyclerViewAdapter.EventListener
{

    private Button addEvnt;
    private FirebaseAuth firebaseAuth;
    private ActivityEventListBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private TourRecyclerViewAdapter tourRecyclerViewAdapter;
    private ArrayList<TourInfo> dataList;
    private String TAG=EventLIstActivity.class.getSimpleName();
    private String user;
    private long startDate,endDate;
    String tourID;
    private SimpleDateFormat dateSDF = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_event_list);
        dataList = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        user = firebaseAuth.getCurrentUser().getUid();
        userRef = database.getReference("tourUser").child(user).child("event");

        getDataFromDatabase();

        binding.recyclerView.setHasFixedSize(true);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        tourRecyclerViewAdapter = new TourRecyclerViewAdapter(EventLIstActivity.this,dataList);


        binding.recyclerView.setAdapter(tourRecyclerViewAdapter);







        addEvnt=findViewById(R.id.addEvnt);
        addEvnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventLIstActivity.this,Create_Event_Tour_Activity.class));
            }
        });
    }

    private void getDataFromDatabase()
    {
        userRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
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

            }
        });
    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu)

    {

        MenuInflater inflater=getMenuInflater();

        inflater.inflate(R.menu.option_menu,menu);

        return true;

    }



    @Override

    public boolean onPrepareOptionsMenu(Menu menu)

    {

        return true;

    }

    public  void Logout(MenuItem item)
    {
            firebaseAuth.signOut();

            final Intent reStart = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
            reStart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(reStart);
    }


    @Override
    public void onEventDelete(String id)
    {
        userRef.child(id).removeValue();
    }

    @Override
    public void onEventUpdate(final String id)
    {
        final Dialog dialog=new Dialog(EventLIstActivity.this);
        dialog.setContentView(R.layout.update_dialog);

        final EditText nameEdit=dialog.findViewById(R.id.tour_nameET);
        final EditText descriptionEdit=dialog.findViewById(R.id.descriptinET);
        final EditText startEdit=dialog.findViewById(R.id.start_dateET);
        final EditText   endEdit=dialog.findViewById(R.id.end_dateET);
        final EditText   budgetEddit=dialog.findViewById(R.id.budget_ET);
        final Button     updateBtn=dialog.findViewById(R.id.updateBtn);
        final Button     cancelBtn=dialog.findViewById(R.id.cancelBtn);

        userRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String name= (String) dataSnapshot.child("tourName").getValue();
                String description= (String) dataSnapshot.child("tourDescription").getValue();
                 tourID= (String) dataSnapshot.child("tourUid").getValue();
                long start= (long) dataSnapshot.child("startDate").getValue();
                long end= (long) dataSnapshot.child("endDate").getValue();
                long budget= (long) dataSnapshot.child("budget").getValue();

                //Log.e(TAG,"name_edit: "+name);
                nameEdit.setText(name);
                descriptionEdit.setText(description);
                startEdit.setText(dateSDF.format(start));
                endEdit.setText(dateSDF.format(end));
                budgetEddit.setText(String.valueOf(budget));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        startEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickStartDate(startEdit);
            }
        });
        endEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickEndDate(endEdit);
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                 final String name=nameEdit.getText().toString();
                final String description=descriptionEdit.getText().toString();
                final Double budget=Double.parseDouble(budgetEddit.getText().toString());
                //String tourName, String tourDescription, long startDate, long endDate, double budget, double remainBudget, String tourUid
                TourInfo tourInfo =new TourInfo(name,description,startDate,endDate,budget,tourID);

                userRef.child(id).setValue(tourInfo);
                dialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void pickStartDate(final EditText startEdit)
    {
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
                startEdit.setText(selectedDate);

            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(EventLIstActivity.this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void pickEndDate(final EditText endEdit)
    {
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
                endEdit.setText(selectedDate1);

            }
        };
        Calendar calendar1 = Calendar.getInstance();
        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH);
        int day = calendar1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog1 = new DatePickerDialog(EventLIstActivity.this, dateSetListener1, year, month, day);
        datePickerDialog1.show();

    }
}

