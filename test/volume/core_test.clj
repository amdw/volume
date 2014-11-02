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
    (is (= 1 (volume [2 1 2])) "U-shaped")
    (is (= 1 (volume [2 1 3])) "U-shaped but leaking right")
    (is (= 2 (volume [3 1 3])) "Longer U-shaped")
    (is (= 2 (volume [2 1 1 2])) "Wider U-shaped")
    (is (= 3 (volume [4 1 2 3])) "Sloping right-hand side")
    (is (= 2 (volume [4 1 2 1 2])) "Two pools, leaking right")
    (is (= 5 (volume [4 1 2 1 3])) "Two pools, leaking right a bit less")
    (is (= 8 (volume [4 1 2 1 4])) "Two full pools")))

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
