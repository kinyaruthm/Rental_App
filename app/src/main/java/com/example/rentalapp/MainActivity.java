package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText Username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button submit = findViewById(R.id.btn1);
        Button signup = findViewById(R.id.signup);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register.class);
               startActivity(intent);
            }
        });
    }

}