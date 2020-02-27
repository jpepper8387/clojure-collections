(ns clojure-collections.collections.vector)

;;Vectors

[1 2 3 4] => [1 2 3 4]
[] => []

'(1 2 3 4) => (1 2 3 4)

'(1 2 3 (*2 2)) => (1 2 3 (*2 2)) ;literal, doesn't eval

(list 1 2 3 4) ;not defining as literal so it evals

[1 2 3 (* 2 2)] => 1 2 3 4 ;vector evaluates, literal syntax

(vector 1 2 3 4) => [1 2 3 4]

[1 :a "ere" 0 nil] => [1 :a "ere" 0 nil]

(vec (list 1 2 3 4)) => [1 2 3 4] ;conversion

(vec {:a 1 :b 2}) => [[:a 1] [:b 2]]

;; Access Patterns

;;; Order

(conj [1 2 3 4] :a) => [1 2 3 4 :a] ;adds to end

(seq [1 2 3 4]) => (1 2 3 4) ;maintains order

;;; Random access by index

(def v [:a :b :c :d :e :f :g])

(get v 3) => :e

(get v 1000) => nil

(get v -1) => nil

(get v :fff) => nil

(get nil nil) => nil

(get (Object.) nil) => nil   ;only failure case for get returns nil

(nth v 3) => :e
(nth v -1) => 'index out of bounds exception'
;nth does bounds checking

;; Keeping duplicates

v => [:a :b :c :d :e :f :g]

(conj v :a) => [:a :b :c :d :e :f :g :a]

;; Count

(count v) => 6

;; Equality checks
; checks item by item, index 0 to index 0, 1 to 1, etc
(= [1 2 3 4] (list 1 2 3 4)) => true ;equal because same elements in same Order
(= [1 2 3 4] [1 2 3 4]) => true
(= [1 2 3 4] [1 2 3 (*2 2)]) => true ;bc vector evals

;; Subvectors
