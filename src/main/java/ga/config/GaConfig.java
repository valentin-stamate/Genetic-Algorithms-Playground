package ga.config;

public class GaConfig {
    public final double mutationProbability;
    public final double crossoverProbability;
    public final int populationSize;
    public final int generations;
    public final int sampleSize;
    public final int geneLength;

    private static GaConfig instance;

    private GaConfig(int populationSize, int generations, int sampleSize, int geneLength,
                     double mutationProbability, double crossoverProbability) {
        this.populationSize = populationSize;
        this.generations = generations;
        this.sampleSize = sampleSize;
        this.geneLength = geneLength;
        this.mutationProbability = mutationProbability;
        this.crossoverProbability = crossoverProbability;
    }

    public static GaConfig initializeWithParameters(int populationSize, int maxGenerations, int sampleSize, int geneLength, double mutationProbability, double crossoverProbability) {
        instance = new GaConfig(populationSize, maxGenerations, sampleSize, geneLength, mutationProbability, crossoverProbability);

        return GaConfig.getInstance();
    }

    public static GaConfig getInstance() {
        if (instance == null) {
            System.out.println("Initialize the configuration first.");
            return null;
        }

        return instance;
    }

}
