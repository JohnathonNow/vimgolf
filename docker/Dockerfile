FROM ubuntu
RUN sed -i 's/in.archive.ubuntu.com/old-releases.ubuntu.com/g' /etc/apt/sources.list
RUN apt-get update
RUN yes | apt-get install vim
RUN yes | apt-get install default-jre
COPY ./ds.jar /
COPY ./file.sh /
ENTRYPOINT ["/file.sh"]
CMD []
