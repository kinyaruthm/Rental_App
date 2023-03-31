package com.example.rentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity  {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item1) {
            // do something for item 1
            return true;
        }
        if (id == R.id.menu_item2) {
            // do something for item 2
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView img1 = findViewById(R.id.image1);
        TextView txt1 = findViewById(R.id.txt1);
        ImageButton img2 = (ImageButton) findViewById(R.id.sngleroom);



        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this, "welcome to our Spacious single rooms", Toast.LENGTH_SHORT).show();
                Intent singles = new Intent(home.this, singlerooms.class);
                startActivity(singles);
            }
        });




    }



}