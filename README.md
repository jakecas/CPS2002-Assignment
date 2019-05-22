# CPS2002-Assignment
Owned by Jake Cassar and Pierre Zahra. 
A multiplayer treasure game for CPS2002 Software Engineering Assignment.

## Feature Branches

### Jenkins
This branch was used to set up the project and test the connection to jenkins, which polls this repository every 5 minutes. 
The link below leads to the project on jenkins.

https://jenkins-ict.research.um.edu.mt/job/CPS2002_PJs_2.0/

### HTML Prototype
This branch was used to create a dummy HTML file along with the necessary CSS to model the structure of the map.

### Initial Class Design
This branch was used to create classes and empty methods along with failing tests. 
Afterwards, logic was added to each method such that the tests succeeded were possible.

### HTML Generation
This branch was used to write the code that would create an HTML file for each player.

### Algorithm
This branch was used to create the basic game loop and logic for the user to play. 
Moreover, previously failing tests were now a success due to the added logic.

### Bugfix assignment.objects.maps.Map Generation
This branch was created to solve an issue with the map as each player had the same map object. 
This was resolved by generating a seed.

### Coverage
This branch was created to add more tests to increase coverage.

### Release
This branch is the release branch for when this part of the assignment was finished. 
Some final changes were made that had been forgotten or missed, such as exception constructors and tests.

## A note on coverage
It was noticed that the university jenkins was duplicating tests, and the duplicates were failing.
Thus, the graph for coverage froze on the last successful build and does not display the correct coverage report.
The IntelliJ IDEA coverage report was used to obtain the following percentages.

Element | Class | Method    | Line 
---     | ---   | ---       | --- 
Total   | 90%   | 94%       | 86%

The class which could not be covered was the HTMLGeneration exception, which only occurs when an IOException is thrown and thus could not be triggered during tests.
The methods are the cconstructor of this exception, and the assignment.main method which would start the game if used.
The lines not covered are thus for the same reasons.

## Features for part 3 of the assignment

### Task 1: Map Factory
This branch was created to fulfill the requirements for task 1 using the factory design pattern.
