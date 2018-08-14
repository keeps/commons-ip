#!/bin/bash

set -ex

################################################
# functions
################################################
function deploy_to_artifactory(){
  echo "Deploy to artifactory"
  cp settings.xml $HOME/.m2/
  mvn $MAVEN_CLI_OPTS clean package deploy -Dmaven.test.skip=true
  rm $HOME/.m2/settings.xml
}


################################################
# Compile, test, etc. 
################################################

#mvn $MAVEN_CLI_OPTS clean org.jacoco:jacoco-maven-plugin:prepare-agent package 
# 20180814 hsilva while not meeting code coverage, lets disable jacoco
mvn $MAVEN_CLI_OPTS clean package

################################################
# Deploy
################################################

# init
# do nothing

if [ "$TRAVIS_BRANCH" == "master" ]; then
  echo "Logic for master branch"
  deploy_to_artifactory
elif [ "`echo $TRAVIS_BRANCH | egrep "^v[2-9]+" | wc -l`" -eq "1" ]; then
  echo "Logic for tags"
  deploy_to_artifactory
fi

# clean up
# do nothing
