## Dockerized
This folder contains the final project moved into a docker-compose environment
Extractor available at: http://127.0.0.1:8080/extract/get
Aggregator available at: http://127.0.0.1:9090/aggregator/findAll ecc..
## Differences
For the two Spring projects the only difference is the modification of some parameters in the application.properties file (like the kafka broker location)
## Run 
inside this folder run:
```console
docker compose -f 'docker-compose.yml' up --build --force-recreate
```