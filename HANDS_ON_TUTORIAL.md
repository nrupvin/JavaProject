# Java Project - Practical Hands-On Tutorial

## Tutorial 1: Modify Your First Java Code ✏️

Let's change the message in your API endpoint!

### Current Code
File: `src/main/java/com/example/HomeController.java`

```java
@GetMapping("/api/hello")
@ResponseBody
public String hello() {
    return "Hello from Spring MVC!";
}
```

### Step 1: Make the Change
Change the return message to:
```java
return "Hello, Vinay! Welcome to Java Development!";
```

### Step 2: Build & Run
```powershell
cd 'c:\Users\vinay\OneDrive\Documents\JavaProject\demo'
mvn clean package
mvn spring-boot:run
```

### Step 3: Verify
Open browser: `http://localhost:8080/api/hello`

You should see:
```
Hello, Vinay! Welcome to Java Development!
```

✅ **You just modified and redeployed Java code!**

---

## Tutorial 2: Add a New API Endpoint 🆕

Let's create a new endpoint that returns your information.

### Step 1: Edit HomeController.java

Add this new method to the HomeController class:

```java
@GetMapping("/api/about")
@ResponseBody
public String about() {
    return "This is Vinay's Java Application - Learning Spring Boot";
}
```

Your file should look like:
```java
package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/api/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring MVC!";
    }

    @GetMapping("/api/about")          // ← NEW ENDPOINT
    @ResponseBody
    public String about() {
        return "This is Vinay's Java Application - Learning Spring Boot";
    }
}
```

### Step 2: Build & Run
```powershell
mvn clean package
mvn spring-boot:run
```

### Step 3: Test New Endpoint
Open browser: `http://localhost:8080/api/about`

You should see:
```
This is Vinay's Java Application - Learning Spring Boot
```

### Step 4: Test Old Endpoints (They Still Work!)
- `http://localhost:8080/` - Home page
- `http://localhost:8080/api/hello` - Hello message

✅ **You just created a new API endpoint!**

---

## Tutorial 3: Create Your First Unit Test 🧪

Now let's write a test to verify your code works.

### Step 1: Create Test File

Create file: `src/test/java/com/example/HomeControllerTest.java`

```java
package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/api/hello"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello from Spring MVC!"));
    }

    @Test
    public void testAboutEndpoint() throws Exception {
        mockMvc.perform(get("/api/about"))
               .andExpect(status().isOk())
               .andExpect(content().string("This is Vinay's Java Application - Learning Spring Boot"));
    }

    @Test
    public void testHomePageLoads() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk());
    }
}
```

### Step 2: Run Tests
```powershell
mvn test
```

### Expected Output:
```
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

✅ **You just wrote and ran unit tests!**

### Understanding the Test Code:
- `@SpringBootTest` = Start the entire application for testing
- `@AutoConfigureMockMvc` = Provide a mock HTTP client
- `mockMvc.perform(get("/api/hello"))` = Send GET request
- `.andExpect(status().isOk())` = Check response is HTTP 200 (OK)
- `.andExpect(content().string(...))` = Check response matches expected text

---

## Tutorial 4: Modify Your HTML Home Page 🎨

Let's change what appears on the home page.

### Step 1: Check Current Home Page

File: `src/main/resources/templates/index.html`

### Step 2: Edit It

Replace the content with:
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vinay's Java Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border-radius: 10px;
        }
        h1 {
            text-align: center;
            font-size: 2.5em;
        }
        p {
            font-size: 1.2em;
            text-align: center;
            line-height: 1.8;
        }
        a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: white;
            color: #667eea;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: all 0.3s;
        }
        a:hover {
            background-color: #f0f0f0;
            transform: scale(1.05);
        }
        .links {
            text-align: center;
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <h1>🚀 Welcome to Vinay's Java Application!</h1>
    
    <p>
        This is a <strong>Spring Boot Web Application</strong> that demonstrates:
    </p>
    
    <p>
        ✅ Java Development<br>
        ✅ Spring Framework<br>
        ✅ Web APIs<br>
        ✅ HTML Templates<br>
    </p>
    
    <div class="links">
        <h3>Try These Endpoints:</h3>
        <a href="/api/hello">Say Hello</a>
        <a href="/api/about">About App</a>
    </div>
</body>
</html>
```

### Step 3: Build & View Changes
```powershell
mvn clean package
mvn spring-boot:run
```

Visit: `http://localhost:8080`

You should see a nicely formatted home page! 🎨

✅ **You customized your web page!**

---

## Tutorial 5: Add a Dependency (Add a Library) 📚

Let's add a library to your project.

### Current pom.xml
Your project currently has 2 dependencies (libraries):
- spring-boot-starter-web
- spring-boot-starter-thymeleaf

### Task: Add Logging Library

### Step 1: Edit pom.xml

In `pom.xml`, find the `<dependencies>` section and add:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-logging</artifactId>
</dependency>
```

Your dependencies section should look like:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-logging</artifactId>
    </dependency>
</dependencies>
```

### Step 2: Build
```powershell
mvn clean package
```

You'll see Maven downloading the new library:
```
Downloading from central: https://repo.maven.apache.org/maven2/...
Downloaded: logging-1.2.jar
```

### Step 3: Use It in Code

Update `HomeController.java`:

```java
package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String index() {
        logger.info("Home page accessed");
        return "index";
    }

    @GetMapping("/api/hello")
    @ResponseBody
    public String hello() {
        logger.info("Hello endpoint called");
        return "Hello from Spring MVC!";
    }

    @GetMapping("/api/about")
    @ResponseBody
    public String about() {
        logger.info("About endpoint called");
        return "This is Vinay's Java Application - Learning Spring Boot";
    }
}
```

### Step 4: Run and Check Logs
```powershell
mvn clean package
mvn spring-boot:run
```

When you visit endpoints, you'll see in the console:
```
2026-01-17T21:30:45.123-05:00  INFO com.example.HomeController : Hello endpoint called
```

✅ **You added a library and used it!**

---

## Tutorial 6: Build & Package for Production 📦

Let's create a standalone JAR that can run anywhere.

### Step 1: Build Complete Package
```powershell
mvn clean package
```

### Step 2: Find Your JAR File
```powershell
ls 'C:\Users\vinay\OneDrive\Documents\JavaProject\demo\target'
```

You'll see: `demo-1.0-SNAPSHOT.jar`

### Step 3: Run the JAR Directly
```powershell
cd 'C:\Users\vinay\OneDrive\Documents\JavaProject\demo'
java -jar target/demo-1.0-SNAPSHOT.jar
```

### Step 4: Verify It Works
Visit: `http://localhost:8080`

✅ **You created a production-ready application!**

### This JAR can be:
- ✅ Sent to anyone with Java installed
- ✅ Run on any OS (Windows, Mac, Linux)
- ✅ Deployed to cloud servers (AWS, Azure, Heroku)
- ✅ Put in Docker container

---

## Tutorial 7: Troubleshoot Build Failures 🐛

### Scenario 1: Compilation Error

**Error Message:**
```
[ERROR] error: not found: type HomeController
```

**Solution:**
- Check file is at: `src/main/java/com/example/HomeController.java`
- Check package declaration: `package com.example;`
- Rebuild: `mvn clean compile`

### Scenario 2: Test Failure

**Error Message:**
```
[ERROR] FAILURE: 1 test failed
```

**Solution:**
```powershell
# See details
mvn test -X

# Run specific test
mvn test -Dtest=HomeControllerTest
```

### Scenario 3: Missing Dependency

**Error:**
```
ERROR - Cannot find symbol: class Logger
```

**Solution:**
- Add to `pom.xml`
- Run: `mvn clean package`
- Maven will download it automatically

### Scenario 4: Port Already in Use

**Error:**
```
ERROR - Address already in use: bind
```

**Solution:**
```powershell
# Kill process on port 8080
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# OR use different port
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9090
```

---

## Your Progress Checklist ✅

- [ ] Read the full JAVA_PROJECT_GUIDE.md
- [ ] Modify the HelloController message
- [ ] Add a new API endpoint (/api/about)
- [ ] Run `mvn test` successfully
- [ ] Create and run HomeControllerTest.java
- [ ] Modify index.html with custom content
- [ ] View changes at http://localhost:8080
- [ ] Add a new dependency to pom.xml
- [ ] Build and run as JAR file
- [ ] Understand Maven lifecycle
- [ ] Understand Spring Boot structure

**Once you've completed these, you understand:**
- How to develop Java applications
- How to build with Maven
- How to test your code
- How to deploy applications
- How to add features

---

## Next Level Learning

### Option 1: Database
Add a database to store data:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
```

### Option 2: Security
Add login/authentication:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### Option 3: REST API
Build a complete REST API:
- GET (retrieve data)
- POST (create data)
- PUT (update data)
- DELETE (remove data)

---

**You're now on your way to becoming a Java developer! 🎓**
