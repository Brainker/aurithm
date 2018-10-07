package aurithm.core.model;

import aurithm.core.network.NetworkTools;
import aurithm.core.network.NeuralNetwork;
import mathematika.core.Mathematika;

import java.io.Serializable;
import java.util.Arrays;

public class Chromosome
        implements Serializable, Cloneable {
    public static final int MAX = 9, MIN = -9;
    public boolean isAlive;
    private boolean fitnessHasChanged = true;
    private int fitness = 0;
    private int[] genes;
    private int[] networkLayerSizes;
    private NeuralNetwork network;

    public Chromosome(int length, int[] networkLayerSizes) {
        this.isAlive = true;
        this.genes = new int[length];
        this.networkLayerSizes = networkLayerSizes;
        this.network = new NeuralNetwork(networkLayerSizes);
    }

    public Chromosome initializeChromosome() {
        for (int i = 0; i < genes.length; i++) {
            genes[i] = NetworkTools.randomValue(MIN, MAX);
        }
        network = new NeuralNetwork(networkLayerSizes).withWeightsAndBias(genes);
        return this;
    }

    public void generateNeuralNetwork() {
        network = new NeuralNetwork(networkLayerSizes).withWeightsAndBias(genes);
    }

    public double[] feedForward(double[] input){
        if (input.length != networkLayerSizes[0])
            return input;
        return this.network.feedForward(input);
    }

    public int[] genes() {
        fitnessHasChanged = true;
        return genes;
    }

    public int fitness() {
        if (fitnessHasChanged) {
            fitness = recalculateFitness();
            fitnessHasChanged = false;
        }
        return fitness;
    }

    public int recalculateFitness(){
        // TODO: 27.09.2018 The recalculateFitness method needs a remake

        double[] input = new double[networkLayerSizes[0]];
        for (int i = 0; i < input.length; i++) {
            input[i] = Mathematika.random(0, 300);
        }

        double[] output = feedForward(input);
        return (int)(output[0]*10);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.genes);
    }
}
