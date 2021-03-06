(ns iroh.test-pretty-display
  (:use midje.sweet)
  (:require [iroh.pretty.display.filter :refer :all]
            [iroh.pretty.display.sort :refer :all]))

(fact "has-name?"
  (has-name? #"get" "getString")
  => true

  (has-name? #"to" "getString")
  => false

  (has-name? "getString" "getString")
  => true

  (has-name? "toString" "getString")
  => false)

(fact "has-params?"
  (has-params? [Integer/TYPE] [Integer/TYPE])
  => true

  (has-params? ["byte[][]"] [(Class/forName "[[B")])
  => true

  (has-params? ["[J"] [(Class/forName "[J")])
  => true)

(fact "has-any-params?"
  (has-any-params? [:any 'int String] [])
  => false

  (has-any-params? [:any 'int String] [Integer/TYPE])
  => true

  (has-any-params? [:any Integer/TYPE String] [String Integer/TYPE])
  => true)

(fact "has-all-params?"
  (has-all-params? [:all Integer/TYPE String] [Integer/TYPE])
  => false

  (has-all-params? [:all Integer/TYPE String] [String Integer/TYPE])
  => true)

(fact "has-modifier?"
  (has-modifier? :static #{:static})
  => true)

(fact "has-type?"
  (has-type? "java.lang.Object[][]" (Class/forName "[[Ljava.lang.Object;"))
  => true)
