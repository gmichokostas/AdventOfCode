(ns day1.solution
  (:require
   [clojure.java.io :as io]))

(def sample-input
  [199
   200
   208
   210
   200
   207
   240
   269
   260
   263])

(def input
  (with-open [rdr (io/reader "input")]
    (->> rdr
         line-seq
         (mapv #(Integer/parseInt %)))))

;; part 1
(loop [x       (first input)
       col     (rest input)
       counter 0]
  (if (empty? col)
    counter
    (recur (first col)
           (rest col)
           (if (< x (first col))
             (inc counter)
             counter))))

;; part 1 simplified
(def count-filtered (comp count filter))

(count-filtered (fn [[a b]] (< a b))
                (partition 2 1 input))


;; part 2
(->> input
     (partition-all 3 1)
     (map #(apply + %))
     (partition 2 1)
     (filter (fn [[a b]] (< a b)))
     count)
