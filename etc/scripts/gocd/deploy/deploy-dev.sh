#!/bin/bash

# NOTE: this script is expectd to be run from project checkout dir

function init() {
  set -eu
  : ${TARGET_ENV:?}
  : ${TARGET_ENV_PORT:?}

  ROOT_DIR="$SCRIPT_DIR/../../../.."

  VERSION=`cat $ROOT_DIR/version`
  AWS_TEST=ubuntu@54.76.115.155
  IDENTITY_FILE=/var/go/hsl-liipi.pem

  DOCKER_REGISTRY="172.31.0.27:5000"
  LOCAL_IMAGE="parkandrideapi/server:$TARGET_ENV"
  REGISTRY_IMAGE="$DOCKER_REGISTRY/$LOCAL_IMAGE"
  CONTAINER_NAME="parkandrideapi-server-$TARGET_ENV"
  DOCKERFILE_DIR="$ROOT_DIR/etc/docker/app"
  APP_LATEST="parkandride-application-latest.jar"
  APP_NEW="parkandride-application-$VERSION.jar"
}

function build_image() {
  local workdir=`pwd`

  cp $ROOT_DIR/staging/fi/hsl/parkandride/parkandride-application/$VERSION/$APP_NEW $DOCKERFILE_DIR/$APP_LATEST
  cd $DOCKERFILE_DIR
  docker build -t $LOCAL_IMAGE .

  cd $workdir
}

function push_image() {
  docker tag $LOCAL_IMAGE $REGISTRY_IMAGE
  docker push $REGISTRY_IMAGE
}

function ssh2test() {
  ssh -i $IDENTITY_FILE -oStrictHostKeyChecking=no $AWS_TEST "$@"
}

function restart_container() {
  ssh2test "docker stop $CONTAINER_NAME || true"
  ssh2test "docker rm $CONTAINER_NAME || true"
  ssh2test "docker pull $REGISTRY_IMAGE"
  ssh2test "docker run -e SPRING_PROFILES_ACTIVE=demo -d -p $TARGET_ENV_PORT:8080 --name $CONTAINER_NAME $REGISTRY_IMAGE"
}

function run() {
  build_image
  push_image
  restart_container
}

VERBOSE="true"
source $(dirname $0)/../../main.inc
