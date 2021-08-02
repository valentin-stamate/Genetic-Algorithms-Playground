import ga.AbstractGeneticAlgorithm;
import ga.config.GaConfig;
import ga.operators.crossover.AbstractCrossover;
import ga.operators.crossover.OnePointCrossover;
import ga.operators.lambda.observers.OnNewGeneration;
import ga.operators.lambda.observers.OnNewIteration;
import ga.operators.mutation.AbstractMutation;
import ga.operators.mutation.SimpleMutation;
import ga.operators.selection.AbstractSelection;
import ga.operators.selection.TournamentSelection;
import implementation.Function;
import implementation.GeneticAlgorithm;

public class MainClass {
    public static void main(String... args) {

        int components = 5;
        int precision = 3;

        Function function = new Function(components, precision);

        int geneLength = function.calculateGeneLength();

        GaConfig gaConfig = GaConfig.initializeWithParameters(
                100,
                2000,
                32,
                geneLength,
                0.008,
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

        abstractGeneticAlgorithm.addPopulationObserver((OnNewIteration) (copyMember, iteration) -> {
            System.out.printf("Iteration %2d : Best Fitness %6.3f : Function Value: %6.3f\n", iteration, copyMember.getFitness(), copyMember.getScore());
        });

        abstractGeneticAlgorithm.addPopulationObserver((OnNewGeneration) (copyPopulation, generation) -> {

        });

        abstractGeneticAlgorithm.showInfo();
        System.out.println("");

        abstractGeneticAlgorithm.start();

    }
}
