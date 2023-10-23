FROM openjdk:8-jdk-slim
VOLUME /tmp
RUN mkdir /work
COPY . /work
WORKDIR /work
RUN apt-get update && apt-get install dos2unix -y && dos2unix gradlew && chmod +x gradlew && ./gradlew build
RUN mv /work/build/libs/*.jar /work/product.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/work/product.jar"]