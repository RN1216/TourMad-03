package com.example.final_project_tourmate;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TourDetailInfoActivity extends AppCompatActivity
{
  TextView addExpense;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_tour_detail_info);

        Intent intent=getIntent();
        String id=intent.getStringExtra("id");

        //Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();
        addExpense=findViewById(R.id.addNewExpense);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dialog dialog =new Dialog(TourDetailInfoActivity.this);
                dialog.setContentView(R.layout.expense_dialog);

                dialog.show();
            }
        });
    }


}
