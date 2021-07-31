package ga.member;

/* YOU CAN EXTEND YOUR OWN MEMBER TO FIT YOUR DESIRED ALGORITHM */
public interface AbstractMember {
    void mutate();
    void calculateFitness();
    double getFitness();

    void show();
}
