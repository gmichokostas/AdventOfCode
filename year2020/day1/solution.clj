(ns solution
  (:require [clojure.java.io :as io]))

(defn read-input [filename]
  (with-open [rdr (io/reader filename)]
    (->> rdr
         line-seq
         (map #(Integer/parseInt %))
         set)))


(def input (read-input "input.txt"))

(for [x input
      y input
      :let [z (- 2020 x y)]
      :when (< x y z)
      :when (contains? input z)]
  (* x y z))
