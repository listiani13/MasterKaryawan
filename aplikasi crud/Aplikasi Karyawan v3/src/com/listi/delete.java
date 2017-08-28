package com.listi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class delete extends Activity {
       /** Called when the activity is first created. */
	private EditText kode, nama, gaji, deskripsi, status2;
    private Button delete, cek;
    private Spinner spinKd;
    private String kode1, nama1, gaji1, deskripsi1;
    JSONArray data = null;

       @Override
       public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.delete);
              //kode = (EditText) findViewById(R.id.editKode);
              spinKd = (Spinner) findViewById(R.id.spinKd);
              nama = (EditText) findViewById(R.id.editNama);
              delete = (Button) findViewById(R.id.buttonDelete);
              fetch();
              //cek = (Button) findViewById(R.id.btnCek);
              spinKd.setOnItemSelectedListener(new OnItemSelectedListener(){

				public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
					// TODO Auto-generated method stub
					kode1 = spinKd.getSelectedItem().toString();
					//String imc_met=spin.getSelectedItem().toString();

	                  String url = "http://10.0.2.2/crud/aksi.php?a=read&kd=" + kode1;
	                        JSONParser jParser = new JSONParser();
	                        JSONObject json = jParser.AmbilJson(url);
	                        try {
	                               data = json.getJSONArray("data");

	                               for (int i = 0; i < data.length(); i++) {
	                                      JSONObject ar = data.getJSONObject(i);

	                                      String kode_d = ar.getString("kd");
	                                      String nama_d = ar.getString("nm");
	                                      
	                                      nama.setText(nama_d);
	                                      
	                               }
	                        } catch (JSONException e) {
	                               e.printStackTrace();
	                        }
				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
            	  
              });
              /*cek.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					kode1 = spinKd.getSelectedItem().toString();
	                  String url = "http://10.0.2.2/crud/aksi.php?a=read&kd=" + kode1;
	                        JSONParser jParser = new JSONParser();
	                        JSONObject json = jParser.AmbilJson(url);
	                        try {
	                               data = json.getJSONArray("data");

	                               for (int i = 0; i < data.length(); i++) {
	                                      JSONObject ar = data.getJSONObject(i);

	                                      String kode_d = ar.getString("kd");
	                                      String nama_d = ar.getString("nm");
	                                      
	                                      nama.setText(nama_d);
	                                      
	                               }
	                        } catch (JSONException e) {
	                               e.printStackTrace();
	                        }
				}
            	  
              });*/
              delete.setOnClickListener(new OnClickListener(){
				public void onClick(View arg0) {
					String url = "";
                    kode1 = spinKd.getSelectedItem().toString();
                    url = "http://10.0.2.2/crud/aksi.php?a=delete&kd="
                                  + kode1;
                    getRequest(url);
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
    public void fetch()
	{					
		String url = "http://10.0.2.2/crud/service.php?ct=SEL_kd";
		HttpClient httpclient = new DefaultHttpClient();
		HttpRequestBase httpRequest = null;
		HttpResponse httpResponse = null;
		InputStream inputStream = null;
		String response = "";
		StringBuffer buffer = new StringBuffer();
		httpRequest = new HttpGet(url);
		try
		{
			httpResponse = httpclient.execute(httpRequest);
		}
		catch (ClientProtocolException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			inputStream = httpResponse.getEntity().getContent();
		}
		catch (IllegalStateException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}			
		byte[] data = new byte[512];
		int len = 0;
		
		try
		{
			while (-1 != (len = inputStream.read(data)) )
			{
				buffer.append(new String(data, 0, len));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			inputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		response = buffer.toString();
		StringParser parser = new StringParser();		
		ArrayList<Object> output = parser.Parse(response);
		Object[] Output = output.toArray();
		String[] content = new String[Output.length];
		for (int i=0;i<content.length;i++)
		{
			content[i] = Output[i].toString();
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, content);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);                   
		spinKd.setAdapter(adapter);
	}
}