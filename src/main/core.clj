(ns main.core
  (:require
   [util.helpers :refer [string-to-map-cpu-usage string-to-map-cpu-load]]
   [clojure.java.shell :refer [sh]]
   [clojure.string :as str]))

;function for cpu usage
(defn get-cpu-usage []
  ;; Run the shell command and capture the output
  (let [result (sh "sh" "-c" "top -l 1 | grep 'CPU usage'")]
    (:out result))) 


;function for cpu load
(defn get-cpu-load []
  ;;gets past 5, 10,15 minute loads w/o any string manipulation done
  (let [result (sh "sh" "-c" "top -l 1 | grep 'Load Avg'")] 
    (:out result)))

(defn -main [& args]
  (let [cpu-info (get-cpu-usage) cpu-load (get-cpu-load)]
    (if (str/blank? cpu-info)
      (println "No CPU data available.") 
      (if (str/blank? cpu-load)
        (println "no cpu load info")
      
        (let [cpu_map (string-to-map-cpu-usage cpu-info) cpu_load (string-to-map-cpu-load cpu-load) ]
          
        (println "Command Output:") ;;This is the converted cpu usage to a map
        (println (keys cpu_map) (vals cpu_load)))))))
