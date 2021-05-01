FROM maven:3.6.3-openjdk-16

COPY ./target/roomscape-backend-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch roomscape-backend-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","roomscape-backend-0.0.1-SNAPSHOT.jar"]