(ns clojure-collections.access-patterns.removing-items)

;; Removing an item from a set

(def numbers #{1 2 3 4 5 6})
;remove by value
(numbers)
;; => #{1 2 3 4 5 6}
;disj is just for sets
(disj numbers 6)
;; => #{1 4 3 2 5}
(disj numbers 2)
;; => #{1 4 6 3 5}

(disj numbers :not-there)
;; => #{1 4 6 3 2 5}
;no-op because its not in set

;removes by key
(dissoc {:a 1 :b 2} :a)
;; => {:b 2}

;removing from a list get slower as the list gets bigger
(def numbersv [1 2 3 4 5 6])

(pop numbersv) ;removes the last element of vector
;; => [1 2 3 4 5]

(defn mydisj [ls v]
  (vec (remove #(= v %) ls)))
;remove and filter are linear, so processes vec one at a time
;seek is linear, one at a time

(mydisj numbersv 6)
;; => [1 2 3 4 5]
