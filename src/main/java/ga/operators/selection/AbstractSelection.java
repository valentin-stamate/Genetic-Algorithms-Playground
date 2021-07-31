package ga.operators.selection;

import ga.member.AbstractMember;

import java.util.List;

public interface AbstractSelection {
    void select(List<AbstractMember> population, int initialPopulation);
}
