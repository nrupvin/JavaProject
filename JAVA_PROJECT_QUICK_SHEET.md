# Java Project Setup - Quick Cheat Sheet

## Your Maven Alias (Save Time!)

Add this to your PowerShell profile so you can just type `mvn` instead of the long path:

```powershell
# Open PowerShell profile
notepad $PROFILE

# Add this line:
Set-Alias -Name mvn -Value 'C:\Users\vinay\.m2\maven\apache-maven-3.9.12\bin\mvn.cmd'

# Save and restart PowerShell
```

Now you can just use:
```powershell
mvn clean package
mvn spring-boot:run
mvn test
```

---

## 5-Minute Quick Start

### 1️⃣ Navigate to Project
```powershell
cd 'c:\Users\vinay\OneDrive\Documents\JavaProject\demo'
```

### 2️⃣ Build Project
```powershell
mvn clean package
```
**Output:** Will compile and create `target\demo-1.0-SNAPSHOT.jar`

### 3️⃣ Run Application
```powershell
mvn spring-boot:run
```
**Output:** You'll see `Tomcat started on port(s): 8080`

### 4️⃣ Open Browser
```
http://localhost:8080
```

### 5️⃣ Stop Application
```powershell
Press Ctrl+C
```

---

## Project Structure Explained

```
YOUR PROJECT
│
├── pom.xml                    ← BUILD CONFIGURATION (Read this!)
│
├── src/
│   ├── main/
│   │   ├── java/com/example/  ← YOUR JAVA CODE
│   │   │   ├── Main.java      ← App startup
│   │   │   └── HomeController.java ← Web pages/APIs
│   │   │
│   │   └── resources/
│   │       ├── templates/     ← HTML FILES (index.html)
│   │       ├── static/        ← CSS, JS, Images
│   │       └── public/        ← Public assets
│   │
│   └── test/
│       └── java/com/example/  ← TEST CODE
│
├── target/                    ← AUTO GENERATED (don't edit)
│   ├── classes/              ← Compiled .class files
│   ├── dependency/           ← Downloaded libraries
│   └── demo-1.0-SNAPSHOT.jar ← Your packaged app
│
└── JAVA_PROJECT_GUIDE.md      ← FULL GUIDE (Read this!)
```

---

## Maven Lifecycle (What Happens When You Run mvn)

```
mvn clean package
  │
  ├─ clean          → Delete target/ folder
  ├─ validate       → Check pom.xml is valid
  ├─ compile        → Compile .java → .class
  ├─ test           → Run unit tests
  └─ package        → Create .jar file
```

---

## Build vs Run vs Test

| Command | Does What | When to Use |
|---------|-----------|------------|
| `mvn clean` | Deletes old build | Clean start |
| `mvn compile` | Compiles code only | Quick syntax check |
| `mvn package` | Builds complete app | Before deploying |
| `mvn test` | Runs unit tests | Verify code works |
| `mvn spring-boot:run` | Runs app (no .jar needed) | Development |
| `java -jar demo.jar` | Runs packaged app | Production |

---

## Your Application Endpoints

| URL | What It Does | File |
|-----|-------------|------|
| `http://localhost:8080/` | Shows home page | `index.html` |
| `http://localhost:8080/api/hello` | Returns text "Hello from Spring MVC!" | `HomeController.java` |

---

## Common Tasks

### Task: Change the "Hello" message
```
File: src\main\java\com\example\HomeController.java

Change:
    return "Hello from Spring MVC!";
To:
    return "Hello, Vinay!";

Then:
    mvn clean package
    mvn spring-boot:run
    Visit: http://localhost:8080/api/hello
```

### Task: Change the home page
```
File: src\main\resources\templates\index.html

Edit the HTML content

Then:
    mvn clean package
    mvn spring-boot:run
    Visit: http://localhost:8080/
```

### Task: Add a new API endpoint
```
File: src\main\java\com\example\HomeController.java

Add new method:
    @GetMapping("/api/info")
    @ResponseBody
    public String info() {
        return "Project information";
    }

Then:
    mvn clean package
    mvn spring-boot:run
    Visit: http://localhost:8080/api/info
```

### Task: Add a new dependency (e.g., Database library)
```
File: pom.xml

In <dependencies> section add:
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

Then:
    mvn clean package (Maven downloads it automatically!)
```

---

## Understanding the Build Output

```
[INFO] Scanning for projects...
[INFO] Building demo 1.0-SNAPSHOT
[INFO] Compiling 2 source files...        ← Compiled your Java files
[INFO] Running tests...                   ← Ran your tests
[INFO] Building jar...                    ← Created .jar file
[INFO] BUILD SUCCESS                      ← Everything worked!
```

---

## Troubleshooting

### Error: "Cannot find Java"
**Fix:** Make sure Java 17 is installed:
```powershell
java -version
```
Should show: `java version "17.0.17"`

### Error: "Port 8080 already in use"
**Fix:** Either:
1. Kill the process using port 8080, or
2. Use different port:
```powershell
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9090
```
Then visit: `http://localhost:9090`

### Error: "mvn command not found"
**Fix:** Use full path OR create alias (see above)

### Error: Tests are failing
**Fix:** Run with verbose output:
```powershell
mvn test -X
```

---

## Pro Tips

✅ **Always do clean build before running:**
```powershell
mvn clean package
mvn spring-boot:run
```

✅ **Check what Maven downloaded:**
```powershell
mvn dependency:tree
```

✅ **Run tests separately:**
```powershell
mvn test
```

✅ **Keep VS Code open** - Makes coding faster with IntelliSense

✅ **Use git** to track changes:
```powershell
git init
git add .
git commit -m "Initial commit"
```

---

## Files You Just Created

📄 **JAVA_PROJECT_GUIDE.md** - Full detailed guide (you're reading it!)
📄 **JAVA_PROJECT_QUICK_SHEET.md** - This cheat sheet

Keep these for reference!

---

**All set! Your Java project is ready to go! 🚀**
