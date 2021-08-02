package ga.operators.lambda.observers;

import ga.member.AbstractMember;
import ga.operators.lambda.PopulationObserver;

@FunctionalInterface
public interface OnNewIteration extends PopulationObserver {
    void fun(AbstractMember copyMember, int iteration);
}
