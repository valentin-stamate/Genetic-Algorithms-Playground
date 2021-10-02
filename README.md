# Genetic Algorithms Playground

## Introduction

This is a mini library for developing genetic algorithms.

## Documentation

In order to solve a problem with a genetic algorithm, `AbstractGeneticAlgorithm` and `AbstractMember` 
should be extended because the library provides a general way of solving an optimization problem.

For example not every problem have the same method of calculating the fitness value, this is why
`AbstractMember` doesn't implement it, as well as other methods.

Also, specific operators can be used by overriding the abstract ones that are given as parameter
when a `AbstractGeneticAlgorithm` variable is instantiated. For every abstract operator, a default 
one is already implemented: `OnePointCrossover`, `SimpleMutation`, `TournamentSelection`.

The parameters such as `population`, `mutation probability` can be easily set by instantiating a `GaConfig`
variable. Note that, before creating this instance also the gene length should be calculated first.
Depending on the problem, this can have different lengths.

In the example given, the gene is calculated this way: `geneLength = dimension * pointLenght`
where `pointLenght` is the bitmap length to represent a point in a given range with a given precision.
All the points makes the coordinates of the point given to the function: `Rastrigin's Function`.

For debugging, there are two `observers`: one for every iteration and the other one for every generation. 
As parameters a copy of the population/member is given.

To test this library, [Rastrigin's Function 6](http://www.geatbx.com/ver_3_3/fcnfun6.html) was used to
find the global minimum. The results are shown in the release section.

To use this library, download the .jar file provided in the release section.

## Resources
 * [Teacher Website](https://profs.info.uaic.ro/~eugennc/teaching/ga)
 * [Homework Repo](https://github.com/StamateValentin/Genetic-Algorithms-Homework)
 * [Snake Project Commit](https://github.com/StamateValentin/Conquering-The-Snake/blob/950a11d9ab2d415780ff4fbf5542e3dba0f702d2)
 * [Gray Code](https://www.geeksforgeeks.org/decimal-equivalent-gray-code-inverse)
 * [Random In Java](https://stackoverflow.com/a/3680648/10805602)
 * [Crossover Types](https://www.ripublication.com/ijcir17/ijcirv13n7_15.pdf)
 * [Numerical Representation](http://webpages.iust.ac.ir/yaghini/Courses/AOR_872/Genetic%20Algorithms_03.pdf)
 * [Numerical Optimization](http://umsl.edu/cmpsci/about/People/Faculty/CezaryJanikow/folder%20two/Numerical.pdf)

 The pdf resources are also available in `resources` folder.