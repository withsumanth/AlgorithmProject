package com.algo.main;

import java.util.ArrayList;
import java.util.List;

import com.algo.functions.FitnessFunction;
import com.algo.model.GenoType;
import com.algo.model.Order;
import com.algo.model.Phenotype;
import com.algo.model.Population;
import com.algo.model.Reactor;

public class MainDriver {

	public static void main(String[] args) {
		Reactor r = new Reactor();
		Population initialPopulation = createNewPopulation();
		for(GenoType g:initialPopulation.getGenotype()) {
			for(Order o:g.getPhenotype().getOrder()) {
				System.out.println(o.getReactor().getDiameter());
				System.out.println(o.getCoeff());
				System.out.println(o.getFitnessVal());
			}
		}
	}

	private static Population createNewPopulation() {
		Population pop = new Population();
		List<Reactor> rList = new ArrayList();
		Reactor r1 = new Reactor();
		r1.setDiameter(0.3);
		rList.add(r1);
		Reactor r2 = new Reactor();
		r2.setDiameter(2.0);
		rList.add(r2);
		Reactor r3 = new Reactor();
		r3.setDiameter(3.0);
		rList.add(r3);
		Reactor r4 = new Reactor();
		r4.setDiameter(5.0);
		rList.add(r4);
		GenoType g = new GenoType();
		g.setReactorList(rList);
		FitnessFunction f = new FitnessFunction();
		List<Double> coeffList = new ArrayList();
		for(Reactor rs:g.getReactorList()) {
			coeffList.add(f.heatCoeffQ(rs));
		}
		g.setCoeffList(coeffList);
		double total = f.total(coeffList);
		List<Order> orderList = new ArrayList();
		for(int i=0;i<g.getReactorList().size();i++) {
			Order o = new Order();
			o.setReactor(g.getReactorList().get(i));
			o.setCoeff(g.getCoeffList().get(i));
			o.setFitnessVal(f.fitnessValue(g.getCoeffList().get(i),total));
			orderList.add(o);
		}
		g.getPhenotype().setOrder(orderList);
		List<GenoType> gentList = new ArrayList();
		gentList.add(g);
		pop.setGenotype(gentList);
		return pop;
	}

	
}
