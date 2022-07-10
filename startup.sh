#!/bin/bash

mvn clean
mvn package
killall java
java -jar ./target/dby.jar --spring.profiles.active=test

