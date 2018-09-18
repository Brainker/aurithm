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
        sortChromosomesByFitness(algorithm);
        return this;
    }

    public Chromosome[] getChromosomes(){
        return chromosomes;
    }

    public Chromosome chromosome(int index){
        return chromosomes[index];
    }

    public void sortChromosomesByFitness(GeneticAlgorithm algorithm){
        Arrays.sort(chromosomes, ((t1, t2) -> {
            int flag = 0;

            if (t1.fitness(algorithm) > t2.fitness(algorithm)) flag = -1;
            else if (t1.fitness(algorithm) < t2.fitness(algorithm)) flag = 1;

            return flag;
        }));
    }
}
