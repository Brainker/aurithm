import aurithm.core.GeneticAlgorithm;
import aurithm.core.data.NetworkConfig;
import aurithm.core.model.Generation;
import aurithm.core.model.Population;
import aurithm.core.util.GenerationPrinter;

import java.util.ArrayList;
import java.util.Arrays;

public class Driver {
    private static GeneticAlgorithm algorithm = GeneticAlgorithm.fromNetworkConfig(NetworkConfig.getInstance());

    private static final ArrayList<Generation> generations = new ArrayList<>();

    public static void main(String[] args) {
        Generation currentGeneration;
        Population population = new Population(algorithm.POPULATION_SIZE).initializePopulation(algorithm);

        currentGeneration = Generation.create(population);

        generations.add(currentGeneration);

        System.out.println("--------------------------------------------------------------");
        System.out.println("Generation #0 " + " | Fittest chromosome fitness: " + population.getChromosomes()[0].fitness());
        GenerationPrinter.printGeneration(currentGeneration, "Target Chromosome: " + Arrays.toString(algorithm.TARGET_CHROMOSOME));

        while (population.getChromosomes()[0].fitness() < algorithm.TARGET_CHROMOSOME.length){
            population = algorithm.evolve(population);
            currentGeneration = Generation.create(population);
            generations.add(currentGeneration);
            System.out.println("--------------------------------------------------------------");
            System.out.println("Generation #"+ currentGeneration.id() + " | Fittest chromosome fitness: " + population.getChromosomes()[0].fitness());
            GenerationPrinter.printGeneration(currentGeneration, "Target Chromosome: " + Arrays.toString(algorithm.TARGET_CHROMOSOME));
        }


        for (Generation g : generations) {
            System.out.println(g);
        }
    }
}
