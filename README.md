# COVID-19 Vaccination Data Microservices

This repository contains a microservices-based project for extracting, processing, storing, and aggregating COVID-19 vaccination data in Lombardy (Italy).

## Project Overview

The system is composed of two Spring Boot microservices:

- **Data Extractor**: Retrieves data on demand from the Regione Lombardia open data portal, adds to it some additional information accessing a PostgreSQL database and provides it via Kafka to the second service.

- **Data Aggregator**: Consumes data via Kafka, stores it in MongoDB, and exposes APIs for aggregated metrics and insights.

## Architecture

- **Data Source**: Public JSON endpoint from Regione Lombardia  
  [https://hub.dati.lombardia.it/resource/vtzi-zmp8.json](https://hub.dati.lombardia.it/resource/vtzi-zmp8.json)
- **Kafka**: Communication channel between Extractor and Aggregator (topic: `SOMMINISTRAZIONI-VACCINI-COVID19-LOMBARDIA`)
- **PostgreSQL**: Used for province code lookups via `province_ita` table
- **MongoDB**: Data persistence for extracted records
- **Spring Boot**: Backend framework for both services
- **Docker Compose**: Used to define and manage the multi-container environment, including:
  - Kafka broker and Zookeeper
  - PostgreSQL with seed data
  - MongoDB instance
  - Both Spring Boot services (Extractor and Aggregator)

## API Endpoints

Extractor (available at `http://localhost:8080/extractor`)

- [GET /get](http://localhost:8080/extractor/get) — Trigger data extraction and Kafka transmission

Aggregator (available at `http://localhost:9090/aggregator`)

- [GET /findAll](http://localhost:9090/aggregator/findAll) — Retrieve all records
- [GET /totalFirstDose](http://localhost:9090/aggregator/totalFirstDose) — Total first dose count
- [GET /totalSecondDose](http://localhost:9090/aggregator/totalSecondDose) — Total second dose count
- [GET /totalFirstDoseByProvinceCode?provinceCode=MI](http://localhost:9090/aggregator/totalFirstDoseByProvinceCode?provinceCode=MI) — First dose total by province
- [GET /totalSecondDoseByProvinceCode?provinceCode=MI](http://localhost:9090/aggregator/totalSecondDoseByProvinceCode?provinceCode=MI) — Second dose total by province
- [GET /vaccinatedByProvinceCode?provinceCode=MI](http://localhost:9090/aggregator/vaccinatedByProvinceCode?provinceCode=MI) — Aggregated data by province
- [GET /vaccinatedForMunicipalityByProvinceCode?provinceCode=MI](http://localhost:9090/aggregator/vaccinatedForMunicipalityByProvinceCode?provinceCode=MI) — Aggregated data by municipality
- [GET /municipalityWithMostSingleDoseVaccinations](http://localhost:9090/aggregator/municipalityWithMostSingleDoseVaccinations)
- [GET /municipalityWithMostDoubleDoseVaccinations](http://localhost:9090/aggregator/municipalityWithMostDoubleDoseVaccinations)
- [GET /municipalityWithLeastSingleDoseVaccinations](http://localhost:9090/aggregator/municipalityWithLeastSingleDoseVaccinations)
- [GET /municipalityWithLeastDoubleDoseVaccinations](http://localhost:9090/aggregator/municipalityWithLeastDoubleDoseVaccinations)
- [GET /municipalityWithMostSingleDoseVaccinationsByProvinceCode?provinceCode=MI](http://localhost:9090/aggregator/municipalityWithMostSingleDoseVaccinationsByProvinceCode?provinceCode=MI)
- [GET /municipalityWithMostDoubleDoseVaccinationsByProvinceCode?provinceCode=MI](http://localhost:9090/aggregator/municipalityWithMostDoubleDoseVaccinationsByProvinceCode?provinceCode=MI)

## How to Run

To launch the full microservices architecture (Kafka, MongoDB, PostgreSQL, Extractor, and Aggregator), make sure Docker is installed and running on your system.

Then, from the root of this project, execute the following command:

```bash
docker compose up -d --build --force-recreate
```

Delete containers and volumes after with command:

```bash
docker-compose down -v
```
