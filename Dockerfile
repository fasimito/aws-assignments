FROM java:8
ARG work_dir
COPY build/libs/* $work_dir
WORKDIR $work_dir
CMD java -jar awsassignment-0.0.1-SNAPSHOT.jar