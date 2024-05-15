FROM bellsoft/liberica-openjdk-alpine:17.0.11-cds
# Install curl jq
RUN apk add curl jq
#workspace
WORKDIR /home/selenium-docker
#add required files to run tests
ADD target/docker-resources ./
ADD runner.sh runner.sh

# Run the tests starting runner.sh

ENTRYPOINT  sh runner.sh