package aurithm.core.util;

import aurithm.core.model.Generation;

import java.util.Arrays;

public class GenerationPrinter {
    private GenerationPrinter(){
        //prevent initializing
    }

    public static void printGeneration(Generation generation, String heading){
        System.out.println(heading);
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < generation.population().getChromosomes().length; i++) {
            System.out.println("Chromosome #" + i + "  :  " + Arrays.toString(generation.population().getChromosomes()[i].genes()) + " | Fitness: " + generation.population().getChromosomes()[i].fitness());
        }
        System.out.println("--------------------------------------------------------------");
    }
}
