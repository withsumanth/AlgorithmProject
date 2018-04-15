package com.algo.functions;

import java.util.List;

import com.algo.model.Phenotype;
import com.algo.model.Reactor;

public class FitnessFunction {
	
	public FitnessFunction() {
		
	}
	
	public double heatCoeffQ(Reactor r) {
		return 800-62.83*(2*r.getDiameter() + 0.91*Math.pow(r.getDiameter(), -0.2));
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
}
