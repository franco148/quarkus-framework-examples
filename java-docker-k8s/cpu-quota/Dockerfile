# FROM openjdk:11-jre-slim
FROM openjdk:21-slim

# 8u131 added cpu_share support
# 8u191 added quota support, why don't you try 8u181?
# try versions 8u102-jre 8 and 11

# This automatically extracts the archive
ADD cpu-quota.tar /opt

ENTRYPOINT ["/opt/cpu-quota/bin/cpu-quota"]

