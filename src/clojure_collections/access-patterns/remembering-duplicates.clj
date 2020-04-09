(ns clojure-collections.access-patterns.remembering-duplicates)

;;; Remember duplicates

;; list
(conj () 1 2 3 4 5)
;; => (5 4 3 2 1)
(conj () 1 2 3 4 5 1 2 3)
;; => (3 2 1 5 4 3 2 1)

;; Vector
(conj [] 1 2 3 4 5 1 2 3)
;; => [1 2 3 4 5 1 2 3 ]

;; Queue
(seq (conj clojure.lang.PersistenQueue/EMPTY 1 2 3 4 5 1 2 3))
;; => (1 2 3 4 5 1 2 3)

;;; Forget duplicates

;; Set
(conj #{} 1 2 3 4 5 1 2 3)
;; => #{1 4 3 2 5}

(conj #{} (list 1 2 3) [1 2 3])
;; => #{(1 2 3)}
(conj #{} [1 2 3] (list 1 2 3))
;; => #{[1 2 3]}
;keeps same if already has one of sequential equality

(conj #{} (with-meta [1] {:a 1}) [1])
;; => #{[1]}
(meta (first (conj #{} (with-meta [1] {:a 1}) [1])))
;; => #{:a 1}
(meta (first (conj #{} [1] (with-meta [1] {:a 1}))))
;; => nil

;; HashMap
(conj {} [1 :a] [2 :a] [3 :a] [4 :a] [5 :a] [1 :b] [2 :b] [3 :b] [1 :c] [1 :d] [1 :e])
;; => {1 :e, 2 :b, 3 :b, 4 :a, 5 :a}
;replaces values when duplicate keys, takes last added value

(conj {} [[1] :a] [2 :a] [3 :a] [4 :a] [5 :a] [1 :b] [2 :b] [3 :b] [1 :c] [1 :d] [(list 1) :e])
;; => {[1] :e, 2 :b, 3 :b, 4 :a, 5 :a, 1 :d}

(def elevator-calls (atom {})) ;map

(defn press! [floor direction]
  (swap! elevator-calls conj [[floor direction] :called]))

(press! 3 :up)
;; => {[3 :up] :called}
(press! 3 :up)
;; => {[3 :up] :called}

(def elevator-calls (atom [])) ;vector remembers duplicates so goes to same floor twice in a row

(defn press! [floor direction]
  (swap! elevator-calls conj [[floor direction] :called]))

(press! 3 :up)
;; => [[[3 :up] :called]]
(press! 3 :up)
;; => [[[3 :up] :called] [[3 :up] :called]]
