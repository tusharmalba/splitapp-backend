# 💸 SplitApp Backend

SplitApp is a simple backend API for managing group expenses. It helps track who paid what, calculate balances between participants, and generate simplified settlements.

---

## 🚀 Features


- Add a new group expense with individual splits
- Retrieve all recorded expenses
- View individual balances (who owes whom)
- Automatically compute optimal settlements
- Update or delete specific expenses
- List all unique participants

---

## 🛠 Tech Stack

- **Java 23**
- **Spring Boot 3.5**
- **PostgreSQL** (hosted on Render/Cloud)
- **Spring Data JPA (Hibernate)**
- **Railway** (for deployment)
- **Postman** (for API testing)

---

## ⚙️ Getting Started (Local)

### Prerequisites

- Java 17+ (Java 23 recommended)
- Maven
- Git
- PostgreSQL or a cloud database (e.g. Render, Railway)

### Steps

```bash
git clone https://github.com/tusharmalba/splitapp-backend.git
cd splitapp-backend
./mvnw spring-boot:run


| Method | Endpoint                | Description                  |
| ------ | ----------------------- | ---------------------------- |
| POST   | `/expenses`             | Add a new expense            |
| GET    | `/expenses`             | Get all expenses             |
| PUT    | `/expenses/{id}`        | Update an expense by ID      |
| DELETE | `/expenses/{id}`        | Delete an expense            |
| GET    | `/expenses/balances`    | Get balances per person      |
| GET    | `/expenses/settlements` | Get suggested settlements    |
| GET    | `/expenses/people`      | Get list of all participants |

