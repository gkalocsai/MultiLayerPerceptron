package main;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import main.mnist.D1Image;
import main.mnist.MnistImageDataLoader;
import mlp.Net;

public class MnistDigits {

	public static void main(String[] args) throws IOException {
		Map<String, List<D1Image>> trainData=new MnistImageDataLoader().loadTrainData();
		System.out.println("Train data loaded");
		System.out.println("Training...");
		Net n=new Net(784,32,16,10);
		for(int i=0;i<300000;i++) {
			int digit= (int) (Math.random()*10);
			D1Image image= getRandomImage(trainData, digit);
			if(i%1000 == 0) System.out.println(""+i+". training completed." );
			n.train(image.getImageDataAsInput(), createExpectedOutput(digit));
		}
		Map<String, List<D1Image>> testData=new MnistImageDataLoader().loadTestData();
		System.out.println("TEST data loaded");
		int testCount=100;
		int hitCount=0;
		for(int i=0;i <testCount; i++) {
			int digit = (int) (Math.random()*10);
			D1Image image= getRandomImage(testData, digit);
			n.feedForward(image.getImageDataAsInput());
			int createdOutput=getDecision(n.getOutputs());
			System.out.println("Current digit: "+digit +" Result of the net: "+createdOutput);
			if(digit == createdOutput) hitCount++;			
		}
		double hp=(double) hitCount/(double)testCount;
		System.out.println(String.format("Hit percentage: %f%c", hp*100,'%'));
	}
	
	private static int getDecision(double[] outputs) {
		int maxIndex=0;
		double max=0.0;
		for(int i=0;i<outputs.length;i++) {
			if(outputs[i] > max) {
			   max=outputs[i];
			   maxIndex=i;
			}
		}
		return maxIndex;
	}
	private static double[] createExpectedOutput(int digit) {
		double[] expected=new double[10];
		expected[digit] = 1.0;
		return expected;
	}
	private static D1Image getRandomImage(Map<String, List<D1Image>> trainData, int digit) {
		List<D1Image> imageList = trainData.get(""+digit);
		int index= (int) (Math.random()*imageList.size());
		return imageList.get(index);
	}
}
