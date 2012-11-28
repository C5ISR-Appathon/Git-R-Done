package com.team9.uxo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Spinner explosiveList;
	private TextView weightText;
	private Spinner calculationType;
	private TextView radius;
	
	
	OnItemSelectedListener listener = new OnItemSelectedListener() {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			update();
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		explosiveList = (Spinner) findViewById(R.id.explosive_list);
		weightText = (TextView) findViewById(R.id.explosive_weight);
		calculationType = (Spinner)findViewById(R.id.calc_method);
		radius = (TextView) findViewById(R.id.blast_radius);
		
		explosiveList.setOnItemSelectedListener(listener);
		calculationType.setOnItemSelectedListener(listener);
		weightText.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus)
					if(weightText.length() > 0)
						update();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
		
	static double[] TNT_EQUIVELANT = {
		1.0, 1.2, 1.3, 0.9, 0.82
	};
	
	public void plot(View v){
		
	}

	private void update() {
		double k = TNT_EQUIVELANT[(int)explosiveList.getSelectedItemId()];
		double m = Double.valueOf(String.valueOf(weightText.getText()));
		Integer d;
		long method = calculationType.getSelectedItemId();
		if(method == 0) {
			d = (int)(444 * Math.pow(k * m, (1.0/6)));
		}
		else {
			BlastCalculator calc = new BlastCalculator();
			d = calc.distance(k, m, method - 1);
		}
		radius.setText(d.toString());
	}
	
	public void onCalcMethodClicked(View v){
		update();
	}
}