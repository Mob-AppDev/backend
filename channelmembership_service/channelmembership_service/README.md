# Channel Membership Service

This is a Spring Boot application that manages channel memberships for a Slack clone.

## Requirements

- Java 21
- PostgreSQL database

## Configuration

The application is configured to use a PostgreSQL database. The database connection settings can be found in `src/main/resources/application.properties`.

## Running the Application

### Important Note

This application requires the JVM argument `--enable-native-access=ALL-UNNAMED` to avoid warnings related to restricted method access in Tomcat.

### Using Maven

```bash
mvn spring-boot:run
```

The Spring Boot Maven plugin is configured to include the required JVM argument.

### Using the JAR file

```bash
java --enable-native-access=ALL-UNNAMED -jar channelmembership_service.jar
```

### In Production Environments

When deploying to production environments, make sure to include the `--enable-native-access=ALL-UNNAMED` JVM argument in your startup scripts or container configurations.

For example, in a Docker environment, you might include this in your Dockerfile:

```dockerfile
ENTRYPOINT ["java", "--enable-native-access=ALL-UNNAMED", "-jar", "app.jar"]
```

## Why is this JVM Argument Needed?

The application uses Tomcat as its embedded web server. Tomcat uses native libraries through JNI (Java Native Interface) for certain optimizations. Starting with Java 16, access to these native methods is restricted by default and requires explicit permission through the `--enable-native-access` JVM argument.

Without this argument, you might see warnings like:

```
WARNING: A restricted method in java.lang.System has been called
WARNING: java.lang.System::load has been called by org.apache.tomcat.jni.Library
WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
WARNING: Restricted methods will be blocked in a future release unless native access is enabled
```

These warnings indicate that Tomcat is trying to access native methods, which is restricted by default in newer Java versions.