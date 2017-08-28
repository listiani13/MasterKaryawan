package com.listi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class pilihan extends Activity {
       /** Called when the activity is first created. */
      

       @Override
       public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.pilihan);
              Button btnSearch = (Button) findViewById(R.id.btnSearch);
              btnSearch.setOnClickListener(new OnClickListener(){
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					 Intent s = new Intent(pilihan.this,search.class);
		              startActivity(s);
				}
				});
              Button btnInsert = (Button) findViewById(R.id.btnInsert);
  			btnInsert.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(pilihan.this,insert.class);
		              startActivity(i);
				}
				});
  			Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
  			btnUpdate.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(pilihan.this,update.class);
		              startActivity(i);
				}
  				
  			});
  			Button btnDelete = (Button) findViewById(R.id.btnDelete);
  			btnDelete.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(pilihan.this,delete.class);
		              startActivity(i);
				}
  			});
       }
}