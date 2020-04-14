(ns clojure-collections.atoms)

(def queue (atom clojure.lang.PersistentQueue/EMPTY))

(comment
  (swap! queue conj :a)
  (seq @queue)
  ;; => (:a)
  (peek @queue)
  ;; => :a
  (swap! queue pop)
  ;; => nil
)

;entire point of atom is to make it atomic
;if another op tries to peek the queue at the same time it does
; the same work twice instead of possibly returning two diff values

;; pre Clojure 1.9
;; convert the above to use a tuple

(def queue (atom [nil clojure.lang.PersistentQueue/EMPTY]))

(defn enqueue! [value]
  (swap! queue update 1 conj value)
  nil)

(defn dequeue! []
  (swap! queue (fn [_ q]
                  [(peek q)
                  (pop q)])))

(comment
  (enqueue! :a)
  ;; => nil
  (enqueue! :b)
  ;; => nil
  (dequeue!)
  ;; => [:a, queue-object]
  (dequeue!)
  ;; => [:b, queue-object]
  ;; queue-object doesn't need to be returned, only want returned value
)

;take first of op to return just the returned value
(defn dequeue! []
  (first (swap! queue (fn [_ q]
                  [(peek q)
                  (pop q)]))))

(comment
  (enqueue! :a)
  ;; => nil
  (enqueue! :b)
  ;; => nil
  (dequeue!)
  ;; => :a
  (dequeue!)
  ;; => :b
)

;had to create our own functions for already existing functions (peek pop remove)

;; post Clojure 1.9

(def queue19 (atom clojure.lang.PersistentQueue/EMPTY))

(defn enqueue19! [value]
  (swap! queue19 conj value)
  nil)

(defn dequeue19! []
  (swap-vals! queue19 pop))
;much more straight foward using existing function

(comment
  (enqueue19! :a)
  (enqueue19! :b)
  (seq @queue19)
  ;; => (:a :b)

  (dequeue19!)
  ;; [old-queue, new-queue]
  )

(defn dequeue19! []
  (let [[old new] (swap-vals! queue19 pop)]
    (peek old)))

(comment
  (enqueue19! :a)
  (enqueue19! :b)
  (seq @queue19)
  ;; => (:a :b)

  (dequeue19!)
  ;; :a
  (dequeue19!)
  ;; :b
  (dequeue19!)
  ;; :nil
  )
