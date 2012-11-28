package com.team9.uxo;


public class BlastCalculator {
	
	public static final double ATM_TO_PSI = 14.6959488;
	public static final int MAX_DISTANCE = 9999;
	
	static double[] THRESHOLDS = {
		0.072,
		0.1,
		1.5,
		5.0,
	};
	
	private double cubedRoot(double value) {
		return Math.pow(value, 1.0/3);
	}
	
	public double pressure(double k, double m, double r) {
		double km = k * m;
		return
		 	(0.95 * cubedRoot(km) / r) +
		 	(3.9 * cubedRoot(km * km) / (r * r)) +
		 	(13.0 * km / (r * r * r));
	}
	
	public int distanceFromPressure(double k, double m, double p) {
		double r;
		for(r = 2000.0; r > 0; r = r - 1) {
			double p2 = pressure(k, m, r);
			if(p2 > p)
				return (int)r;
		}
		return 0;
	}
	
	public int distance(double k, double m, long index) {
		return distanceFromPressure(k, m, THRESHOLDS[(int)index]);
	}
	
}