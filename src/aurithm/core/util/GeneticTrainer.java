package aurithm.core.util;

import aurithm.core.GeneticAlgorithm;
import aurithm.core.model.Chromosome;
import aurithm.core.model.Generation;
import mathematika.core.Mathematika;
import stringlify.core.Stringlify;

import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneticTrainer
        implements Serializable, Cloneable {
    private final Logger LOGGER;

    private final int counter;
    private final GeneticAlgorithm algorithm;

    private boolean isEvolving;

    public GeneticTrainer(GeneticAlgorithm algorithm, int counter) {
        LOGGER = Logger.getLogger(this.getClass().getName());
        LOGGER.setLevel(Level.FINEST);
        this.counter = counter;
        this.algorithm = algorithm;
        this.isEvolving = false;
    }

    public Generation startTraining(){
        isEvolving = true;
        Generation generation = algorithm.createGeneration();

        double bestAvFi = generation.averageFitness();
        int bestGenId = 0;

        int survivedRounds = 0;
        for (int i = 0; i < counter; i++) {
            if (i != 0) generation = algorithm.evolve(generation);
            while (generation.population().isAlive()){
                survivedRounds++;
                for (Chromosome c : generation.population().chromosomes()){
                    if (c.isAlive)
                        c.isAlive = Math.random() > 0.1;
                }
            }

            double avFi;
            if ((avFi = generation.averageFitness()) > bestAvFi){
                bestAvFi = avFi;
                bestGenId = generation.id();
            }


            System.out.println(Stringlify.stringlify("Generation {0} survived {1} rounds. Average fitness: {2}", generation.id(), survivedRounds, generation.averageFitness()));
            survivedRounds = 0;
        }
        LOGGER.info(Stringlify.stringlify("Latest generation: {0}", generation));
        LOGGER.info(Stringlify.stringlify("Best generation: {0}. Average Fitness: {1}", bestGenId, bestAvFi));
        return generation;
    }
}
