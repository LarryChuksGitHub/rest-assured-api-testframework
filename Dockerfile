FROM maven:3.6.3-jdk-8
#FROM myName/myCommitedImage , to run the containner from already existing image
COPY src home/restframework/src
COPY pom.xml home/restframework/pom.xml
COPY testng.xml home/restframework/testng.xml
WORKDIR home/restframework
ENTRYPOINT mvn clean test

# To build docker image navigate to root folder and run
#docker build -t myFirstImage:1 .    // t = tag = 1, myFirstImage = image to be built, . current directory

# docker image // list all omages

#docker run myFirstImage:1  // myFirstimage = image, 1 = Tag

#docker commit <container ID> to create a reusable image
# docker tag <image ID> <myName/myCommitedImage>:latest

#docker login, to login to docker hub/registry