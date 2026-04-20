#!/bin/sh
DIR=$(cd $(dirname "$0") && pwd)
cd "$DIR/tomcat/webapps/book/WEB-INF/src"
exec $SHELL
