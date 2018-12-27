(ns day2.solution
  (:require [clojure.string :as string]
            [clojure.data :as d]))

(defn- create-seq
  [file-name]
  (->> (clojure.java.io/reader file-name)
       (line-seq)))

(defn- counter
  [a b]
  [(if (some #{2} (vals (frequencies b)))
     (inc (a 0))
     (a 0))
   (if (some #{3} (vals (frequencies b)))
     (inc (a 1))
     (a 1))
   ])

(defn part-1
  [file-name]
  (->> (create-seq file-name)
       (reduce counter [0 0])
       (reduce *)))

(println (part-1 "input.txt"))

(defn part-2
  [file-name]
  (let [sq (create-seq file-name)]
    (reduce (fn [a b]
              (let [result (remove nil?
                                   ((d/diff
                                     (string/split (b 0) #"")
                                     (string/split (b 1) #"")) 2))
                    len (- (count (b 0)) 1)]
                ;; (println result len)
                (if (= (count result) len)
                  (reduced result)
                  a)))
            ""
            (for [x sq, y sq]
              [x y]))))

(println (string/join "" (part-2 "input.txt")))
