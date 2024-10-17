FROM 035717374288.dkr.ecr.us-west-2.amazonaws.com/3rdparty:maven_3-openjdk-11 AS build
COPY . /home/maven/src
WORKDIR /home/maven/src
RUN mkdir /root/.m2
COPY settings.xml /root/.m2/
ENV MAVEN_CONFIG /root/.m2/settings.xml
RUN mvn package

FROM 035717374288.dkr.ecr.us-west-2.amazonaws.com/3rdparty:openjdk_11
EXPOSE 8080:8080
ARG ARTIFACT_VERSION \
    SERVICE_NAME
ENV SERVICE_NAME=$SERVICE_NAME \
    ARTIFACT_VERSION=$ARTIFACT_VERSION
RUN mkdir /app
COPY --from=build /home/maven/src/target/*-with-dependencies.jar /app/${SERVICE_NAME}-${ARTIFACT_VERSION}.jar
ENTRYPOINT ["sh", "-c", "java -jar /app/${SERVICE_NAME}-${ARTIFACT_VERSION}.jar"]
