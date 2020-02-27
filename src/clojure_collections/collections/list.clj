(ns clojure-collections.collections.list)

;; Lists

()
;;=> ()

'(1 2 3 4)
;;=>(1 2 3 4)

'(:a :b :c)
;;=> (:a :b :c)

'(1 (+ 1 1) 3 4)
;;=> (1 (+ 1 1) 3 4)    ;did not evaluate this

[1 (+ 1 1) 3 4]
;;=> [1 2 3 4]     ;prefer vectors if its just for data as it evaluates

;;(1 (+ 1 1) 3 4)     bad code so commented out

(list 1 2 3)
;;=> (1 2 3)
(list 1 (+ 1 1) 3)
];;=> (1 2 3)      ;evaluates when declared this way

(cons 0 (list 1 2 3))
;; => (0 1 2 3)

;lists are linear but they cache the count
(count (cons 0 (cons 1 (cons 2 (cons 3 nil)))))
;;=> 4     ;each cons knows the count so its not linear

(def a (list 1 2 3))

(into () [1 2 3])
;;=> (3 2 1)     ;reverses them because it adds to front - could also use cons

(identical? a a)
;;=> true      means they are the same object
(identical? a (seq a))
;;=> true      ;seq first and rest are natural to lists

(list? a)
;;=> true
(seq? a)
;;=> true
(seq? [1 2 3])
;;=>false
(seq? (seq [1 2 3]))
;;=> true
(list? (seq [1 2 3]))
;;=> false

(defn f
  ([]
    ;;;
    )
  ([x]
    ;;;
    )
  ([x & xs]
    (let [xs (cons x xs)]
      (reduce (fn [a b])
        [] xs))))
