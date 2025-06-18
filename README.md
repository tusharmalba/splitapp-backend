# 💸 SplitApp Backend

SplitApp is a backend REST API built with **Spring Boot** and **PostgreSQL** for managing group expenses. It helps track who paid, how much was spent, calculates balances between users, and provides smart settlement suggestions.

---

## 🛠 Tech Stack

- Java 23
- Spring Boot 3.5
- PostgreSQL (via Render or Railway)
- Spring Data JPA
- RESTful API
- Deployed on Railway
- Postman for API Testing

---

## 🔧 Configuration

Set up your `src/main/resources/application.properties` file as below (with your actual credentials):

```properties
spring.application.name=splitapp
spring.datasource.url=jdbc:postgresql://<your-db-host>:5432/splitapp_dbzi
spring.datasource.username=<your-db-username>
spring.datasource.password=<your-db-password>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

#Steps
git clone https://github.com/tusharmalba/splitapp-backend.git
cd splitapp-backend
./mvnw spring-boot:run

App is deployed at:
https://splitapp-backend-production.up.railway.app

| Method | Endpoint                | Description                  |
| ------ | ----------------------- | ---------------------------- |
| POST   | `/expenses`             | Add a new expense            |
| GET    | `/expenses`             | Get all expenses             |
| PUT    | `/expenses/{id}`        | Update an expense by ID      |
| DELETE | `/expenses/{id}`        | Delete an expense            |
| GET    | `/expenses/balances`    | Get balances per person      |
| GET    | `/expenses/settlements` | Get suggested settlements    |
| GET    | `/expenses/people`      | Get list of all participants |


➕ POST /expenses
json

{
  "description": "Dinner",
  "amount": 600,
  "paidBy": "Tushar",
  "splits": {
    "Tushar": 200,
    "Sanket": 200,
    "Om": 200
  }
}

🔁 PUT /expenses/{id}
json

{
  "description": "Updated Dinner",
  "amount": 900,
  "paidBy": "Om",
  "splits": {
    "Tushar": 300,
    "Sanket": 300,
    "Om": 300
  }
}

👨‍💻 Author
Tushar Kadam Malba
GitHub: @tusharmalba

