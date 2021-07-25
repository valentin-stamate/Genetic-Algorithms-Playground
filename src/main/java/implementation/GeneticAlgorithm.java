package implementation;

import ga.AbstractGeneticAlgorithm;
import ga.member.AbstractMember;
import ga.operators.selection.AbstractSelection;
import ga.operators.selection.TournamentSelection;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm implements AbstractGeneticAlgorithm {
    private List<AbstractMember> population;
    private final AbstractSelection selection;

    public GeneticAlgorithm() {
        this.population = new ArrayList<>();
        this.selection = new TournamentSelection();
    }

    @Override
    public void select() {
        population = selection.select(population);
    }
}
