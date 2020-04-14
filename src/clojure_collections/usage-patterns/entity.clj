(ns clojure-collections.usage-patterns.entity)

;; Entity

(def eric
{:name "Eric Normand"
 :address "124 Main St"
 :height 1.6
 :date-of-birth #inst "1981-07-18"})

 (:name eric)
 ;; => "Eric Normand"
 (:height eric)
 ;; => 1.6
 (get eric :name)
 ;; => "Eric Normand"

 (assoc eric :name "John Doe")
 ;; => {:name "John Doe" :address "124 Main St" :height 1.6 :date-of-birth #inst "1981-07-18"}

;;add a new key
(assoc eric :user-id "3221312")
;; => {:name "John Doe" :address "124 Main St" :height 1.6 :date-of-birth #inst "1981-07-18" :user-id "3221312"}

(dissoc eric :name)
;; => {:address "124 Main St" :height 1.6 :date-of-birth #inst "1981-07-18" :user-id "3221312"}

;; Variant Entity

;; cash
;;    amount in dollars
;; check
;;    amount in dollars
;;    account number
;;    routing number
;; credit card
;;    number
;;    amount
;;    code
;;    expiration

;have identifier key (payment method) to easily distinguish between them

{:payment-method :cash
 :amount 100} ;cash
{:payment-method :check
 :amount 100
 :account "43242342"
 :routing "4534534532"} ;check
 {:payment-method :credit
  :amount 100
  :number "43424255"
  :code "433"
  :expiration "11/23"} ;credit card

  ; code smell
  ; type doesn't tell the category it belongs to
  ; very important to use semantically loaded work, that is meaningful

{:type :cash
 :amount 100} ;cash
{:type :check
 :amount 100
 :account "43242342"
 :routing "4534534532"} ;check
 {:type :credit
  :amount 100
  :number "43424255"
  :code "433"
  :expiration "11/23"} ;credit card
{:type :circle
 :radius 5}

; re-set

{:payment-method :cash
 :amount 100} ;cash
{:payment-method :check
 :amount 100
 :account "43242342"
 :routing "4534534532"} ;check
 {:payment-method :credit
  :amount 100
  :number "43424255"
  :code "433"
  :expiration "11/23"} ;credit card
{:shape :circle
 :radius 5}


;anti-pattern

{:account-type :admin
 :userid "42354536"
 :username "eric"
 :email "eric@lispcast.com"}


(defn save-to-db! [entity]
  (case (or (:payment-method entity)
            (:account-type entity))
    :cash
    ;;
    :check
    ;;
    :credit
    ;;
    :admin
    ;;
    )
  )

; create one save to db method grouping similar function

(defn save-payment-method-to-db! [entity]
  (case (:payment-method entity)
    :cash
    ;;
    :check
    ;;
    :credit
    ;;
    )
  )

(defn save-account-to-db! [entity]
  (case (:account-type entity)
    :admin
    ;;
    )
  )
