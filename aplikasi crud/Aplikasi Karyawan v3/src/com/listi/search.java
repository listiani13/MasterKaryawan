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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class search extends Activity {
       /** Called when the activity is first created. */
	private EditText kode, nama, gaji, deskripsi, status2;
    private Button cek;
    private String kode1;
    JSONArray data = null;

       @Override
       public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.search);
              kode = (EditText) findViewById(R.id.editKode);
              nama = (EditText) findViewById(R.id.editNama);
              gaji = (EditText) findViewById(R.id.editGaji);
              status2 = (EditText) findViewById(R.id.editStatus);
              deskripsi = (EditText) findViewById(R.id.editDes);
              cek = (Button) findViewById(R.id.btnCek);
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
                                      String status_d = ar.getString("status");

                                      kode.setText(kode_d);
                                      nama.setText(nama_d);
                                      gaji.setText(gaji_d);
                                      deskripsi.setText(des_d);
                                      status2.setText(status_d);
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