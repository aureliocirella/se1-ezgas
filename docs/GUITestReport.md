# GUI  Testing Documentation 

Authors: Aurelio Cirella, Behnam Lotfi, Federica Giorgione, Lorenzo Cardone

Date: 31/05/2020

Version: 1.0

# GUI testing

This part of the document reports about testing at the GUI level. Tests are end to end, so they should cover the Use Cases, and corresponding scenarios.

## Coverage of Scenarios and FR

```
<Complete this table (from IntegrationApiTestReport.md) with the column on the right. In the GUI Test column, report the name of the .py  file with the test case you created.>
```

### 

| Scenario ID | Functional Requirements covered | GUI Test(s) |
| ----------- | :-------------------------------: | ----------- | 
| UC3.1       | FR1.2          |    GUItestUC3.1.sikuli
| UC4.1       | FR1.2          |    testUC4.1.sikuli
| UC5.1       | FR3.1          |    testUC5.1.sikuli
| UC6.1       | FR3.2          |    testUC6.1.sikuli
| UC10.1      | FR1.2          |    testUC10.1.sikuli
| ...         |                                 |             |             
| ...         |                                 |             |             
| ...         |                                 |             |             


# REST  API  Testing

This part of the document reports about testing the REST APIs of the back end. The REST APIs are implemented by classes in the Controller package of the back end. 
Tests should cover each function of classes in the Controller package

## Coverage of Controller methods


<Report in this table the test cases defined to cover all methods in Controller classes >

| class.method name | Functional Requirements covered |REST  API Test(s) | 
| ----------- | :--------------------: | ----------- | 
|  UserServiceimpl.saveUser()   | FR1.1                |testSaveUser()|   
|  UserServiceimpl.deleteUser() | FR1.2                |testDeleteUser()|  
|  UserServiceimpl.getUserById()| FR1.3                |testUserById()|  
|  UserServiceimpl.getAllUsers()| FR1.4                |testAllUser() |  
|  UserServiceimpl.testincreaseUserReputation()   | FR1.1                |testincreaseUserReputation()| 
|  UserServiceimpl.testdecreaseUserReputation()   | FR1.1                |testdecreaseUserReputation()|
|  UserServiceimpl.testlogin()   | FR1                 |testlogin()|  
|  GasStationServiceimpl.saveGasStation()   | FR3.1                |testSaveGasStation()|  
|  GasStationServiceimpl.deleteGasStation()| FR3.2                |testDeleteGasStation() |   
|  GasStationServiceimpl.getAllGasStations()| FR3.3                |testAllGasStations() |       
| GasStationServiceimpl.getGasStationsWithCoordinates()| FR4.1|testGasStationWithCoordinate()|
|  GasStationServiceimpl.testgetGasStation()   | FR4               |testgetGasStation()| 