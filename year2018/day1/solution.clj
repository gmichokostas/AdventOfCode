(ns day1.solution
  (:require [clojure.string :as str]))

(defn- create-seq
  [file-name]
  (-> file-name
      (clojure.java.io/reader)
      (line-seq)))

;; part 1

(defn part-1
  [file-name]
  (->> (create-seq file-name)
       (map read-string)
       (reduce + 0)))

(println (part-1 "input.txt"))

;; part 2

(defn part-2
  [file-name]
  (->> (create-seq file-name)
       (map read-string)
       cycle
       (reductions + 0)
       (reduce (fn [acc x]
                 (if (contains? acc x)
                   (reduced x)
                   (conj acc x)))
               #{})))

(println (part-2 "input.txt"))
