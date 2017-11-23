(ns clojureceptron.core
  (:gen-class))

(def training-data [[0 0 0]
                    [1 0 1]
                    [0 1 0]
                    [1 1 1]])
(def result-data [0 1 1 1])

(def errors (atom []))
(def w (atom []))

(def eta 0.2)
(def n 100)
(def input-test '(1 0 0))

(defn start-weights [count]
  (->> (repeat count 1)
       (map #(* % (rand)))))

(defn unit-step [x]
  (if (<= x 0) 0 1))

(defn scalar-multiplication [scalar vector]
  (map #(* scalar %) vector))

(defn addition [x y]
  (->> (interleave x y)
       (partition 2 2)
       (map #(apply + %))))

(defn dot-product [x y]
  (->> (interleave x y)
       (partition 2 2)
       (map #(apply * %))
       (reduce +)))

(defn train [step eta]
    (dotimes [i step]
      (do
        (println "- Step " i)
        (let [index-sample (rand-int (count result-data))
              x (training-data index-sample)
              expected (result-data index-sample)
              result (dot-product @w x)
              error (- expected (unit-step result))
              scalar-mult (scalar-multiplication (* eta error) x)]

          (swap! errors conj error)
          (swap! w addition scalar-mult)))))

(defn -main [& args]
  (println "Starting the training!")
  (reset! w (start-weights 3))
  (train n eta)
  (println "weights: " @w)
  (println "input: " input-test)
  (println "result: " (unit-step (dot-product input-test @w))))
