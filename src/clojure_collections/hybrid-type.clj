(ns clojure-collections.hybrid-type)

{:set #{}
  :vec []}

'hy-conj
'hy-seq

(deftype unique-vec [s v]
  clojure.lang.IPersistentCollection
  (count [this]
    (.count v))
  (cons [this value]
    if (contains? s value)
      this
      (unique-vec. (conj s value) (conj v value)))
  (empty [this]
    (unique-vec. #{} []))
  (equiv [this o]
    (.equiv v o))
  clojure.lang.Seqable
  (seq [this]
    (.seq v))
  clojure.lang.Counted
  clojure.lang.Sequential

  clojure.lang.Indexed
  (nth [this i]
    (.nth v i))
  (nth [this i not-found]
    (.nth v i not-found))

  clojure.lang.Reversible
  (rseq [this]
    (.rseq v))

  clojure.lang.IPersistentStack
  (peek [this]
    (.peek v))
  (pop [this]
    (if (.seq v)
    (let [value (.peek v)]
    (unique-vec. (disj s value) (.pop v)))
    this))

    Iterable
    (iterator [this]
      (.iterator v))

    Comparable
    (compareTo [this o]
      (.compareTo v o))

  )

;; count calls a java method to figure out to call count
;; .count calls method directly

(def empty-unique-vec (unique-vec. #{} []))

(comment
  (unique-vec. #{} [])

  (seq (conj empty-unique-vec :a :b :c :a))
  ;; => (:a :b :c)
  (count (conj empty-unique-vec :a :b :c :a))
  ;; => 3

  (= (conj empty-unique-vec :a :b :c) [:a :b :c])
  ;; => true
  (= (conj empty-unique-vec :a :b :c :a) [:a :b :c :a])
  ;; => false
  (= [:a :b :c] (conj empty-unique-vec :a :b :c))
  ;; => false
  ;; but after implementing Sequential in the deftype
  (= [:a :b :c] (conj empty-unique-vec :a :b :c))
  ;; => true

  (nth (conj empty-unique-vec :a :b :c :a) 2)
  ;; => :c

  (-> empty-unique-vec
    (conj :a)
    (conj :b)
    (conj :c)
    (pop)
    (pop)
    (peek)
    ;; => :a
    )

  )
