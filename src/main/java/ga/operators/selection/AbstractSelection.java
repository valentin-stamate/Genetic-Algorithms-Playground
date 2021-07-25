package ga.operators.selection;

import ga.member.AbstractMember;

import java.util.List;

public interface AbstractSelection {
    List<AbstractMember> select(List<AbstractMember> population);
}
