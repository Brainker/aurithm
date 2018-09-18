package aurithm.core.model;

import aurithm.core.GeneticAlgorithm;

import java.util.Arrays;

public class Population {
    private Chromosome[] chromosomes;

    public Population(int length) {
        this.chromosomes = new Chromosome[length];
    }

    public Population initializePopulation(GeneticAlgorithm algorithm) {
        for (int i = 0; i < chromosomes.length; i++) {
            chromosomes[i] = new Chromosome(algorithm.TARGET_CHROMOSOME.length).initializeChromosome();
        }
        sortChromosomesByFitness();
        return this;
    }

    public Chromosome[] getChromosomes(){
        return chromosomes;
    }

    public Chromosome chromosome(int index){
        return chromosomes[index];
    }

    public void sortChromosomesByFitness(){
        Arrays.sort(chromosomes, ((t1, t2) -> {
            int flag = 0;

            if (t1.fitness() > t2.fitness()) flag = -1;
            else if (t1.fitness() < t2.fitness()) flag = 1;

            return flag;
        }));
    }
}
