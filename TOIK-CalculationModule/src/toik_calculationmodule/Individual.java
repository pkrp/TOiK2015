package toik_calculationmodule;

import java.util.Random;

public class Individual {
	
	private double calculatedPi;
	
	public Individual(){
		calculatedPi = 0.0;
		initializeIndividual();
	}
	
	public void initializeIndividual(){
		calculatedPi = new Random().nextDouble() + 3.0;
	}
	
	public double getValue(){
		return calculatedPi;
	}
	
	public void setValue(double value){
		calculatedPi = value;
	}
	
}
