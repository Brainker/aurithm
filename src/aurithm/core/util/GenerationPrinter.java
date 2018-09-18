package aurithm.core.util;

import aurithm.core.GeneticAlgorithm;
import aurithm.core.model.Population;

import java.util.Arrays;

public class GenerationPrinter {
    private GenerationPrinter(){
        //prevent initializing
    }
    public static void printPopulation(GeneticAlgorithm algorithm, Population population, String heading){
        System.out.println(heading);
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < population.getChromosomes().length; i++) {
            System.out.println("Chromosome # " + i + "  :  " + Arrays.toString(population.getChromosomes()[i].genes()) + " | Fitness: " + population.getChromosomes()[i].fitness(algorithm));
        }
        System.out.println("--------------------------------------------------------------");
    }
}
