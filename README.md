## Introduction

I've decided to implement this API using spring boot and maven.

I've created several model classes for the Hotel object, as well as the Location
and Reviews. I've also decided to create a `SearchQuery` class to model the request.

I've also created a mocked database for hotels, that will return some sample entries.
For simplicity, I've created a single database, instead of separating the reviews one.

For the filtering part, I've made the logic so that any missing parameter will return
all the results for it. For instance, if the location is not specified, all hotels for
all locations are returned. In the same way, if the price range is not specified, all
hotels are returned, regardless of their price, as long as the other params constrains
are respected.

Regarding the sorting part of the API, I've also considered using a priority queue,
instead of sorting the list of hotels. However, the time complexity was equivalent,
since the default implementation of PriorityQueue in java populates the queue in
O(N logN) time. It is possible to create a queue that is populated in linear O(N) time,
like in Python with `heapq` or C++ `make_heap`, but I felt that implmenting my own
priority queue data structure from scratch would have gone against the "keep it simple"
tenet of the exercise.

## How to test the code

Make sure to have java and maven installed.

You can run unit tests using:
```shell
mvn test
```

explain how to test this:
* curl
* mvn test

You can run the application with:
```shell
mvn spring-boot:run
```

Once the application is up and running, you can send cURL requests to localhost.
You can either send a request will all the 4 parameters (location, checkinDate,
checkoutDate, priceRange), or remove any (or all) of them. Removing the checkin/checkout
dates won't affect the results, but removing the location or price range will.

Example request:
```shell
curl -X GET 'http://localhost:8080/hotels?location=Madrid&checkinDate=2023-07-21&checkoutDate=2023-07-21&priceRange=100,200'
```

Example response:
```json
[
  {
    "id":"A",
    "name":"Hotel A",
    "description":"Description A",
    "location":{
      "id":"1",
      "name":"Madrid"
    },
    "price":100,
    "imageUrl":"img",
    "reviews":[
      {
        "id":"1",
        "rating":5,
        "comment":"Comment"
      },
      {
        "id":"2",
        "rating":5,
        "comment":"Comment"
      }
    ]
  },
  {
    "id":"F",
    "name":"Hotel F",
    "description":"Description F",
    "location":{
      "id":"1",
      "name":"Madrid"
    },
    "price":120,
    "imageUrl":"img",
    "reviews":[
      {
        "id":"4",
        "rating":4,
        "comment":"Comment"
      },
      {
        "id":"3",
        "rating":4,
        "comment":"Comment"
      }
    ]
  },
  {
    "id":"C",
    "name":"Hotel C",
    "description":"Description C",
    "location":{
      "id":"1",
      "name":"Madrid"
    },
    "price":200,
    "imageUrl":"img",
    "reviews":[
      {
        "id":"5",
        "rating":1,
        "comment":"Comment"
      },
      {
        "id":"4",
        "rating":4,
        "comment":"Comment"
      }
    ]
  }
]
```
