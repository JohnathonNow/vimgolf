FROM ubuntu
RUN apt-get update
RUN yes | apt-get install vim
COPY ./file.sh /
ENTRYPOINT ["/file.sh"]
CMD []
