FROM cassandra:3.11.3

RUN apt-get update && apt-get install openjdk-8-jdk -y

WORKDIR /app
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=7000,server=y,suspend=n
ADD ./build/libs/spring-boot-playground-0.0-1.jar .
ENTRYPOINT ["java", "-cp", "spring-boot-playground.jar", "ming.playground.SampleApplication"]
