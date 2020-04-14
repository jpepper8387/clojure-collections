(ns clojure-collections.sequences)

;;sequences vs collections

(map inc [1 2 3])
;; => (2 3 4)
(update [1 2 3] 1 -)
;; => [1 -2 3]

;; sequence operations
;;collection comes last
;; call seq method on the collection so the functions work with all seq collections

(map inc [1 2 3])
;; => (2 3 4)
(filter even? [1 2 3])
;; => (2)
(take 2 [1 2 3 4 5])
;; => (1 2)
(drop 2 [1 2 3 4 5])
;; => (3 4 5)

(->> [1 2 3 4 5]
  (map inc)
  (filter even?)
  (drop 2))
;; => (6)

(def s (atom (list 1 2 3 4 5)))
(swap! s (fn [s] (map inc s))) ;changes type so not really made for this

;; collection operations
;; collection comes first
;; similar to method on object, do a diff thing based on type

(update [1 2 3] 1 -)
;; => [1 -2 3]
(assoc [1 2 3] 1 4)
;; => [1 4 3]
(conj [1 2 3] 4)
;; => [1 2 3 4]
(-> [1 2 3 4 5 6]
  (update 2 -)
  (assoc 0 :zero)
  (conj 7))
;; => [:zero 2 -3 4 5 6 7]

(def a (atom {}))

(swap! a assoc :key :value)
;; => {:key :value}
