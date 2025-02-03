This repository contains Spring Boot microservice:

- **Service Shuffle**: Handles number shuffling requests.

---

## Service Shuffle

### 📋 **Description**

The **Service Shuffle** API accepts an integer and returns a shuffled sequence of numbers from `1` to `n`.

### ⚙️ **Tech Stack**
- **Java 11**
- **Spring Boot 3.4.2**
- **Maven**

### 📥 **API Endpoints**

#### 1️⃣ **Shuffle Numbers**
- **URL:** `POST /shuffle`
- **Request Body:**
  ```json
  {
    "number": 5
  }
  ```
- **Response:**
  ```
  {
    3, 1, 5, 2, 4
  }
  ```
- **Validation:** `number` must be between `1` and `1000`.

### 🧪 **Running Tests**
```bash
  mvn clean test
```

### **Run Locally**
```bash
  mvn spring-boot:run
```

### 🔗 **Logs Integration**
Logs requests asynchronously to **Service Log** using REST calls.

---

## ⚡ How to Run Both Services Together

1. **Start Service Log:**
   ```bash
   cd service-log
   mvn spring-boot:run
   ```

2. **Start Service Shuffle:**
   ```bash
   cd service-shuffle
   mvn spring-boot:run
   ```

3. **Test the Shuffle API:**
   ```bash
   curl -X POST http://localhost:8080/shuffle -H "Content-Type: application/json" -d '{"number": 5}'
   ```

---

## 🛠️ **Troubleshooting**
- Ensure both services are running.
- Check logs for error messages.
- Verify the `service-log.url` in Service Shuffle's `application.properties`.

---