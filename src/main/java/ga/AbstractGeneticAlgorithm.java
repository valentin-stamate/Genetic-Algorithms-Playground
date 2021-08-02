package ga;

import ga.config.GaConfig;
import ga.member.AbstractMember;
import ga.operators.crossover.AbstractCrossover;
import ga.operators.lambda.PopulationObserver;
import ga.operators.lambda.observers.OnNewGeneration;
import ga.operators.lambda.observers.OnNewIteration;
import ga.operators.mutation.AbstractMutation;
import ga.operators.selection.AbstractSelection;
import ga.util.Number;
import ga.util.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractGeneticAlgorithm {
    protected final int populationSize;
    protected final int sampleSize;
    protected final int generations;
    protected final double crossoverProbability;
    protected final double mutationProbability;
    protected final double geneLength;

    protected final GaConfig gaConfig;

    private final AbstractCrossover abstractCrossover;
    protected final AbstractMutation abstractMutation;
    private final AbstractSelection abstractSelection;

    private final List<PopulationObserver> populationObserverList;

    public AbstractGeneticAlgorithm(GaConfig gaConfig, AbstractMutation abstractMutation,
                                       AbstractCrossover abstractCrossover, AbstractSelection abstractSelection) {

        this.abstractCrossover = abstractCrossover;
        this.abstractMutation = abstractMutation;
        this.abstractSelection = abstractSelection;

        this.populationSize = gaConfig.populationSize;
        this.sampleSize = gaConfig.sampleSize;
        this.generations = gaConfig.generations;
        this.crossoverProbability = gaConfig.crossoverProbability;
        this.mutationProbability = gaConfig.mutationProbability;
        this.geneLength = gaConfig.geneLength;

        this.gaConfig = gaConfig;

        this.populationObserverList = new ArrayList<>();
    }

    public void start() {
        double bestFitness = 0;
        double bestScore = Double.MAX_VALUE;

        long timeA = Time.getCurrentTime();

        for (int i = 1; i <= sampleSize; i++) {
            AbstractMember bestMember = oneSimulation();

            bestFitness = Math.max(bestFitness, bestMember.getFitness());
            bestScore = Math.min(bestScore, bestMember.getScore());

            onPopulationNewIteration(bestMember, i);
        }

        System.out.println("\nDone\n");

        long timeB = Time.getCurrentTime();
        String time = Time.toTime(timeB - timeA);

        System.out.printf("Time: %s \n", time);
        System.out.printf("Best Overall: %6.3f | Score: %6.3f\n", bestFitness, bestScore);
    }

    AbstractMember oneSimulation() {
        List<AbstractMember> population = generatePopulation();

        evaluatePopulation(population);
        for (int i = 1; i <= generations; i++) {
            selectPopulation(population);
            mutatePopulation(population);
            crossoverPopulation(population);
            evaluatePopulation(population);

            onPopulationNewGeneration(population, i);
        }

        population.sort(Collections.reverseOrder());

        return population.get(0);
    }

    /* OPERATORS & EVALUATION */
    public void selectPopulation(List<AbstractMember> population) {
        abstractSelection.selectPopulation(population, populationSize);
    }

    public void mutatePopulation(List<AbstractMember> population) {
        abstractMutation.mutatePopulation(population, mutationProbability);
    }

    public void crossoverPopulation(List<AbstractMember> population) {
        List<AbstractMember> crossoverPool = new ArrayList<>();

        for (AbstractMember member : population) {
            double rand = Number.random(0, 1);

            if (rand >= crossoverProbability) {
                crossoverPool.add(member);
            }
        }

        for (int i = 0; i < crossoverPool.size() - 1; i += 2) {
            AbstractMember parentA = crossoverPool.get(i);
            AbstractMember parentB = crossoverPool.get(i + 1);

            List<short[]> offspringGene = abstractCrossover.crossoverParents(parentA, parentB);

            List<AbstractMember> offspring = getAbstractMembersFromGene(offspringGene, gaConfig, abstractMutation);

            population.addAll(offspring);
        }
    }

    public void evaluatePopulation(List<AbstractMember> population) {
        for (AbstractMember abstractMember : population) {
            abstractMember.calculateFitness();
        }
    }

    /* OBSERVERS */
    public void addPopulationObserver(PopulationObserver populationObserver) {
        populationObserverList.add(populationObserver);
    }

    private void onPopulationNewGeneration(List<AbstractMember> population, int generation) {
        if (!populationObserverList.isEmpty()) {
            List<AbstractMember> populationCopy = getPopulationCopy(population);

            for (PopulationObserver populationObserver : populationObserverList) {
                if (populationObserver instanceof OnNewGeneration) {
                    ((OnNewGeneration) populationObserver).fun(populationCopy, generation);
                }
            }
        }
    }

    private void onPopulationNewIteration(AbstractMember abstractMember, int iteration) {
        if (!populationObserverList.isEmpty()) {
            AbstractMember copyMember = abstractMember.getCopy();

            for (PopulationObserver populationObserver : populationObserverList) {
                if (populationObserver instanceof OnNewIteration) {
                    ((OnNewIteration) populationObserver).fun(copyMember, iteration);
                }
            }
        }
    }

    /* METHODS SPECIFIC TO THE PROBLEM */
    public abstract List<AbstractMember> generatePopulation();
    public abstract List<AbstractMember> getAbstractMembersFromGene(List<short[]> offspringGene, GaConfig gaConfig, AbstractMutation abstractMutation);

    /* UTILITY METHODS */
    public void showInfo() {
        System.out.println("---==== Genetic Algorithm Info ===---\n");

        System.out.printf("Crossover Method: %s\n", abstractCrossover.getName());
        System.out.printf("Mutation Method: %s\n", abstractMutation.getName());
        System.out.printf("Selection Method: %s\n", abstractSelection.getName());
        System.out.printf("Sample Size: %d\n", sampleSize);
        System.out.printf("Generations: %d\n", generations);
        System.out.printf("Population: %d\n", populationSize);
        System.out.printf("Crossover Probability: %4.2f%%\n", Number.toPercent(crossoverProbability));
        System.out.printf("Mutation Probability: %4.2f%%\n", Number.toPercent(mutationProbability));
    }

    private void showGenerationStats(int generationCount, List<AbstractMember> population) {
        List<AbstractMember> copyPopulation = new ArrayList<>(population);
        copyPopulation.sort(Collections.reverseOrder());

        AbstractMember best = copyPopulation.get(0);
        AbstractMember worst = copyPopulation.get(copyPopulation.size() - 1);

        System.out.printf("Generation %d \n Fit Best: %6.3f | Fit Worst: %6.3f | Fun Best: %6.3f | Fun Worst: %6.3f \n\n",
                generationCount, best.getFitness(), worst.getFitness(), best.getScore(), worst.getScore());

        showPopulation(population);

    }

    private void showPopulation(List<AbstractMember> population) {
        for (AbstractMember abstractMember : population) {
            abstractMember.show();
        }
    }

    public static List<AbstractMember> getPopulationCopy(List<AbstractMember> population) {
        List<AbstractMember> populationCopy = new ArrayList<>();

        for (AbstractMember abstractMember : population) {
            populationCopy.add(abstractMember.getCopy());
        }

        return populationCopy;
    }

    protected static List<AbstractMember> getNBestMembers(int n, List<AbstractMember> population) {
        List<AbstractMember> copyPopulation = new ArrayList<>(population);
        Collections.reverse(copyPopulation);

        List<AbstractMember> bestMembers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            bestMembers.add(copyPopulation.get(i));
        }

        return bestMembers;
    }

    private static AbstractMember getBestMember(List<AbstractMember> population) {
        List<AbstractMember> copyPopulation = new ArrayList<>(population);
        copyPopulation.sort(Collections.reverseOrder());

        return copyPopulation.get(0);
    }

}
