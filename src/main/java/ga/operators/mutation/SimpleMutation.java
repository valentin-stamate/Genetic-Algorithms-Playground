package ga.operators.mutation;

import ga.util.Number;

public abstract class SimpleMutation {
    public static void mutate(boolean[] gene, double mutationProbability) {
        int n = gene.length;

        for (int i = 0; i < n; i++) {
            double rand = Number.random(0, 1);

            if (rand <= mutationProbability) {
                gene[i] = !gene[i];
            }
        }
    }
}
