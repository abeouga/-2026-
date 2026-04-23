#!/bin/sh
DIR=$(cd $(dirname "$0") && pwd)
CATALINA_HOME="$DIR/tomcat"
CATALINA_OPTS="-Dfile.encoding=UTF-8"
export CATALINA_HOME CATALINA_OPTS
"$CATALINA_HOME/bin/catalina.sh" run
