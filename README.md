# Apache Gravitino + Spring Boot: Zero-to-One Quickstart 🚀

![Java](https://img.shields.io/badge/Java-17-orange.svg) ![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen.svg) ![Apache Gravitino](https://img.shields.io/badge/Apache_Gravitino-1.1.0-blue.svg) ![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)

## Overview
This repository provides a frictionless, zero-to-one integration guide demonstrating how to manage Lakehouse metadata using **Apache Gravitino** and a standard **Java Spring Boot** application.

This prototype builds a federated metadata tree (`Metalake -> Catalog -> Schema`) using the official Gravitino Java Client, simulating a real-world enterprise logistics data architecture.

## 🏗️ Architecture: The Metadata Tree
This application connects to a local Gravitino server via REST and establishes the following federated governance hierarchy:
1. **Metalake:** `fedex_global` (Top-level namespace for the enterprise)
2. **Catalog:** `eu_iceberg_data` (Connection to the specific data source/format)
3. **Schema:** `routing_schema` (Logical grouping for real-time tracking data)



## ⚙️ Prerequisites
Before you begin, ensure you have the following installed:
* **Docker** (To run the local Gravitino Server)
* **Java 17+** * **Maven** ## 🚀 Quick Start Guide (Time-to-Hello-World: < 3 mins)

### Step 1: Spin up the Apache Gravitino Server
Run the official Gravitino Docker image locally. This exposes the unified REST API on port `8090`.
```bash
docker run --rm -d -p 8090:8090 -p 9001:9001 apache/gravitino:latest
