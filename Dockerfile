FROM openjdk
WORKDIR /app
ADD . /app
RUN java source1
CMD ["java", "dest1"]
