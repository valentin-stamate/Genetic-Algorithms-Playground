package ga.operators.lambda.observers;

import ga.member.AbstractMember;
import ga.operators.lambda.PopulationObserver;

import java.util.List;

@FunctionalInterface
public interface OnNewGeneration extends PopulationObserver {
    void fun(List<AbstractMember> copyPopulation, int generation);
}
