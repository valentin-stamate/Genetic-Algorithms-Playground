package ga;

import ga.config.GaConfig;
import ga.member.AbstractMember;
import ga.operators.crossover.AbstractCrossover;
import ga.operators.crossover.OnePointCrossover;
import ga.operators.mutation.AbstractMutation;
import ga.operators.selection.AbstractSelection;
import ga.operators.selection.TournamentSelection;
import ga.util.Number;
import ga.util.Time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractGeneticAlgorithm {
    protected final int populationSize = 150;
    protected final int sampleSize = 30;
    protected final int generations = 4000;
    protected final double crossoverProbability = 0.2;
    protected final double mutationProbability = 0.007;

    /* Operators */
    public final AbstractSelection abstractSelection = new TournamentSelection();
    public final AbstractCrossover abstractCrossover = new OnePointCrossover();

    /* TODO: dsakdj */
    protected final double geneLength = -1;

    public void select(List<AbstractMember> population) {
        List<AbstractMember> bestNThMember = getNBestMembers(5, population);

        abstractSelection.select(population, populationSize);
        population.addAll(bestNThMember);
    }

    public void mutate(List<AbstractMember> population) {
        for (AbstractMember member : population) {
            member.mutate();
        }
    }

    public void evaluate(List<AbstractMember> population) {
        for (AbstractMember member : population) {
            member.calculateFitness();
        }
    }

    public void start() {
        double bestFitness = 0;
        double bestScore = Double.MAX_VALUE;

        long timeA = Time.getCurrentTime();

        for (int i = 1; i <= sampleSize; i++) {

            AbstractMember bestMember = oneSimulation();

            System.out.printf("Iteration %2d : Best Fitness %6.3f : Function Value: %6.3f\n",
                    i, bestMember.getFitness(), bestMember.getScore());

            bestFitness = Math.max(bestFitness, bestMember.getFitness());
            bestScore = Math.min(bestScore, bestMember.getScore());
        }

        System.out.println("\nDone\n");

        long timeB = Time.getCurrentTime();
        String time = Time.toTime(timeB - timeA);

        System.out.printf("Time: %s \n", time);
        System.out.printf("Best Overall: %6.3f | Function Value: %6.3f\n", bestFitness, bestScore);
    }

    public AbstractMember oneSimulation() {
        List<AbstractMember> population = generatePopulation();

        evaluate(population);

        for (int i = 1; i <= generations; i++) {
//            showGenerationStats(i, population);

            select(population);
            mutate(population);
            crossOver(population);
            evaluate(population);
        }

        population.sort(Collections.reverseOrder());

        return population.get(0);
    }

    public abstract List<AbstractMember> generatePopulation();

    public void showInfo() {
        System.out.println("---==== Genetic Algorithm Info ===---\n");

        System.out.printf("Crossover Method: %s\n", "One Point Crossover");
        System.out.printf("Mutation Method: %s\n", "Simple Mutation");
        System.out.printf("Selection Method: %s\n", "Tournament Selection");
        System.out.printf("Sample Size: %d\n", sampleSize);
        System.out.printf("Generations: %d\n", generations);
        System.out.printf("Population: %d\n", populationSize);
        System.out.printf("Crossover Probability: %4.2f%%\n", Number.toPercent(crossoverProbability));
        System.out.printf("Mutation Probability: %4.2f%%\n", Number.toPercent(mutationProbability));

        System.out.println("");
    }

    public List<AbstractMember> getNBestMembers(int n, List<AbstractMember> population) {
        List<AbstractMember> copyPopulation = new ArrayList<>(population);
        Collections.reverse(copyPopulation);

        List<AbstractMember> bestMembers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            bestMembers.add(copyPopulation.get(i));
        }

        return bestMembers;
    }

    private void showGenerationStats(int generationCount, List<AbstractMember> population) {
        List<AbstractMember> copyPopulation = new ArrayList<>(population);
        copyPopulation.sort(Collections.reverseOrder());

        AbstractMember best = copyPopulation.get(0);
        AbstractMember worst = copyPopulation.get(copyPopulation.size() - 1);

        System.out.printf("Generation %d \n Fit Best: %6.3f | Fit Worst: %6.3f | Fun Best: %6.3f | Fun Worst: %6.3f \n\n",
                generationCount, best.getFitness(), worst.getFitness(), best.getScore(), worst.getScore());

//        System.out.println("Population:");
//        for (Member m : population) {
//            m.show();
//        }

    }

    public void crossOver(List<AbstractMember> population) {
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

            List<short[]> offspringGene = abstractCrossover.crossover(parentA, parentB);

            /* TODO: csadcad */
            List<AbstractMember> offspring = getAbstractMembersFromGene(offspringGene, null, null);

            population.addAll(offspring);
        }

    }

    public abstract List<AbstractMember> getAbstractMembersFromGene(List<short[]> offspringGene, GaConfig gaConfig, AbstractMutation abstractMutation);
}
