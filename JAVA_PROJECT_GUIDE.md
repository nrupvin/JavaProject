# Complete Java Project Setup & Build Guide

## 📁 Part 1: Understanding Your Project Structure

Your Java project follows a standard Maven structure:

```
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── Main.java              (Application entry point)
│   │   │       └── HomeController.java    (Web handler)
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── index.html             (HTML template)
│   │       ├── static/                    (CSS, JS, images)
│   │       └── public/                    (Public assets)
│   └── test/
│       └── java/                          (Unit tests)
├── target/                                (Build output - auto generated)
│   ├── classes/                           (Compiled .class files)
│   └── dependency/                        (Downloaded libraries)
└── pom.xml                                (Project configuration)
```

### What Each Part Does:

| Folder | Purpose |
|--------|---------|
| `src/main/java` | Your Java source code |
| `src/main/resources` | Configuration files, templates, static assets |
| `src/test/java` | Test code for your application |
| `target` | Generated - contains compiled code & dependencies |
| `pom.xml` | **Build configuration** - tells Maven how to build your project |

---

## 🔧 Part 2: Understanding pom.xml (Project Configuration)

Your `pom.xml` is the blueprint that tells Maven:
- What your project is called
- What libraries it needs
- How to build it
- What Java version to use

### Key Sections Explained:

#### 1. **Basic Project Information**
```xml
<groupId>com.example</groupId>              <!-- Organization/package name -->
<artifactId>demo</artifactId>               <!-- Project name -->
<version>1.0-SNAPSHOT</version>             <!-- Current version -->
```
- **groupId**: Like a domain (com.example)
- **artifactId**: The project name
- **version**: Current version (SNAPSHOT = development)

#### 2. **Java Version**
```xml
<maven.compiler.source>17</maven.compiler.source>  <!-- Write code for Java 17 -->
<maven.compiler.target>17</maven.compiler.target>  <!-- Compile to Java 17 -->
<java.version>17</java.version>                    <!-- Use Java 17 runtime -->
```

#### 3. **Dependencies** (Libraries Your Project Needs)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
This says: "I need the Spring Boot Web library"

Maven automatically:
- Downloads these libraries from Maven Central Repository
- Places them in `target/dependency/`
- Adds them to your classpath when building/running

#### 4. **Build Plugins** (Tools for Building)
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```
This gives Maven the ability to run Spring Boot applications.

---

## 🛠️ Part 3: Building Your Project

### What "Building" Means:
1. **Compile** - Converts `.java` files to `.class` files (machine readable)
2. **Download Dependencies** - Gets all required libraries
3. **Package** - Bundles everything into a `.jar` file

### Build Commands:

#### **Option 1: Clean Build** (Recommended)
```powershell
cd 'c:\Users\vinay\OneDrive\Documents\JavaProject\demo'
C:\Users\vinay\.m2\maven\apache-maven-3.9.12\bin\mvn.cmd clean package
```

**What happens:**
```
clean → Deletes old target/ folder
compile → Compiles .java to .class
package → Creates demo-1.0-SNAPSHOT.jar
```

#### **Option 2: Build Without Cleaning**
```powershell
C:\Users\vinay\.m2\maven\apache-maven-3.9.12\bin\mvn.cmd package
```

#### **Output Location:**
After building, find your compiled code at:
- **Classes**: `C:\Users\vinay\OneDrive\Documents\JavaProject\demo\target\classes\com\example\`
- **JAR File**: `C:\Users\vinay\OneDrive\Documents\JavaProject\demo\target\demo-1.0-SNAPSHOT.jar`

---

## ▶️ Part 4: Running Your Project

Your project is a **Spring Boot Web Application** that starts a web server on port 8080.

### Method 1: Using Maven (Easiest)
```powershell
C:\Users\vinay\.m2\maven\apache-maven-3.9.12\bin\mvn.cmd spring-boot:run
```

This:
1. Compiles code automatically
2. Starts Tomcat web server on port 8080
3. Loads your application

**Output you'll see:**
```
Tomcat started on port(s): 8080 (http)
Started Main in 3.337 seconds
```

### Method 2: Using Java Directly
```powershell
cd 'C:\Users\vinay\OneDrive\Documents\JavaProject\demo'
C:\Program Files\Microsoft\jdk-17.0.17.10-hotspot\bin\java.exe -jar target\demo-1.0-SNAPSHOT.jar
```

### Method 3: Using VS Code
- Click the **Run** icon next to your main() method in Main.java
- Or use **Debug** for step-by-step execution

---

## 🌐 Part 5: Accessing Your Running Application

Once running (you see "Tomcat started on port(s): 8080"), open your browser:

### Endpoint 1: Home Page
```
http://localhost:8080
```
Returns: HTML from `index.html`

### Endpoint 2: API
```
http://localhost:8080/api/hello
```
Returns: `Hello from Spring MVC!`

### How This Works:
1. Your browser sends HTTP request
2. Tomcat server receives it
3. **HomeController** processes it:
   - `GET /` → `index()` method → returns "index" template
   - `GET /api/hello` → `hello()` method → returns JSON text
4. Browser displays response

---

## 🧪 Part 6: Testing Your Project

### Why Test?
Tests verify your code works correctly WITHOUT manually checking everything.

### Types of Tests:

#### **Unit Tests** (Test individual methods)
Example test file: `src/test/java/com/example/HomeControllerTest.java`

```java
@SpringBootTest
public class HomeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/api/hello"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello from Spring MVC!"));
    }
}
```

This test:
1. Sends GET request to `/api/hello`
2. Expects HTTP 200 (OK) status
3. Expects response text to match "Hello from Spring MVC!"

#### **Running Tests:**
```powershell
# Run all tests
C:\Users\vinay\.m2\maven\apache-maven-3.9.12\bin\mvn.cmd test

# Run specific test
C:\Users\vinay\.m2\maven\apache-maven-3.9.12\bin\mvn.cmd test -Dtest=HomeControllerTest

# Run tests during build
C:\Users\vinay\.m2\maven\apache-maven-3.9.12\bin\mvn.cmd clean package
```

### Test Output:
```
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## 💻 Part 7: Understanding Your Application Code

### Main.java (Entry Point)
```java
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
```

**What it does:**
- `@SpringBootApplication` = Enable Spring Boot features
- `main()` = Starting point when you run the app
- `SpringApplication.run()` = Start the embedded Tomcat server

### HomeController.java (Web Handler)
```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";  // Returns index.html template
    }

    @GetMapping("/api/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring MVC!";  // Returns plain text
    }
}
```

**What it does:**
- `@Controller` = This class handles web requests
- `@GetMapping("/")` = When browser visits `/`, run `index()` method
- `@GetMapping("/api/hello")` = When browser visits `/api/hello`, run `hello()` method
- `@ResponseBody` = Return the text directly (not a template)

---

## 📊 Part 8: Complete Build & Run Workflow

### Step-by-Step Process:

```
1. SETUP (One-time)
   ├─ Install Java 17 ✓
   ├─ Install Maven ✓
   └─ Create project (already done)

2. DEVELOPMENT
   ├─ Edit Java files (.java)
   ├─ Edit HTML/CSS files
   └─ Edit pom.xml if adding libraries

3. BUILD
   └─ mvn clean package
      ├─ Clean old builds (delete target/)
      ├─ Download dependencies
      ├─ Compile .java → .class
      └─ Package into .jar

4. TEST
   └─ mvn test
      ├─ Run all unit tests
      └─ Show test results

5. RUN
   └─ mvn spring-boot:run (Development)
      OR
      java -jar target/demo-1.0-SNAPSHOT.jar (Production)
      ├─ Start web server on port 8080
      └─ Load application

6. VERIFY
   └─ Open http://localhost:8080
      └─ Check if it works
```

---

## 🐛 Part 9: Common Issues & Solutions

### Issue 1: "mvn command not found"
**Solution:** Use full path:
```powershell
C:\Users\vinay\.m2\maven\apache-maven-3.9.12\bin\mvn.cmd clean package
```

### Issue 2: "Cannot find symbol: class Main"
**Solution:** Ensure file is in correct folder:
```
src/main/java/com/example/Main.java ✓
```

### Issue 3: "Port 8080 already in use"
**Solution:** Either:
- Stop other application using port 8080
- Or use different port:
```powershell
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

### Issue 4: Tests fail
**Solution:**
```powershell
# See what failed
mvn test

# Run with verbose output
mvn test -X
```

---

## 📝 Quick Reference Commands

| Task | Command |
|------|---------|
| Build project | `mvn clean package` |
| Run tests | `mvn test` |
| Start app | `mvn spring-boot:run` |
| Run JAR | `java -jar target/demo-1.0-SNAPSHOT.jar` |
| See dependencies | `mvn dependency:tree` |
| Compile only | `mvn compile` |
| Clean build folder | `mvn clean` |

---

## 🎯 Summary

### Key Concepts:
1. **pom.xml** = Project blueprint (what to build, dependencies, Java version)
2. **Build** = Compile Java code + download libraries
3. **Test** = Verify code works automatically
4. **Run** = Start the application server
5. **Access** = Open browser to http://localhost:8080

### Your Project Flow:
```
Write Code → Build → Test → Run → Access via Browser
```

### Maven Advantages:
- ✅ Automatic dependency management
- ✅ Standardized project structure
- ✅ Easy testing
- ✅ Simple build with one command
- ✅ Works on any OS (Windows, Mac, Linux)

---

## 🚀 Next Steps

1. **Try modifying code:**
   - Edit `HomeController.java` to change the message
   - Edit `index.html` to change the home page
   - Run `mvn clean package` and `mvn spring-boot:run`

2. **Create tests:**
   - Add test files in `src/test/java/`
   - Run `mvn test`

3. **Add features:**
   - Create new `@Controller` classes
   - Add dependencies in `pom.xml`
   - Run `mvn clean package`

Happy coding! 🎉
