FROM java:8
ARG work_dir
COPY target/* $work_dir
WORKDIR $work_dir
CMD java -jar com-aws-assignments-1.0-SNAPSHOT.war