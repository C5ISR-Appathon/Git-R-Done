package com.team9.uxo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Spinner explosiveList;
	private TextView weightText;
	private int weight;
	private Spinner calculationType;
	private String blastType;
	private TextView radius;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		explosiveList = (Spinner) findViewById(R.id.explosive_list);
		weightText = (TextView) findViewById(R.id.explosive_weight);
		calculationType = (Spinner)findViewById(R.id.calc_method);
		radius = (TextView) findViewById(R.id.blast_radius);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void onWeightClick(View v){
		System.out.println("Weight: " + weightText.getText());
		System.out.println("Resource: " + getString(R.string.weight));
		if(weightText.getText().equals(getString(R.string.weight))){
			System.out.println("match");
			weightText.setText("test");
		} else {
			//String temp = (String) weightText.getText();
			//weight = Integer.getInteger(temp);
		}
	}
	
	public void onExplosiveType(View v){
		
	}
	
	public void onCalcMethodClicked(View v){
		blastType = (String) calculationType.getSelectedItem();
	}
	
	public double calculateBlastRadius(){
		double x =0;
		 if(blastType.equals("Blast")){
			 x = calcTypeBlast(convertToTNT(weight));
		 } else if(blastType.equals("Frag")) {
			 x = calcTypeFrag(convertToTNT(weight));
		 }
		 radius.setText(x+"");
		 return x;
	}
	
	private double convertToTNT(int weight) {
		String explosiveType = (String) explosiveList.getSelectedItem();
		if(explosiveType.equals("C4")){
			return  (weight *1.2);
		}else if(explosiveType.equals("Composition B")){
			return weight * 1.3;
		} else if(explosiveType.equals("Dynamite")) {
			return weight * 0.9;
		} else if(explosiveType.equals("Amonimum Nitrate")){
			return weight * 0.82;
		} else {
			return (double) weight;
		}
	}
	
	private double calcTypeBlast(double x){
		
		return x*2;
	}
	private double calcTypeFrag(double x) {
		return x*3;
	}
	
	
	
	

}
