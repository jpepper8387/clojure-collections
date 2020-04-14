(ns clojure-collections.access-patterns.fifo)

;; LIFO - last in, first out
;; Stack

;; Vector
(def todos (atom []))

(defn add-todo! [task]
  (swap! todos conj task))

(defn get-todo! []
  (let [[old new] (swap-vals! todos pop)]
    (peek old)))

(add-todo! 1)
;; => [1]
(add-todo! 2)
;; => [1 2]
(add-todo! 3)
;; => [1 2 3]
(get-todo!)
;; => 3
(get-todo!)
;; => 2
(get-todo!)
;; => 1



;; List
(def todos (atom ()))

(add-todo! 1)
;; => (1)
(add-todo! 2)
;; => (2 1)
(add-todo! 3)
;; => (3 2 1)
(get-todo!)
;; => 3
(get-todo!)
;; => 2
(get-todo!)
;; => 1
