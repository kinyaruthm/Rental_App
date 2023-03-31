package com.example.rentalapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Handler handler;

    TextView becomeLandLord;

    SQLiteDatabase sqLitedatabase;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler=new Handler();



        sqLitedatabase= new SQLiteDatabase(getApplicationContext());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor cursor= sqLitedatabase.syncDetails();
                if(cursor !=null && cursor.getCount()>0){
                    homeLayout();
                }else{
                    startActivity(new Intent(MainActivity.this, login.class));
                }
            }
        }, 3000);
    }

    private void homeLayout() {
        setContentView(R.layout.activity_home);
        toolbar =findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigation);
        drawerLayout=findViewById(R.id.drawer);
        becomeLandLord=findViewById(R.id.becomeLandlord);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.Open_navigation, R.string.close_navigation);;
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        becomeLandLord.setText("Become Land Lord");

    }

    public void becomeLandlord(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Would you like to become a Landlord")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setBecomeLandLord();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Become a landlord");
        alert.show();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.menu_item1){

            return true;
        }
        else if(item.getItemId()==R.id.item3){
            startActivity(new Intent(getApplicationContext(), landlord.class));
            return true;
        }
        return false;
    }
public void setBecomeLandLord(){
    Toast.makeText(this, "You have Become a Landlord", Toast.LENGTH_SHORT).show();
}
}


