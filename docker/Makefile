bob: 
	docker build -t test .

run:
	docker run -a stdin -a stdout -i test

clean:
	docker rm `docker ps -a -q` -f || true
	docker rmi `docker images -a | grep '^<none>' | awk '{print $3}'`
