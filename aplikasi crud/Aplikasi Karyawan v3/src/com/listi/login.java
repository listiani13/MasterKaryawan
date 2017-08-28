package com.listi;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends Activity {
       /** Called when the activity is first created. */
       EditText us, ps;
       Button login;
       String i;
       TextView pesan;

       @Override
       public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.login);
              us = (EditText) findViewById(R.id.edPass);
              ps = (EditText) findViewById(R.id.edUser);
              pesan = (TextView) findViewById(R.id.TextView01);
              login = (Button) findViewById(R.id.btnLogin);
              login.setOnClickListener(new OnClickListener() {
                     public void onClick(View v) {
                           // TODO Auto-generated method stub
                    	 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                    	 postParameters.add(new BasicNameValuePair("user", us.getText().toString()));
                    	 postParameters.add(new BasicNameValuePair("pass", ps.getText().toString()));
                    	 /* String valid = "1"; */
                    	 String response = null;
                    	 try {
                    		 response = CustomHttpClient.executeHttpPost("http://10.0.2.2/crud/check.php", postParameters);
                    		 String res = response.toString();
                    		 res = res.trim();
                    		 res = res.replaceAll("\\s+", "");
                    		 pesan.setText(res);
                    		 if (res.equals("1")) {
                                  pesan.setText("Correct Username or Password");
                                         berhasil(v);

                                  } else {
				                     pesan.setText("Sorry!! Wrong Username or Password Entered");
				                                  }
				                           }
				                           catch (Exception e) {
				                                  us.setText(e.toString());
				                           }
                     }
              });
       }

       public void berhasil(View theButton) {
              Intent s = new Intent(this,pilihan.class);
              s.putExtra("nama", us.getText().toString());
              startActivity(s);
       }

}