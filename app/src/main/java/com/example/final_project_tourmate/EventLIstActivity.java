package com.example.final_project_tourmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class EventLIstActivity extends AppCompatActivity
{

    private Button addEvnt;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        firebaseAuth = FirebaseAuth.getInstance();

        addEvnt=findViewById(R.id.addEvnt);
        addEvnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventLIstActivity.this,Create_Event_Tour_Activity.class));
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

}

