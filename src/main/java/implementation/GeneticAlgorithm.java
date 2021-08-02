package implementation;

import ga.AbstractGeneticAlgorithm;
import ga.member.AbstractMember;
import ga.operators.crossover.OnePointCrossover;
import ga.util.Number;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm extends AbstractGeneticAlgorithm {

    private final Function function;
    private static final int PRECISION = 5;
    private static final int DIMENSIONS = 5;

    public GeneticAlgorithm() {
        this.function = new Function(DIMENSIONS, PRECISION);
    }

    @Override
    public void crossOver(List<AbstractMember> population) {
        List<AbstractMember> crossoverPool = new ArrayList<>();

        for (AbstractMember member : population) {
            double rand = Number.random(0, 1);

            if (rand >= crossoverProbability) {
                crossoverPool.add(member);
            }
        }

        for (int i = 0; i < crossoverPool.size() - 1; i += 2) {
            Member parentA = (Member) crossoverPool.get(i);
            Member parentB = (Member) crossoverPool.get(i + 1);

            List<boolean[]> offspring = OnePointCrossover.crossover(parentA.getGene(), parentB.getGene());

            Member offA = new Member(offspring.get(0), parentA.getFunction(), mutationProbability);
            Member offB = new Member(offspring.get(1), parentB.getFunction(), mutationProbability);

            population.add(offA);
            population.add(offB);
        }

    }

    @Override
    public List<AbstractMember> generatePopulation() {
        List<AbstractMember> population = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            population.add(new implementation.Member(function, mutationProbability));
        }

        return population;
    }


}
