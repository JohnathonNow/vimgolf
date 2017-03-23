dock-img: dockerscript/target/uberjar/dockerscript-0.1.0-SNAPSHOT-standalone.jar \
          date/date
	cp dockerscript/target/uberjar/dockerscript-0.1.0-SNAPSHOT-standalone.jar \
	   docker/ds.jar
	cp date/date docker/date
	cd docker && make
	rm docker/ds.jar docker/date
dockerscript/target/uberjar/dockerscript-0.1.0-SNAPSHOT-standalone.jar: \
	dockerscript/src/dockerscript/core.clj
	cd dockerscript && lein uberjar
date/date: date/main.c
	cd date && make
