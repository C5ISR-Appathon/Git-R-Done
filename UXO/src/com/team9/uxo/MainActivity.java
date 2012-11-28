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
	private double weight;
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
//		System.out.println("Weight: " + weightText.getText());
//		
//		
//		String temp = weightText.getText() + "";
//		weight = Double.valueOf(temp); 
//		System.out.println("Value: " + temp);
	}
	
	static double[] TNT_EQUIVELANT = {
		1.0, 1.2, 1.3, 0.9, 0.82
	};
	
	public void calculate(View v){
		System.out.println("Type: " + explosiveList.getSelectedItem());
		System.out.println("Weight: " + weight);
		System.out.println("Blast: " + calculationType.getSelectedItem());
//		blastType = (String) calculationType.getSelectedItem();
		
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
//		blastType = (String) calculationType.getSelectedItem();
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
	
	private double convertToTNT(double weight) {
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
