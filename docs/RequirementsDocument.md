# Requirements Document 

Authors:

Date:

Version:

# Contents

- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
	
- [Requirements Document](#requirements-document)
- [Contents](#contents)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	- [Context Diagram](#context-diagram)
	- [Interfaces](#interfaces)
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	- [Functional Requirements](#functional-requirements)
	- [Non Functional Requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	- [Use case diagram](#use-case-diagram)
		- [Use case 1, UC1 - FR1 Handle User Account](#use-case-1-uc1---fr1-handle-user-account)
				- [Scenario 1.1](#scenario-11)
				- [Scenario 1.2](#scenario-12)
		- [Use case 2, UC2 - FR2 Show the Gas Station List](#use-case-2-uc2---fr2-show-the-gas-station-list)
				- [Scenario 2.1](#scenario-21)
				- [Scenario 2.2](#scenario-22)
		- [Use case 3, UC3 - FR3 Display Gas Station details and center the Map on the Gas Station position](#use-case-3-uc3---fr3-display-gas-station-details-and-center-the-map-on-the-gas-station-position)
				- [Scenario 3.1](#scenario-31)
		- [Use case 4, UC4 - FR4 Handle Gas Station Item](#use-case-4-uc4---fr4-handle-gas-station-item)
				- [Scenario 4.1](#scenario-41)
				- [Scenario 4.2](#scenario-42)
				- [Scenario 4.3](#scenario-43)
		- [Use case 5, UC5 - FR5 Manage EZGas](#use-case-5-uc5---fr5-manage-ezgas)
				- [Scenario 5.1](#scenario-51)
		- [Use case 6, UC6 - FR6 Handle Map](#use-case-6-uc6---fr6-handle-map)
				- [Scenario 6.1](#scenario-61)
		- [Use case 7, UC7 - FR7 Initialize Database](#use-case-7-uc7---fr7-initialize-database)
- [Glossary](#glossary)
- [System Design](#system-design)
- [Deployment Diagram](#deployment-diagram)


# Stakeholders



| Stakeholder name  | Description | 
| ----------------- |:-----------|
|         User          |He’s interested in searching for the nearest gas station or the cheapest one.| 
|  Authenticated User   |He has the same interests as User and can, additionally, modify Gas Station information. | 
|	Administrator       |He’s interested in monitoring how the users use the app if they behave correctly and manage account settings.| 
|		Developer		|He’s interested in producing a well working and appealing EZGas application.| 
|		Map System		|It provides an explorable and well design map on which Gas Stations are shown which is obtained from (www.openstreetmap.org).| 
|		Database		|It offers a storage solution for managing account and Gas Station details. | 
| Gas Stations Source |This is a free file obtained from (www.datiopen.it) which contains a starting point to initialize the Database at its first usage.|

# Context Diagram and interfaces

## Context Diagram
```plantuml
@startuml
left to right direction
actor User as a
actor AuthenticatedUser as auth
actor Administrator as admin
actor MapSystem as Map
actor GasStationList as list
database Database as db


a -- (EZGas)
Map -- (EZGas)
admin --|> a
auth --|> a
list -- (EZGas)
db -- (EZGas)
@enduml
```

## Interfaces

| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| :-----:|
|User |GUI |Screen, keyboard, touchscreen |
|Database |API |Internet |
|Map System |API |Internet |
|Gas Station List |XML |Internet |

# Stories and personas

• Persona 1: middle age, middle-high income, professional<br>
• Emily travels every day for a lot of time going and returning from work. She wants to get home early to spend more time with her family, so she uses EZGas to quickly find the nearest gas station.
 <br><br>
• Persona 2: young, low income, student <br>
 • John lives in the town near to his university and each day he has to travel in his car to attend the lessons. Since he has little income he would like to save on fuel, which is a considerable expense, so John uses EZGas to find the best fuel price from gas stations along the way to university.



# Functional and non functional requirements

## Functional Requirements

| ID        | Description  |
| -------------|:-------------| 
|  FR1         | Handle User Account  |  
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR1.1	   | Account creation |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR1.2       | Log in|  
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR1.3       | Log out |  
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR1.4       | Request Account deletion  |  
|  FR2         | Show the Gas Station List|
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR2.1      | Locate by type of fuel|
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR2.2      | Locate by price order|
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR2.3      | Locate by distance order|
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR2.4      | Locate by name|
|  FR3	       | Display Gas Station details and center the Map on the Gas Station position |
|  FR4	       | Handle Gas Station Item |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR4.1      | Update the price of fuels in a Gas Station |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR4.2      | Send open/closed Report for a given Gas Station Item |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR4.3      | Add a new Gas Station Item |
|  FR5	       | Manage EZGas |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR5.1	   | Manage Account deletion Requests |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR5.2	   | Manage open/closed Report for a given Gas Station Item  |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR5.3      | Remove a Gas Station definitely |
| FR6		   | Handle map |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR6.1	   | Center map |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FR6.2	   | Resize map |
| FR7		   | Initialize Database |

## Non Functional Requirements


| ID        | Type (efficiency, reliability, .. see iso 9126)           | Description  | Refers to |
| ------------- |:----------:| :---------------| :-----:|
|  NFR1     | Efficiency | All the function completed in less than 0.5 second.  | All FR |
|  NFR2     | External | If the software is used in Europe, it has to treat personal data according to the current privacy laws (see: GDRP).  | All FR |
|  NFR3     | Portability | EZGas runs on recent Android(v.5 on) and iOS (v.11 on) devices.  | All FR |
|  NFR4     | Portability | EZGas runs on the following recent browsers(Safari v.10 on, Chrome v.68 on, Firefox v.60 on).  | All FR |
|  NFR5     | Safety | EZGas should advertise the user to not use the application while driving.  | All FR |
|  NFR6     | Localisation | Decimal numbers use . (dot) as decimal separator | FR2, FR3, FR4 |
|  NFR7		| Reliability | Shown data are referred to the last confirmed update| FR2, FR3 |
|  NFR8	    | Functionality | Currencies are changed basing on the country. | FR2, FR3, FR4, FR6 |
|  NFR9	    | Functionality | English and Italian languages are supported. | All FR |





# Use case diagram and use cases

```plantuml

@startuml
left to right direction
actor Administrator as a
actor User as u #0024ff/7888ed
actor "Authicated User" as auth 
actor "Map System" as m #92ff00/92ff00
database "Database" as db #7c00fb/9b5ed9
actor "Gas Station Source" as gss

rectangle EZGas {
	(FR1 Handle User Account) as FR1
	(FR1.1 Account creation) as FR1.1
	(FR1.2 Log in) as FR1.2
	(FR1.3 Log out) as FR1.3
	(FR1.4 Request Account deletion) as FR1.4
	(FR2 Show the Gas Station List) as FR2
	(FR2.1 Locate by type of fuel) as FR2.1
	(FR2.2 Locate by price order) as FR2.2
	(FR2.3 Locate by distance order) as FR2.3
	(FR2.4 Locate by name) as FR2.4
	(FR3 Display Gas Station details and center the Map on the Gas Station position) as FR3
	(FR4 Handle Gas Station Item) as FR4
	(FR4.1 Update the price of fuels in a Gas Station) as FR4.1
	(FR4.2 Send open/closed Report for a given Gas Station Item) as FR4.2
	(FR4.3 Add a new Gas Station Item) as FR4.3
	(FR5 Manage EZGas) as FR5
	(FR5.1 Manage Account deletion Request) as FR5.1
	(FR5.2 Manage open/closed Report for a given Gas Station Item) as FR5.2
	(FR5.3 Remove a Gas Station definitely) as FR5.3
	(FR6 Handle Map) as FR6
	(FR6.1 Center Map) as FR6.1
	(FR6.2 Resize Map) as FR6.2
	(FR7 Initialize Database) as FR7
	
}

FR1 .-> FR1.1 : <<include>>
FR1 .-> FR1.2 : <<include>>
FR1 .-> FR1.3 : <<include>>
FR1 .-> FR1.4 : <<include>>

FR2 .-> FR2.1 : <<include>>
FR2 .-> FR2.2 : <<include>>
FR2 .-> FR2.3 : <<include>>
FR2 .-> FR2.4 : <<include>>

FR4 .-> FR4.1 : <<include>>
FR4 .-> FR4.2 : <<include>>
FR4 .-> FR4.3 : <<include>>

FR5 .-> FR5.1 : <<include>>
FR5 .-> FR5.2 : <<include>>
FR5 .-> FR5.3 : <<include>>

FR6 .-> FR6.1 : <<include>>
FR6 .-> FR6.2 : <<include>>

a -|>u
auth -|> u 



:db: -up- FR1 #7c00fb 
u -- FR1 #0024ff

u -- FR2 #0024ff
:db: -up- FR2 #7c00fb 



auth -- FR4
:db: -up- FR4 #7c00fb 

a -- FR5
:db: -up- FR5 #7c00fb 


u -- FR6 #0024ff
:m: -down- FR6 #92ff00

u -- FR3 #0024ff
:db: -up- FR3 #7c00fb 
:m: -down- FR3 #92ff00

a -- FR7
:db: -up- FR7 #7c00fb 
gss -- FR7
@enduml
```

## Use case diagram

### Use case 1, UC1 - FR1 Handle User Account
| Actors Involved        | User, Database |
| ------------- |:-------------| 
|  Precondition     |  - |  
|  Post condition     | Account created or login performed. |
|  Nominal Scenario     | User wants to create a new account or log in in his account in order to interact in a better way with EZGas (contribute to add or remove Gas Station, update Gas Station prices and current state). |
|  Variants     | User could desire to use EZGas without an account|

##### Scenario 1.1 

| Scenario 1.1 |  Account creation|
| ------------- |:-------------| 
|  Precondition     | - |
|  Post condition     | Account created succesfully. |
| Step#        | Description  | 
|  1	 |User access Account Management page|
|  2     | Personal information is asked|
|  3     | Once, provided, personal information is stored in Database|
|  4     | User can perform the log in|


##### Scenario 1.2

| Scenario 1.2|  Log in|
| ------------- |:-------------| 
|  Precondition     | Account exists|
|  Post condition     | User succesfully logged in |
| Step#        | Description  | 
|  1	 | User access Account Management page|
|  2     | Username and password are provided|
|  3     | EZGas checks if this data is correct|
|  4     | User accesses full EZGas functionalities|


### Use case 2, UC2 - FR2 Show the Gas Station List
| Actors Involved        | User, Database |
| ------------- |:-------------| 
|  Pre condition     |  Main screen is correctly loaded |  
|  Post condition     | Gas Station List is provided to the user |
|  Nominal Scenario     | User wants to see Gas Station List ordered by a specific parameter. |
|  Variants     | GPS doesn't work or User selects another initial position: User inserts manually the initial position|

##### Scenario 2.1 

| Scenario 2.1 |  Locate gas stations by price order|
| ------------- |:-------------| 
|  Precondition     | Main screen is correctly loaded |
|  Post condition     | Sorted Gas Station List is provided to the User |
| Step#        | Description  | 
|  1	 | User presses Search button|
|  2	 | User selects the "Order by price" option|
|  3	 | User selects the radius of the search|
|  4     | Sorted Gas Station List is provided to the User|

##### Scenario 2.2

| Scenario 2.2 |  Locate gas stations by fuel type |
| ------------- |:-------------| 
|  Precondition     | Main screen is correctly loaded |
|  Post condition     | Gas Station List is provided to the User |
| Step#        | Description  | 
|  1	 | User presses Search button|
|  2	 | User selects the "Filter by fuel type" option|
|  3	 | User selects the type of fuel|
|  4	 | User selects the radius of the search|
|  5     | Gas Station List is provided to the User|


### Use case 3, UC3 - FR3 Display Gas Station details and center the Map on the Gas Station position
| Actors Involved        | User, Database, Map System |
| ------------- |:-------------| 
|  Pre condition     |  Gas station state is "Open" |  
|  Post condition     | Gas Station position and details are shown |
|  Nominal Scenario     | User wants to know Gas Station position and details. |
|  Variants     | Gas station state is "Closed", only name, address and state are shown |

##### Scenario 3.1 

| Scenario 3.1 | Select and display Gas Station details |
| ------------- |:-------------| 
|  Pre condition     | Map is loaded or Gas Stations List is shown |
|  Post condition     | Gas Station details are shown on the Map |
| Step#        | Description  | 
|  1	 | User selects one of the Gas Stations on the Map or in the Gas Station List |
|  2	 | Map is centered on selected Gas Station Item |
|  3	 | A speech bubble in corrispondence of the Gas Station position on the Map displays details |


### Use case 4, UC4 - FR4 Handle Gas Station Item
| Actors Involved        | Authenticated User, Database |
| ------------- |:-------------| 
|  Pre condition     | User is authenticated  |  
|  Post condition     | Gas Station details are submitted or Gas Station Item is added |
|  Nominal Scenario     | User wants to update Gas Station details or add a new Gas Station |
|  Variants     | User leaves empty fields so no updates are made |
|| Update fails because of poor connection |


##### Scenario 4.1 

| Scenario 4.1 | User submits new fuel prices |
| ------------- |:-------------| 
|  Pre condition     | Gas Station details are shown (look at UC3 for details) |
|  Post condition     | User update is received by Database |
| Step#        | Description  | 
|  1	 | User selects Modify button on the speech bubble that appears |
|  2	 | User selects one of the prices of current Gas Station |
|  3	 | User inserts new values in the selected field |
|  4	 | User presses the submit button |
|  5	 | The new value is sent to the Database for being accepted by the algorithm |

##### Scenario 4.2 

| Scenario 4.2 | Send closed Report for a given Gas Station Item |
| ------------- |:-------------| 
|  Pre condition     | Gas Station details are shown (look at UC3 for details) |
|  Post condition     | Gas Station Report is sent to the Database |
| Step#        | Description  | 
|  1	 | User selects Modify button on the speech bubble that appears |
|  2	 | User selects the Send closed Report button on the bubble speech |
|  3	 | User fills the optional description popup |
|  4	 | The closed Report is sent to the Database in order to be validated by the Administrator|

##### Scenario 4.3 

| Scenario 4.3 | User inserts new Gas Station |
| ------------- |:-------------| 
|  Pre condition     | Gas Station doesn't exist in the database |
|  Post condition     | Gas Station is inserted in the database |
| Step#        | Description  | 
|  1	 | User selects the add Gas Station button |
|  2	 | User selects a company from a list of the available companies |
|  3	 | User inserts Gas Station address |
|  4	 | User fills the optional phone number field |
|  5	 | User selects which kind of fuel type are sold from a list of available fuel types |
|  6	 | For each selected fuel type the User provides a price |


### Use case 5, UC5 - FR5 Manage EZGas
| Actors Involved        | Administrator, Database, User |
| ------------- |:-------------| 
|  Pre condition     |  A Request/Report has to be managed |  
|  Post condition     | Administrator has managed the Request/Report |
|  Nominal Scenario     | Administrator has to manage Account deletion Requests and Closed Gas Station Reports. |
|  Variants     |  |

##### Scenario 5.1 

| Scenario 5.1 | Manage Account deletion Requests |
| ------------- |:-------------| 
|  Pre condition     | User has requested for account deletion  |
|  Post condition     | User account is deleted |
| Step#        | Description  | 
|  1	 | Administrator accesses Management page (from the Account section from the Main screen) and select Account deletion Request |
|  2	 | Administrator selects the Request to manage |
|  2	 | Administrator deletes all User information from database |


### Use case 6, UC6 - FR6 Handle Map
| Actors Involved        | User, Map System |
| ------------- |:-------------| 
|  Pre condition     | Map is loaded on User's device |  
|  Post condition     | Map is scaled or centered on selected Gas Station Item |
|  Nominal Scenario     | User wants to scale the map for better legibility or to center it on selected Gas Station Item |
|  Variants     | |

##### Scenario 6.1 

| Scenario 6.1 | User centers the Map on selected Gas Station Item |
| ------------- |:-------------| 
|  Pre condition     | Gas Station Item is selected from Gas Station List and EZGas has fetched its position from the Database |
|  Post condition     | Map is centered on selected Gas Station Item |
| Step#        | Description  | 
|  1	 | EZGas provides to the Map System the Gas Station position |
|  2	 | Map is moved so that selected Gas Station Item finds itself in the middle of the viewport |

### Use case 7, UC7 - FR7 Initialize Database
| Actors Involved        | Administrator, Database, Gas Station Source |
| ------------- |:-------------| 
|  Pre condition     |No Gas Station Item is stored in the Database   |  
|  Post condition     |Database stores Gas Station Items  |
|  Nominal Scenario     |Before EZGas is launched on the market, the Database is initialized using the Gas Station source file. From this point on, Users will interact with Database only.  |
|  Variants     |Database Initialization is performed as soon as EZGas development is finished for testing reasons |




# Glossary

```plantuml
class User {
+ name
+ surname
}
class Administrator {
+ email
}

class Fuel {
+type
+price
}

class GasStationList {
+url
}

class GasStationItem {
+ name
+ address
+ position
}

class "Open/Closed Report"{
+ date

}

class "Price Update"{
+ date
+ price
+fuel type
}

class Map {
+ url

}

class AuthenticatedUser{
+ email
}

EZGas -- "*" User :serve >
GasSationSource -down- Database :  initialize > 
Database -- GasStationList : provide >
Administrator -- AuthenticatedUser: manage >
User <|-- Administrator 
GasStationList -right- "*" GasStationItem :list >
User <|-right- AuthenticatedUser 
GasStationItem "*"-- Map : < show position
User -- "*"  GasStationList : query >
GasStationItem -- "*" "Open/Closed Report" :< modify 
Administrator -- "*"  "Open/Closed Report" :  manage >
Database -- "*"  "Price Update" :  store >
AuthenticatedUser-- "*" "Open/Closed Report" : produce >
AuthenticatedUser-- "*"  "Price Update" : produce >
GasStationItem "*" -- Database : < modify
GasStationItem  -- "*" Fuel : > offer
```

# System Design
\<describe here system design>

\<must be consistent with Context diagram>

# Deployment Diagram 
```plantuml
@startuml
left to right direction
Node Database as db <<server>>

Node PC as pc <<device>> {
Artifact "PC client" as pcc
}

Node Smartphone as sm <<device>> {
Artifact "Smartphone client" as smc
}

db -- pc : internet
db -- sm : internet
@enduml
```