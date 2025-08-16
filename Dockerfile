# Stage 1 - Build with GraalVM and Maven
FROM ghcr.io/graalvm/native-image:ol8-java17 AS builder
WORKDIR /app

# Install tools
RUN microdnf install -y gzip curl tar

# Install Maven
ENV MAVEN_VERSION=3.9.6
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    | tar -xz -C /opt && \
    ln -s /opt/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn

# Copy project and build native image
COPY . .
RUN mvn -Pnative -DskipTests package

# Pre-create temp dirs so we can COPY them into the scratch image
RUN mkdir -p /app/tmp && chmod 1777 /app/tmp \
 && mkdir -p /tmp && chmod 1777 /tmp

# Stage 2 - Final minimal image
FROM scratch
WORKDIR /app

# Copy binary and temp dirs
COPY --from=builder /app/target/smartquote /app/smartquote
COPY --from=builder /app/tmp /app/tmp
COPY --from=builder /tmp /tmp

# Optional: help libs that read TMPDIR
ENV TMPDIR=/app/tmp

EXPOSE 8080

# Point Spring/Tomcat at a writable tmp inside the container
ENTRYPOINT ["/app/smartquote", "-Djava.io.tmpdir=/app/tmp", "--server.tomcat.basedir=/app/tmp"]
