package mlp;

public class NonInputLayer implements Layer {

	private Neuron[] neurons;
	private Layer previousLayer;
	private double[] activations;

	public NonInputLayer(int size, Layer prev) {
		this.previousLayer=prev;
		neurons=new Neuron[size];
		for(int i=0;i<neurons.length;i++) {
			neurons[i] = new Neuron(previousLayer, i);
		}
		activations=new double[neurons.length];
		
		
			}

	@Override
	public double[] getActivations() {
			return activations;
	}

	@Override
	public void evaluateActivations() {
		int activationIndex=0;
		for(Neuron n:neurons) {
			activations[activationIndex] = n.net();
			activations[activationIndex] = Net.applyNonLinearFunction(activations[activationIndex]);
		    n.setLastActivation(activations[activationIndex]);
		    activationIndex++;
		}
		
	}

	@Override
	public Layer getPreviousLayer() {
		
		return this.previousLayer;
	}

	@Override
	public void initWeightsAndBiases() {
		for(Neuron n:neurons) {
			n.initWeightsAndBias();
		}

	}

	public Neuron[] getNeurons() {
		return this.neurons;
	}

}
