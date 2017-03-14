(require '[clojure.string :as str])

;; replaces the special keystrokes, like \\<esc>, with the letter E
;; returns count of keystrokes in the argument 'string'
(defn count-keystrokes [string] 
    (count 
        (seq 
            (str/replace string #"\\<\S{0,4}>" "E"))))

