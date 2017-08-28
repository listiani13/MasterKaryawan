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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class insert extends Activity {
       /** Called when the activity is first created. */
	private EditText kode, nama, gaji, deskripsi;
    private Button insert, update, delete, cek;
    private String kode1, nama1, gaji1, deskripsi1;
    JSONArray data = null;

       @Override
       public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.insert);
              kode = (EditText) findViewById(R.id.editKode);
              nama = (EditText) findViewById(R.id.editNama);
              gaji = (EditText) findViewById(R.id.editGaji);
              deskripsi = (EditText) findViewById(R.id.editDes);
              cek = (Button) findViewById(R.id.btnCek);
              Button btnInsert = (Button) findViewById(R.id.buttonInsert);
              btnInsert.setOnClickListener(new OnClickListener(){
				public void onClick(View arg0) {
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