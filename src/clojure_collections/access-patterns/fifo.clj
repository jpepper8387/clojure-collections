(ns clojure-collections.access-patterns.fifo
  (:require [clojure.java.io :as io]))

;; First-in, first-out, FIFO, Queue

(conj [] 1 2 3)
;; => [1 2 3]
;can't remove first element of a vector
;would only have seq option first

;; Queue
;clojure.lang.PersistentQueue/EMPTY
;;=> queue object
(seq clojure.lang.PersistentQueue/EMPTY)
;; => ()
(seq clojure.lang.PersistentQueue/EMPTY)
;; => []

;; conj -add
(def empty-queue clojure.lang.PersistentQueue/EMPTY)
(vec (conj empty-queue :a :b :c))
;; => [:a :b :c]

;; peek -get next
(first (conj empty-queue :a :b :c))
;; => :a
;works in this instance because it happens to be the same
(peek (conj empty-queue :a :b :c))
;; => :a
;guaranteed retrieval of first item that was added

;; pop  -drop next
(vec (pop (conj empty-queue :a :b :c)))
;; => [:b :c]

;these allow you to add to end while removing from the beginning

;; Traverse file system
(def root (io/file "C:/Users/john/github/clojure-collections/src"))
(.exists root)
;; => true  ;proves path is correct

(loop [q (conj empty-queue root)]
  (let [cur (peek q)]
    (cond
      (nil? cur)
      nil   ;if no more files quicks branch

      (.isDirectory cur)
      (recur (into (pop q) (.listFiles cur)))

      :else
      (do
        (println cur)
        (recur (pop q))))))
;prints only file names from root down
