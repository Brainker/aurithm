import aurithm.core.GeneticAlgorithm;
import aurithm.core.model.Population;
import aurithm.core.util.GenerationPrinter;

import java.util.Arrays;

public class Driver {
    private static GeneticAlgorithm algorithm =  new GeneticAlgorithm.Builder()
            .mutationRate(0.25)
            .numbOfEliteChromosomes(1)
            .tournamentSelectionSize(10)
            .targetChromosome(1,0,1)
            .populationSize(10)
            .build();

    public static void main(String[] args) {
        Population population = new Population(algorithm.POPULATION_SIZE).initializePopulation(algorithm);

        System.out.println("--------------------------------------------------------------");
        System.out.println("Generation # 0 " + " | Fittest chromosome fitness: " + population.getChromosomes()[0].fitness(algorithm));
        GenerationPrinter.printPopulation(algorithm, population, "Target Chromosome: " + Arrays.toString(algorithm.TARGET_CHROMOSOME));

        int generationNumber = 0;
        while (population.getChromosomes()[0].fitness(algorithm) < algorithm.TARGET_CHROMOSOME.length){
            generationNumber++;
            population = algorithm.evolve(population);
            System.out.println("--------------------------------------------------------------");
            System.out.println("Generation # "+ generationNumber + " | Fittest chromosome fitness: " + population.getChromosomes()[0].fitness(algorithm));
            GenerationPrinter.printPopulation(algorithm, population, "Target Chromosome: " + Arrays.toString(algorithm.TARGET_CHROMOSOME));
        }
    }
}
