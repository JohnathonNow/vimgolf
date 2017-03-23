FROM ubuntu
COPY ./ds.jar /
COPY ./date /bin/date
RUN sed -i 's/in.archive.ubuntu.com/old-releases.ubuntu.com/g' /etc/apt/sources.list
COPY ./file.sh /
RUN apt-get update && apt-get install -y \
    vim \
    default-jre
ENTRYPOINT ["/file.sh"]
CMD []
