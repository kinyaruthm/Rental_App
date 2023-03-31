package com.example.rentalapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;


public class register extends AppCompatActivity {

Boolean EditTextEmpty;
SQLiteDatabase sqLiteDatabaseObj;
SQLiteOpenHelper sqLiteOpenHelper;
String SQLiteQueryHolder;
EditText name, password, email, username, phone;
Button signup, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
         name = findViewById(R.id.name);
         password = findViewById(R.id.passwrd);
         email = findViewById(R.id.email);
         username = findViewById(R.id.username);
         phone = findViewById(R.id.phoneno);
         signup = findViewById(R.id.btn2);
         login = findViewById(R.id.btn3);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
    public  void signUp(){
        String nam=name.getText().toString();
        String mail=email.getText().toString();
        String pwd= password.getText().toString();
        String UName=username.getText().toString();
        String phoneNO=phone.getText().toString();

        String type="reg";

        new android.os.AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void[] params) {
                String response = "";
                try {
                   /* ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    final String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
*/
                    String[] strings = new String[6];
                    strings[0] = type;
                    strings[1] = nam;
                    strings[2] = mail;
                    strings[3] = pwd;
                    strings[4] = UName;
                    strings[5] = phoneNO;


                    Httpprocesses httpProcesses = new Httpprocesses();
                    response = httpProcesses.sendRequest(strings);
                } catch (Exception e) {
                    response = e.getMessage();
                }
                return response;
            }
            protected void onPostExecute(String response) {
               // profile.setImageResource(android.R.color.transparent);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean result = jsonObject.getBoolean("status");
                    //  JSONArray      jsonArray = jsonObject.optJSONArray("data");
                    String message = jsonObject.getString("message");
                    if (result == true) {
                        // String names=jsonArray.getString(jsonObject.getInt("name "));
                       // Toast.makeText(register.this, "registration successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),message , Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();

    }

}
