# Project Estimation  

Authors: Aurelio Cirella, Behnam Lotfi, Federica Giorgione, Lorenzo Cardone

Date: 28/06/2020

Version: 2

# Contents



- [Estimate by product decomposition]
- [Estimate by activity decomposition ]



# Estimation approach

<Consider the EZGas  project as described in YOUR requirement document, assume that you are going to develop the project INDEPENDENT of the deadlines of the course>

# Estimate by product decomposition



### 
*In this estimation, Spring framework usage is taken in consideration*
|             | Estimate                        |             
| ----------- | ------------------------------- |  
| NC =  Estimated number of classes to be developed   | 20                           |             
| A = Estimated average size per class, in LOC       | 150                        | 
| S = Estimated size of project, in LOC (= NC * A) | 3000 |
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  | 300                                     |   
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 9000 | 
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 1.875                   |               


# Estimate by activity decomposition



### 

|         Activity name    | Estimated effort (person hours)   |             
| ----------- | ------------------------------- | 
|Requirements | |
|&emsp; Define functional requirements | 30 |
|&emsp; Define non functional requirements | 16 |
|&emsp; Define system interfaces | 20 |
|&emsp; GUI prototype definition | 14 |
|Software Design | |
|&emsp; Architecture definition | 14 |
|&emsp; Design definition | 26 |
|&emsp; Verification | |
|&emsp;&emsp; Define Treacability matrix | 8 |
|&emsp;&emsp; Scenarios simulation | 6 |
|Implementation | |
|&emsp; Module (e.g.classes) coding| 30 |
|&emsp; Module interfaces definition |20 |
|&emsp; GUI implementation | 16 |
|Test | |
|&emsp; Define test cases | 30 |
|&emsp; Testing modules separately | 34 |
|&emsp; Merge modules incrementally and test the system obtained at each step| 16 |
|&emsp; Test the system in its entirety | 20 |


# Gantt chart 
```plantuml
[Requirements] lasts 10 days
[Software Design] lasts 8 days
[Implementation] lasts 8 days
[Test] lasts 13 days


[Software Design] starts at [Requirements]'s end
[Implementation] starts at [Software Design]'s end
[Test] starts at [Implementation]'s end
```

