FROM gradle:jdk11 AS build

COPY . /home/gradle/

WORKDIR /home/gradle/

RUN gradle distTar --no-daemon

FROM openjdk:11-jre-slim

COPY --from=build /home/gradle/intro/build/distributions/intro.tar /opt/
RUN cd /opt/ && tar -xf intro.tar && rm intro.tar

ENTRYPOINT ["/opt/intro/bin/intro"]