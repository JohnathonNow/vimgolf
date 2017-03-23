(ns vaas.core
  (:gen-class)
  (:require [clojure.data.json :as json])
)

(require '[aleph.http :as http])
(require '[clojure.string :as str])
(use '[clojure.java.shell :only [sh]])



(defn handler [req]
  (println req)
  (let [reply {:status 200
               :headers {"content-type" "text/html"}
               :body "hello!"}]
    (case (:uri req) 
      "/"  (assoc reply :body (slurp "resources/index.html"))
      "/jquery.js" (assoc reply :body (slurp "resources/jquery.js"))
      "/k" (let [reqbod (json/read-str (slurp (:body req)))]
             (let [in (get reqbod "document" "SERVER ERROR")
                 cmd (get reqbod "command" "")]
             (do 
                 (println cmd)
                 (println in)
               (assoc reply :body
                 (json/write-str {
                   :document (:out (sh "docker" "run" "--rm" "-a" "stdin" 
                                       "-a" "stdout" "-i" "test"
                                       :in (str cmd "\n" in)))
                   :status "success"
                   }))
             )
           ))
    )
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (.wait-for-close (http/start-server handler {:port 9090}))
)
