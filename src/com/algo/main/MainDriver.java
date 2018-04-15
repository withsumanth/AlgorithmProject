package com.algo.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.algo.functions.FitnessFunction;
import com.algo.model.GenoType;
import com.algo.model.Order;
import com.algo.model.Phenotype;
import com.algo.model.Population;
import com.algo.model.Reactor;

public class MainDriver {

	static List<GenoType> gList;
	static Population population;
	
	public static void main(String[] args) {
		population = new Population();
		gList = new ArrayList();
		GenoType g = new GenoType();
		List<Reactor> rList = new ArrayList();
		for(int i=0;i<4;i++) {
			Random random = new Random();
			double diameter = random.nextInt(6);
			Reactor r = addReactor(diameter);
			rList.add(r);
		}
		g.setReactorList(rList);
		g = calculateOrder(g);
		gList.add(g);
		for(int i=0;i<10;i++) {
			crossOver(gList.get(gList.size()-1));
			mutate();
		}
		/*for(GenoType g:initialPopulation.getGenotype()) {
			for(Order o:g.getPhenotype().getOrder()) {
				System.out.println(o.getReactor().getDiameter());
				System.out.println(o.getCoeff());
				System.out.println(o.getFitnessVal());
			}
		}*/
	}
	
	private static Reactor addReactor(double diameter) {
		Reactor r = new Reactor();
		r.setDiameter(diameter);
		return r;
	}
	
	private static GenoType calculateOrder(GenoType g) {
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
		return g;
	}
	
	private static void crossOver(GenoType g) {
		List<Reactor> r = g.getReactorList();
		int[] child=new String[father.length];
		  int crossPoint = Math.random()*father.length;//make a crossover point
		  for (int i=0;i<father.length;++i)
		  {
		    if (i<crossPoint)
		      child[i]=father[i];
		    else
		      child[i]=mother[i];
		  }
		  return child;
	}
	
	private static void mutate() {
		
	}
}
