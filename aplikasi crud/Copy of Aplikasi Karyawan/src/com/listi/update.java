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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class update extends Activity {
       /** Called when the activity is first created. */
	private EditText kode, nama, gaji, deskripsi;
    private Button update;
    private Spinner spinKd;
   /* private String kode1, nama1, gaji1, deskripsi1;*/
    JSONArray data = null;

       @Override
       public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.update);
              //kode = (EditText) findViewById(R.id.editKode);
              //String kode = spinKd.getSelectedItem().toString();
              spinKd = (Spinner) findViewById(R.id.spinKd);
              nama = (EditText) findViewById(R.id.editNama);
              gaji = (EditText) findViewById(R.id.editGaji);
              fetch();
              
              deskripsi = (EditText) findViewById(R.id.editDes);
              update = (Button) findViewById(R.id.buttonUpdate);
              update.setOnClickListener(new OnClickListener(){
				public void onClick(View arg0) {
					String url = "";
                    url = "http://10.0.2.2/crud/aksi.php";
                    try {
                   String ko = URLEncoder.encode(spinKd.getSelectedItem().toString(),"utf-8");
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
		String url = "http://10.0.2.2/crud/service.php";
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