package aurithm.core;

import aurithm.core.network.NeuralNetwork;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import stringlify.core.Stringlify;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Configuration
        implements Serializable, Cloneable {
    public final NeuralNetwork NETWORK;
    public final int POPULATION_SIZE;
    public final int NUMBER_OF_ELITE_CHROMOSOMES;
    public final int TOURNAMENT_SELECTION_SIZE;
    public final int CHROMOSOME_GENE_LENGTH;
    public final double MUTATION_RATE;

    private Configuration(NeuralNetwork NETWORK,
                         int POPULATION_SIZE,
                         int NUMBER_OF_ELITE_CHROMOSOMES,
                         int TOURNAMENT_SELECTION_SIZE,
                         double MUTATION_RATE) {
        this.NETWORK = NETWORK;
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.NUMBER_OF_ELITE_CHROMOSOMES = NUMBER_OF_ELITE_CHROMOSOMES;
        this.TOURNAMENT_SELECTION_SIZE = TOURNAMENT_SELECTION_SIZE;
        this.CHROMOSOME_GENE_LENGTH = calcChromosomeGeneLength(NETWORK);
        this.MUTATION_RATE = MUTATION_RATE;
    }

    private int calcChromosomeGeneLength(NeuralNetwork network) {
        int geneLength = 0;

        int cur;
        int prev;
        for (int i = network.NETWORK_LAYER_SIZES.length-1; i >= 0; i--) {
            cur = i;
            prev = i-1;
            if (prev >= 0)
                geneLength += (network.NETWORK_LAYER_SIZES[cur] * network.NETWORK_LAYER_SIZES[prev])+network.NETWORK_LAYER_SIZES[cur];
        }

        return geneLength;
    }

    @SuppressWarnings("all")
    public void writeToJSON(File file){
        final String filePath = file.getAbsolutePath();
        final Map<String, Object> map = new HashMap<>();

        final JSONObject json = new JSONObject();

        final JSONArray array = new JSONArray();
        for (int i : this.NETWORK.NETWORK_LAYER_SIZES) {
            array.add(i);
        }

        json.put("network_layer_sizes", array);
        json.put("population_size", this.POPULATION_SIZE);
        json.put("number_of_elite_chromosomes", this.NUMBER_OF_ELITE_CHROMOSOMES);
        json.put("tournament_selection_size", this.TOURNAMENT_SELECTION_SIZE);
        json.put("chromosome_gene_length", this.CHROMOSOME_GENE_LENGTH);
        json.put("mutation_rate", this.MUTATION_RATE);


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(json.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Stringlify.stringlify("File path: {0}", filePath));
        }
    }

    public void writeToJSON(){
        writeToJSON(new File("configuration.json"));
    }

    public static class Builder {
        private NeuralNetwork network;
        private int populationSize;
        private int numbOfEliteChromosomes;
        private int tournamentSelectionSize;
        private double mutationRate;

        public Builder(){
            this.network = new NeuralNetwork(5,4,1);
            this.populationSize = 50;
            this.numbOfEliteChromosomes = 2;
            this.tournamentSelectionSize = 8;
            this.mutationRate = 0.32;
        }

        public Builder network(NeuralNetwork neuralNetwork){
            this.network = neuralNetwork;
            return this;
        }

        public Builder populationSize(int size){
            this.populationSize = size;
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

        public Builder mutationRate(double rate){
            this.mutationRate = rate;
            return this;
        }

        public Configuration build(){
            return new Configuration(network, populationSize, numbOfEliteChromosomes, tournamentSelectionSize, mutationRate);
        }
    }
}
