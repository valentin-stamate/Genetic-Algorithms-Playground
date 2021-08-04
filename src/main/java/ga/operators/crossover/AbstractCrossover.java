package ga.operators.crossover;

import ga.member.AbstractMember;
import java.util.List;
/** This interface provides a contract to the crossover operator. You can use your own implementation. */
public interface AbstractCrossover {
    List<short[]> crossoverParents(AbstractMember parentA, AbstractMember parentB);
    String getName();
}
