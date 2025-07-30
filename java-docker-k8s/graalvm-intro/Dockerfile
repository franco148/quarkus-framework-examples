FROM oracle/graalvm-ce:20.0.0-java11 AS build

RUN gu install native-image

COPY graalvm-intro.jar /build/

RUN cd build && native-image --static -jar graalvm-intro.jar -H:Name=output

FROM scratch

COPY --from=build /build/output /opt/output

CMD ["/opt/output"]




