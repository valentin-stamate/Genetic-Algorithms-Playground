package ga.operators.selection;

import ga.member.AbstractMember;
import ga.util.Number;

import java.util.ArrayList;
import java.util.List;

public class TournamentSelection implements AbstractSelection {
    @Override
    public void select(List<AbstractMember> population, int initialPopulation) {
        double fitnessSum = 0;

        for (AbstractMember member : population) {
            fitnessSum += member.getFitness();
        }

        int n = population.size();

        double[] probabilities = new double[n + 1];

        probabilities[0] = 0;

        for (int i = 1; i <= n; i++) {
            double memberFitness = population.get(i - 1).getFitness();

            probabilities[i] = probabilities[i - 1] + memberFitness / fitnessSum;
        }

        List<AbstractMember> newMemberList = new ArrayList<>();

        for (int i = 0; i < initialPopulation; i++) {
            double rand = Number.random(0, 1 - 0.00001);

            int s;
            for (s = 0; s < n; s++) {
                if (probabilities[s] < rand && rand <= probabilities[s + 1]) {
                    break;
                }
            }

            newMemberList.add(population.get(s));
        }

        population.clear();
        population.addAll(newMemberList);
    }
}
