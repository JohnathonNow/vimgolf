(ns backend.core
  (:gen-class)
  (:require [clojure.data.json :as json]))
(require '[aleph.http :as http])
(require '[clojure.string :as str])
(require '[manifold.deferred :as d])
(require '[byte-streams :as bs])

(def VaaSURL  "http://localhost:9090/k")

(defmacro against-all-charles-holds-dear [l] 
  (into [] 
    (for [i l] (list 'transient i))))

(def state (against-all-charles-holds-dear
           [{:name "null" :score 999} {:name "null" :score 999}
            {:name "null" :score 999} {:name "null" :score 999}]))

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
      "/static/usrmsg.txt" (assoc reply :body (slurp "../static/txt/usrmsg.txt"))
      "/static/vimlogo.jpg" (assoc (assoc reply :body (slurp-bytes "../static/img/vimlogo.png")) :headers {"content-type" "image/png"})
      "/static/syntaxhighlighter.js" (assoc reply :body (slurp "../static/js/syntaxhighlighter.js"))
      "/k"     (do (println (slurp(:body req)))
               (assoc reply :body "{\"status\": \"success\"}"))
      "/hs"    (do 
                  (let [in (json/read-str (slurp (:body req))) ]
                  (let [hole (get in "hole")]
                    (assoc reply :body 
                           (json/write-str {:status "success" :hs 
                                            (persistent! (get state (- hole 1)))})))))
      "/sub"   (do  ; for sub we have to get the user input
                    ; score it, send it to VaaS,
                    ; compare the results to what is right
                    ; etc
                  (let [in (json/read-str (slurp (:body req))) ]
                    (let [score (count-keystrokes (get in "cmd"))
                          hole  (get in "hole")
                          user  (get in "name")
                          cmd   (get in "cmd")
                          result (assoc (json/read-str (-> @(http/post VaaSURL
                            {:body (json/write-str
                               {:document (slurp 
                                  (str "../holes/start/h" hole ".txt"))
                                :command cmd})
                            }) :body bs/to-string))
                           :score score)]
                      (if (= 0 (compare 
                        (slurp (str "../holes/end/h" hole ".txt"))
                        (get result "document")))
                          (if (< score (:score (nth state (- hole 1))))
                             (do
                               (assoc! (nth state (- hole 1)) :name  user)
                               (assoc! (nth state (- hole 1)) :score score)
                               (assoc reply :body (json/write-str result))
                          ))
                         (assoc reply :body (json/write-str
                           (assoc result "status" "failure"))))
                      )))
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
  (assoc! (nth state 0) :name "john")
  (println (:name (nth state 0)))
  (.wait-for-close (http/start-server handler {:port 8080}))
  ;(println (count-keystrokes (first args))))
)
