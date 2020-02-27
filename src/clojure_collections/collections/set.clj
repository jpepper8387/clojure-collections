(ns clojure-collections.collections.set)

;; Sets

;; Literal syntax

#{}
;;=> #{}

#{1 2 3 :a :b}
;;=> #{1 3 2 :b :a}

#{1}
;; => {#{1}

#{1 2}
;;=> #{1 2}

#{1 1.0}
;;==>#{1.0 1}

(hash-set 1 2 3 4)
;;=> #{1 4 3 2}

(set [1 2 3 4])
;;=> #(1 4 3 2)

(set (for [x (range 10)]
  ;;;
  x
))
;;=>#{1 2 3 4 5 6 7 8 9 10} ;would be without order

;; Patterns

;;; no order

;;; forgets duplicates
(def a "abc")
(def s #{a})
(identical? s s)
;;=> true
(identical? s (conj s a))
;;=> true     was already in there so it did nothing, compare itself to itself
(identical? s (conj s "a b c"))
;;=> true

;;lookup by value
(get s "abc")
;;=>"abc"

(identical? (get s "abc") a)
;;=> true
(identical? a (String. "abc"))
;;=> false
(= a (String. "abc"))
;;=> true

;; count
(count s)
;; => 1

;; set equality partition

;; Removing an item
(disj s a)
;;=> #{}
(disj s "11")
;;=> #{"abc"}

;; Containment check
(contains? s "abc")
;; => true
(contains? s 123)
;; => false

;; Multi-comparison
(defn vp? [name]
  (or (= name "John")
      (= name "Linda")
      (= name "June")
      (= name "Fred")))  ;;computes in linear time (one by one)

(def vice-presidents #{"John" "Linda" "June" "Fred"})

(defn vp? [name]
  (contains? vice-presidents name))  ;;rewritten as this makes it constant time

(def attendance ["Eric" "John" "June" "Jane" "Laura"])

(filter vp? attendance)
;; => ("John" "June")
(filter vice-presidents attendance)
;; => ("John" "June")

(vice-presidents "John")
;;=> "John"

(#{false} false)
;;=> false
(#{nil} nil)
;;=> nil
(contains? #{nil} nil)
;;=> true

;; elevator   only does it once
(def activated-buttons (atom #{}))

(defn push! [button-id]
  (swap! activated-buttons conj button-id))
(push! :first-floor)
;;=>#{:first-floor}
(push! :first-floor)
;;=>#{:first-floor}  ;only added it the first time
