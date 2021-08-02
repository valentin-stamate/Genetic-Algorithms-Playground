package implementation;

import ga.AbstractGeneticAlgorithm;
import ga.config.GaConfig;
import ga.member.AbstractMember;
import ga.operators.mutation.AbstractMutation;
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
    public List<AbstractMember> generatePopulation() {
        List<AbstractMember> population = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            population.add(new implementation.Member(function, mutationProbability));
        }

        return population;
    }

    @Override
    public List<AbstractMember> getAbstractMembersFromGene(List<short[]> offspringGene, GaConfig gaConfig, AbstractMutation abstractMutation) {
        List<AbstractMember> offspring = new ArrayList<>();

        for (short[] gene : offspringGene) {
            offspring.add(new Member(gene, function, mutationProbability));
        }

        return offspring;
    }

}
