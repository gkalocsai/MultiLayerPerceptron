package mlp;

public class Neuron {

	private double[] biasWeightsAndIncomingWeights;
	private double[] prevActivations;
	private double lastActivation;
	private double efficiency;
	private int neuronIndexInLayer;
	

	public Neuron(Layer previousLayer, int i) {
		this.prevActivations = previousLayer.getActivations();
		this.neuronIndexInLayer = i;
		this.biasWeightsAndIncomingWeights = new double[1+prevActivations.length];
	}

	public double net() {
		double sum=biasWeightsAndIncomingWeights[0];
		for(int i=0;i<prevActivations.length;i++) {
			sum+=prevActivations[i]*biasWeightsAndIncomingWeights[i+1];
			
		}
		lastActivation = Net.applyNonLinearFunction(sum);
		return sum;
	}
	
	public void initWeightsAndBias() {
		for(int i=0;i<biasWeightsAndIncomingWeights.length;i++) {
			biasWeightsAndIncomingWeights[i] =Math.random()-0.5;
		}
	}

	public double getLastActivation() {
		return lastActivation;
	}
	
	public void updateWeightsInHidden(NonInputLayer nextLayer) {
		double activationDeriv=Net.derivativeOfTheNonLinearFunction(net());
		for(int i=0; i<biasWeightsAndIncomingWeights.length;i++) {
			double prevActivation=1.0;
			if(i>0) prevActivation = prevActivations[i-1];
			double sum = 0.0;
			for(int k=0; k<nextLayer.getNeurons().length;k++) {
				Neuron next=nextLayer.getNeurons()[k];
				sum+=next.efficiency*next.biasWeightsAndIncomingWeights[neuronIndexInLayer];
			}
			efficiency = -sum*activationDeriv;
			double pdOfCost=-efficiency*prevActivation;
			biasWeightsAndIncomingWeights[i]-=Net.LEARNING_RATE*pdOfCost;
		}
		
	}

	public void setLastActivation(double d) {
		this.lastActivation = d;
	}

	public void updateWeightsInOutput(double target) {
		double activationDeriv=Net.derivativeOfTheNonLinearFunction(net());
		efficiency= -activationDeriv*(target-this.lastActivation);
		for(int i=0; i<biasWeightsAndIncomingWeights.length;i++) {
			double prevActivation = 1.0;
			if(i>0) prevActivation = prevActivations[i-1];
			biasWeightsAndIncomingWeights[i] -= Net.LEARNING_RATE*efficiency*prevActivation;
		}
	}
	
}
