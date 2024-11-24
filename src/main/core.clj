(ns main.core
  (:require [clojure.java.shell :refer [sh]]))
(defn get-cpu []
  (:out (sh "top" "-l" "1" "-o" "cpu")))
(defn -main [& args]
  (println (get-cpu))
  (println ()))

