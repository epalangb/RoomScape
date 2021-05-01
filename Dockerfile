FROM maven:3.6.3-openjdk-16

COPY ./target/roomscape-backend.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch roomscape-backend.jar'

ENTRYPOINT ["java","-jar","roomscape-backend.jar"]