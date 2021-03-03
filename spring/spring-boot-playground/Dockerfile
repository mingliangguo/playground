FROM openjdk:8

WORKDIR /app
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=7000,server=y,suspend=n

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.2.1/wait wait
COPY build/libs/spring-boot-playground-0.0.1.jar spring-boot-playground.jar
RUN chmod +x wait

EXPOSE 8443

# ENTRYPOINT ["java", "-jar", "spring-boot-playground.jar"]
CMD ./wait && java -jar spring-boot-playground.jar
