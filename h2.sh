#!/bin/sh
DIR=$(cd $(dirname "$0") && pwd)
java -cp "$DIR/h2/bin/h2-2.1.214.jar" org.h2.tools.Server -ifNotExists
