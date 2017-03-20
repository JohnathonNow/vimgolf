(ns vaas.core
  (:gen-class)
)

(require '[aleph.http :as http])
(require '[clojure.string :as str])


(defn handler [req]
  (println req)
  (let [reply {:status 200
               :headers {"content-type" "text/html"}
               :body "hello!"}]
    (case (:uri req) 
      "/"  (assoc reply :body (slurp "../frontend/index.html"))
      "/jquery.js" (assoc reply :body (slurp "../frontend/jquery.js"))
      "/k"     (do (println (slurp(:body req)))
               (assoc reply :body "{\"status\": \"success\"}"))
    )
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (.wait-for-close (http/start-server handler {:port 8080}))
)
