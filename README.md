[![Build Status](https://travis-ci.org/DatabaseGroup9/ProjectGutenberg_G9.svg?branch=master)](https://travis-ci.org/DatabaseGroup9/ProjectGutenberg_G9)

### Link to the application:
http://207.154.232.47:8080/group9/#!/home
# Gutenberg-Geolocation

### _Which database engines are used?_ <br>
- MongoDB (Document-based database). Verion 3.4.10.
- Neo4J (Graph database). Version 3.4.0 (current latest version).

_extra:_
- MySQL (Relational Database). Version 5.7.22.

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
![MySQLSchemaDiagram](https://github.com/DatabaseGroup9/Documentation/blob/master/images/mysqlSchemaModelingDiagram.png)

### _How data is modeled in your application?_ <br>
> _We make used of entities/Java objects to map the collections that we have in the database with its attributes as shown in the Class Diagram below. This objects are sent to the REST API and displayed in the frontend._
![entity](https://github.com/DatabaseGroup9/Documentation/blob/master/images/ClassDiagram_Entity.png)
> _This is the class diagram representing the data layer of the application, wherein we make connection to the respective database and perform the query requested by the user._
![data](https://github.com/DatabaseGroup9/Documentation/blob/master/images/ClassDiagram_Data.png)

See more in-detailed implementation [here](https://github.com/DatabaseGroup9/Documentation).

### _How the data is imported?_ <br>
> _The data was fetched from Project Gutenberg Data Collection and processed by our PGParser that extracted the relevant data for the final project such as the book title, authors, and cities mentioned, see [PGParser](https://github.com/DatabaseGroup9/PGParser) for complete details. We created DigitalOcean droplets for each database, and imported the returned csv files from PGParser to MongoDB and Neo4J by using a Java Application project here for MongoDB, the GUI for [Neo4J](https://github.com/DatabaseGroup9/Documentation/blob/master/Neo4j.md), [parseMaster](https://github.com/DatabaseGroup9/parseMaster) and phpAdmin for MySQL. During the development of the application, we have encountered the necessity of refactoring the data to make it cleaner and removed duplicates, removing errornous data. We made data cleaner that generates new set of nicely formed data, see the implementation in [dataimport](https://github.com/DatabaseGroup9/dataimport)._

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
***
ID  | MySQL                                                 |
----|---------------------------------------------------------|
MS1  | "SELECT bookTitle,fullName FROM BooksTable WHERE name = '" + city + "'" |
MS2  | "SELECT name,lat,lon FROM BooksTable WHERE bookTitle = '" + bookTitle + "'"| 
MS3  | "SELECT bookTitle,fullName,name,lat,lon FROM BooksTable WHERE fullName = '" + authorFullName + "'" | 
MS4  | "SELECT bookTitle,fullName,name,lat,lon FROM BooksTable WHERE lat = " + lat + " AND lon = " + lon |
***

### Measuremet of Application Behavior

* https://github.com/DatabaseGroup9/Performace-Tests-With-JMeter/blob/master/DATABASE-Benchmark/BENCHMARK_BEFORE.md
* https://github.com/DatabaseGroup9/Performace-Tests-With-JMeter/blob/master/DATABASE-Benchmark/BENCHMARK_AFTER.md

### Discussion

Blabla

#### A. Query Runtime is influenced by the DB engine <br>



Quering The Databases

|   | Story 1 avg. ms |Story 2 avg. ms  | Story 3 avg. ms  | Story 4 avg. ms |
|---|---|---|---|---|
|  MongoDB | 689  | 42  |  49 | 234  |
|  Neo4J |  44  |  15 | 23  | 10  |
|  MySQL | 22  | 5  | 16  | 8  |



#### B. Query Runtime is influenced by the application frontend <br>

Quering The REST API

|   | Story 1 avg. ms |Story 2 avg. ms  | Story 3 avg. ms  | Story 4 avg. ms |
|---|---|---|---|---|
|  MongoDB | 5680  | 349  | 1072  | 3944  |
|  Neo4J |  1462 | 327  |  736 | 601  |
|  MySQL |  45 |  39 |  394 | 215  |
|  Stub |  61 |  36 |  42 | 41  |

Application Overhead 

*REST API time - query in the database*

|   | Story 1 avg. ms |Story 2 avg. ms  | Story 3 avg. ms  | Story 4 avg. ms |
|---|---|---|---|---|
|  MongoDB | 4991  | 307  | 1023  | 3710  |
|  Neo4J | 1323  |  222 | 572  | 562  |
|  MySQL | 23  | 34  | 378  | 207  |


### Comments to the results

We can conslude that the Application has an overead which we belive has to do with processig the data returned from the databases. Becuase of how we have modeled the data in MongoDB there is more data to be processed in bytes than for example MySQL. This can be a reason for the huge query times for MongoDB.

### _Recommendation_ <br>
_which database engine to use in such a project for production_


OBS Remember to use the approprite diagram notaions for documentation.

##### Additional Documentation:
> See here: https://github.com/DatabaseGroup9




### Problems we encounterd

_1) Problem to create spatial index and queries in MongoDB_

Although we had out model structured like this in MongoDB we could net get the spatial index and queries to work. WE tried with the following queries.

```/* 5 */
{
    "_id" : ObjectId("5b045038c7db703de897fae4"),
    "bookID" : "3631",
    "title" : "On the Significance of Science and Art",
    "author" : "On the Significance of Science and Art",
    "cities" : [ 
        {
            "coordinates" : [ 
                27.81049, 
                79.79185
            ],
            "type" : "Point"
        }, 
        {
            "coordinates" : [ 
                28.16951, 
                94.8006
            ],
            "type" : "Point"
        }, 
        {
            "coordinates" : [ 
                -12.46113, 
                130.84185
            ],
            "type" : "Point"
        }, 
        {
            "coordinates" : [ 
                50.50301, 
                13.63617
            ],
            "type" : "Point"
        }, 
        {
            "coordinates" : [ 
                -32.69844, 
                -57.62693
            ],
            "type" : "Point"
        }, 
        {
            "coordinates" : [ 
                8.42729, 
                -82.43085
            ],
            "type" : "Point"
        }, 
        {
            "coordinates" : [ 
                26.21591, 
                -98.32529
            ],
            "type" : "Point"
        }, 
        {
            "coordinates" : [ 
                54.19609, 
                37.61822
            ],
            "type" : "Point"
        }, 
        {
            "coordinates" : [ 
                40.76078, 
                -111.89105
            ],
            "type" : "Point"
        }, 
        {
            "coordinates" : [ 
                64.83778, 
                -147.71639
            ],
            "type" : "Point"
        }
    ]
}
``` 


```db.colname.createIndex({"cities.location":"2dsphere"})

db.colname.find(
   {
     location:
       { $near :
          {
            $geometry: { type: "Point",  coordinates: [ -73.9667, 40.78 ] },
            $minDistance: 1000,
            $maxDistance: 5000
          }
       }
   }
)

```

_Work around_

For Story 4, *Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation*, we have implemented this in MongoDB so it only returns a result if the geolocation is match exaclty.


_Spacial index Neo4J_

We looked into using a spatial index plugin for Neo4J. But because we preceived it as a steep learning curve to be able to implement it correctly we only query using points which is supported in Neo4J and Cypher.


