package ga.operators.mutation;

import ga.member.AbstractMember;

public interface AbstractMutation {
    void mutate(AbstractMember abstractMember);
    String getName();
}
