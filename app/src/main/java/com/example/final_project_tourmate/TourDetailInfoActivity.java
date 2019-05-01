package com.example.final_project_tourmate;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_project_tourmate.Package.ExpenseInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TourDetailInfoActivity extends AppCompatActivity
{
    private static final String TAG =TourDetailInfoActivity.class.getSimpleName() ;
    TextView addExpense,budgetTV,viewExpense,remainBudgetText,tourNameTV;
    private double budget, remainBudget;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String eventId, userId;
    private String tourName;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_tour_detail_info);

        addExpense=findViewById(R.id.addNewExpense);
        viewExpense=findViewById(R.id.viewAllxpense);
        budgetTV=findViewById(R.id.budgetTV);
        remainBudgetText=findViewById(R.id.remainBudgetTV);
        tourNameTV=findViewById(R.id.tourName);
        Intent intent=getIntent();
        eventId=intent.getStringExtra("id");
        budget=intent.getDoubleExtra("budget",0.0);
        tourName=intent.getStringExtra("tourName");

        tourNameTV.setText(tourName);
        budgetTV.setText("Budget :"+String.valueOf(budget));

        firebaseAuth = FirebaseAuth.getInstance();;
        database = FirebaseDatabase.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        Log.e(TAG,"name_budget: "+eventId);
        myRef = database.getReference("tourUser").child(userId).child("event").child(eventId);

        //Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();


        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final Dialog dialog =new Dialog(TourDetailInfoActivity.this);
                dialog.setContentView(R.layout.expense_dialog);

                final EditText addExpense=dialog.findViewById(R.id.expenseAmount);
                final EditText expenseDes=dialog.findViewById(R.id.expenseComment);
                Button   addExpenseBtn=dialog.findViewById(R.id.addExpBtn);
                Button   cancelBtn=dialog.findViewById(R.id.cancelBtn);
                addExpenseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //double expense=Double.parseDouble(addExpense.getText().toString());
                        //String comment=expenseDes.getText().toString();
                        saveTourToDatabase(expenseDes,addExpense);
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
        });

        viewExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(TourDetailInfoActivity.this, Expense_show.class);
                intent.putExtra("eventId", eventId);
                intent.putExtra("budget",budget);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        getRemainingBalance();
    }

    private void saveTourToDatabase(EditText expenseDes, EditText addExpense)
    {
        String expenseDescription = expenseDes.getText().toString().trim();
        String tourCost = addExpense.getText().toString();
        double cheekTourCost = Double.parseDouble(tourCost);
        //cheekBlance(tourCost);
        if (budget == cheekTourCost || budget > cheekTourCost)
        {


            if (remainBudget == cheekTourCost || remainBudget > cheekTourCost){
                Toast.makeText(this, "me"+remainBudget, Toast.LENGTH_SHORT).show();
                ExpenseInfo expenseInfo = new ExpenseInfo(expenseDescription, tourCost);
                String expenseId = myRef.push().getKey();
                expenseInfo.setCostId(expenseId);
                myRef.child("expenses").child(expenseId).setValue(expenseInfo);
            }else
                {
                Toast.makeText(this, "You can spend only "+remainBudget, Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "You cross your budget.", Toast.LENGTH_SHORT).show();
        }

    }

    private void getRemainingBalance()
    {
        DatabaseReference databaseReferenc1 = FirebaseDatabase.getInstance().getReference("tourUser").child(userId).child("event").child(eventId);
        databaseReferenc1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                remainBudget=Double.parseDouble(dataSnapshot.child("remainBudget").getValue().toString().trim());
                remainBudgetText.setText("Remaining Budget :"+String.valueOf(remainBudget));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
