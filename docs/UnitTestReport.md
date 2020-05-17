# Unit Testing Documentation

Authors:

Date:

Version:

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)




- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and (traceability) the correspondence with the JUnit test case writing the 
    class and method name that contains the test case>
    <JUnit test classes must be in src/test/java/it/polito/ezgas   You find here, and you can use,  class EZGasApplicationTests.java that is executed before 
    the set up of all Spring components
    >


### **Class *GasStation* - method *setGasStationId(Integer gasStationId)***

**Criteria for method *setGasStationId(Integer gasStationId)*:**
	
 - Range
 - Sign
  
  
**Predicates for method *setGasStationId(Integer gasStationId)*:**

| Criteria | Predicate |
| -------- | :---------: |
|  Range    |  gasStationId ≥ maxint    |
|           |  gasStationId ≤ minint    |
|           | minint ≤ gasStationId ≤ maxint    |
|  Sign     |  gasStationId > 0         |
|           |  gasStationId < 0         |


**Boundaries**:

| Criteria | Boundary values |
| -------- | :---------: |
|  Range    |  minint, maxint    |
|  Sign     |  0        |



**Combination of predicates**:


| Range|Sign| Valid / Invalid | Description of the test case | JUnit test case |
|:-------:|:-------:|:-------:|-------|-------|
|gasStationId ≥ maxint|gasStationId > 0 |Valid|setGasStationId(Integer.MAX_VALUE+1)-> Integer.MIN_VALUE| testGasStation1_1 |
||gasStationId < 0 |Invalid|-| |
|gasStationId ≤ maxint|gasStationId > 0 |Invalid|-| |
||gasStationId < 0 |Valid|setGasStationId(Integer.MIN_VALUE-1)-> Integer.MAX_VALUE| testGasStation1_2|
|minint ≤ gasStationId ≤ maxint|gasStationId > 0 |Valid|setGasStationId(4)-> 4| testGasStation1_3|
||gasStationId < 0 |Valid|setGasStationId(-4)-> -4| testGasStation1_4|

 
### **Class *GasStation* - method *getGasStationId()***

**Criteria for method *getGasStationId()*:**
	
 - gasStationId is null
  
**Predicates for method *getGasStationId()*:**

| Criteria | Predicate |
| -------- | :---------: |
| gasStationId is null    |  True    |
|                                |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:


| gasStationId is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getGasStationId()-> NULL| testGasStation1_5 |
|False|Valid|getGasStationId()-> 4| testGasStation1_3|


### **Class *GasStation* - method *setGasStationName(String gasStationName)***

**Criteria for method *setGasStationName(String gasStationName)*:**
	
 - String length
  
  
  
  
**Predicates for method *setGasStationName(String gasStationName)*:**

| Criteria | Predicate |
| -------- | :---------: |
|  String length    |  0 < s.length < s.maxlength   |
|           | s.length > s.maxlength  |



**Boundaries**:

| Criteria | Boundary values |
| -------- | :---------: |
|  String length| 0, s.maxlenght



**Combination of predicates**:


| Length| Valid / Invalid | Description of the test case | JUnit test case |
|:-------:|:-------:|:-------:|-------|-------|
| 0 < s.length < s.maxlength  | Valid | setGasStationName("Agip") -> "Agip"|testGasStation1_6|
| s.length = 0  | Valid | setGasStationName("") -> ""|testGasStation1_7|


### **Class *GasStation* - method *getGasStationName()***

**Criteria for method *getGasStationName()*:**
	
 - gasStationName is null
  
**Predicates for method *getGasStationName()*:**

| Criteria | Predicate |
| -------- | :---------: |
| gasStationName is null    |  True    |
|                                |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:


| gasStationName is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getGasStationName()-> NULL|testGasStation1_8 |
|False|Valid|getGasStationName()-> "Agip"| testGasStation1_6|


### **Class *GasStation* - method *setReportDependability(double reportDependability)***

**Criteria for method *setReportDependability(double reportDependability)*:**
	
 - Range
 - Sign
  
  
**Predicates for method *setReportDependability(double reportDependability)*:**

| Criteria | Predicate |
| -------- | :---------: |
|  Range    |  reportDependability ≥ maxint    |
|           |  reportDependability ≤ minint    |
|           | minint ≤ reportDependability ≤ maxint    |
|  Sign     |  reportDependability > 0         |
|           |  reportDependability < 0         |


**Boundaries**:

| Criteria | Boundary values |
| -------- | :---------: |
|  Range    |  minint, maxint    |
|  Sign     |  0        |



**Combination of predicates**:


| Range|Sign| Valid / Invalid | Description of the test case | JUnit test case
|:-------:|:-------:|:-------:|-------|-------|
|reportDependability ≥ maxint|reportDependability > 0 |Valid|setReportDependability(Double.MAX_VALUE+1)-> Double.MAX_VALUE| testGasStation2_1|
||reportDependability < 0 |Invalid|-| |
|reportDependability ≤ maxint|reportDependability > 0 |Invalid|-| |
|minint ≤ reportDependability ≤ maxint|reportDependability > 0 |Valid|setReportDependability(4.2)-> 4.2| testGasStation2_2|
||reportDependability < 0 |Valid|setReportDependability(-4.2)-> -4.2|testGasStation2_3 |

### **Class *GasStation* - method *getReportDependability()***

**Criteria for method *getReportDependability()*:**
	
 - ReportDependability is null
  
**Predicates for method *getReportDependability()*:**

| Criteria | Predicate |
| -------- | :---------: |
| ReportDependability is null    |  True    |
|                                |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:


| ReportDependability is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getReportDependability()-> NULL| testGasStation2_4 |
|False|Valid|getReportDependability()-> 4.2| testGasStation2_2 |

### **Class *User* - method *setUserId(Integer userId)***

**Criteria for method *setUserId(Integer userId)*:**
	
 - Range
 - Sign
  
  
**Predicates for method *setUserId(Integer userId)*:**

| Criteria | Predicate |
| -------- | :---------: |
|  Range    |  userId ≥ maxint    |
|           |  userId ≤ minint    |
|           | minint ≤ userId ≤ maxint    |
|  Sign     |  userId > 0         |
|           |  userId < 0         |


**Boundaries**:

| Criteria | Boundary values |
| -------- | :---------: |
|  Range    |  minint, maxint    |
|  Sign     |  0        |



**Combination of predicates**:


| Range|Sign| Valid / Invalid | Description of the test case | JUnit test case |
|:-------:|:-------:|:-------:|-------|-------|
|userId ≥ maxint|userId > 0 |Valid|setUserId(Integer.MAX_VALUE+1)-> Integer.MIN_VALUE| testUser1_1|
||userId < 0 |Invalid|-| |
|userId ≤ maxint|userId > 0 |Invalid|-| |
||userId < 0 |Valid|setUserId(Integer.MIN_VALUE-1)-> Integer.MAX_VALUE| testUser1_2|
|minint ≤ userId ≤ maxint|userId > 0 |Valid|setUserId(4)-> 4| testUser1_3|
||userId < 0 |Valid|setUserId(-4)-> -4| testUser1_4|

 ### **Class *GasStation* - method *getUserId()***

**Criteria for method *getUserId()*:**
	
 - userId is null
  
**Predicates for method *getUserId()*:**

| Criteria | Predicate |
| -------- | :---------: |
| userId is null    |  True    |
|                                |  False   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:


| userId is null| Valid / Invalid | Description of the test case | JUnit test case 
|:-------:|:-------:|-------|-------|
|True|Valid|getUserId())-> NULL| testUser1_5|
|False|Valid|getUserId()-> 3| testUser1_3|

 ### **Class *LoginDto* - *getUserName***



**Criteria for *getUserName*:**
	

 - Length of *userName* string




**Predicates for method *getUserName*:**

| Criteria | Predicate |
| -------- | --------- |
| Length of *userName* string       | >0          |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Length of *userName* string         | ""                |
|          | null                |



**Combination of predicates**:


| Length of *userName* string | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|>0|Valid|Object initialized with "Mario Rossi"; getUserName() -> "Mario Rossi"|testLoginDto1_1()|
|=0|Valid|Object initialized with ""; getUserName() -> ""|testLoginDto1_2()|
|null|Valid|Object initialized with null; getUserName() -> null|testLoginDto1_3()|
|<0|Invalid|Object initialized with a string of negative length |Not feasible|
|>max array size|Invalid|Object initialized with a string of length > max array size|Not feasible|




 ### **Class *LoginDto* - *setUserName***



**Criteria for *setUserName*:**
	

 - Length of *userName* string




**Predicates for method *setUserName*:**

| Criteria | Predicate |
| -------- | --------- |
| Length of *userName* string       | >0          |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Length of *userName* string | "" |
|          | null |



**Combination of predicates**:


| Length of *userName* string | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|>0|Valid|setUserName("Luigi Verdi"); getUserName() -> "Luigi Verdi"|testLoginDto1_4()|
|=0|Valid|setUserName(""); getUserName() -> ""|testLoginDto1_5()|
|null|Valid|setUserName(null); getUserName() -> null|testLoginDto1_6()|
|<0|Invalid|Not feasible |Not feasible|
|>max array size|Invalid|str = string of length > max array size|setUserName(str) -> java.lang.OutOfMemoryError: Requested array size exceeds VM limit|




 ### **Class *LoginDto* - *getAdmin***



**Criteria for *getAdmin*:**
	

 - Value of *admin* boolean




**Predicates for method *getAdmin*:**

| Criteria | Predicate |
| -------- | --------- |
| Value of *admin* boolean       | Uninitialized          |
|          | After setAdmin(null)          |
|          | After setAdmin(false)          |
|          | After setAdmin(true)          |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of *admin* boolean | null |
|          | false |
|          | true |


**Combination of predicates**:


| Value of *admin* boolean | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|Uninitialized|Valid| getAdmin() -> false|testLoginDto2_1()|
|false|Valid| setAdmin(null); getAdmin() -> null | testLoginDto2_2()|
|false|Valid| setAdmin(false); getAdmin() -> false | testLoginDto2_3()|
|false|Valid| setAdmin(true); getAdmin() -> true | testLoginDto2_4()|




 ### **Class *GasStationServiceimpl* - *distance***



**Criteria for *distance*:**
	

 - Value of *lat1* double
 - Value of *lon1* double
 - Value of *lat2* double
 - Value of *lon2* double




**Predicates for method *distance*:**

| Criteria | Predicate |
| -------- | --------- |
| Value of *lat1* double       | Any value          |
| Value of *lon1* double       | Any value          |
| Value of *lat2* double       | Any value          |
| Value of *lon2* double       | Any value          |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of *lat1* double | (Double) null |
|          | MAX_VALUE |
|          | MAX_VALUE+1.0 |
|          | MIN_VALUE |
|          | MIN_VALUE-1.0 |
| Value of *lon1* double | (Double) null |
|          | MAX_VALUE |
|          | MAX_VALUE+1.0 |
|          | MIN_VALUE |
|          | MIN_VALUE-1.0 |
| Value of *lat2* double | (Double) null |
|          | MAX_VALUE |
|          | MAX_VALUE+1.0 |
|          | MIN_VALUE |
|          | MIN_VALUE-1.0 |
| Value of *lon2* double | (Double) null |
|          | MAX_VALUE |
|          | MAX_VALUE+1.0 |
|          | MIN_VALUE |
|          | MIN_VALUE-1.0 |
| Value of *lon2* double | (Double) null |
|          | MAX_VALUE |
|          | MAX_VALUE+1.0 |
|          | MIN_VALUE |
|          | MIN_VALUE-1.0 |




**Combination of predicates**:


| *lat1* | *lat2* | *lon1* | *lon2* | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|-------|
|*lat1*|*lat1*|*lon1*|*lon1*|Valid|distance(0.0, 0.0, 0.0, 0.0) -> 0.0|testGasStationServiceimpl1_1()|
|*lat1*|*lat1* + n * 360|*lon1*|*lon1* + n * 360|Valid|distance(0.0, 180.0, 0.0, -180.0) -> 0.0|testGasStationServiceimpl1_2()|
|*lat1*|!=*lat1* + n * 360|*lon1*|!=*lon1* + n * 360|Valid|distance(0.0, 1.0, 0.0, 0.0) -> !=0.0|testGasStationServiceimpl1_3()|




# White Box Unit Tests

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezgas>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>


| Unit name | JUnit test case |
|--|--|
|LoginDto.getUserName|testLoginDto1_1|
||testLoginDto1_2|
||testLoginDto1_3|
|LoginDto.setUserName|testLoginDto1_4|
||testLoginDto1_5|
||testLoginDto1_6|
|LoginDto.getAdmin|testLoginDto2_1|
||testLoginDto2_2|
||testLoginDto2_3|
||testLoginDto2_4|
|GasStationServiceimpl.distance|testGasStationServiceimpl1_1|
||testGasStationServiceimpl1_2|
||testGasStationServiceimpl1_3|
||testGasStationServiceimpl1_4|
||testGasStationServiceimpl1_5|
||testGasStationServiceimpl1_6|
||testGasStationServiceimpl1_7|
||testGasStationServiceimpl1_8|
|GasStation.setGasStationId| testGasStation1_1|
||testGasStation1_2|
||testGasStation1_3|
||testGasStation1_4|
|GasStation.getGasStationId|testGasStation1_5|
||testGasStation1_3|
|GasStation.setGasStationName| testGasStation1_6|
||testGasStation1_7|
|GasStation.getGasStationName|testGasStation1_8|
|| testGasStation1_6|
|GasStation.setReportDependeability| testGasStation2_1|
||testGasStation2_2|
||testGasStation2_3|
|GasStation.getReportDependeability|testGasStation2_4|
||testGasStation2_2|
|User.setUserId|testUser1_1|
||testUser1_2|
||testUser1_3|
||testUser1_4|
|User.getUserId|testUser1_5|
||testUser1_3|


### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >
LoginDto
![Login Dto](../coverage_img/LoginDto.png)
GasStationServiceimpl.distance
![Distance](../coverage_img/Distance.png)
GasStation 
![GasStation](../coverage_img/GasStation.png)
User 
![User](../coverage_img/User.png)

### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||


