FROM java:8

VOLUME /tmp
ADD portero.jar portero.jar
RUN bash -c 'touch /portero.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/portero.jar"]