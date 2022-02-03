#!/bin/bash

./gradlew build
docker build -t hierarchy .
