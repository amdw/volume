(defproject volume "0.1.0-SNAPSHOT"
  :description "Clojure code to calculate volume of water held by a simple discrete 2D structure"
  :url "https://github.com/amdw/volume"
  :license {:name "BSD License"
            :url "http://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/test.check "0.5.9"]]
  :main ^:skip-aot volume.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
