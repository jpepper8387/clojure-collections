(ns clojure-collections.access-patterns.associate-key-value)

;; Associate key and value

;; HashMap (or Sorted Map)

(assoc {} :a 1)
  ;; => {:a 1}

(assoc {:a 1} :b 2 :c 3)
;; => {:a 1 :b 2 :c 3}

(assoc {:a 1} :b 2 :c 3 :a 9)
;; => {:a 9 :b 2 :c 3}
(assoc {:a 1} :b 2 :c 3 :a 9 :c 4)
;; => {:a 9 :b 2 :c 4}
;the key-value updated last overwrites original key's value

;; Vector

(assoc [0 1 2 3 4] 1 :one)
;; => [0 :one 2 3 4]

(assoc [:A :B :C :D] 1 :one)
;; => [:A :one :C :D]
;assoc collection index new value

(assoc [:A :B :C :D] 10 :I)
;; => Index Out of Bounds error

(assoc [:A :B :C :D] 4 :E)
;; => [:A :B :C :D :E]
;; allowed to add one after the end so there are no gaps

(assoc [] :A 1)
;; IllegalArgumentException as no index = :A

(update {:a 1} :a inc)
;; => {:a 2}
;; update collection key function

(update {:a 1} :a + 4)
;; => {:a 5}

(update {:a 1} :a + 4 6 8)
;; => {:a 19}

(update {:a []} :a conj 10)
;; => {:a [10]}

(update ["a" "b" "c"] 0 #(.toUpperCase %))
;; => ["A" "b" "c"]

(get-in {:a {:xs []}} [:a :xs])
;; => []

(update-in {:a {xs []}} [:a :xs] conj 1)
;; => {:a {:xs [1]}}
;does get in to get [], then conj to get [1], then assoc
;to update value at {:a {:xs [1]}}

(get-in {} [:a :xs])
;; => nil

(update-in {} [:a :xs] conj 1)
  ;; => {:a {:xs (1)}}

(assoc nil :a :val)
;; => {:a :val}
