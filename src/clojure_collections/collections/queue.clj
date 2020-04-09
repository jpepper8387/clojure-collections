(ns clojure-collections.collections.queue)

;;Queue
;; add to beginning, remove from end
(def queue clojure.lang.PersistentQueue/EMPTY)

(conj queue 1 2 3)
;; #object[clojure.lang etc]

(seq (conj queue 1 2 3))
;; (1 2 3)

;; Queue Pattern
;; peek
;; conj
;; pop

(def queue (atom clojure.lang.PersistentQueue/EMPTY))

(swap! queue conj 1)
(swap! queue conj 2)

(seq @queue)
;; => (1 2)

(peek @queue)
;; 1

;; can't have simulateneous peek/pops as they will return same values
(swap! queue pop)
;; object
(seq @queue)
;; => (1)

(swap! queue conj :a)
(swap! queue conj :b)
(swap! queue conj :c)
(swap! queue conj :d)
(swap! queue conj :e)
;; => (:a :b :c :d :e)

(defn my-pop []
  (let [[old _] (swap-vals! queue pop)]
  (peek old)))

(my-pop)
;; => :a
(my-pop)
;; => :b
(my-pop)
;; => :c
(my-pop)
;; => :d
(my-pop)
;; => :e
(my-pop)
;; => nil

;; Ordered
;; Duplicates
;; Count
;; sequential equality partition
(= () clojure.lang.PersistentQueue/EMPTY [])
;;; all equal based on sequential values
