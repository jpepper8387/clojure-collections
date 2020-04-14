(ns clojure-collections.hybrids)

;; Hybrids

;; Forget duplicates

;;; Hashmap Set

;; Remember order

;;; Vector List Queue

(def empty-hybrid {:set {}  ;; forget duplicates
                   :vec []  ;; remember order
                   })
;; normall set and vec are names specific to what u are storing, like elements or colors

(defn hy-conj [coll value]
  (if (contains? (:set coll) value )
    coll
    (-> coll
      (update :set conj value)
      (update :vec conj value))))

(defn hy-seq [coll]
  (seq (:vec coll)))
