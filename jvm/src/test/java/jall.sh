#!/usr/bin/env bash

clear

images=("openjdk:7-alpine" "openjdk:8-alpine" "openjdk:11-slim" "openjdk:13-alpine")

for image in ${images[@]}; do
    echo "================== ${image} =================="
    docker run --rm -v $(pwd):/app/ ${image} sh /app/run.sh
done

rm -rf *.class
