package aurithm.core.model;

import aurithm.core.GeneticAlgorithm;

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

    public int fitness(GeneticAlgorithm algorithm) {
        if (fitnessHasChanged) {
            fitness = recalculateFitness(algorithm);
            fitnessHasChanged = false;
        }
        return fitness;
    }

    public int recalculateFitness(GeneticAlgorithm algorithm){
        int chromosomeFitness = 0;
        for (int i = 0; i < genes.length; i++)
            if (genes[i] == algorithm.TARGET_CHROMOSOME[i])
                chromosomeFitness++;
        return chromosomeFitness;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.genes);
    }
}
