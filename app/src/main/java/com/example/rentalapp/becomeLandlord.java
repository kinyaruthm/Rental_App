package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class becomeLandlord extends AppCompatActivity {
    EditText type, location, rent, noOfRooms, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_landlord);
        type=findViewById(R.id.type);
        location=findViewById(R.id.location);
        rent=findViewById(R.id.rent);
        noOfRooms=findViewById(R.id.number);
        description=findViewById(R.id.des);

    }

    public void saveLandLord(View view) {
    }
}