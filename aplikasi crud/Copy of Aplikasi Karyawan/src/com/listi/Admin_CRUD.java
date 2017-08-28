package com.listi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_CRUD extends Activity {
       /** Called when the activity is first created. */
       private EditText kode, nama, gaji, deskripsi;
       private Button insert, update, delete, cek;
       private String kode1, nama1, gaji1, deskripsi1;
       JSONArray data = null;

       @Override
       public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.main);
              kode = (EditText) findViewById(R.id.editKode);
              nama = (EditText) findViewById(R.id.editNama);
              gaji = (EditText) findViewById(R.id.editGaji);
              deskripsi = (EditText) findViewById(R.id.editDes);
              cek = (Button) findViewById(R.id.btnCek);
              insert = (Button) findViewById(R.id.buttonInsert);
              update = (Button) findViewById(R.id.buttonUpdate);
              delete = (Button) findViewById(R.id.buttonDelete);

              insert.setOnClickListener(new OnClickListener() {
                     public void onClick(View v) {
                           // TODO Auto-generated method stub
                           String url = "";
                           url = "http://10.0.2.2/crud/aksi.php";

                           try {
                           String ko = URLEncoder.encode(kode.getText().toString(),"utf-8");
                           String n = URLEncoder.encode(nama.getText().toString(), "utf-8");
                           String hr = URLEncoder.encode(gaji.getText().toString(),"utf-8");
                           String d = URLEncoder.encode(deskripsi.getText().toString(), "utf-8");
                           url += "?a=insert&kd=" + ko + "&nm=" + n + "&gaji=" + hr
                                                + "&deskripsi=" + d;
                           getRequest(url);
                           } catch (UnsupportedEncodingException e) {
                                  e.printStackTrace();
                           }

                     }
              });
             
              update.setOnClickListener(new OnClickListener() {
                     public void onClick(View v) {
                           String url = "";
                            url = "http://10.0.2.2/crud/aksi.php";
                            try {
                           String ko = URLEncoder.encode(kode.getText().toString(),"utf-8");
                           String n = URLEncoder.encode(nama.getText().toString(),"utf-8");
                           String hr = URLEncoder.encode(gaji.getText().toString(),"utf-8");
                           String d = URLEncoder.encode(deskripsi.getText().toString(), "utf-8");
                           url += "?a=update&kd=" + ko + "&nm=" + n + "&gaji=" + hr+ "&des=" + d;
                           getRequest(url);
                           } catch (UnsupportedEncodingException e) {
                                  e.printStackTrace();
                           }  
                     }
              });
              delete.setOnClickListener(new OnClickListener() {
                     public void onClick(View v) {
                           // TODO Auto-generated method stub
                           String url = "";
                           kode1 = kode.getText().toString();
                           url = "http://10.0.2.2/crud/aksi.php?a=delete&kd="
                                         + kode1;
                           getRequest(url);
                     }
              });
              cek.setOnClickListener(new OnClickListener() {
                     public void onClick(View v) {
                           // TODO Auto-generated method stub
                           kode1 = kode.getText().toString();
                     String url = "http://10.0.2.2/crud/aksi.php?a=read&kd=" + kode1;
                           JSONParser jParser = new JSONParser();
                           JSONObject json = jParser.AmbilJson(url);
                           try {
                                  data = json.getJSONArray("data");

                                  for (int i = 0; i < data.length(); i++) {
                                         JSONObject ar = data.getJSONObject(i);

                                         String kode_d = ar.getString("kd");
                                         String nama_d = ar.getString("nm");
                                         String gaji_d = ar.getString("gaji");
                                         String des_d = ar.getString("deskripsi");

                                         kode.setText(kode_d);
                                         nama.setText(nama_d);
                                         gaji.setText(gaji_d);
                                         deskripsi.setText(des_d);
                                  }
                           } catch (JSONException e) {
                                  e.printStackTrace();
                           }   

                     }
              });

       }

       public void getRequest(String url) {
              Toast.makeText(this, url, Toast.LENGTH_LONG).show();
              HttpClient client = new DefaultHttpClient();
              HttpGet request = new HttpGet(url);
              try {
                     HttpResponse response = client.execute(request);
                     Toast.makeText(this, request(response) + ":)",
                                  Toast.LENGTH_SHORT).show();
              } catch (Exception ex) {
                     Toast.makeText(this, "data gagal", Toast.LENGTH_SHORT).show();
              }

       }

       public static String request(HttpResponse reponse) {
              String result = "";

              try {
                     InputStream in = reponse.getEntity().getContent();
                     BufferedReader reader = new BufferedReader(
                                  new InputStreamReader(in));
                     StringBuilder str = new StringBuilder();
                     String line = null;

                     while ((line = reader.readLine()) != null) {
                           str.append(line + "\n");
                     }

                     in.close();
                     result = str.toString();
              } catch (Exception e) {
                     result = "Error";
              }

              return result;
       }
}