package aurithm.core.model;

public class Generation {
    private static int id_counter = 0;

    private final int id;
    private final Population population;

    private Generation(int id, Population population) {
        this.id = id;
        this.population = population;
    }

    public static Generation create(Population population){
        Generation g = new Generation(id_counter, population);
        id_counter++;
        return g;
    }

    public int id(){
        return id;
    }

    public Population population(){
        return population;
    }

    @Override
    public String toString() {
        return "Generation #"+this.id+" | Fittest chromosome fitness: "+population.getChromosomes()[0].fitness();
    }
}
