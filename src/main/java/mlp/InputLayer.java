package mlp;

public class InputLayer implements Layer {

	
	
	private int size;
	private double[] activations;

	
	public InputLayer(int size) {
		this.size=size;
		this.activations=new double[size];
	}

	public void setActivations(double[] acs) {
		if(acs.length != size) throw new RuntimeException("Number of activations must be "+size);
		for(int i=0;i<activations.length;i++) {
			activations[i] = acs[i];
		}
		
	}
	
	@Override
	public double[] getActivations() {
		return this.activations;
	}

	@Override
	public void evaluateActivations() {
		// TODO Auto-generated method stub

	}

	@Override
	public Layer getPreviousLayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initWeightsAndBiases() {
		// TODO Auto-generated method stub

	}

}
