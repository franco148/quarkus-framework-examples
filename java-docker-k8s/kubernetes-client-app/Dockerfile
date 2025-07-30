FROM openjdk:21-jdk-slim

# This automatically extracts the archive
ADD kubernetes-client-app.tar /opt

ENTRYPOINT ["/opt/kubernetes-client-app/bin/kubernetes-client-app"]

