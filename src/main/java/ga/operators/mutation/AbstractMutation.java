package ga.operators.mutation;

import ga.member.AbstractMember;

import java.util.List;

public interface AbstractMutation {
    void mutateMember(AbstractMember abstractMember, double mutationProbability);
    void mutatePopulation(List<AbstractMember> population, double mutationProbability);
    String getName();
}
