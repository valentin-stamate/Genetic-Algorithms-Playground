package ga;

import ga.member.AbstractMember;

import java.util.List;

public interface AbstractGeneticAlgorithm {
    void select(List<AbstractMember> population);
    void mutate(List<AbstractMember> population);
    void crossOver(List<AbstractMember> population);
    void evaluate(List<AbstractMember> population);

    void start();
     AbstractMember oneSimulation();

    int getGenerations();
    int getIterations();

    List<AbstractMember> generatePopulation();

    void showInfo();
}
