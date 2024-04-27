# Build stage
FROM openjdk:17-slim AS build

# Install required tools
RUN apt-get update && apt-get install -y curl

# Install Maven
ENV MAVEN_VERSION 4.0.0-alpha-10
ENV BASE_URL https://archive.apache.org/dist/maven/maven-4/${MAVEN_VERSION}/binaries/

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

WORKDIR /home/app

# Copy the project files
COPY src /home/app/src
COPY pom.xml /home/app

# Build the application, skipping tests
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Test stage
FROM build AS test
WORKDIR /home/app
# Use the compiled and packaged resources from the build stage
# Run Maven test on the current project
CMD ["mvn", "test"]

# Package stage
FROM openjdk:17 AS package

# Define the port the application exposes
EXPOSE 8080

# Set the container's working directory
WORKDIR /usr/local/lib

# Copy the built jar file from the build stage
COPY --from=build /home/app/target/*.jar app.jar

# Set the container entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]
