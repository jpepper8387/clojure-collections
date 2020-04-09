(ns clojure-collections.access-patterns.equality-comparison)

;; Equality comparison

;; Java: ==   ;reference comparison

;; Clojure: =

(= [1 2 3] [1 2 3])
;; => true

;; Equality partitions

;; Sequential Partition
;; interface java.util.List
;; class java.util.LinkedList
;; class java.util.ArrayList
;; want to be able to compare stuff but not worry about types
(= [1 2 3] '(1 2 3)')
;; => true

(= [1 2 3] [3 2 1])
;; => false

(= 1)
;; => true
;; works with one argument but not zero

(= 1 1 1 1 1 1)
;; => true

(= 1 1 1 1 1 1 [2])
;; => false

;; Map Equality Partition
;; compares keys and values

(= {:a 2} {:a 2})
;; => true

(= {:a 1 :b 2} {:b 2 :a 1})
;; => true

(= (hash-map :a 1 :b 2) (array-map :b 2 :a 1))
;; => true

;array map is more efficient when small
;hash map is more efficient when large

;; Set equality partition
(= #{1 2 3} #{3 2 1})
;; => true

;; Across partitions
(= [1 2 3] #{1 2 3})
;; => false
;always going to be false across partitions

(seq ({:a 1 :b 2}))
;; => ([:a 1][:b 2])
(seq {:b 2 :a 1})
;; => ([:b 2][:a 1])
(= (seq ({:a 1 :b 2})) (seq {:b 2 :a 1}))
;; => false
;not true because even tho they are maps and same value, we are converting them
;to seq which is a different partition type where order matters

(hash [1 2 3])
;; => 736442005
(hash '(1 2 3))
;; => 736442005

(def my-map {[1 2 3] :answer})

(get my-map [1 2 3])
;; => :answer
(get my-map '(1 2 3))
;; => :answer
