package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {
    EditText Username;
    EditText password ;
    Button submit;
    TextView signup;
    SQLdb sqLitedatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        submit = findViewById(R.id.btn1);
        signup = findViewById(R.id.signup);
        sqLitedatabase = new SQLdb(getApplicationContext());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString();
                String Password = password.getText().toString();
                 //Cursor cursor = sqLitedatabase.getSyncedTables();
                String url = "";
                String type = "login";

               // Toast.makeText(MainActivity., "Login successful", Toast.LENGTH_SHORT).show();


               login();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), register.class);
                startActivity(intent);
            }
        });
    }
   public void login(){
        String type="login";
        String username = Username.getText().toString();
        String Password = password.getText().toString();

       boolean insertLogged=sqLitedatabase.insertUsers(username, Password);
        if(insertLogged){
            startActivity(new Intent(getApplicationContext(), home.class));
        }else {
            Toast.makeText(this, "Failed, Try Again", Toast.LENGTH_SHORT).show();
        }


        new android.os.AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                try {
                    String strings[]=new String[3];
                    strings[0]=type;
                    strings[1]=username;
                    strings[2]=Password;
                    Httpprocesses httpProcesses=new Httpprocesses();

                    response=httpProcesses.sendRequest(strings);

                } catch (Exception e) {
                    response=e.getMessage();
                }

                return response;
            }
            protected void onPostExecute(String response) {

                //do something with response
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Boolean result=jsonObject.getBoolean("status");
                    if(result==true){
                        String message=jsonObject.getString("message");


                        boolean insertLogged=sqLitedatabase.insertUsers(username, Password);
                        if(insertLogged){
                            Toast.makeText(getApplicationContext(), message+"\n \tWelcome", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), home.class));
                        }

                    }
                    else{
                        // error.setText("Invalid UserName Or Password");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }.execute();

    }
}