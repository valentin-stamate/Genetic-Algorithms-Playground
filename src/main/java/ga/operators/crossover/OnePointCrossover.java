package ga.operators.crossover;

import ga.member.AbstractMember;
import ga.util.Number;

import java.util.ArrayList;
import java.util.List;

public class OnePointCrossover implements AbstractCrossover{

    public List<short[]> crossover(AbstractMember parentA, AbstractMember parentB) {
        short[] geneA = parentA.getGeneCopy();
        short[] geneB = parentB.getGeneCopy();

        int n = geneA.length;

        short[] offspringA = new short[n];
        short[] offspringB = new short[n];

        List<short[]> offspring = new ArrayList<>();

        int cut = (int) Number.random(1, n - 0.01); // (0, n)

        for (int i = 0; i < n; i++) {
            if (i < cut) {
                offspringA[i] = geneA[i];
                offspringB[i] = geneB[i];
                continue;
            }

            offspringA[i] = geneB[i];
            offspringB[i] = geneA[i];
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
