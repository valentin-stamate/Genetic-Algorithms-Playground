package ga.operators.mutation;

import ga.member.AbstractMember;
import ga.util.Number;

import java.util.List;

public class SimpleMutation implements AbstractMutation {

    @Override
    public void mutateMember(AbstractMember abstractMember, double mutationProbability) {
        short[] geneReference = abstractMember.getGeneReference();

        int n = geneReference.length;

        for (int i = 0; i < n; i++) {
            double rand = Number.random(0, 1);

            if (rand <= mutationProbability) {
                geneReference[i] = (short) (1 - geneReference[i]);
            }
        }
    }

    @Override
    public void mutatePopulation(List<AbstractMember> population, double mutationProbability) {
        for (AbstractMember abstractMember : population) {
            mutateMember(abstractMember, mutationProbability);
        }
    }

    @Override
    public String getName() {
        return "Simple Mutation";
    }

}
