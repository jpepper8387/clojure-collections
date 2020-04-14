(ns clojure-collections.usage-patterns.tuple)

;; Tuple

;;entity
;can add keys and ignore keys that aren't needed
;work better with change
;keys have no order when added
;allowed to have partial entities
{:first-name "Eric"
 :last-name  "Normand"
 :phone "444-3322"
 :address "23 Jones St"
 :email "eric@lispcast.com"
 :dob nil}

;;tuple -- code smell! long tuple
;based on position, not key word
;can get too long and confusing as to what it means
;also requires a nil for missing values
;loses human readability
;has to use all data in lookups
;can only add new values to the end
;partial entities have nil values
["Eric" "Normand" "444-3322" "23 Jones St" "eric@lispcast.com" nil]

(re-find #"aa(b+)(c+)" "aabbccc")
;; => ["aabbccc", "bb", "ccc"]
(re-find #"aa(b+)(d*)(c+)" "aabbccc")
;; => ["aabbccc", "", "bb", "ccc"]

(let [[_ bs ds cs] (re-find #"aa(b+)(d*)(c+)" "aabbccc")])

(let [[old new] (swap-vals! (atom 0) inc)] )
;; => [0 1]

{:new 1 :old 0}

;tuple escapes context
;turn tuple into something with more meaning
(defn matching-as-bs-cs [s]
  (let [[_ bs ds cs] (re-find #"aa(b+)(d*)(c+)" s)]
  {:number-of-bs (count bs)
   :number-of-cs (count cs)
   :number-of-ds (count ds)))

;; Variant Tuple

(defn get-file-location []
  ...)

[:url "http://storage.com/my-file.txt"]
[:disk "/home/eric/my-file.txt"]
[:paper 5 3 12]

;still lacks detial compared to entity
{:file-location :paper
 :row 5
 :shelf 3
 :box 12}

 {...

  :file-location [:url "http://storage.com/my-file.txt"]}

(defn fetch-file [location]
  (case (first location)
    :paper ...
    :disk ...
    :url ...))
