package com.algo.model;

import java.util.List;

import com.algo.functions.FitnessFunction;

public class GenoType {
	private List<Reactor> reactorList;
	private List<Double> coeffList;
	private Phenotype phenotype;
	
	public GenoType() {
		phenotype = new Phenotype();
	}

	public List<Double> getCoeffList() {
		return coeffList;
	}

	public void setCoeffList(List<Double> coeffList) {
		this.coeffList = coeffList;
	}

	public List<Reactor> getReactorList() {
		return reactorList;
	}

	public void setReactorList(List<Reactor> reactorList) {
		this.reactorList = reactorList;
	}

	public Phenotype getPhenotype() {
		return phenotype;
	}

	public void setPhenotype(Phenotype phenotype) {
		this.phenotype = phenotype;
	}
	
}
