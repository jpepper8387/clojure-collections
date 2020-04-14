(ns clojure-collections.usage-patterns.index)

;; Index

;; HashMap
;;   Keys are all the same "kind"
;;   Values are all the same "kind"

;; Used to look things up

(def friends [{:name "Eric"
               :phone "504-302-3742"}
              {:name "Jane"
               :phone "504-933-9202"}
              {:name "John"
               :phone "949-499-2023"}])

(def rolodex (into {} (for [friend friends]
                       [(:name friend) friend])))

rolodex
;; => {"Eric" {:name "Eric" :phone "504-302-3742"},
;;     "Jane" {:name "Jane" :phone "504-933-9202"},
;;     "John" {:name "John" :phone "949-499-2023"}}

(get rolodex "Jane")
;; => {:name "Jane" :phone "504-933-9202"}

;; Accumulator Index

;; Also used to accumulate values for given keys

(def visits (atom {}))

(defn log-visit! [url]
  (swap! visits update url (fnil inc 0)))

(log-visit! "/page")
;; => {"/page" 1}
(log-visit! "/page")
;; => {"/page" 2}
(log-visit! "/page")
;; => {"/page" 3}
(log-visit! "/page")
;; => {"/page" 4}
(log-visit! "/page2")
;; => {"/page" 4, "/page2" 1}
(log-visit! "/page3")
;; => {"/page" 4, "/page2" 1, "/page3" 1}

@visits
;; => {"/page" 4, "/page2" 1, "/page3" 1}

(def numbers [3 4 4 3 2 1 2 3 4 5 6 5 4 3 2 4 3 6 7 8 6 44 6 6])

(reduce (fn [idx n]
          (update index
             (if (even? n) :even :odd)
             (fnil conj []) n))
  {} numbers)
;; => {:odd [3 3 1 3 5 5 3 3 7], :even [4 4 2 2 4 6 4 2 4 6 8 6 44 6 6]}

;; Dispatch Table

;; For converting a value to a function

;make index table
(def prep-routines {:coffee brew-coffee
                    :tea    make-tea
                    :bagel  prepare-bagel})
;function to look up item from table that calls function
(defn prepare [item]
  (let [f (get prep-routines item)]
    (f)))

;; Conversion Table

;; statically convert one kind of thing to another

;; Create Read Update Delete

(def op-table {:post   :create
               :get    :read
               :put    :update
               :delete :delete})

(defn http->crud [method]
  (get op-table method))
