(ns clojure-collections.collections.hashmap)
;; Hashmap
{}
;;=> {}

{:a 1
 :b 2
 :c 3}
;;=> {:a 1, :b 2, :c 3}

{ 1 2
  2 3}
;; => {1 2, 2 3}

{[1 2] :alive
 [3 3] :dead}
;;=> {[1 2] :alive, [3 3] :dead}

{:a 1
 :a 2}
 ;;=> duplicate key exception

 (rand) => generates random number
 (rand-int 100) => random int from 0 to 100

 {(rand-int 100) 0
  (rand-int 100) 8}
  ;;=> illegal argument exception

(read-string "{}")
;;=>{}
(read-string "{:a 1 (* 2 2) :four}")
;;=>{:a 1, (* 2 2), :four}
(eval (read-string "{:a 1 (* 2 2) :four}"))
;;=>{:a 1, 4, :four}
(read-string "{(rand-int 100) 1 (rand-int 100) 10}")
;;=> same key twice is illegal

(hash-map :a 1 :b 2 :c 3)
;;=>{:c 3, :b 2, :a 1}    no order in hash map
(array-map :a :b :c 3)
;;=>{:a 1, :b 2, :c 3}    seems to maintain order, but only looks that way when map is small
(into {} [[:a 1] [:b 2] [:c 3]])
;;=>{:a 1, :b 2, :c 3}
(into {} (for [k (range 10)]
          [k (* k k)]))
;; => {0 0, 7 49, 1 1, 4 16, 6 36, 3 9, 2 4, 9 81, 5 25, 8 64}
(def squares (into {} (for [k (range 10)]
          [k (* k k)])))
(seq squares)
;; => ([0 0], [7 49], [1 1], [4 16], [6 36], [3 9], [2 4], [9 81], [5 25], [8 64])
(keys squares)
;;=> (0 7 1 4 6 3 2 9 5 8)
(vals squares)
;;=> (0 49 1 16 36 9 4 81 25 64)
(merge {:a 1 :b 2 :c 3} {:d 4 :e 5})
;;=> {:a 1, :b 2, :c 3, :d 4, :e 5}
(merge {:a 1 :b 2 :c 3} {:d 4 :a 5})  ;things get replaced from right to left
;;=> {:a 5, :b 2, :c 3, :d 4}
(select-keys squares [1 2 3])
;;=> {1 1, 2 4, 3 9}
(select-keys squares [1 2 3 99]) ;still works because it returns every value it can
;;=> {1 1, 2 4, 3 9}

;;  Access Patterns

;;; Order is forgotten

;;; Duplicates are forgotten
(conj {:a 1} [:a 1])
;;=> {:a 1}

;;; Random access by key
(get {:a 1 :b 2} :a)
;; => 1
(get {} {:a})
;;=> nil

;;; Associate key to value
(assoc {:a 1} :b 2 :c 3 "hello" "world")
;;=> {:a 1, :b 2, :c 3, "hello" "world"}
(merge {:a 1} {:b 2 :c 3 "hello" "world"})  ;notice extra curly braces for 2nd arg
;;=> {:a 1, :b 2, :c 3, "hello" "world"}

;;; Remove a pair by key
squares
;;=> {0 0, 7 49, 1 1, 4 16, 6 36, 3 9, 2 4, 9 81, 5 25, 8 64}
(dissoc squares 0)
;;=>{7 49, 1 1, 4 16, 6 36, 3 9, 2 4, 9 81, 5 25, 8 64}

;;; Count
(count squares)
;;=> 10

;;; Equality
(= (hash-map :a 1 :b 2)(array-map :a 1 :b 2))
;;=> true because all key value pairs are present in each

;;; Containment
(contains? squares 1)
;; => true
(contains? squares 2)
;; => true
(contains? squares "hello")
;; => false

;;; Entity
;stores key-value pairs that are different from one another
{:id 123
 :name "Eric"
 :email "eric@lispcast.com"}

{:source-directory "src/"
 ;;....
}

;;; Index

squares
;;=> {0 0, 7 49, 1 1, 4 16, 6 36, 3 9, 2 4, 9 81, 5 25, 8 64}  ;all have same meaning, square of a key

;;; Functions
(map squares (range 6))
;;=> (0 1 4 9 16 25)
(def days {0 :Sunday
           1 :Monday
           2 :Tuesday
           3 :Wednesday
           4 :Thursday
           5 :Friday
           6 :Saturday})
(def day-numbers [1 4 4 2 5 5 4 2 2 5 3 2])
(map days day-numbers)
;;=>(:Monday :Thursday :Thursday :Tuesday :Friday :Friday :Thursday :Tuesday :Tuesday :Friday :Wednesday :Tuesday)
