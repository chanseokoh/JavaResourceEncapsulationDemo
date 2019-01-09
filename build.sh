#!/bin/sh

"${JAVA_HOME}"/bin/javac -d build/java-mods \
    --module-source-path awesome-library/java-src \
    $( find awesome-library/java-src -name '*.java' )

cp --recursive awesome-library/resources/my.awesome.module/* \
    build/java-mods/my.awesome.module

"${JAVA_HOME}"/bin/javac -d build/non-modular-main \
    --module-path build/java-mods \
    --add-modules my.awesome.module \
    non-modular-main/java-src/com/company/Main.java
