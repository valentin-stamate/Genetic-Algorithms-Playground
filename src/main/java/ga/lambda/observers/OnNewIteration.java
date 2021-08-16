package ga.lambda.observers;

import ga.member.AbstractMember;
import ga.lambda.PopulationObserver;

@FunctionalInterface
public interface OnNewIteration extends PopulationObserver {
    void fun(AbstractMember copyMember, int iteration);
}
