(ns day4.solution
  (:require [clojure.string :as s]))

(def required-fields #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})

(defn get-all-passports [input]
  (s/split input #"\n\n"))

(defn serialize-passport [passport]
  (let [str-fields (s/split passport #"\s")]
    (into {}
          (map #(s/split %1 #":")
               str-fields))))

(defn part-one-valid? [passport]
  (every? passport required-fields))

;; part one
(->> (slurp "input.txt")
     get-all-passports
     (map serialize-passport)
     (filter part-one-valid?)
     count)

;; part 2

(defn validate-year [& args]
  (let [{:keys [year length minimum maximum]} args]
    (and (= (count year) length)
         (<= minimum (Integer/parseInt year) maximum))))

(defn validate-hair-color [hair]
  (re-matches #"^#[0-9a-f]{0,9}" hair))

(defn validate-eye-color [color]
  (re-matches #"amb|blu|brn|gry|grn|hzl|oth" color))

(defn validate-passport-id [passport]
  (re-matches #"[0-9]{9}" passport))

(defn validate-height [height]
  (let [[_ num unit] (->> height (re-matcher #"(\d+)(cm|in)") re-find)]
    (cond                               ; NOTE: alternative use `case`
      (= unit "cm") (<= 150 (Integer/parseInt num) 193)
      (= unit "in") (<= 59 (Integer/parseInt num) 76)
      :else false)))

(defn part-two-valid [passport]
  (let [{:strs [byr iyr eyr hgt hcl ecl pid]} passport]
    (try
      (and (validate-year :length 4 :minimum 1920 :maximum 2002 :year byr)
           (validate-year :length 4 :minimum 2010 :maximum 2020 :year iyr)
           (validate-year :length 4 :minimum 2020 :maximum 2030 :year eyr)
           (validate-height hgt)
           (validate-hair-color hcl)
           (validate-eye-color ecl)
           (validate-passport-id pid))
      (catch Exception _ false))))

(->> (slurp "input.txt")
     get-all-passports
     (map serialize-passport)
     (filter part-two-valid)
     count)
