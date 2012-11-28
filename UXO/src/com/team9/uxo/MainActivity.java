package com.team9.uxo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Spinner explosiveList;
	private TextView weightText;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		explosiveList = (Spinner) findViewById(R.id.explosive_list);
		weightText = (TextView) findViewById(R.id.explosive_weight);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
