[![Build Status](https://travis-ci.org/DatabaseGroup9/ProjectGutenberg_G9.svg?branch=master)](https://travis-ci.org/DatabaseGroup9/ProjectGutenberg_G9)

# Gutenberg-Geolocation

### _Which database engines are used?_ <br>
- MongoDB (Document-based database)
- Neo4J (Graph database)

_extra:_
- MySQL (Relational Database)

### _How data is modeled in the database?_ <br>
_ER Diagram:_<br>
![alt ERDiagram](https://github.com/DatabaseGroup9/Documentation/blob/master/diagrams/Chenerdiagram2.png)

### _How data is modeled in your application?_ <br>


### _How the data is imported?_ <br>


### _Behavior of query test set?_ <br>

_Database Queries corresponding to the end-user queries:_

ID  | MongoDB                                                 |
----|---------------------------------------------------------|
M1  | db.books.find({"cities.name": $cityName})               |
M2  | db.books.find({bookTitle: $bookTitle})                  | 
M3  | db.books.find({"author.fullName",$authorFullName})      | 
M4  | db.books.find({"cities.lat":$lat},{"cities.lon", $lot}) |
*** 
ID  | Neo4J                                                 |
----|---------------------------------------------------------|
N1  | MATCH (a:Author)-[ra:AUTHORED]->(b:Book)-[r:MENTIONS ]-(c:City) WHERE LOWER(c.name) = LOWER($cityName) RETURN a,b |
N2  | MATCH (b:Book)-[r:MENTIONS]->(c:City) WHERE LOWER(b.bookTitle) = LOWER($bookTitle) RETURN c| 
N3  | MATCH (a:Author)-[ra:AUTHORED]->(b:Book)-[r:MENTIONS ]->(c:City) WHERE LOWER(a.fullName) = LOWER($fullName) RETURN a,b,collect(c)| 
N4  | MATCH (a:Author)-[ra:AUTHORED]->(b:Book)-[r:MENTIONS ]->(c:City) WHERE c.lat = $lat AND c.lon = $lon RETURN a,b,collect(c)|


#### A. Query Runtime is influenced by the DB engine <br>

#### B. Query Runtime is influenced by the application frontend <br>

### _Recommendation_ <br>
_which database engine to use in such a project for production_

OBS Remember to use the approprite diagram notaions for documentation.

##### Additional Documentation:
> See here: https://github.com/DatabaseGroup9
