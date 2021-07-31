package implementation;

import ga.AbstractGeneticAlgorithm;
import ga.member.AbstractMember;
import ga.operators.crossover.OnePointCrossover;
import ga.operators.selection.AbstractSelection;
import ga.operators.selection.TournamentSelection;
import ga.util.Number;
import ga.util.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneticAlgorithm implements AbstractGeneticAlgorithm {
    private static final int POPULATION_SIZE = 150;
    private static final int SAMPLE_SIZE = 30;
    private static final int GENERATIONS = 4000;
    private static final double CROSSOVER_PROBABILITY = 0.2;
    private static final double MUTATION_PROBABILITY = 0.007;

    private final Function function;
    private static final int PRECISION = 5;
    private static final int DIMENSIONS = 5;

    /* Operators */
    private final AbstractSelection selection;

    public GeneticAlgorithm() {
        this.function = new Function(DIMENSIONS, PRECISION);
        this.selection = new TournamentSelection();
    }

    @Override
    public AbstractMember oneSimulation() {
        List<AbstractMember> population = generatePopulation();

        evaluate(population);

        for (int i = 1; i <= getGenerations(); i++) {
//            showGenerationStats(i, population);

            select(population);
            mutate(population);
            crossOver(population);
            evaluate(population);
        }

        population.sort(Collections.reverseOrder());

        return population.get(0);
    }

    private void showGenerationStats(int generationCount, List<Member> population) {
        List<Member> copyPopulation = new ArrayList<>(population);
        copyPopulation.sort(Collections.reverseOrder());

        Member best = copyPopulation.get(0);
        Member worst = copyPopulation.get(copyPopulation.size() - 1);

        System.out.printf("Generation %d \n Fit Best: %6.3f | Fit Worst: %6.3f | Fun Best: %6.3f | Fun Worst: %6.3f \n\n",
                generationCount, best.getFitness(), worst.getFitness(), best.getFunctionValue(), worst.getFunctionValue());

//        System.out.println("Population:");
//        for (Member m : population) {
//            m.show();
//        }

    }

    private List<AbstractMember> getNBestMembers(int n, List<AbstractMember> population) {
        List<AbstractMember> copyPopulation = new ArrayList<>(population);
        Collections.reverse(copyPopulation);

        List<AbstractMember> bestMembers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            bestMembers.add(copyPopulation.get(i));
        }

        return bestMembers;
    }

    @Override
    public void start() {
        Member best = new Member(function, MUTATION_PROBABILITY);
        best.calculateFitness();

        long timeA = Time.getCurrentTime();

        for (int i = 1; i <= SAMPLE_SIZE; i++) {

            Member bestMember = (Member) oneSimulation();
            System.out.printf("Iteration %2d : Best Fitness %6.3f : Function Value: %6.3f\n",
                    i, bestMember.getFitness(), bestMember.getFunctionValue());

            if (best.getFitness() < bestMember.getFitness()) {
                best = bestMember;
            }
        }

        System.out.println("\nDone\n");

        long timeB = Time.getCurrentTime();
        String time = Time.toTime(timeB - timeA);
        System.out.printf("Time: %s \n", time);
        System.out.printf("Best Overall: %6.3f | Function Value: %6.3f\n", best.getFitness(), best.getFunctionValue());

    }

    @Override
    public void select(List<AbstractMember> population) {
        List<AbstractMember> bestNThMember = getNBestMembers(5, population);

        selection.select(population, POPULATION_SIZE);
        population.addAll(bestNThMember);
    }

    @Override
    public void crossOver(List<AbstractMember> population) {
        List<AbstractMember> crossoverPool = new ArrayList<>();

        for (AbstractMember member : population) {
            double rand = Number.random(0, 1);

            if (rand >= CROSSOVER_PROBABILITY) {
                crossoverPool.add(member);
            }
        }

        for (int i = 0; i < crossoverPool.size() - 1; i += 2) {
            Member parentA = (Member) crossoverPool.get(i);
            Member parentB = (Member) crossoverPool.get(i + 1);

            List<boolean[]> offspring = OnePointCrossover.crossover(parentA.getGene(), parentB.getGene());

            Member offA = new Member(offspring.get(0), parentA.getFunction(), MUTATION_PROBABILITY);
            Member offB = new Member(offspring.get(1), parentB.getFunction(), MUTATION_PROBABILITY);

            population.add(offA);
            population.add(offB);
        }

    }

    @Override
    public void mutate(List<AbstractMember> population) {
        for (AbstractMember member : population) {
            member.mutate();
        }
    }

    @Override
    public void evaluate(List<AbstractMember> population) {
        for (AbstractMember member : population) {
            member.calculateFitness();
        }
    }

    @Override
    public int getGenerations() {
        return GENERATIONS;
    }

    @Override
    public int getIterations() {
        return SAMPLE_SIZE;
    }

    @Override
    public List<AbstractMember> generatePopulation() {
        List<AbstractMember> population = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(new implementation.Member(function, MUTATION_PROBABILITY));
        }

        return population;
    }

    @Override
    public void showInfo() {
        System.out.println("---==== Genetic Algorithm Info ===---\n");

        System.out.printf("Crossover Method: %s\n", "One Point Crossover");
        System.out.printf("Mutation Method: %s\n", "Simple Mutation");
        System.out.printf("Selection Method: %s\n", "Tournament Selection");
        System.out.printf("Sample Size: %d\n", SAMPLE_SIZE);
        System.out.printf("Generations: %d\n", GENERATIONS);
        System.out.printf("Population: %d\n", POPULATION_SIZE);
        System.out.printf("Crossover Probability: %4.2f%%\n", Number.toPercent(CROSSOVER_PROBABILITY));
        System.out.printf("Mutation Probability: %4.2f%%\n", Number.toPercent(MUTATION_PROBABILITY));
        System.out.printf("Precision: %d\n", PRECISION);
        System.out.printf("Function Dimensions: %d\n", DIMENSIONS);

        System.out.println("");
    }

}
