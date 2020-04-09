(ns clojure-collections.access-patterns.split-a-sequence)

;; Splitting a sequence

;; List is not fast

(take 4 '(1 2 3 4 5 6 7 8))
;; => (1 2 3 4)
(drop 4 '(1 2 3 4 5 6 7 8))
;; => (5 6 7 8)

;; Vectors
(def numbers (vec (range 1000)))
(count numbers)
;; => 1000

(subvec numbers 900 1000)
(subvec numbers 900)
;; => [900 901 902 903 904 905 906 907 .... 999]

(subvec numbers 900 950)
;; => [900 .... 949]
;index is off by 1 because starts at 0, element 950 is 949

(subvec numbers 10 12)
;; => [10 11]

(def subnumbers (subvec numbers 10 100))
(count subnumbers)
;; => 90

(.start subnumbers)
;; => 100   ;this is an accessor

(.end subnumbers)
;; => 100

(count (.v subnumbers))
;; => 1000
;remembers parent vector size
(get subnumbers 0)
;; => 10
;; remembers subnumbers start at 10th index of numbers, so 0 index subnumbers = 10th index numbers
