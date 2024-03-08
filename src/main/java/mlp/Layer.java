package mlp;

public interface Layer {
      double[] getActivations();
      void evaluateActivations();
      Layer getPreviousLayer();
      void initWeightsAndBiases();
      
}
