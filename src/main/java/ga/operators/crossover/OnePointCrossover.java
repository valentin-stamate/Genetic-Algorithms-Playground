package ga.operators.crossover;

import ga.util.Number;

import java.util.ArrayList;
import java.util.List;

public class OnePointCrossover {

    public static List<boolean[]> crossover(boolean[] geneA, boolean[] geneB) {
        int n = geneA.length;

        boolean[] offspringA = new boolean[n];
        boolean[] offspringB = new boolean[n];

        List<boolean[]> offspring = new ArrayList<>();

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

}
