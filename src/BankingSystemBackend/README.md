# Banking System Backend With Spring Security

### Prerequisites
* java 1.8.x
* maven 3.x

### Steps To Setup

**1. Clone the repository**
```bash
    git clone https://github.com/AbhayKatharotiya/BankingSystemBackend.git
```

**2. Build project**
```bash
    mvn clean install
``` 

**3. Run project** 
```bash
    java -jar target/backend-0.0.1-SNAPSHOT.jar
``` 
- Alternatively, you can run the app without packaging it using -
```bash
    mvn spring-boot:run
```

**4. Open url**  
  
Open following url.
```
   http://localhost:8080/ 
```

  ## Explore apis 

The app defines following APIs. 
 
```   
    POST /api/signup   
    POST /api/user
    GET /api/user
    PUT /api/user
    GET /api/account
    POST /api/transfer
    GET /api/transaction      
```

## Frontend Repository

- Please Find the frontend repository for the frontend code in angular. 
```link
    https://github.com/AbhayKatharotiya/BankingSystemFrontend.git
```
