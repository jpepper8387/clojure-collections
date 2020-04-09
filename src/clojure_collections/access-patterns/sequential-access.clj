(ns clojure-collections.access-patterns.sequential-access)

;;; Arbitrary Stable Order

;;; List
()
;; => ()
; no random access (only front item in constant time)
; other items require a linear search

(def l (list 1 2 3 4))
(seq l)
;; => (1 2 3 4)
(conj l :A)
;; => (:A 1 2 3 4)
(into l [10 20 30 40 50])
;; > (50 40 30 20 10 1 2 3 4)
;adds to front

(conj :A :B :C)
;; => (:C :B :A 1 2 3 4)

;;; Vector
(def v [1 2 3 4 5])
(seq v)
;; => (1 2 3 4 5)

(conj v :A)
;; => [1 2 3 4 5 :A]
;maintains order
;adds to end
;removes from end

(into v [10 20 30 40 50])
;; => [1 2 3 4 5 10 20 30 40 50]

;;; Queue
(def q clojure.lang.PersistenQueue/EMPTY)
(seq (conj q 1 2 3))
;; => (1 2 3)

;add to end, remove from front

;;; Sorted Order

;; Sorted Hash Map
(hash-map :a 1 :b 2)
;; => {:b 2, :a 1}
(seq (hash-map :a 1 :b 2))
;; => {:b 2, :a 1}
;random order, can't rely on it

(def shm (sorted-map :a 1 :b 2))

(seq shm)
;; => ([:a 1] [:b 2])

(seq (sorted-map "zA" 1 "yB" 2))
;; => (["yB" 2] ["zA" 1])
;sorts by first letter

(seq (sorted-map-by (fn [a b]
                      (compare (str/join (rest a)) (str/join (rest b))))
                       "zA" 1 "yB" 2))
;; => (["zA" 1] ["yB" 2])
;sorts by second/rest of string

;; Sorted Set
(seq (set [1 2 3 4 5 6 7]))
;; => (7 1 4 6 3 2 5)
;no order

(seq (sorted-set 1 2 3 4))
;; => (1 2 3 4 5 6 7)
;keeps order

(seq (sorted-set
  {:name "Eric"}
  {:name "Jane"}
  {:name "John"}))
;not comparable as is

(sorted-set-by (fn [a b]
                (compare (:name a) (:name b)))
                {:name "Zelda"}
                {:name "Eric"}
                {:name "Jane"}
                {:name "John"})
;; => #{{:name "Eric"} {:name "Jane"} {:name "John"} {:name "Zelda"}}

(def todos (atom []))  ;vector

(defn add-todo! [item]
  (swap! todos conj item))


(do
  (add-todo! "Buy kitten")
  (add-todo! "Buy cat food")
  (add-todo! "Feed kitten")

  (seq @todos)
  )
;; => ("Buy Kitten" "Buy cat food" "Feed kitten")

(def todos (atom ()))   ;list

(defn add-todo! [item]
  (swap! todos conj item))


(do
  (add-todo! "Buy kitten")
  (add-todo! "Buy cat food")
  (add-todo! "Feed kitten")

  (seq @todos)
  )
;; => ("Feed kitten" "Buy cat food" "Buy Kitten")

(def todos (atom clojure.lang.PersistentQueue/EMPTY))  ;queue

(defn add-todo! [item]
  (swap! todos conj item))


(do
  (add-todo! "Buy kitten")
  (add-todo! "Buy cat food")
  (add-todo! "Feed kitten")

  (seq @todos)
  )
;; => ("Buy Kitten" "Buy cat food" "Feed kitten")

(def todos (atom (sorted-set)))  ;sorted-set

(defn add-todo! [item]
  (swap! todos conj item))


(do
  (add-todo! "Buy kitten")
  (add-todo! "Buy cat food")
  (add-todo! "Feed kitten")

  (seq @todos)
  )
;; => ("Buy cat food" "Buy Kitten" "Feed kitten")  ;puts it in abc order

(defn priority-order [a b]
  (compare (:priority a) (:priority b)))

(def todos (atom (sorted-set-by priority-order)))  ;;sorted-set with defined priority order

(defn add-todo! [item]
  (swap! todos conj item))

(do
  (add-todo! {:priority 10 :name "Buy kitten"})
  (add-todo! {:priority 1 :name "Buy cat food"})
  (add-todo! {:priority 2 :name "Feed kitten"})

  (seq @todos)
  )
;; => ({:priority 1, :name "Buy cat food"}{:priority 2, :name "Feed Kitten"}{:priority 10, :name "Buy kitten"})

(def todos (atom #{}))  ;;sorted-set with defined priority order

(defn add-todo! [item]
  (swap! todos conj item))

(do
  (add-todo! {:priority 10 :name "Buy kitten"})
  (add-todo! {:priority 1 :name "Buy cat food"})
  (add-todo! {:priority 2 :name "Feed kitten"})

  (seq @todos)
  )
;; => ({:priority 10, :name "Buy kitten"}{:priority 1, :name "Buy cat food"}{:priority 2, :name "Feed Kitten"})
; just a random order with non-sorted set
