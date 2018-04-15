package com.algo.model;

/**
 * @author Sumanth
 * @author Kavya
 */

import java.util.List;

public class GenoType {
	private List<Reactor> reactorList;
	private List<Double> coeffList;
	private Phenotype phenotype;
	String[] representation;

	public GenoType() {
		phenotype = new Phenotype();
	}

	public String[] getRepresentation() {
		return representation;
	}

	public void setRepresentation(String[] representation) {
		this.representation = representation;
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
