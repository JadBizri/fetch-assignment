# Receipt Processor Web Service

This project is a Dockerized Java Spring Boot web service that processes receipts and calculates points based on predefined rules. Follow the steps below to build, run, and test the application.

---

## Features

- **API Endpoints**:
  - `POST /receipts/process`: Process a receipt and return a unique ID.
  - `GET /receipts/{id}/points`: Retrieve the points for a specific receipt.

- **Points Rules**:  
  The points are calculated based on rules outlined in the project documentation.

---

## Prerequisites

Before running the project, ensure you have the following installed on your system:

- **Docker**: [Download Docker](https://www.docker.com/get-started)  
- (Optional) **Postman (Or something similar)**: [Download Postman](https://www.postman.com/downloads/) For testing.

---

## Quick Start with Docker

### 1. Pull the Docker Image

Pull the pre-built image from Docker Hub:

```
docker jadbizri/receipt-processor:latest
```

### 2. Run the Docker Container

Run the container and expose it on any port (usually ```8080```):

```
docker run -p 8080:8080 jadbizri/receipt-processor:latest
```
### 3. Test the API endpoints

Use a tool like **Postman** or **curl** to interact with the API.
- Process a receipt:

```
curl -X POST -H "Content-Type: application/json" \
-d '{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}' http://localhost:8080/receipts/process

```
Example Response:
```
{ "id": "7fb1377b-b223-49d9-a31a-5a02701dd310" }
```

- You can then copy the ID generated above to test the GET action:
```
curl http://localhost:8080/receipts/{id}/points
```

Expected Response:
```
{ "points": 28 }
```
