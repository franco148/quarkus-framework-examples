#FROM openjdk:11-jre-slim
FROM adoptopenjdk/openjdk11:alpine-jre

ADD signals.tar /opt
ADD signals.jar /opt
ADD start.sh /opt/

#CMD "/opt/signals/bin/signals"
CMD "/opt/start.sh"

