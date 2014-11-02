(ns volume.core
  (:gen-class))

(defn- volume-at-height
  "Amount of volume contained by a structure at a certain height"
  ([structure height] (volume-at-height structure height false 0 0))
  ([structure    ; Vector of heights
    height       ; Height which we are looking at
    seen-left?   ; Have we seen a left-hand wall at this height?
    confirmed    ; Volume which we have confirmed to be contained at this level
    potential]   ; Volume which will be contained at this level if we find a right-hand wall
  (if (empty? structure) confirmed
    (let [[h & hs] structure]
      (if (>= h height)
        (recur hs height true (+ confirmed potential) 0)
        (recur hs height seen-left? confirmed
               (if seen-left? (inc potential) potential)))))))

(defn volume
  "Calculate the volume of water held by a simple 2D structure described by a vector of heights.
   For example, the volume held by [4 1 2 3] would be 3, as follows:
   #
   #..#
   #.##
   ####"
  [heights]
  (if (empty? heights) 0
    (let [max-height (apply max heights)]
      (reduce + (map (partial volume-at-height heights) (range 1 (inc max-height)))))))

(defn -main
  "Show some examples."
  [& args]
  (println "[4 1 2 3] " (volume [4 1 2 3])))

