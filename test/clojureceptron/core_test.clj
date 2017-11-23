(ns clojureceptron.core-test
  (:require [clojure.test :refer :all]
            [clojureceptron.core :refer :all]))

(deftest wights-generator-test
  (testing "Should return a collection of random numbers"
    (-> (start-weights 20)
        (count)
        (= 20)
        (is true))))

(deftest unit-step-test
  (testing "Should return a collection of random numbers"
    (is (= (unit-step -1) 0))
    (is (= (unit-step 1) 1))
    (is (= (unit-step 0) 1))
    ))

(deftest scalar-multiplication-test
  (testing "Should return a collection of random numbers"
    (is (= (scalar-multiplication 2 [1 2 3]) [2 4 6]))
    ))

(deftest addition-test
  (testing "Should return a collection of random numbers"
    (is (= (addition [1 2 3] [1 2 3]) [2 4 6]))
    ))

(deftest dot-product-test
  (testing "Should return a collection of random numbers"
    (is (= (dot-product [1 2 3] [1 2 3]) 14))
    ))






