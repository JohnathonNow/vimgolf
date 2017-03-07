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
    (println (first (str/split (slurp(:body req)) #"\&")))
    (case (:uri req) 
      "/"  (assoc reply :body "<b>HI!</b>")
      "/k" (assoc reply :body "<b>k</b>")
    )
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (.wait-for-close (http/start-server handler {:port 8080}))
)
