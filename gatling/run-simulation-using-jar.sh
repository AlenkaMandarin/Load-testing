#!/usr/bin/env bash
JAVA_OPTS="-DbaseUrl=https://mysite.test.ru:443 -DdurationMin=1 -DrequestPerSecond=1"
SIMULATION_NAME=gatling.test.example.simulation.ExampleSimulation
java ${JAVA_OPTS} -cp target/gatling-java-example.jar io.gatling.app.Gatling -s "${SIMULATION_NAME}"
