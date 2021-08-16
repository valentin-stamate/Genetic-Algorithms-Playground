package ga.lambda;

import ga.member.AbstractMember;
import java.util.List;

@FunctionalInterface
public interface BeforeEvaluationEvent {
    /** Provides as a parameter only a copy reference of the population, not the members itself. */
    void fun(List<AbstractMember> copyPopulationReference);
}
