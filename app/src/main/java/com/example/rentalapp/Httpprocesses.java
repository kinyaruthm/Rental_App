package com.example.rentalapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Httpprocesses {

        public String sendRequest(String strings[]){
            String type = strings[0];
            constants constants=new constants();

            try {

                URL url=null;
                String insert_data="";
                if (type.equals("reg")) {
                    url = new URL(constants.regUrl);
                    String Names = strings[1];
                    String Email = strings[2];
                    String Password = strings[3];
                    String Username = strings[4];
                    String Phone = strings[5];


                    //String image=strings[9];
                    insert_data=URLEncoder.encode("Names", "UTF-8") + "=" + URLEncoder.encode(Names, "UTF-8")
                            + "&&" + URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8")
                            + "&&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8")
                            + "&&" + URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8")
                            + "&&" + URLEncoder.encode("Phone", "UTF-8") + "=" + URLEncoder.encode(Phone, "UTF-8");

                           // + "&&" + URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(image, "UTF-8");
                }  else if (type.equals("login")) {

                    url = new URL(constants.loginUrl);
                    String Username = strings[1];
                    String Password = strings[2];
                    insert_data = URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8")
                            + "&&" + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");
                }

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                bufferedWriter.write(insert_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String result = "";
                String reg_line = "";

                StringBuilder stringBuilder = new StringBuilder();
                while ((reg_line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(reg_line).append("\n");
                }
                result = stringBuilder.toString();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

