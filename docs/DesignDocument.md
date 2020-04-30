# Design Document 


Authors: 

Date:

Version:


# Contents

- [Design Document](#design-document)
- [Contents](#contents)
- [Instructions](#instructions)
- [High level design](#high-level-design)
  - [Front End](#front-end)
  - [Back End](#back-end)
- [Low level design](#low-level-design)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)
  - [Scenario 10.1](#scenario-101)
  - [Use case 6](#use-case-6)

# Instructions

The design must satisfy the Official Requirements document (see EZGas Official Requirements.md ). <br>
The design must comply with interfaces defined in package it.polito.ezgas.service (see folder ServicePackage ) <br>
UML diagrams **MUST** be written using plantuml notation.

# High level design 

The style selected is client - server. Clients can be smartphones, tablets, PCs.
The choice is to avoid any development client side. The clients will access the server using only a browser. 

The server has two components: the frontend, which is developed with web technologies (JavaScript, HTML, Css) and is in charge of collecting user inputs to send requests to the backend; the backend, which is developed using the Spring Framework and exposes API to the front-end.
Together, they implement a layered style: Presentation layer (front end), Application logic and data layer (back end). 
Together, they implement also an MVC pattern, with the V on the front end and the MC on the back end.



```plantuml
@startuml
package "Backend" {

}

package "Frontend" {

}


Frontend -> Backend
@enduml


```


## Front End

The Frontend component is made of: 

Views: the package contains the .html pages that are rendered on the browser and that provide the GUI to the user. 

Styles: the package contains .css style sheets that are used to render the GUI.

Controller: the package contains the JavaScript files that catch the user's inputs. Based on the user's inputs and on the status of the GUI widgets, the JavaScript controller creates REST API calls that are sent to the Java Controller implemented in the back-end.


```plantuml
@startuml
package "Frontend" {

    package "it.polito.ezgas.resources.views" {

    }


package "it.polito.ezgas.resources.controller" {

    }


package "it.polito.ezgas.resources.styles" {

    }



it.polito.ezgas.resources.styles -down-> it.polito.ezgas.resources.views

it.polito.ezgas.resources.views -right-> it.polito.ezgas.resources.controller


}
@enduml

```

## Back End

The backend  uses a MC style, combined with a layered style (application logic, data). 
The back end is implemented using the Spring framework for developing Java Entrerprise applications.

Spring was selected for its popularity and relative simplicity: persistency (M and data layer) and interactions are pre-implemented, the programmer needs only to add the specific parts.

See in the package diagram below the project structure of Spring.

For more information about the Spring design guidelines and naming conventions:  https://medium.com/the-resonant-web/spring-boot-2-0-project-structure-and-best-practices-part-2-7137bdcba7d3



```plantuml
@startuml
package "Backend" {

package "it.polito.ezgas.service"  as ps {
   interface "GasStationService"
   interface "UserService"
} 


package "it.polito.ezgas.controller" {

}

package "it.polito.ezgas.converter" {

}

package "it.polito.ezgas.dto" {

}

package "it.polito.ezgas.entity" {

}

package "it.polito.ezgas.repository" {

}

    
}
note "see folder ServicePackage" as n
n -- ps
```



The Spring framework implements the MC of the MVC pattern. The M is implemented in the packages Entity and Repository. The C is implemented in the packages Service, ServiceImpl and Controller. The packages DTO and Converter contain classes for translation services.



**Entity Package**

Each Model class should have a corresponding class in this package. Model classes contain the data that the application must handle.
The various models of the application are organised under the model package, their DTOs(data transfer objects) are present under the dto package.

In the Entity package all the Entities of the system are provided. Entities classes provide the model of the application, and represent all the data that the application must handle.




**Repository Package**

This package implements persistency for each Model class using an internal database. 

For each Entity class, a Repository class is created (in a 1:1 mapping) to allow the management of the database where the objects are stored. For Spring to be able to map the association at runtime, the Repository class associated to class "XClass" has to be exactly named "XClassRepository".

Extending class JpaRepository provides a lot of CRUD operations by inheritance. The programmer can also overload or modify them. 



**DTO package**

The DTO package contains all the DTO classes. DTO classes are used to transfer only the data that we need to share with the user interface and not the entire model object that we may have aggregated using several sub-objects and persisted in the database.

For each Entity class, a DTO class is created (in a 1:1 mapping).  For Spring the Dto class associated to class "XClass" must be called "XClassDto".  This allows Spring to find automatically the DTO class having the corresponding Entity class, and viceversa. 




**Converter Package**

The Converter Package contains all the Converter classes of the project.

For each Entity class, a Converter class is created (in a 1:1 mapping) to allow conversion from Entity class to DTO class and viceversa.

For Spring to be able to map the association at runtime, the Converter class associated to class "XClass" has to be exactly named "XClassConverter".




**Controller Package**

The controller package is in charge of handling the calls to the REST API that are generated by the user's interaction with the GUI. The Controller package contains methods in 1:1 correspondance to the REST API calls. Each Controller can be wired to a Service (related to a specific entity) and call its methods.
Services are in packages Service (interfaces of services) and ServiceImpl (classes that implement the interfaces)

The controller layer interacts with the service layer (packages Service and ServieImpl) 
 to get a job done whenever it receives a request from the view or api layer, when it does it should not have access to the model objects and should always exchange neutral DTOs.

The service layer never accepts a model as input and never ever returns one either. This is another best practice that Spring enforces to implement  a layered architecture.



**Service Package**


The service package provides interfaces, that collect the calls related to the management of a specific entity in the project.
The Java interfaces are already defined (see file ServicePackage.zip) and the low level design must comply with these interfaces.


**ServiceImpl Package**

Contains Service classes that implement the Service Interfaces in the Service package.










# Low level design



```plantuml
@startuml
package "Backend" {



note top of pkgserv: These classes methods\n are called by Controller\n classes methods. In a cascade fashion \nService class methods \ncall Repository class methods.
package "it.polito.ezgas.service" as pkgserv{
} 


note top of pkgcontr: These classes methods call\nService class methods.

package "it.polito.ezgas.controller" as pkgcontr {

}


package "it.polito.ezgas.converter" as pkgconv{
}

package "it.polito.ezgas.dto" as pkgdto {
}

package "it.polito.ezgas.entity" as pkgent {
}

note top of pkgrep: These classes methods\n are called by Service\n classes methods in order\n to access database 

package "it.polito.ezgas.repository" as pkgrep {
}


pkgcontr ---> pkgserv 
pkgserv ---> pkgrep
pkgent ---> pkgrep
pkgent ---> pkgconv
pkgconv ---> pkgdto
pkgcontr ---> pkgent

    
}
@enduml
```

```plantuml
@startuml
package "it.polito.ezgas.service" as pkgserv{
  
   class GasStationService {
       
    +GasStationDto getGasStationById(Integer gasStationId)
    +GasStationDto saveGasStation(GasStationDto gasStationDto)
    +List<GasStationDto> getAllGasStations()
    +Boolean deleteGasStation(Integer gasStationId)
    +List<GasStationDto> getGasStationsByGasolineType(String gasolinetype)
    +List<GasStationDto> getGasStationsByProximity(double lat, double lon)
    +List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype, String carsharing)
    +List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
    +void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, Integer userId)
    +List<GasStationDto> getGasStationByCarSharing(String carSharing)

    
    }
   class "UserService"{
       +UserDto getUserById(Integer userId)
       +UserDto saveUser(UserDto userDto)
       +List<UserDto> getAllUsers()
       +Boolean deleteUser(Integer userId)
       +LoginDto login(IdPw credentials)
       +Integer increaseUserReputation(Integer userId)
       +Integer decreaseUserReputation(Integer userId)


   }
}
@enduml
```

```plantuml
@startuml
package "it.polito.ezgas.controller" as pkgcontr {
 class GasStatioController {
       
    +GasStationDto getGasStationById(Integer gasStationId)
    +GasStationDto saveGasStation(GasStationDto gasStationDto)
    +List<GasStationDto> getAllGasStations()
    +Boolean deleteGasStation(Integer gasStationId)
    +List<GasStationDto> getGasStationsByGasolineType(String gasolinetype)
    +List<GasStationDto> getGasStationsByProximity(double lat, double lon)
    +List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype, String carsharing)
    +List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
    +void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, Integer userId)
    +List<GasStationDto> getGasStationByCarSharing(String carSharing)
+ showGasStationOnMap(GasStationDto gsdto)
+ evaluatePriceReport(GasStationDto gsdto, Boolean veridicity)
+ updatePriceReportTrustLevel(GasStationDto gsdto)
    
    }
   class "UserController"{
       +UserDto getUserById(Integer userId)
       +UserDto saveUser(UserDto userDto)
       +List<UserDto> getAllUsers()
       +Boolean deleteUser(Integer userId)
       +LoginDto login(IdPw credentials)
       +Integer increaseUserReputation(Integer userId)
       +Integer decreaseUserReputation(Integer userId)
       +manageRights(Integer userId, String newRole)
   }


}
@enduml
```


```plantuml
@startuml
package "it.polito.ezgas.converter" {
 class UserConverter{
+UserDto convert(User source)
+User convert(UserDto source)
    }
    class GasStationConverter{
+GasStationDto convert(GasStation source)
+GasStation convert(GasStationDto source)
        
    }
    class PriceReportConverter{
+ PriceReportDto convert(PriceReport source)
+ PriceReportDto convert(PriceReportDto source)
       
    }
class CarSharingCompanyConverter {
+ CarSharingCompanyDto convert(CarSharingCompany source)
+ CarSharingCompany convert(CarSharingCompanyDto source)

}
class GeoPointConverter {
+ GeoPointDto convert(GeoPoint source)
+ GeoPoint convert(GeoPointDto source)
}

}

UserConverter --"*" PriceReportConverter
UserConverter "*"-- GeoPointConverter
GasStationConverter -- GeoPointConverter
GasStationConverter "*"--"0..1" CarSharingCompanyConverter
PriceReportConverter "0..1"--GasStationConverter
@enduml
```

```plantuml
@startuml
package "it.polito.ezgas.dto" {

    note top of IdPw : Class used to allow \nthe user to perform \nthe login, it's sent from \nthe front end
    class IdPw{
        +Integer userId
        +String userPassword
    }
    note top of LoginDto : Class used to allow \nthe system to get user \ndetails using its userId
    class LoginDto{
        +Integer userId
        //other attributes should be added

    }
    class UserDto{
        +Integer userId
+ manageRights(Integer userId, String newRole)
         
        
    }
    class GasStationDto{
        +Integer gasStationId
        +String gasolineType
        +double lat
        +double lon
        +String carSharing
        +PriceReport priceReportDto
+ showGasStationOnMap(GasStationDto gsdto)
+ evaluatePriceReport(GasStationDto gsdto, Boolean veridicity)
+ updatePriceReportTrustLevel(GasStationDto gsdto)
    }
    class PriceReportDto{
        +double dieselPrice
        +double superPrice
        +double superPlusPrice
        +double gasPrice
        +double methanePrice
        +Integer userId
    }
class CarSharingCompanyDto {
+ String name
}
class GeoPointDto {
+ double latitude
+ double longitude
}
}
UserDto --"*" PriceReportDto
UserDto "*"-- GeoPointDto
GasStationDto -- GeoPointDto
GasStationDto "*"--"0..1" CarSharingCompanyDto
PriceReportDto "0..1"--GasStationDto
UserDto -- LoginDto
UserDto -- IdPw
@enduml
```

```plantuml
@startuml
package "it.polito.ezgas.entity" as pkgent {


class PriceReport {
+Integer priceReportID
+double dieselPrice
+double superPrice
+double superPlusPrice
+double gasPrice
+double methanePrice
+Integer userId

}



note top of User:Spring SetRole method will be\nused to differentiate \ndifferent kind of users.




class User {
+ String role
+ Integer userId
+ String userPassword
+ String email
+ Integer trustLevel
+ manageRights(Integer userId, String newRole)



}



class GasStation {
+Integer gasStationId
+String gasolineType
+double lat
+double lon
+String carSharing
+PriceReport priceReportDto
+ showGasStationOnMap(GasStationDto gsdto)
+ evaluatePriceReport(GasStationDto gsdto, Boolean veridicity)
+ updatePriceReportTrustLevel(GasStationDto gsdto)
}

class CarSharingCompany {
+ String name
}
class GeoPoint {
+ double latitude
+ double longitude
}

}


User --"*" PriceReport
User "*"-- GeoPoint
GasStation -- GeoPoint
GasStation "*"--"0..1" CarSharingCompany
PriceReport "0..1"--GasStation
@enduml
```

```plantuml
@startuml
package "it.polito.ezgas.repository" as pkgrep {

class PriceReportRepository{

}


class UserRepository {


 +UserDto getUserById(Integer userId)
       +UserDto saveUser(UserDto userDto)
       +List<UserDto> getAllUsers()
       +Boolean deleteUser(Integer userId)
       +LoginDto login(IdPw credentials)
       +Integer increaseUserReputation(Integer userId)
       +Integer decreaseUserReputation(Integer userId)
       +manageRights(Integer userId, String newRole)
 
}



class GasStationRepository {
 +GasStationDto getGasStationById(Integer gasStationId)
    +GasStationDto saveGasStation(GasStationDto gasStationDto)
    +List<GasStationDto> getAllGasStations()
    +Boolean deleteGasStation(Integer gasStationId)
    +List<GasStationDto> getGasStationsByGasolineType(String gasolinetype)
    +List<GasStationDto> getGasStationsByProximity(double lat, double lon)
    +List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype, String carsharing)
    +List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
    +void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, Integer userId)
    +List<GasStationDto> getGasStationByCarSharing(String carSharing)
+ showGasStationOnMap(GasStationDto gsdto) 
+ evaluatePriceReport(GasStationDto gsdto, Boolean veridicity) 
+ updatePriceReportTrustLevel(GasStationDto gsdto) 


}

class CarSharingCompanyRepository {

}
class GeoPointRepository {

}

}


UserRepository --"*" PriceReportRepository
UserRepository "*"-- GeoPointRepository
GasStationRepository -- GeoPointRepository
GasStationRepository "*"--"0..1" CarSharingCompanyRepository
PriceReportRepository "0..1"--GasStationRepository
@enduml
```












# Verification traceability matrix

|  | UserController | GasSationController | UserService | GasStationService | UserRepository| GasStationRepository|User|GasStation|
| :------- |:-------:| :-----:| :-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| FR1.1 | x |  | x |  | x| |||
| FR1.2 | x |  | x |  | x| |||
| FR1.3 | x |  | x |  | x| |||
| FR1.4 | x |  | x |  | x| |||
| FR2   | x |  |  |  | x | |x||
| FR3.1 |  | x |  | x | | x|||
| FR3.2 |  | x |  | x | | x|||
| FR3.3 |  | x |  | x | | x|||
| FR4.1 |  | x |  | x | | x|||
| FR4.2 |  | x |  | x | | x|||
| FR4.3 |  | x |  | x | | x|||
| FR4.4 |  | x |  | x | | x|||
| FR4.5 |  | x |  | x | | x|||
| FR5.1 |  | x |  | x | | x|||
| FR5.2 |  | x |  |  | | x||x|
| FR5.3 |  | x |  |  | | x||x|







# Verification sequence diagrams 
## Scenario 10.1
```plantuml
@startuml
GasStationController -> GasStationService: getGasStationById()
GasStationService -> GasStationRepo.: getGasStationById()
GasStationRepo. -> GasStationController: return GasStationDto
GasStationController -> GasStation: evaluatePriceReport()
GasStation -> GasStationRepo.: evaluatePriceReport()
GasStationService -> UserService: getUserById()
UserService -> UserRepository : getUserById()
UserRepository -> UserService: return UserDto
UserService -> UserRepository: increase/decreaseUserReputation()
GasStationService -> GasStation: updatePriceReportTrustLevel()
GasStation -> GasStationRepo.: updatePriceReportTrustLevel()

@enduml
```

## Use case 6 

```plantuml
@startuml

GasStationController -> GasStationService: getGasStationById()
GasStationService -> GasStationRepo.: getGasStationById()
GasStationRepo. -> GasStationController: return GasStationDto
GasStationController -> GasStationService: deleteGasStation()
GasStationService -> GasStationRepo.: deleteGasStation()
@enduml
```





