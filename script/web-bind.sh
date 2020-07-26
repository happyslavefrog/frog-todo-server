#!/bin/bash
set -e

WEB_JAR=./todo-web/build/libs/*.jar
WEB_DEPLOY_SCRIPT=./todo-web/script/deploy.sh
WEB_BASE=./script/web
WEB_TARGET=./script/web/items

echo "> WEB DIR 생성"
mkdir -p $WEB_BASE
mkdir -p $WEB_TARGET

ehco "> WEB 배포 스크립트 이동"
cp $WEB_DEPLOY_SCRIPT $WEB_TARGET/
echo "> WEB jar 이동"
cp $WEB_JAR $WEB_TARGET/
echo "> WEB 배포 스크립트, jar 압축"
cd $WEB_TARGET
tar -cvf web.tar *
mv web.tar ../

echo "> PROJECT_ROOT/script/web/web.tar 생성"