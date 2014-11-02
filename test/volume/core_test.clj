(ns volume.core-test
  (:require [clojure.test :refer :all]
            [volume.core :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]))

(deftest basic-cases
  (testing "Specific test cases"
    (is (= 0 (volume [])) "Empty")
    (is (= 0 (volume [1])) "Single element")
    (is (= 0 (volume [1 1])) "Flat")
    (is (= 0 (volume [1 2])) "Sloping")
    (is (= 1 (volume [2 1 2])))
    (is (= 1 (volume [2 1 3])))
    (is (= 2 (volume [3 1 3])))
    (is (= 2 (volume [2 1 1 2])))
    (is (= 3 (volume [4 1 2 3])))
    (is (= 2 (volume [4 1 2 1 2])))
    (is (= 5 (volume [4 1 2 1 3])))
    (is (= 8 (volume [4 1 2 1 4])))))

;; Property-based tests

(def num-tests 100)

;; Volume is the same backwards as forwards
(defspec forwards-same-as-backwards num-tests
  (prop/for-all [v (gen/vector gen/nat)]
                (= (volume v) (volume (reverse v)))))

;; Volume is the same if you increment all elements by a constant
(defspec constant-lift-unchanged num-tests
  (prop/for-all [v (gen/vector gen/nat)
                 i gen/nat]
                (= (volume v) (volume (map (partial + i) v)))))
