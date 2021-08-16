package ga.lambda.observers;

import ga.member.AbstractMember;
import ga.lambda.PopulationObserver;

import java.util.List;

@FunctionalInterface
public interface OnNewGeneration extends PopulationObserver {
    void fun(List<AbstractMember> copyPopulation, int generation);
}
