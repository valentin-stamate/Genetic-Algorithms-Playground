package ga.member;

/* YOU CAN EXTEND YOUR OWN MEMBER TO FIT YOUR DESIRED ALGORITHM */
public abstract class AbstractMember implements Comparable<AbstractMember> {
    public double score;
    public double fitness;

    public short[] gene;

    public abstract void mutate();
    public abstract double calculateFitness();
    public abstract double calculateScore();

    public abstract void show();

    public double getScore() {
        return score;
    }
    public double getFitness() {
        return fitness;
    }

    public short[] getGeneReference() {
        return gene;
    }

    public short[] getGeneCopy() {
        int n = gene.length;
        short[] b = new short[n];

        System.arraycopy(gene, 0, b, 0, n);

        return b;
    }

    @Override
    public int compareTo(AbstractMember member) {
        return Double.compare(fitness, member.fitness);
    }
}
