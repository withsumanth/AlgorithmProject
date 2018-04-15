package com.algo.model;

import java.util.List;

import com.algo.functions.FitnessFunction;

public class Phenotype {
	private List<Order> order;
	private double avgHeatRelease;
	
	public Phenotype() {
		
	}

	public List<Order> getOrder() {
		return order;
	}


	public double getAvgHeatRelease() {
		return avgHeatRelease;
	}

	public void setAvgHeatRelease(double avgHeatRelease) {
		this.avgHeatRelease = avgHeatRelease;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	
}
