import ga.AbstractGeneticAlgorithm;
import ga.config.GaConfig;
import ga.operators.crossover.AbstractCrossover;
import ga.operators.crossover.OnePointCrossover;
import ga.operators.mutation.AbstractMutation;
import ga.operators.mutation.SimpleMutation;
import ga.operators.selection.AbstractSelection;
import ga.operators.selection.TournamentSelection;
import implementation.Function;
import implementation.GeneticAlgorithm;

public class MainClass {
    public static void main(String... args) {

        int components = 30;
        int precision = 4;

        Function function = new Function(components, precision);

        int geneLength = function.calculateGeneLength();

        GaConfig gaConfig = GaConfig.initializeWithParameters(
                300,
                7000,
                1,
                geneLength,
                0.0008,
                0.2
        );

        AbstractMutation abstractMutation = new SimpleMutation();
        AbstractSelection abstractSelection = new TournamentSelection();
        AbstractCrossover abstractCrossover = new OnePointCrossover();

        AbstractGeneticAlgorithm abstractGeneticAlgorithm = new GeneticAlgorithm(
                function,
                gaConfig,
                abstractMutation,
                abstractCrossover,
                abstractSelection
        );

        abstractGeneticAlgorithm.start();

    }
}
