FROM openjdk:11
LABEL maintainer="Tahir Baboci"

ADD build/libs/hierarchy-0.0.1.jar hierarchy.jar

ENTRYPOINT ["java","-jar","hierarchy.jar"]