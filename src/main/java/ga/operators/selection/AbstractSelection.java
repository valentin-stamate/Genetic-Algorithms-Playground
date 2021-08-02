package ga.operators.selection;

import ga.member.AbstractMember;

import java.util.List;

public interface AbstractSelection {
    void selectPopulation(List<AbstractMember> population, int initialPopulation);
    String getName();
}
