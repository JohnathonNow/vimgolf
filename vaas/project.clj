(defproject vaas "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [aleph "0.4.2-alpha6"]
                 ]
  :main ^:skip-aot vaas.core
  :target-path "target/%s"
  :ring {:handler vaas.core/-main}
  :profiles {:uberjar {:aot :all}})