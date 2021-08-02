package implementation;

import ga.AbstractGeneticAlgorithm;
import ga.config.GaConfig;
import ga.member.AbstractMember;
import ga.operators.crossover.AbstractCrossover;
import ga.operators.mutation.AbstractMutation;
import ga.operators.selection.AbstractSelection;
import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm extends AbstractGeneticAlgorithm {
    private final Function function;

    public GeneticAlgorithm(Function function, GaConfig gaConfig, AbstractMutation abstractMutation, AbstractCrossover abstractCrossover, AbstractSelection abstractSelection) {
        super(gaConfig, abstractMutation, abstractCrossover, abstractSelection);

        this.function = function;
    }

    @Override
    public void selectPopulation(List<AbstractMember> population) {
        List<AbstractMember> bestNThMembers = getNBestMembers(5, population);

        super.selectPopulation(population);

        population.addAll(bestNThMembers);
    }

    @Override
    public List<AbstractMember> generatePopulation() {
        List<AbstractMember> population = new ArrayList<>();

        for (int i = 0; i < super.populationSize; i++) {
            population.add(new Member(function, gaConfig.geneLength));
        }

        return population;
    }

    @Override
    public List<AbstractMember> getAbstractMembersFromGene(List<short[]> offspringGene, GaConfig gaConfig, AbstractMutation abstractMutation) {
        List<AbstractMember> offspring = new ArrayList<>();

        for (short[] gene : offspringGene) {
            offspring.add(new Member(function, gene));
        }

        return offspring;
    }
}
