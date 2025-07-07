#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=com.francofral \
        -DprojectArtifactId=quarkus-rest-book \
        -DclassName="org.francofral.quarkus.BookResource" \
        -Dpath="/api/books" \
        -Dextensions="resteasy-jsonb"
