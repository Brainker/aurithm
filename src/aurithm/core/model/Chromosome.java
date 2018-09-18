package aurithm.core.model;

import aurithm.core.data.NetworkConfig;

import java.util.Arrays;

public class Chromosome {
    private boolean fitnessHasChanged = true;
    private int fitness = 0;
    private int[] genes;

    public Chromosome(int length) {
        this.genes = new int[length];
    }

    public Chromosome initializeChromosome() {
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.random() >= 0.5 ? 1 : 0;
        }
        return this;
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
        int chromosomeFitness = 0;
        for (int i = 0; i < genes.length; i++)
            if (genes[i] == NetworkConfig.getInstance().targetChromosome()[i])
                chromosomeFitness++;
        return chromosomeFitness;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.genes);
    }
}
