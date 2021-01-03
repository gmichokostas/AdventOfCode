(ns day5.solution
  (:require [clojure.string :as s]))

(def columns (apply vector (range 0 8)))
(def rows    (apply vector (range 0 128)))

(defn div [collection]
  (/ (count collection) 2))

(defn lower-half [& collection]
  (-> collection div (take collection)))

(defn upper-half [& collection]
  (-> collection div (drop collection)))

(defn seat-id [row column]
  (+ (* row 8)
     column))

(defn navigation [directions]
  (map (fn [dir]
         (case dir
           (\F \L) lower-half
           (\B \R) upper-half))
       directions))

(defn locate [collection directions]
  (reduce (fn [init dir]
            (apply dir init))
          collection
          (navigation directions)))

(defn find-seat-id [boarding-pass]
  (let [[row col] boarding-pass
        r (-> (locate rows row) first)
        c (-> (locate columns col) first)]
    (seat-id r c)))

(def input (-> (slurp "input.txt") (s/split #"\n")))

;; part 1
(->> input
     (map #(split-at 7 %))
     (map find-seat-id)
     (apply max))

(defn find-missing-seat [in]
  (let [f (first in)
        l (last in)
        r (set (range f l))]
    (clojure.set/difference r in)))

;; part 2
(->> input
     (map #(split-at 7 %))
     (map find-seat-id)
     (sort <)
     find-missing-seat)
