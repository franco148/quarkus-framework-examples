# quarkus-framework-examples
All about quarkus

### Main MicroProfile Configuration APIs

`https://github.com/eclipse/microprofile-config`

| API | Description |
| ---- |-------------|
| @ConfigProperty | Binds the injection point with a configured value |
| ConfigProvider | Central class to access a Config |
| Config | Resolves the property value by searching through all the configuration sources |
| ConfigSource | Provides configuration values from a specific place |
| Converter | Converts a configured values from a String to a Java type |


### All configuration options
- quarkus.io/guides/all-config


### Quarkus Profiles
| DEV             | TEST     | PROD      | CUSTOM             |
|-----------------|----------|-----------|--------------------|
| %dev            | %test    | %prod     | custom             |
| mvn quarkus:dev | mvn test | java -jar | -Dquarkus.profile= |

In properties file we can have something like:

```properties
%dev.quarkus.log.console.level=DEBUG
%test.quarkus.log.console.level=TRACE
%prod.quarkus.log.console.level=SEVERE

%prod.books.genre=Information Technology
%staging.books.genre=Custom
```

### Packaging and Executing JARs
```bash
  mvn package
  mvn package -Dquarkus.package.type=jar
  mvn package -Dquarkus.package.type=legacy-jar
  mvn package -Dquarkus.package.type=uber-jar
  java -jar target/quarkus-run.jar 
  
  mvn package -Dquarkus.package.type=native
  mvn package -Pnative
  
```


