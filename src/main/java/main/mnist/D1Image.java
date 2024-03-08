package main.mnist;

public class D1Image {

	double[] imageDataAsInput;
	public D1Image(int [][] imageData) {
		int nLength=0;
		for(int[] i:imageData) {
			nLength+=i.length;
		}
		this.imageDataAsInput = new double[nLength];
		int imageDataIndex=0;
		for(int[] i:imageData) {
			for(int k:i) {
				imageDataAsInput[imageDataIndex] = ((double)k)/255.0;
				imageDataIndex++;
			}
		}
	}
	public final double[] getImageDataAsInput() {
		return imageDataAsInput;
	}
	
}
