[![Build Status](https://travis-ci.org/DatabaseGroup9/ProjectGutenberg_G9.svg?branch=master)](https://travis-ci.org/DatabaseGroup9/ProjectGutenberg_G9)

# Gutenberg-Geolocation

### _Which database engines are used?_ <br>
- MongoDB (Document-based database)
- Neo4J (Graph database)

_extra:_
- MySQL (Relational Database)

### _How data is modeled in the database?_ <br>
> _Our data model is designed as shown in the ER Diagram, wherein we have 3 collections: Book, Author and City. Books can be written by multiple authors and have multiple cities mentioned._

![alt ERDiagram](https://github.com/DatabaseGroup9/Documentation/blob/master/images/ER.jpg)

##### In MongoDB, the document is structured like this: (JSON Format)
```
{ "_id" : ObjectId("5b06bc8246391031d0a72538"), 
  "bookID" : "1257",
  "bookTitle" : "The Three Musketeers",
  "author" : {
    "authorID" : "v10500468",
    "fullName" : "Dumas, Alexandre",
    "firstName" : "Alexandre",
    "surName" : "Dumas",
    "title" : "" 
  },
  "cities" : [{ 
    "cityID" : "1002145",
    "name" : "George",
    "lat" : -33.963,
    "lon" : 22.46173 
   },... 
] }
```
##### In Neo4J, it is structured like this:(Cypher Text)
```
MATCH (c:City)<-[:MENTIONS]-(b:Book)<-[:AUTHORED]-(a:Author) return c,b,a limit 1
```
![Neo4j Structure](https://github.com/DatabaseGroup9/Documentation/blob/master/images/graph.png)
##### In MySQL, it is structured like this:(Database Schema Diagram)


### _How data is modeled in your application?_ <br>

![entity](https://github.com/DatabaseGroup9/Documentation/blob/master/images/ClassDiagram_Entity.png)
![data](https://github.com/DatabaseGroup9/Documentation/blob/master/images/ClassDiagram_Data.png)

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
