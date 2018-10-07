import aurithm.core.Configuration;
import aurithm.core.GeneticAlgorithm;
import aurithm.core.util.GeneticTrainer;

public class Driver {
    public static void main(String[] args) {
        Configuration configuration = new Configuration.Builder().build();
        GeneticAlgorithm algorithm = GeneticAlgorithm.fromConfiguration(configuration);

        GeneticTrainer trainer = new GeneticTrainer(algorithm, 100);
        trainer.startTraining();
    }
}
