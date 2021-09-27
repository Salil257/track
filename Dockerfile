FROM alpine:3.14
MAINTAINER salilk257
COPY target/track-0.0.1-SNAPSHOT.jar track-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/track-0.0.1-SNAPSHOT.jar"]
