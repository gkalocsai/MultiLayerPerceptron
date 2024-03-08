package main;

import mlp.Net;

public class Xor {

	public static void main(String[] args) {
		Net n = new Net(2,12,10,1);
		n.initWeightsAndBiases();
		for(int i=0;i<1000000;i++) {
			double randomInput[] = createRandomInput();
			n.train(randomInput, createExpected(randomInput));
		}
		
		double[][] testInputs = {{0,0},{0,1},{1,0},{1,1}};
		
		for(double[] testInput:testInputs) {
			n.feedForward(testInput);
			System.out.print("Input: " +n.getInputs());
			System.out.println("The result: " +n.getResults());
			
		}
		
	}

	private static double[] createExpected(double[] randomInput) {
		double r= 1.0;
		if(randomInput[0] > 0.5 == randomInput[1] > 0.5) {
			r= 0.0;
		}
		double[] result=new double[1];
		result[0] = r;
		return result;
	}

	private static double[] createRandomInput() {
		double[] inputs= new double[2];
		for(int i=0;i<inputs.length;i++) {
			if(Math.random() <0.5) inputs[i] =0;
			else inputs[i] =1;
		}
		return inputs;
	}
}
