(ns backend.core
  (:gen-class))
(require '[aleph.http :as http])
(require '[clojure.string :as str])
(require '[manifold.deferred :as d])
(require '[byte-streams :as bs])

;; replaces the special keystrokes, like \\<esc>, with the letter E
;; returns count of keystrokes in the argument 'string'
(defn count-keystrokes 
  [string] 
  (count 
    (seq 
      (str/replace 
        (str/replace string #"\\<\S{0,4}>" "E") #"\\\\" "S"))))

(defn slurp-bytes
  "Slurp the bytes from a slurpable thing"
  [x]
  (with-open [out (java.io.ByteArrayOutputStream.)]
    (clojure.java.io/copy (clojure.java.io/input-stream x) out)
    (.toByteArray out)))

(defn handler 
  [req]
  (println req)
  (let [reply {:status 200
               :headers {"content-type" "text/html"}
               :body "hello!"}]
    (case (:uri req) 
      "/"  (assoc reply :body (slurp "../frontend/index.html"))
      "/jquery.js" (assoc reply :body (slurp "../frontend/jquery.js"))
      "/code.js" (assoc reply :body (slurp "../frontend/code.js"))
      "/static/bootstrap.css" (assoc reply :body (slurp "../static/css/bootstrap.css"))
      "/static/vimlogo.jpg" (assoc (assoc reply :body (slurp-bytes "../static/img/vimlogo.png")) :headers {"content-type" "image/png"})
      "/static/syntaxhighlighter.js" (assoc reply :body (slurp "../static/js/syntaxhighlighter.js"))
      "/k"     (do (println (slurp(:body req)))
               (assoc reply :body "{\"status\": \"success\"}"))
      "/o"     (assoc reply :body
                  (-> @(http/get "https://google.com/") :body bs/to-string))
      ;default
            (do  
              (if (or 
                    (re-matches #"/start/h\d+" (:uri req))
                    (re-matches #"/end/h\d+" (:uri req)))
                  (assoc 
                    (assoc reply :body (slurp (str "../holes" (:uri req) ".txt")))
                     :headers {"content-type" "text/plain"})))
    )
  )
)

(defn -main
  [& args]
  (.wait-for-close (http/start-server handler {:port 8080}))
  ;(println (count-keystrokes (first args))))
)
