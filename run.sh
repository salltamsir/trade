#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Error : The script requires one argument"
    exit 1
fi

if [ -d "target" ]; then
    if [ -n "$(find target -maxdepth 1 -type f -name '*.jar')" ]; then
        java -jar target/*.jar "$1"
    else
        echo "----------------- Building application -----------------"
        mvn clean install > /dev/null 2>&1
        echo "----------------- Building finished    -----------------"
        java -jar target/*.jar "$1"
    fi
else
    echo "----------------- Building application -----------------"
    mvn clean install > /dev/null 2>&1
    echo "----------------- Building finished    -----------------"
    java -jar target/*.jar "$1"
fi
