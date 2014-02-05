(ns iroh.element.constructor
  (:require [iroh.types.element :refer [element invoke-element
                                        to-element format-element]]
            [iroh.element.common :refer [seed]]
            [iroh.element.method :refer [format-element-method]]))

(defmethod invoke-element :constructor [ele & args]
  (.newInstance (:delegate ele) (object-array args)))

(defmethod to-element java.lang.reflect.Constructor [obj]
  (let [body (seed :constructor obj)]
    (-> body
        (assoc :name "new")
        (assoc :static true)
        (assoc :type (.getDeclaringClass obj))
        (assoc :params (vec (seq (.getParameterTypes obj))))
        (element))))

(defmethod format-element :constructor [ele]
  (format-element-method ele))
