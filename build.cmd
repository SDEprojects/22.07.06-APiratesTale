@echo off

javac -d classes -classpath "lib\*" src\com\company\client\*.java src\com\company\models\*.java

jar --create --file 22.07.06-APiratesTale-1.0.jar -C classes .