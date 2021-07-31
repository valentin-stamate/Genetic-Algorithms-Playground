package implementation;

import ga.member.AbstractMember;
import ga.operators.mutation.SimpleMutation;
import ga.util.Encoding;
import ga.util.Number;
import ga.util.Vector;

public class Member implements AbstractMember, Comparable<Member> {
    private final boolean[] gene;
    private final Function function;
    private final int geneLength;
    private final int pointSize;

    private final double mutationProbability;

    private double fitness = Double.MAX_VALUE;

    public Member(Function function, double mutationProbability) {
        this.mutationProbability = mutationProbability;
        this.function = function;

        double interval = function.max() - function.min();

        this.pointSize = Encoding.calculateBitMapNumberLength(interval, function.getPrecision());
        this.geneLength = pointSize * function.components();

        this.gene = new boolean[geneLength];

        for (int i = 0; i < geneLength; i++) {
            int rand = (int) Number.random(1, 100);

            if (rand % 2 == 1) {
                gene[i] = !gene[i];
            }
        }

    }

    public Member(boolean[] gene, Function function, double mutationProbability) {
        this.mutationProbability = mutationProbability;
        this.gene = gene;
        this.function = function;
        this.geneLength = gene.length;

        double interval = function.max() - function.min();

        this.pointSize = Encoding.calculateBitMapNumberLength(interval, function.getPrecision());
    }

    @Override
    public void mutate() {
        SimpleMutation.mutate(gene, mutationProbability);
    }

    /* CHANGE THIS IF YOU CHANGE THE PROBLEM */
    @Override
    public void calculateFitness() {
        double functionValue = getFunctionValue();

        this.fitness = Math.pow(10, 1 / (functionValue + 40) * 100);
    }

    public double getFunctionValue() {
        double[] X = Encoding.toDoubleVector(gene, function.min(), function.max(), function.getPrecision());
        return function.fun(X);
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void show() {
        Encoding.printBitmapVector(gene, pointSize);
        double[] values = Encoding.toDoubleVector(gene, function.min(), function.max(), function.getPrecision());
        Vector.print(values);

        System.out.println("");
    }

    public boolean[] getGene() {
        int n = gene.length;
        boolean[] b = new boolean[n];

        System.arraycopy(gene, 0, b, 0, n);

        return gene;
    }

    public Function getFunction() {
        return function;
    }

    @Override
    public int compareTo(Member member) {
        return Double.compare(fitness, member.fitness);

    }
}
