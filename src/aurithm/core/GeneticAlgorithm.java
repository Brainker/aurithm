package aurithm.core;

import aurithm.core.model.Chromosome;
import aurithm.core.model.Population;

public class GeneticAlgorithm {
    public final int POPULATION_SIZE;
    public final int[] TARGET_CHROMOSOME;
    private final double MUTATION_RATE;
    public final int NUMB_OF_ELITE_CHROMOSOMES;
    public final int TOURNAMENT_SELECTION_SIZE;

    private GeneticAlgorithm(int POPULATION_SIZE, int[] TARGET_CHROMOSOME, double MUTATION_RATE, int NUMB_OF_ELITE_CHROMOSOMES, int TOURNAMENT_SELECTION_SIZE) {
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.TARGET_CHROMOSOME = TARGET_CHROMOSOME;
        this.MUTATION_RATE = MUTATION_RATE;
        this.NUMB_OF_ELITE_CHROMOSOMES = NUMB_OF_ELITE_CHROMOSOMES;
        this.TOURNAMENT_SELECTION_SIZE = TOURNAMENT_SELECTION_SIZE;
    }

    public Population evolve(Population population){
        return mutatePopulation(crossoverPopulation(population));
    }

    private Population crossoverPopulation(Population population){
        Population crossoverPopulation = new Population(population.getChromosomes().length);
        for (int i = 0; i < NUMB_OF_ELITE_CHROMOSOMES; i++) {
            crossoverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
        }
        for (int i = NUMB_OF_ELITE_CHROMOSOMES; i < population.getChromosomes().length; i++) {
            Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
            Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
            crossoverPopulation.getChromosomes()[i] = crossoverChromosome(chromosome1, chromosome2);
        }
        crossoverPopulation.sortChromosomesByFitness(this);
        return crossoverPopulation;
    }

    private Population mutatePopulation(Population population){
        Population mutatePopulation = new Population(population.getChromosomes().length);
        for (int i = 0; i < NUMB_OF_ELITE_CHROMOSOMES; i++) {
            mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
        }

        for (int i = NUMB_OF_ELITE_CHROMOSOMES; i < population.getChromosomes().length; i++) {
            mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
        }

        mutatePopulation.sortChromosomesByFitness(this);
        return mutatePopulation;
    }

    private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2){
        Chromosome crossoverChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for (int i = 0; i < chromosome1.genes().length; i++) {
            crossoverChromosome.genes()[i] = Math.random() < 0.5
                    ? chromosome1.genes()[i]
                    : chromosome2.genes()[i];
        }
        return crossoverChromosome;
    }

    private Chromosome mutateChromosome(Chromosome chromosome){
        Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);

        for (int i = 0; i < chromosome.genes().length; i++) {
            if (Math.random() < MUTATION_RATE){
                mutateChromosome.genes()[i] = Math.random() < 0.5
                        ? 1
                        : 0;
            } else mutateChromosome.genes()[i] = chromosome.genes()[i];
        }

        return mutateChromosome;
    }

    private Population selectTournamentPopulation(Population population){
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);

        for (int i = 0; i < TOURNAMENT_SELECTION_SIZE; i++) {
            tournamentPopulation.getChromosomes()[i] = population.getChromosomes()[(int)(Math.random()*population.getChromosomes().length)];
        }

        tournamentPopulation.sortChromosomesByFitness(this);
        return tournamentPopulation;
    }

    public static class Builder{
        private int populationSize = 10;
        private int[] targetChromosome = {1,0,1,0,0,1,0,1};
        private double mutationRate = 0.25;
        private int numbOfEliteChromosomes = 1;
        private int tournamentSelectionSize = 4;

        public Builder(){

        }

        public Builder populationSize(int size){
            this.populationSize = size;
            return this;
        }

        public Builder targetChromosome(int... chromosome){
            this.targetChromosome = chromosome;
            return this;
        }

        public Builder mutationRate(double rate){
            this.mutationRate = rate;
            return this;
        }

        public Builder numbOfEliteChromosomes(int numb){
            this.numbOfEliteChromosomes = numb;
            return this;
        }

        public Builder tournamentSelectionSize(int size){
            this.tournamentSelectionSize = size;
            return this;
        }

        public GeneticAlgorithm build(){
            return new GeneticAlgorithm(populationSize, targetChromosome, mutationRate, numbOfEliteChromosomes, tournamentSelectionSize);
        }
    }
}