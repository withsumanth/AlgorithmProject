package com.algo.model;

public class Order {
	private Reactor reactor;
	private double fitnessVal;
	private double coeff;
	
	public Order() {
		
	}

	public Reactor getReactor() {
		return reactor;
	}

	public void setReactor(Reactor reactor) {
		this.reactor = reactor;
	}

	public double getFitnessVal() {
		return fitnessVal;
	}

	public void setFitnessVal(double fitnessVal) {
		this.fitnessVal = fitnessVal;
	}

	public double getCoeff() {
		return coeff;
	}

	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
	
	
}
