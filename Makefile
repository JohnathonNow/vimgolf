dock-img: dockerscript/target/uberjar/dockerscript-0.1.0-SNAPSHOT-standalone.jar
	cp dockerscript/target/uberjar/dockerscript-0.1.0-SNAPSHOT-standalone.jar \
	   docker/ds.jar
	cd docker && make
	rm docker/ds.jar
dockerscript/target/uberjar/dockerscript-0.1.0-SNAPSHOT-standalone.jar: \
	dockerscript/src/dockerscript/core.clj
	cd dockerscript && lein uberjar

