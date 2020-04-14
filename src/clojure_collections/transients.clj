(ns clojure-collections.transients)

;; Transients

[] {} () #{}
;;immutable structures
;modifying structures first makes a copy of it then modifies the copy
;many intermediate copies that are a waste
;can use transients to avoid the waste

(reduce conj #{} (range 1000000000))
;; => #{0 ... 1000000000 without ord(time (reduce conj #{} (range 1000000000))er}

(time (reduce conj #{} (range 1000000000))
;; 1.6 secs
;; makes a million copies as it goes through the million one by one
;reduce only returns the last copy, all intermediate is garbage collected

(reduce conj (transient #{}) (range 1000000000)
;;conj cannot be cast to transient hash error
(reduce conj! (transient #{}) (range 1000000000)
;;add a ! to make this work
(persistent! (reduce conj (transient #{}) (range 1000000000))
;takes transient collection and turns it back into a persistent collection
(time (persistent! (reduce conj (transient #{}) (range 1000000000)))
;; => .6 seconds
;; ! turns it into a mutable collection
