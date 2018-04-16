package com.algo.main;

/**
 * @author Sumanth
 * @author Kavya
 * @topic Heat Transfer from Spherical Reactor
 * Created on 04/14/2018
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import com.algo.graph.DrawGraph;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.algo.functions.FitnessFunction;
import com.algo.model.GenoType;
import com.algo.model.Order;
import com.algo.model.Population;
import com.algo.model.Reactor;

public class MainDriver {

	static FitnessFunction f;

	public static void main(String[] args) {
		f = new FitnessFunction();
		Population population = new Population();
		List<GenoType> gList = new ArrayList<GenoType>();
		GenoType g = new GenoType();
		List<Reactor> rList = new ArrayList<Reactor>();
		Random random = new Random();
		random.setSeed(0L);
		for (int i = 0; i < 4; i++) {
			double diameter = random.nextInt(5) + 1;
			Reactor r = addReactor(diameter);
			rList.add(r);
		}
		g.setReactorList(rList);
		g = calculateOrder(g);
		gList.add(g);
		for (;;) {
			rList = new ArrayList<Reactor>();
			g = crossOver(gList.get(gList.size() - 1));
			double newDiameters[] = mutate(g);
			for (int j = 0; j < 4; j++) {
				Reactor r = addReactor(newDiameters[j]);
				rList.add(r);
			}
			g = new GenoType();
			g.setReactorList(rList);
			g = calculateOrder(g);
			gList.add(g);
			boolean check = false;
			for (Order o : g.getPhenotype().getOrder()) {
				if (o.getCoeff() > 746.8 && o.getFitnessVal() > 0.7) {
					check = true;
				}
			}
			if (check) {
				break;
			} else {
				continue;
			}
		}
		population.setGenotype(gList);
		DecimalFormat f = new DecimalFormat("0.00");
		System.out.println("Total Number of Iteration: " + population.getGenotype().size());
		for (GenoType gen : population.getGenotype()) {
			System.out.print("Diameter: " + gen.getReactorList().get(0).getDiameter() + ","
					+ gen.getReactorList().get(1).getDiameter() + "," + gen.getReactorList().get(2).getDiameter() + ","
					+ gen.getReactorList().get(3).getDiameter());
			System.out.print("  ");
			System.out.print("Heat Transferred: " + f.format(gen.getPhenotype().getOrder().get(0).getCoeff()) + ","
					+ f.format(gen.getPhenotype().getOrder().get(1).getCoeff()) + ","
					+ f.format(gen.getPhenotype().getOrder().get(2).getCoeff()) + ","
					+ f.format(gen.getPhenotype().getOrder().get(3).getCoeff()));
			System.out.print("  ");
			System.out.print("Fitness Value: " + f.format(gen.getPhenotype().getOrder().get(0).getFitnessVal()) + ","
					+ f.format(gen.getPhenotype().getOrder().get(1).getFitnessVal()) + ","
					+ f.format(gen.getPhenotype().getOrder().get(2).getFitnessVal()) + ","
					+ f.format(gen.getPhenotype().getOrder().get(3).getFitnessVal()));
			System.out.print(" Average Heat Transferred: " + f.format(gen.getPhenotype().getAvgHeatRelease()));
			System.out.println("");
		}
		SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            createAndShowGui(population.getGenotype().size(),population);
	         }
	      });
	}

	/**
	 * Creates a Reactor of specific diameter
	 */
	private static Reactor addReactor(double diameter) {
		Reactor r = new Reactor();
		if (diameter == 0.0) {
			diameter += 0.7;
		}
		r.setDiameter(diameter);
		return r;
	}

	/**
	 * Creates a Order for Phenotype
	 */

	private static GenoType calculateOrder(GenoType g) {
		List<Double> coeffList = new ArrayList<Double>();
		for (Reactor rs : g.getReactorList()) {
			coeffList.add(f.heatCoeffQ(rs));
		}
		g.setCoeffList(coeffList);
		double total = f.total(coeffList);
		List<Order> orderList = new ArrayList<Order>();
		for (int i = 0; i < g.getReactorList().size(); i++) {
			Order o = new Order();
			o.setReactor(g.getReactorList().get(i));
			o.setCoeff(g.getCoeffList().get(i));
			o.setFitnessVal(f.fitnessValue(g.getCoeffList().get(i), total));
			orderList.add(o);
		}
		g.getPhenotype().setOrder(orderList);
		g.getPhenotype().setAvgHeatRelease(total / 4.0);
		return g;
	}

	/**
	 * CrossOver Returns the Genotype after crossover to proceed for Mutation
	 */

	private static GenoType crossOver(GenoType g) {
		double[] diameters = new double[4];
		Map<Double, Double> map = new HashMap<>();
		for (int i = 0; i < g.getPhenotype().getOrder().size(); i++) {
			map.put(g.getPhenotype().getOrder().get(i).getFitnessVal(),
					g.getPhenotype().getOrder().get(i).getReactor().getDiameter());
		}
		Map<Double, Double> sortedMap = map.entrySet().stream().sorted(Entry.comparingByValue())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		diameters = f.calculateDiameter(sortedMap);
		String[] representation = f.doubleToBin(diameters);
		representation = f.getChild(representation);
		g.setRepresentation(representation);
		return g;
	}

	/**
	 * Mutation Returns new diameter values for next generations
	 */

	private static double[] mutate(GenoType g) {
		double[] newDiameter = new double[g.getRepresentation().length];
		int count = 0;
		for (String s : g.getRepresentation()) {
			StringBuilder val = shuffle(s);
			newDiameter[count] = Integer.parseInt(val.toString(), 2) / 10.0;
			count++;
		}
		return newDiameter;
	}

	/**
	 * Shuffles the value for mutation
	 */

	public static StringBuilder shuffle(String input) {
		List<Character> characters = new ArrayList<Character>();
		for (char c : input.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(input.length());
		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}
		return output;
	}
	
	private static void createAndShowGui(int size,Population p) {
	      List<Double> scores = new ArrayList<Double>();
	      int maxDataPoints = size;
	      for (int i = 0; i < maxDataPoints ; i++) {
	         scores.add( p.getGenotype().get(i).getPhenotype().getAvgHeatRelease());
	      }
	      DrawGraph mainPanel = new DrawGraph(scores);

	      JFrame frame = new JFrame("DrawGraph");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().add(mainPanel);
	      frame.pack();
	      frame.setLocationByPlatform(true);
	      frame.setVisible(true);
	   }
}
