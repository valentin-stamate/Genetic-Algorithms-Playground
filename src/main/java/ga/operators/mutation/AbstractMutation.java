package ga.operators.mutation;

import ga.member.AbstractMember;
import java.util.List;

/** This interface provides a contract to the mutation operator. You can use your own implementation. */
public interface AbstractMutation {
    void mutateMember(AbstractMember abstractMember, double mutationProbability);
    void mutatePopulation(List<AbstractMember> population, double mutationProbability);
    String getName();
}
