#!/bin/sh

mvn clean install
mvn p2:site
mvn jetty:run
