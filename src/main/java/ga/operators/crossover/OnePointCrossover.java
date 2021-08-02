package ga.operators.crossover;

import ga.member.AbstractMember;
import ga.util.Number;
import java.util.ArrayList;
import java.util.List;

public class OnePointCrossover implements AbstractCrossover {

    @Override
    public List<short[]> crossoverParents(AbstractMember parentA, AbstractMember parentB) {
        short[] parentGeneA = parentA.getGene();
        short[] parentGeneB = parentB.getGene();

        int n = parentGeneA.length;

        short[] offspringA = new short[n];
        short[] offspringB = new short[n];

        List<short[]> offspring = new ArrayList<>();

        int cut = (int) Number.random(1, n - 0.0001); // (0, n)

        for (int i = 0; i < n; i++) {
            if (i < cut) {
                offspringA[i] = parentGeneA[i];
                offspringB[i] = parentGeneB[i];
                continue;
            }

            offspringA[i] = parentGeneB[i];
            offspringB[i] = parentGeneA[i];
        }

        offspring.add(offspringA);
        offspring.add(offspringB);

        return offspring;
    }

    @Override
    public String getName() {
        return "One Point Crossover";
    }

}
