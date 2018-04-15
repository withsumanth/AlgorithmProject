package com.algo.functions;

import java.util.List;

import com.algo.model.Phenotype;
import com.algo.model.Reactor;

public class FitnessFunction {
	
	public FitnessFunction() {
		
	}
	
	public double heatCoeffQ(Reactor r) {
		return 850-(62.83*(2*r.getDiameter() + (0.91*Math.pow(r.getDiameter(), -0.2))));
	}
	
	public double fitnessValue(double coeff, double total) {
		return coeff/total;
	}
	
	public double total(List<Double> coeffList) {
		double total = 0.0;
		for(Double d:coeffList) {
			total = total+d;
		}
		return total;
	}
	
	public String[] doubleToBin(double[] d){
		String [] str = new String[d.length];
		for(int i=0;i<d.length;i++) {
			str[i] = String.format("%6s", Integer.toBinaryString((int)(d[i]*10))).replace(' ', '0');
		}
		return str;
	}
	
	public String[] getChild(String [] r) {
		String[] rNew = new String[r.length];
		String fath_moth[] = new String[2];
		int count = 0;
		for(int i=0;i<r.length;i+=2) {
			fath_moth[count] = r[i];
			count++;
		}
		fath_moth = findChild(fath_moth);
		rNew[0] = fath_moth[0];
		rNew[1] = fath_moth[1];
		count =0;
		for(int i=1;i<r.length;i+=2) {
			fath_moth[count] = r[i];
			count++;
		}
		fath_moth = findChild(fath_moth);
		rNew[2] = fath_moth[0];
		rNew[3] = fath_moth[1];
		return rNew;
	}
	
	public String[] findChild(String[] fath_moth) {
		String[] child = new String[2];
		char[] father = fath_moth[0].toCharArray();
		char[] mother = fath_moth[1].toCharArray();
		char c[] = new char[father.length];
		for(int i=0;i<3;i++) {
			c[i] = father[i];
			c[i+3] = mother[i+3];
		}
		child[0] = String.valueOf(c);
		for(int i=3;i<6;i++) {
			c[i-3] = father[i];
			c[i] = mother[i-3];
		}
		child[1] = String.valueOf(c);
		return child;
	}
}