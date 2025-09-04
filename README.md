# 🚀 Spring Boot CLI Cheatsheet

## 🔍 Project Management (Maven & Gradle)
| Command | Description |
|---------|-------------|
| `mvn spring-boot:run` | Runs the Spring Boot application. |
| `mvn clean install` | Compiles, tests, and packages the app. |
| `mvn package` | Builds a JAR/WAR file. |
| `mvn dependency:tree` | Shows the dependency hierarchy. |
| `mvn versions:display-dependency-updates` | Checks for dependency updates. |
| `mvn clean install -DskipTests` | Builds the project but skips tests. |
| `mvn spring-boot:build-image` | Creates a Docker image of the app. |
| `mvn test` | Runs tests. |

## 🏗 Spring Boot Initializer
| Command | Description |
|---------|-------------|
| `spring init --dependencies=web,data-jpa mysql-demo` | Creates a new Spring Boot project with Web & JPA dependencies. |
| `spring run app.groovy` | Runs a Spring Boot Groovy script. |

## 🔥 Running & Debugging
| Command | Description |
|---------|-------------|
| `mvn spring-boot:run -Dspring-boot.run.profiles=dev` | Runs with a specific profile. |
| `mvn spring-boot:run -Ddebug` | Enables debug mode. |
| `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"` | Changes the default port. |

## 🛠 Spring Boot Actuator
| Command | Description |
|---------|-------------|
| `curl http://localhost:8080/actuator/health` | Checks application health. |
| `curl http://localhost:8080/actuator/env` | Displays environment variables. |
| `curl http://localhost:8080/actuator/metrics` | Shows application metrics. |

## 📦 Dependency & Version Management
| Command | Description |
|---------|-------------|
| `mvn dependency:tree` | Shows dependency tree. |
| `mvn dependency:analyze` | Analyzes unused dependencies. |
| `mvn versions:display-dependency-updates` | Lists outdated dependencies. |
| `mvn versions:use-latest-releases` | Updates dependencies to the latest versions. |

## 🔄 Database & Migrations
| Command | Description |
|---------|-------------|
| `mvn flyway:migrate` | Runs Flyway migrations. |
| `mvn liquibase:update` | Applies Liquibase changes. |

## 🏗 Packaging & Deployment
| Command | Description |
|---------|-------------|
| `mvn package` | Packages the application as a JAR. |
| `java -jar target/myapp.jar` | Runs the built application. |
| `mvn clean package -DskipTests` | Builds the app without running tests. |

## 🔍 Checking Spring Boot Version
| Command | Description |
|---------|-------------|
| `mvn spring-boot:version` | Displays the current Spring Boot version. |

---

Maven follows a predefined build lifecycle:

1. **`validate`** - Checks if the project is correct.
2. **`compile`** - Compiles the source code.
3. **`test`** - Runs unit tests using **Surefire Plugin**.
4. **`package`** - Packages the compiled code into JAR/WAR.
5. **`verify`** - Runs integration tests (if defined).
6. **`install`** - Installs the artifact into the local repository.
7. **`deploy`** - Deploys to a remote repository.

---
