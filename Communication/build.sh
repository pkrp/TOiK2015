#!/bin/sh

mvn clean install
cd ./communication-bundle
mvn p2:site
mvn jetty:run