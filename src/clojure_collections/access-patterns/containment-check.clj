(ns clojure-collections.access-patterns.containment-check)

;; Containment check

;; Set

(def numbers (set (range 1000)))

(contains? numbers 10)
;; => true
(contains? numbers 100)
;; => true
(contains? numbers 24.5)
;; => false
(contains? numbers :ten)
;; => false

;; Vector & HashMaps
; but returns results about keys, not values

(contains? {:a 1 :b 2} :a)
;; => true
(contains? {:a 1 :b 2} :b)
;; => true
(contains? {:a 1 :b 2} 1)
;; = false
(contains? {:a 1 :b 2} 2)
;; = false
;not fast to go through the values
; is fast to go through the keys

(contains? [10 20 30 40 50 60] 20)
;; => false   ;there is no index 20
(contains? [10 20 30 40 50 60] 10)
;; => false
(contains? [10 20 30 40 50 60] 0)
;; => true ;index 0 exists
(contains? [10 20 30 40 50 60] 2)
;; => true ;index 2 exists
(contains? [10 20 30 40 50 60] 6)
;; => false ;off the end of the vector
(contains? [10 20 30 40 50 60] -1)
;; => false ;off the left end of the vector
(contains? (vec (range 10)) 0)
;; => true
;hard to see on this one because numbers are equal to its index
(contains? (vec (range 10 20)) 0)
;; => true

(def x 10)

(if (< x (count v))
 ;;
 ;;
 )
 (if (contains? v x)
 ;;
 ;;
 )
 ;just don't do contains? on vectors

 
