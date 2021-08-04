package ga.member;

import ga.util.Number;

import java.util.Arrays;

/** Abstract member. In order to use it, extend it with your own gene interpretation as well as the fitness value
 * and other methods provided. */
public abstract class AbstractMember implements Comparable<AbstractMember> {
    /** The gene could be represented as binary(e.g. minimum of a function) or numeric(e.g. TSP problem). */
    protected final short[] gene;

    /*  FITNESS AND SCORE */
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

    public short[] getGeneCopy() {
        return Arrays.copyOf(gene, gene.length);
    }

    /* INITIALIZATION */
    /** Generates a random binary gene. If the desired representation is numeric, it can be overridden.  */
    public static short[] generateRandomGene(int length) {
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
    /** Shows few member stats. */
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
    /** Compares two members by their fitness value. */
    @Override
    public int compareTo(AbstractMember abstractMember) {
        return Double.compare(fitness, abstractMember.fitness);
    }
}
