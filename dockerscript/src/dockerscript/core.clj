(ns dockerscript.core
  (:gen-class))
(use '[clojure.java.shell :only [sh]])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [command (read-line)
        document (slurp *in*)]
    (spit "j.txt" document)
    (sh "vim" "j.txt" "-c" (str "execute \"normal! " command "\""))
    (print (slurp "j.txt"))
    (flush)
    (System/exit 0)
  )
)
