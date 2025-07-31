#FROM gradle:jdk11 AS build
#
#COPY . /home/gradle/
#
#WORKDIR /home/gradle/
#
#RUN gradle distTar --no-daemon
#
#FROM openjdk:11-jre-slim
#
#COPY --from=build /home/gradle/intro/build/distributions/intro.tar /opt/
#RUN cd /opt/ && tar -xf intro.tar && rm intro.tar
#
#ENTRYPOINT ["/opt/intro/bin/intro"]

# ---- Build stage ----
FROM gradle:8.7.0-jdk21 AS build
# Note: Ensure this Gradle version supports your project (Gradle 8.7+ supports JDK 21)

COPY . /home/gradle/

WORKDIR /home/gradle/

RUN gradle distTar --no-daemon

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre AS runtime
# Alternatively: openjdk:21-slim, but eclipse-temurin is stable and widely used

COPY --from=build /home/gradle/intro/build/distributions/intro.tar /opt/
RUN cd /opt/ && tar -xf intro.tar && rm intro.tar

ENTRYPOINT ["/opt/intro/bin/intro"]
