package implementation;

import ga.member.AbstractMember;
import ga.operators.mutation.SimpleMutation;
import ga.util.Encoding;
import ga.util.Number;
import ga.util.Vector;

public class Member extends AbstractMember {
    private final Function function;
    private final int geneLength;
    private final int pointSize;

    private final double mutationProbability;

    public Member(Function function, double mutationProbability) {
        this.mutationProbability = mutationProbability;
        this.function = function;

        double interval = function.max() - function.min();

        this.pointSize = Encoding.calculateBitMapNumberLength(interval, function.getPrecision());
        this.geneLength = pointSize * function.components();

        this.gene = new short[geneLength];

        for (int i = 0; i < geneLength; i++) {
            int rand = (int) Number.random(1, 100);

            if (rand % 2 == 1) {
                gene[i] = 1;
            }
        }

    }

    public Member(short[] gene, Function function, double mutationProbability) {
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
    public double calculateFitness() {
        double functionValue = calculateScore();

        this.fitness = Math.pow(10, 1 / (functionValue + 40) * 100);

        return this.fitness;
    }

    @Override
    public double calculateScore() {
        double[] X = Encoding.toDoubleVector(gene, function.min(), function.max(), function.getPrecision());
        this.score = function.fun(X);
        return this.score;
    }

    @Override
    public void show() {
        Encoding.printBitmapVector(gene, pointSize);
        double[] values = Encoding.toDoubleVector(gene, function.min(), function.max(), function.getPrecision());
        Vector.print(values);

        System.out.println("");
    }

    public Function getFunction() {
        return function;
    }

}
