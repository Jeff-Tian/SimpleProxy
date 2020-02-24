FROM openjdk:7
COPY src /usr/src/simple-proxy
WORKDIR /usr/src/simple-proxy
RUN javac -encoding utf8 ProxyCache.java
CMD ["java", "ProxyCache", "80"]