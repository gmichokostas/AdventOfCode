(ns solution
  (:require [clojure.string :as s]))

(def tree \#)
(def input (-> (slurp "input.txt") (s/split #"\n")))
(def line-length (count (first input)))
(def start-pos 0)

;; part 1
(defn count-trees
  [by-step piste]
  (let [positions      (iterate #(mod (+ by-step %1) line-length) start-pos)
        entries-on-pos (map nth piste positions)
        trees          (filter #(= tree %1) entries-on-pos)]
    (count trees)))

(count-trees 3 input)


;; part 2
(def slopes [[1 1] [3 1] [5 1] [7 1] [1 2]])

(reduce *
        1
        (for [[right down] slopes
              :let         [piste (take-nth down input)]]
          (count-trees right piste)))
