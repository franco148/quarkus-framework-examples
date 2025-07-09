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

### Building and executing Docker Images for JARs

```bash
  mvn quarkus:add-extension -Dextensions="container-image-docker"
  
  mvn package -Dquarkus.container-image.build=true
  
  mvn package -Dquarkus.container-image.build=true
              -Dquarkus.package.type=jar
              
  mvn package -Dquarkus.container-image.build=true
              -Dquarkus.package.type=legacy-jar
              -Dquarkus.container-image.tag=jvm
              
  # At the end, I used the following command because I was getting
  # an exception with message: [ERROR] 	java.lang.IllegalArgumentException: SRCFG00039: 
  # The config property quarkus.package.jar.type with the config value "jar" threw an Exception whilst being 
  # converted SRCFG00049: Cannot convert jar to enum class io.quarkus.deployment.pkg.PackageConfig$JarConfig$JarType, 
  # allowed values: fast-jar,legacy-jar,uber-jar,mutable-jar
  
  mvn package -Dquarkus.container-image.build=true -Dquarkus.package.type=fast-jar -Dquarkus.container-image.tag=jvm            
              
              
  docker run -i --rm -p 8080:8080 francofral/quarkus-rest-book:1.0-SNAPSHOT
  docker run -i --rm -p 8080:8080 francofral/quarkus-rest-book:jvm
```


### Containerizing the app

- `mvn package -Dquarkus.container-image.build=true -Dquarkus.package.type=fast-jar -Dquarkus.container-image.tag=jvm`

- `docker image ls`
- `docker run -i --rm -p 8080:8080 fracofral/<image>:jvm`

### Building and executing a Linux Binary

```bash
  mvn package -Dquarkus.container-image.build=true
              -Dquarkus.package.type=native
              -Dquarkus.native.container-build=true
              -Dquarkus.container-image.tag=native
              
  docker run -i --rm -p 8080:8080 francofral/quarkus-rest-book:native            
```










