# Project Estimation  

Authors: Aurelio Cirella, Behnam Lotfi, Federica Giorgione, Lorenzo Cardone

Date: 01/05/2020

Version: 1

# Contents



- [Estimate by product decomposition]
- [Estimate by activity decomposition ]



# Estimation approach

<Consider the EZGas  project as described in YOUR requirement document, assume that you are going to develop the project INDEPENDENT of the deadlines of the course>

# Estimate by product decomposition



### 

|             | Estimate                        |             
| ----------- | ------------------------------- |  
| NC =  Estimated number of classes to be developed   | 6                            |             
| A = Estimated average size per class, in LOC       | 100                           | 
| S = Estimated size of project, in LOC (= NC * A) | 600 |
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  | 60                                     |   
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 1800 | 
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 0.375                   |               


# Estimate by activity decomposition



### 

|         Activity name    | Estimated effort (person hours)   |             
| ----------- | ------------------------------- | 
|Requirements | |
|&emsp; Define functional requirements |14 |
|&emsp; Define non functional requirements |4 |
|&emsp; Define system interfaces |4 |
|&emsp; GUI prototype definition |5 |
|Software Design | |
|&emsp; Architecture definition |4 |
|&emsp; Design definition |4 |
|&emsp; Verification | |
|&emsp;&emsp; Define Treacability matrix |2 |
|&emsp;&emsp; Scenarios simulation |2 |
|Implementation | |
|&emsp; Module (e.g.classes) coding|10 |
|&emsp; Module interfaces definition |10 |
|&emsp; GUI implementation |10 |
|Test | |
|&emsp; Define test cases |5 |
|&emsp; Testing modules separately |5 |
|&emsp; Merge modules incrementally and test the system obtained at each step|5 |
|&emsp; Test the system in its entirety |5 |


###
Gantt chart 
```plantuml
[Requirements] lasts 1 days
[Software Design] lasts 1 days
[Implementation] lasts 1 days
[Test] lasts 1 days


[Software Design] starts at [Requirements]'s end
[Implementation] starts at [Software Design]'s end
[Test] starts at [Implementation]'s end
```

