package mlp;

import java.text.DecimalFormat;

public class Net {

	public static final double LEARNING_RATE = 0.075;
	private Layer[] layers;
	
	
	public Net (int... layerSizes) {
		if(layerSizes.length <2 || layerSizes[0] <1) throw new RuntimeException("Invalid layer sizes");
		layers=new Layer[layerSizes.length];
		layers[0]= new InputLayer(layerSizes[0]);
		for(int i=1;i<layerSizes.length;i++) {
			layers[i] = new NonInputLayer(layerSizes[i], layers[i-1]);
			
		}
	}
	
	public void feedForward(double[] input) {
		((InputLayer)layers[0]).setActivations(input);
	    for(Layer l:layers) {
	    	l.evaluateActivations();
	    }
	}
	public void initWeightsAndBiases() {
		for(Layer l:layers) {
			l.initWeightsAndBiases();
		}
	}
	public static double applyNonLinearFunction(double d) {
		//Sigmoid
		return 1 / (1 + Math.exp(-d));
		//ReLU
		//if(d>0) return d;
		//else return 0;
		
	}
	public static double derivativeOfTheNonLinearFunction(double d) {
		//derivativeOfTheSigmoid
		double s = applyNonLinearFunction(d);
		return (1-s)*s;
		
		//derivativeOfTheRelu
		//if(d>0) return 1;
		//else return 0;
	}
	
	public String getResults() {
		final DecimalFormat df=new DecimalFormat("0.00000000000000");
		StringBuilder sb=new StringBuilder();
		for(double d: layers[layers.length-1].getActivations()) {
			sb.append(df.format(d)+" ");
		}
		return sb.toString();
		
	}
    public double[] getOutputs() {
    	double[] result = new double[layers[layers.length-1].getActivations().length];
    	
    	int i=0;
    	for(double d: layers[layers.length-1].getActivations()) {
    		result[i++] = d;
    	}
    	return result;
    }
	
    public String getInputs() {
    	StringBuilder sb=new StringBuilder();
    	for(double d:layers[0].getActivations()) {
    		sb.append(d+"   ");
    	}
    	return sb.toString();
    }
    
    
    public void train(double[] input, double[] expected) {
    	feedForward(input);
    	propagateBack(expected);
    }

	private void propagateBack(double[] expected) {
		NonInputLayer output=(NonInputLayer)layers[layers.length-1];		
	    int expectedIndex=0;
	    for(Neuron n:output.getNeurons()) {
	    	n.updateWeightsInOutput(expected[expectedIndex]);
	    	expectedIndex++;
	    }
	    for(int i=layers.length-2;i>=1;i--) {
	    	NonInputLayer hiddenLayer=(NonInputLayer)layers[i];
	    	for(Neuron n:hiddenLayer.getNeurons()) {
	    		n.updateWeightsInHidden((NonInputLayer)layers[i+1]);
	    	}
	    }
	
	}
}
