(ns solution
  (:require [clojure.java.io :as io]
            [clojure.string  :as str]))

;; shorter version to use regexs
;; (re-matcher #"(\d+)-(\d+) (\w): (\w+)" "1-3 a: abc")
(defn- parse [line]
  (let [[policy char password] (str/split line #" ")]
    ;; a better option is to use a map with the ch normalized
    [(->> (str/split policy #"-")
          (map #(Integer/parseInt %)))
     (subs char 0 1)
     password]))

(defn- read-input [file]
  (with-open [rdr (io/reader file)]
    (->> rdr
         line-seq
         (mapv parse))))

(defn part1-valid? [[[beg end] ch password]]
  (let [times (get (frequencies password) (nth ch 0) 0)]
    (<= beg times end)))

(defn part2-valid? [[[beg end] ch password]]
  (let [ch (nth ch 0)]
    (not= (= ch (nth password (dec beg)))
          (= ch (nth password (dec end))))))

(->> (read-input "input.txt")
     (filter part2-valid?)
     count)

;; use reduce but more verbose
(reduce (fn [init input]
          (let [[policy ch password] (parse input)
                [beg end]            policy
                ch                   (nth ch 0)
                freqs                (get (frequencies password) ch 0)]
            (if (<= beg freqs end)
              (inc init)
              init)))
        0
        (read-input "input.txt"))
