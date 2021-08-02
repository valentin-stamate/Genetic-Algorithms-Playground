package ga.operators.crossover;

import ga.member.AbstractMember;
import java.util.List;

public interface AbstractCrossover {
    List<short[]> crossoverParents(AbstractMember parentA, AbstractMember parentB);
    String getName();
}
