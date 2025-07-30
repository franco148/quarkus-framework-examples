#FROM openjdk:11-jre-slim
FROM openjdk:21-slim
# try versions 8u102-jre 8 and 11

# This automatically extracts the archive
ADD cpu-shares.tar /opt

ENTRYPOINT ["/opt/cpu-shares/bin/cpu-shares"]

