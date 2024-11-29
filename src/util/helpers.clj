(ns util.helpers
  (:require [clojure.string :as str]))
;;
;;
;;  This file contains some rather annoying string to map conversions of the 
;;  command line outputs.
;;
;;


;; for the cpu usage command that gets what entity is using what % of the cpu
;; fields user, system, idle.
;; appears in a weird order output is like
;; 12.4% user, 22% system,... not like key-value so that is why the code is a bit weird
(defn string-to-map-cpu-usage [cpu-string]
  (let [stripped (str/replace cpu-string #"CPU usage: " "")
        parts (str/split stripped #", ")
        parsed (map (fn [part]
                      (let [[value key] (str/split part #" ")]
                        [(keyword key) (Double/parseDouble (str/replace value #"%" ""))]))
                    parts)]
    (into {} parsed)))

;; for the cpu load command
;; a bit easier as output is only numbers like 1.23,2.5,...
;; still annoying
;; 
(defn string-to-map-cpu-load [cpu-load]
  (let [time [1 5 15] ;;time periods of the command outputs
        stripped (str/replace cpu-load #"Load Avg: " "")
        parts (str/split stripped #", ")
        parsed (map (fn [t value]
                      [(keyword (str t "min")) (Double/parseDouble value)])
                    time
                    parts)]
    (into {} parsed)))