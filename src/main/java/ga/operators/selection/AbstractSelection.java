package ga.operators.selection;

import ga.member.AbstractMember;
import java.util.List;

/** This interface provides a contract to the selection operator. You can use your own implementation. */
public interface AbstractSelection {
    void selectPopulation(List<AbstractMember> population, int initialPopulation);
    String getName();
}
