#!/bin/sh

"${JAVA_HOME}"/bin/java --module-path build/java-mods \
    --add-modules my.awesome.module \
    --add-opens my.awesome.module/com.example.baz=ALL-UNNAMED \
    -classpath build/non-modular-main \
    com.company.Main
