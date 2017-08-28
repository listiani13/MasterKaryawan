package com.listi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class berhasilLogin extends Activity {
       TextView tNama;

       @Override
       public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.hal2);
              tNama = (TextView) findViewById(R.id.txtNama);

              Intent in = getIntent();
              String nm = in.getStringExtra("nama");
              tNama.setText(nm);
       }

       public void SignOut(View v) {
              finish();
              Intent i = new Intent(this, AplikasikuActivity.class);
              startActivity(i);
       }

}