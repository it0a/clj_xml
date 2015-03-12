(ns clj-xml.core
  (:gen-class))
(defn -main
  [& args]
  (println "incomplete"))

(use 'clojure.data.xml)

(def sample-invoice [
  {:id 1}
  {:id 2}
  {:id 3}])

(def sample-line-item [
  {:id 1 :invoice_id 1 :amount 7234.12}
  {:id 2 :invoice_id 1 :amount 5235.50}
  {:id 3 :invoice_id 1 :amount 4440.57}
  {:id 4 :invoice_id 2 :amount 1456.36}
  {:id 5 :invoice_id 2 :amount 2459.79}
  {:id 6 :invoice_id 2 :amount 4236.91}
  {:id 7 :invoice_id 3 :amount 5342.36}
  {:id 8 :invoice_id 3 :amount 6436.56}
  {:id 9 :invoice_id 3 :amount 8237.54}])

(defn filter-line-items [invoice] 1)

(defn join-line-items [invoice]
  assoc invoice :line-items (filter-line-items invoice))

(map join-line-items sample-invoice)

(assoc-in sample-invoice [0 :line-items] 999)


(defn invoice-from [data]
  (element :invoices {}
               (reduce (fn [invoices i] (println invoices) (println i)
                   (conj invoices (element :invoice {:id (:id i)})))
                 () data)))

(defn line-item-from [data]
  (element :line-item {}
           (reduce (fn [line-items li]
                     (conj line-items (element :line-item {:id (:id i)})))
                   () data)))

(emit-str (invoice-from sample-invoice))
