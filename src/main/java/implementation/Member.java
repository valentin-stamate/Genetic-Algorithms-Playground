package implementation;

import ga.conversion.RangeDoubleToInterval;
import ga.member.AbstractMember;

import java.util.Arrays;

public class Member extends AbstractMember {
    private final Function function;

    public Member(Function function, int geneLength) {
        super(geneLength);
        this.function = function;
    }

    public Member(Function function, short[] gene) {
        super(Arrays.copyOf(gene, gene.length));
        this.function = function;
    }

    @Override
    public void calculateFitness() {
        calculateScore();
        double functionValue = getScore();

        super.fitness = Math.pow(10, 1 / (functionValue + 40) * 100);
    }

    @Override
    public void calculateScore() {
        double[] X = RangeDoubleToInterval.toDoubleVector(gene, function.min(), function.max(), function.getPrecision());
        super.score =  function.fun(X);
    }

    @Override
    public AbstractMember getCopy() {
        Member copyMember = new Member(function, gene);

        copyMember.fitness = fitness;
        copyMember.score = score;

        return copyMember;
    }
}
