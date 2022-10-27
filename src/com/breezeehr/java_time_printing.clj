(ns com.breezeehr.java-time-printing
  (:import (java.time Duration Instant LocalDateTime LocalDate LocalTime ZonedDateTime)))

(defmethod print-method LocalDateTime [^LocalDateTime x writer]
  (doto writer
    (.write "#LocalDateTime ")
    (.write "\"")
    (.write (.toString x))
    (.write "\"")))
(defmethod print-method ZonedDateTime [^ZonedDateTime x writer]
  (doto writer
    (.write "#ZonedDateTime ")
    (.write "\"")
    (.write (.toString x))
    (.write "\"")))
(defmethod print-method LocalDate [^LocalDate x writer]
  (doto writer
    (.write "#LocalDate ")
    (.write "\"")
    (.write (.toString x))
    (.write "\"")))

(defmethod print-method LocalTime [^LocalTime x writer]
  (doto writer
    (.write "#LocalTime ")
    (.write "\"")
    (.write (.toString x))
    (.write "\"")))

(defmethod print-method Duration [^Duration x writer]
  (doto writer
    (.write "#Duration ")
    (.write "\"")
    (.write (.toString x))
    (.write "\"")))

(defmethod print-method Instant [^Instant x writer]
  (doto writer
    (.write "#Instant ")
    (.write "\"")
    (.write (.toString x))
    (.write "\"")))