(ns com.breezeehr.malli-java-time
  (:require [malli.core :as m]
            [clojure.test.check.generators :as gen]
            [malli.transform :as mt])
  (:import (java.time LocalDateTime LocalDate LocalTime ZonedDateTime ZoneId)))

(defn -string->localDateTime [x]
  (if (string? x)
    (try
      (LocalDateTime/parse x)
      (catch Exception _e x))
    x))

(defn -string->localDate [x]
  (if (string? x)
    (try
      (LocalDate/parse x)
      (catch Exception _e x))
    x))

(defn -string->zonedDateTime [x]
  (if (string? x)
    (try
      (ZonedDateTime/parse x)
      (catch Exception _e x))
    x))

(defn -string->localTime [x]
  (if (string? x)
    (try
      (LocalTime/parse x)
      (catch Exception _e x))
    x))



(def local-date
  (m/-simple-schema
    {:type            :local-date
     :pred            #(instance? LocalDate %)
     :type-properties {:error/message "should be localDate"
                       :decode/string -string->localDate
                       :encode/string mt/-any->string
                       ;:json-schema/type    "integer"
                       ;:json-schema/format  "int64"
                       ;:json-schema/minimum 6
                       :gen/gen       (gen/let [year ^Long (gen/large-integer* {:min 0 :max 10000})
                                                month ^Long (gen/large-integer* {:min 1 :max 12})
                                                day ^Long (gen/large-integer* {:min 1 :max 29})]
                                        (LocalDate/of year month day))
                       }}))
(def local-date-time
  (m/-simple-schema
    {:type            :local-date-time
     :pred            #(instance? LocalDateTime %)
     :type-properties {:error/message "should be localDateTime"
                       :decode/string -string->localDateTime
                       :encode/string mt/-any->string
                       ;:json-schema/type    "integer"
                       ;:json-schema/format  "int64"
                       ;:json-schema/minimum 6
                       :gen/gen       (gen/let [year ^Long (gen/large-integer* {:min 0 :max 10000})
                                                month ^Long (gen/large-integer* {:min 1 :max 12})
                                                day ^Long (gen/large-integer* {:min 1 :max 29})
                                                hour ^Long (gen/large-integer* {:min 0 :max 23})
                                                minute ^Long (gen/large-integer* {:min 0 :max 59})
                                                second ^Long (gen/large-integer* {:min 0 :max 59})
                                                nanosofsecond ^Long (gen/large-integer* {:min 0 :max 1000000})]
                                        (LocalDateTime/of year month day hour minute second nanosofsecond))}}))

(def zoned-date-time
  (m/-simple-schema
    {:type            :zoned-date-time
     :pred            #(instance? ZonedDateTime %)
     :type-properties {:error/message "should be localDateTime"
                       :decode/string -string->zonedDateTime
                       :encode/string mt/-any->string
                       ;:json-schema/type    "integer"
                       ;:json-schema/format  "int64"
                       ;:json-schema/minimum 6
                       :gen/gen       (gen/let [year ^Long (gen/large-integer* {:min 0 :max 10000})
                                                month ^Long (gen/large-integer* {:min 1 :max 12})
                                                day ^Long (gen/large-integer* {:min 1 :max 29})
                                                hour ^Long (gen/large-integer* {:min 0 :max 23})
                                                minute ^Long (gen/large-integer* {:min 0 :max 59})
                                                second ^Long (gen/large-integer* {:min 0 :max 59})
                                                nanosofsecond ^Long (gen/large-integer* {:min 0 :max 1000000})
                                                zone (gen/elements ZoneId/SHORT_IDS)]
                                        (ZonedDateTime/of (LocalDateTime/of year month day hour minute second nanosofsecond) zone))}}))
(def local-time
  (m/-simple-schema
    {:type            :local-time
     :pred            #(instance? LocalTime %)
     :type-properties {:error/message "should be localTime"
                       :decode/string -string->localTime
                       :encode/string mt/-any->string
                       ;:json-schema/type    "integer"
                       ;:json-schema/format  "int64"
                       ;:json-schema/minimum 6
                       :gen/gen       (gen/let [hour ^Long (gen/large-integer* {:min 0 :max 23})
                                                minute ^Long (gen/large-integer* {:min 0 :max 59})
                                                second ^Long (gen/large-integer* {:min 0 :max 59})
                                                nanosofsecond ^Long (gen/large-integer* {:min 0 :max 1000000})]
                                        (LocalTime/of hour minute second nanosofsecond))}}))
