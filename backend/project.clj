(defproject backend "0.1.0-SNAPSHOT"
  :description "Backend for Vim Golf"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
:dependencies [[org.clojure/clojure "1.8.0"]
                 [aleph "0.4.2-alpha6"]
                 [org.clojure/data.json "0.2.6"]
                 ]
  :main ^:skip-aot backend.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
