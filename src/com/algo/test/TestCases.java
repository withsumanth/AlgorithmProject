package com.algo.test;

/**
 * @author Sumanth
 * @author Kavya
 */

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.algo.functions.FitnessFunction;
import com.algo.model.Reactor;

@RunWith(JUnitPlatform.class)
class TestCases {

	FitnessFunction f;

	TestCases() {
		f = new FitnessFunction();
	}

	@Test
	void gatest1() {
		List<Double> values = new ArrayList<Double>();
		values.add(1.0);
		values.add(2.0);
		values.add(3.0);
		values.add(4.0);
		values.add(5.0);
		values.add(6.0);
		Reactor r = new Reactor();
		r.setDiameter(values.get(0));
		double heatCoeff1 = f.heatCoeffQ(r);
		r.setDiameter(values.get(1));
		double heatCoeff2 = f.heatCoeffQ(r);
		r.setDiameter(values.get(2));
		double heatCoeff3 = f.heatCoeffQ(r);
		r.setDiameter(values.get(3));
		double heatCoeff4 = f.heatCoeffQ(r);
		r.setDiameter(values.get(4));
		double heatCoeff5 = f.heatCoeffQ(r);
		r.setDiameter(values.get(5));
		double heatCoeff6 = f.heatCoeffQ(r);
		DecimalFormat formatNum = new DecimalFormat("0.00");
		assertEquals(formatNum.format(heatCoeff1), String.valueOf(667.16));
		assertEquals(formatNum.format(heatCoeff2), String.valueOf(548.91));
		assertEquals(formatNum.format(heatCoeff3), String.valueOf(427.12));
		assertEquals(formatNum.format(heatCoeff4), String.valueOf(304.03));
		assertEquals(formatNum.format(heatCoeff5), String.valueOf(180.26));
		assertEquals(formatNum.format(heatCoeff6), String.valueOf(56.08));
	}

	@Test
	void gatest2() {
		List<Double> coeffList = new ArrayList<Double>();
		coeffList.add(667.16);
		coeffList.add(548.91);
		coeffList.add(427.12);
		coeffList.add(304.03);
		coeffList.add(180.26);
		coeffList.add(56.08);
		assertEquals(f.total(coeffList), 2183.56);
	}

	@Test
	void gatest3() {
		assertEquals(f.fitnessValue(667.16, 2183.56), 0.30553774569968306);
	}

	@Test
	void gatest4() {
		double[] d = { 1.0, 2.0 };
		String[] str = { "001010", "010100" };
		String[] str2 = f.doubleToBin(d);
		assertEquals(str2[0], str[0]);
		assertEquals(str2[1], str[1]);
	}

}
