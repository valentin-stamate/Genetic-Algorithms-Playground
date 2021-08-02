package ga.member;

import ga.util.Number;

import java.util.Arrays;

/* YOU CAN EXTEND YOUR OWN MEMBER TO FIT YOUR DESIRED ALGORITHM */
public abstract class AbstractMember implements Comparable<AbstractMember> {

    protected final short[] gene;

    /*  PARAMETERS */
    protected double fitness;
    protected double score;

    public AbstractMember(int geneLength) {
        this.gene = generateRandomGene(geneLength);
    }

    public AbstractMember(short[] gene) {
        this.gene = gene;
    }

    /* ABSTRACTIONS */
    public abstract void calculateFitness();
    public abstract void calculateScore();
    public abstract AbstractMember getCopy();

    /* GETTERS AND SETTERS */
    public double getFitness() {
        return fitness;
    }

    public double getScore() {
        return score;
    }

    public short[] getGeneReference() {
        return gene;
    }

    public short[] getGene() {
        return Arrays.copyOf(gene, gene.length);
    }

    /* INITIALIZATION */
    private static short[] generateRandomGene(int length) {
        short[] gene = new short[length];

        int n = gene.length;

        for (int i = 0; i < n; i++) {
            int rand = (int) Number.random(1, 100);

            if (rand % 2 == 1) {
                gene[i] = 1;
            }
        }

        return gene;
    }

    /* UTILITY METHODS */
    public void show() {
        System.out.println("---=== Member ===---:");

        System.out.println("");
        for (short locus : gene) {
            System.out.printf("%d", locus);
        }

        System.out.println("");

        System.out.printf("Fitness: %6.3f\n", fitness);
        System.out.printf("Score: %6.3f\n", score);

        System.out.println("");
    }

    /* COMPARATOR */
    @Override
    public int compareTo(AbstractMember abstractMember) {
        return Double.compare(fitness, abstractMember.fitness);
    }
}
