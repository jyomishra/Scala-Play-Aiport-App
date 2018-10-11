# Scala-Play-Airport-Example

## Running

Run this using [sbt](http://www.scala-sbt.org/).  

```bash
sbt run
```

And then go to <http://localhost:9000> to see the running web application.

## Supported APIs

#### 1. Airport List By Country Name : {{HOST}}/airport/query?name={Country_Name}

##### URL : 
    http://localhost:9000/airport/query?name=italy

##### Response : 
    [
        {
            "id": "46181",
            "ident": "IT-0001",
            "a_type": "small_airport",
            "name": "PRATI NUOVI",
            "longitude": "45.26866494",
            "latitude": "7.947943211",
            "elevation": "768",
            "continent": "EU",
            "country": "IT",
            "region": "IT-21",
            "muncipality": "Mazzè (TO)",
            "schld_service": "no",
            "gps_code": "",
            "iata_code": "",
            "local_code": "",
            "home_link": "http://www.aviosuperficieilfalco.it/",
            "wikipedia_link": "",
            "keywords": ""
        },
        ......
    ]

#### 2. Airport List By Country Code : {{HOST}}/airport/query?name={Country_Code}

##### URL : 
    http://localhost:9000/airport/query?code=IT

##### Response :  
    [
            {
                "id": "46181",
                "ident": "IT-0001",
                "a_type": "small_airport",
                "name": "PRATI NUOVI",
                "longitude": "45.26866494",
                "latitude": "7.947943211",
                "elevation": "768",
                "continent": "EU",
                "country": "IT",
                "region": "IT-21",
                "muncipality": "Mazzè (TO)",
                "schld_service": "no",
                "gps_code": "",
                "iata_code": "",
                "local_code": "",
                "home_link": "http://www.aviosuperficieilfalco.it/",
                "wikipedia_link": "",
                "keywords": ""
            },
            ......
        ]

#### 3. Airport Reports Query

##### 1. Aggregated Report of the reports mentioned in 3.2, 3.3, 3.4, 3.5 : {{HOST}}/airport/report

##### URL :
    http://localhost:9000/airport/report
                             
##### Response :
    {
        "countriesWithMaxAirport": [.....],
        "countriesWithMinAirport": [.....],
        "listOfRunwayTypesPerCountry": {.....},
        "mostCommonRunwayIdentifications": [......]
    }

##### 2. Countries With Max Airports : {{HOST}}/airport/report/1

##### URL :
    http://localhost:9000/airport/report/1
                             
##### Response :
    [
        {
            "country": {
                "id": "302755",
                "code": "US",
                "name": "United States",
                "continent": "NA",
                "wikiLink": "http://en.wikipedia.org/wiki/United_States",
                "keywords": [
                    "US",
                    "United States",
                    "NA",
                    "http://en.wikipedia.org/wiki/United_States",
                    "America"
                ]
            },
            "airportCount": 21476
        },
        ............
    ]
    
##### 3. Countries With Min Airports : {{HOST}}/airport/report/2

##### URL :
    http://localhost:9000/airport/report/2
                             
##### Response :
    [
        {
            "country": {
                "id": "302698",
                "code": "JE",
                "name": "Jersey",
                "continent": "EU",
                "wikiLink": "http://en.wikipedia.org/wiki/Jersey",
                "keywords": [
                    "302698",
                    "JE",
                    "Jersey",
                    "EU",
                    "http://en.wikipedia.org/wiki/Jersey"
                ]
            },
            "airportCount": 1
        },
        ..............
     ]
    
##### 4. Type Of Runways Per Country : {{HOST}}/airport/report/3

##### URL :
    http://localhost:9000/airport/report/3
                             
##### Response :
    {
        "Gibraltar": [
            "ASP"
        ],
        "Haiti": [
            "ASP"
        ],
        ..............
    ]
    
##### 5. Most Common runway identifications : {{HOST}}/airport/report/4

##### URL :
    http://localhost:9000/airport/report/4
                             
##### Response :
    [
        "08U",
        "H02",
        "N80E",
        "04B",
        "110",
        "132",
        "13G",
        "01H",
        "H10",
        "18H"
    ]
