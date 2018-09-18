package aurithm.core.data;

public class NetworkConfig {
    private static NetworkConfig instance;
    private static boolean hasBeenInitialized = false;

    private int populationSize;
    private int[] targetChromosome;
    private double mutationRate;
    private int numbOfEliteChromosomes;
    private int tournamentSelectionSize;
    private int chromosomeGeneLength;

    private NetworkConfig(int populationSize, int[] targetChromosome, double mutationRate, int numbOfEliteChromosomes, int tournamentSelectionSize) {
        this.populationSize = populationSize;
        this.targetChromosome = targetChromosome;
        this.mutationRate = mutationRate;
        this.numbOfEliteChromosomes = numbOfEliteChromosomes;
        this.tournamentSelectionSize = tournamentSelectionSize;
        this.chromosomeGeneLength = targetChromosome.length;
    }

    public static synchronized NetworkConfig getInstance(){
        if (instance == null)
            new NetworkConfig.Builder().build();
        return instance;
    }

    public static boolean hasBeenInitialized() {
        return hasBeenInitialized;
    }

    public int populationSize() {
        return populationSize;
    }

    public int[] targetChromosome() {
        return targetChromosome;
    }

    public double mutationRate() {
        return mutationRate;
    }

    public int numbOfEliteChromosomes() {
        return numbOfEliteChromosomes;
    }

    public int tournamentSelectionSize() {
        return tournamentSelectionSize;
    }

    public int chromosomeGeneLength() {
        return chromosomeGeneLength;
    }

    private int[] generateChromosomeWithLength(int chromosomeGeneLength) {
        int[] arr = new int[chromosomeGeneLength];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random() < 0.5 ? 0 : 1;
        }

        return arr;
    }

    public static class Builder{
        private int populationSize = 32;
        private int[] targetChromosome = {1,0,1,0,0,1,0,1,1,1,0,0,0,1,0,1,1,1,0,1,0,1,0,0,1,0,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,1,0,1,1,0,0,1,1,0,1,0,1,0,0,0,1,0,1,0,1};
        private double mutationRate = 0.32;
        private int numbOfEliteChromosomes = 2;
        private int tournamentSelectionSize = 8;

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

        public void build(){
            instance = new NetworkConfig(populationSize, targetChromosome, mutationRate, numbOfEliteChromosomes, tournamentSelectionSize);
            hasBeenInitialized = true;
        }
    }
}
