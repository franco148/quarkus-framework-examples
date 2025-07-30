FROM adoptopenjdk/openjdk21:alpine-jre

RUN apk add --no-cache jattach --repository http://dl-cdn.alpinelinux.org/alpine/edge/community/

# This automatically extracts the archive
ADD debug-app.tar /opt

ENTRYPOINT ["/opt/debug-app/bin/debug-app"]

