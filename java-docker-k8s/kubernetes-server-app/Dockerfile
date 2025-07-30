FROM openjdk:21-jdk-slim

# This automatically extracts the archive
ADD kubernetes-server-app.tar /opt

ENTRYPOINT ["/opt/kubernetes-server-app/bin/kubernetes-server-app"]

