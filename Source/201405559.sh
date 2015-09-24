#!/bin/bash


javac -d . -cp ./code: code/Comp.java
javac -d . -cp ./code: code/container.java
javac -d . -cp ./code: code/mergeFiles.java
javac -d . -cp ./code: code/myOwn.java
javac -d . -cp ./code: code/TwoWaySort.java

java -Xmx"$3"M TwoWaySort $@
