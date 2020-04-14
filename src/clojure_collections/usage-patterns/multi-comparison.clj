(ns clojure-collections.usage-patterns.multi-comparison)

;; Multi-comparison

(defn vp? [name]
  (or (= name "John Jacobson")
      (= name "Linda Laurens")
      (= name "June James")
      (= name "Fred Franklin")))

(filter vp? employees)

;; Is it one of these?

(def vice-presidents #{"John Jacobson"
                       "Linda Laurens"
                       "June James"
                       "Fred Franklin"
                       ;....
                       })

(filter vice-presidents employees)

(class #{})
;; => PersistentHashSet
(supers (class #{}))
;list of super classes

(#{1 2} 1)
;; => 1
(#{1 2} 2)
;; => 2
(#{1 2} 3)
;; => nil

;filter out vowels
(filter #{\a \e \i \o \u} "hello my name is eric")
;; => (\e \o \a \e \i \e \ i)

(defn vowel? [letter]
  (contains? #{\a \e \i \o \u}))

(vowel? \a)
;; => true
(vowel? \l)
;; = false
(#{\a \e \i \o \u} \a)
;; => \a
(#{\a \e \i \o \u} \l)
;; => nil

;;trouble as they are not equal
(#{false} false)
;; => false
(contains? #{false} false)
;; => true
(#{nil} nil)
;; => nil
(contains? #{nil} nil)
;; => true
