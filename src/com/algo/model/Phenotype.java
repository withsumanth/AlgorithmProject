package com.algo.model;

import java.util.List;

import com.algo.functions.FitnessFunction;

public class Phenotype {
	private List<Order> order;
	
	public Phenotype() {
		
	}

	public List<Order> getOrder() {
		return order;
	}


	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	
}
