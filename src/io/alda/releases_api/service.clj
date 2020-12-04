(ns io.alda.releases-api.service
  (:require [io.pedestal.http       :as http]
            [io.pedestal.http.route :as route]))

(defn report-health
  [_request]
  {:status 200, :body "I'm healthy!"})

(def routes
  (route/expand-routes
    #{["/health" :get `report-health]}))

(defn serve
  [port]
  (-> (http/create-server
        {::http/routes routes
         ::http/type   :jetty
         ::http/host   "0.0.0.0"
         ::http/port   port})
      http/start))
