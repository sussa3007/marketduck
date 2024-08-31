#!/bin/bash

# /home/sussa/Desktop/WorkSpace/marketduck/
# /home/sussa/log/marketduck/
BUILD=/home/sussa/Desktop/WorkSpace/marketduck
DEV_JAR=marketduck-0.0.1-SNAPSHOT.jar
LOG_PATH=/home/sussa/log/marketduck
DEPLOY_PATH=/home/sussa/Desktop/WorkSpace/marketduck/

cd $BUILD || { echo "Failed to change directory to $BUILD"; exit 1; }

# 스크립트 로그 파일이 없다면 생성
mkdir -p $LOG_PATH

git fetch --all

git reset --hard origin/dev

git checkout dev
git pull origin dev

chmod 700 gradlew
sudo chmod -R 755 .

# MySQL 컨테이너 이름
MYSQL_CONTAINER_NAME=mysql

# MySQL 컨테이너가 실행 중인지 확인하고 실행되지 않은 경우 시작
if [ ! "$(sudo docker ps -q -f name=$MYSQL_CONTAINER_NAME)" ]; then
    echo "Starting MySQL container..."
    sudo docker-compose up -d db
    sleep 20  # 컨테이너가 완전히 시작될 때까지 대기
fi

./gradlew clean build


echo "> 현재 시간: $(date)" >> $LOG_PATH/deploy.log
echo "> dev build 파일명: $DEV_JAR" >> $LOG_PATH/deploy.log
echo "> dev build 파일 복사" >> $LOG_PATH/deploy.log

CONTAINER_NAME=marketduck-app

# Redis 컨테이너 이름
REDIS_CONTAINER_NAME=redis_boot
REDIS_DEV_CONTAINER_NAME=redis_boot_dev

# 기존 컨테이너 종료 후 이미지 제거
OLD_IMAGES=$(sudo docker images -aq --filter "reference=$CONTAINER_NAME")

if [ ! -z "$OLD_IMAGES" ]; then
  echo "Stopping and removing containers using images..."
  CONTAINERS=$(sudo docker ps -q --filter "ancestor=$CONTAINER_NAME")

  if [ ! -z "$CONTAINERS" ]; then
    echo "Stopping running containers..."
    sudo docker stop $CONTAINERS
    echo "Removing stopped containers..."
    sudo docker rm $CONTAINERS
  fi

  echo "Removing old images..."
  sudo docker rmi -f $OLD_IMAGES
else
  echo "No images found for reference: $CONTAINER_NAME"
fi


# 기존 MySQL 컨테이너를 중지하고 제거
if [ "$(sudo docker ps -q -f name=$MYSQL_CONTAINER_NAME)" ]; then
    echo "Stopping existing MySQL container..."
    sudo docker stop $MYSQL_CONTAINER_NAME
    echo "Removing existing MySQL container..."
    sudo docker rm $MYSQL_CONTAINER_NAME
fi

# 기존 Redis 컨테이너를 중지하고 제거
if [ "$(sudo docker ps -q -f name=$REDIS_CONTAINER_NAME)" ]; then
    echo "Stopping existing Redis container..."
    sudo docker stop $REDIS_CONTAINER_NAME
    echo "Removing existing Redis container..."
    sudo docker rm $REDIS_CONTAINER_NAME
fi

# 기존 Redis Dev 컨테이너를 중지하고 제거
if [ "$(sudo docker ps -q -f name=$REDIS_DEV_CONTAINER_NAME)" ]; then
    echo "Stopping existing Redis Dev container..."
    sudo docker stop $REDIS_DEV_CONTAINER_NAME
    echo "Removing existing Redis Dev container..."
    sudo docker rm $REDIS_DEV_CONTAINER_NAME
fi

NGINX_CONTAINER_NAME=nginx_server
# 기존 Nginx 컨테이너를 중지하고 제거
if [ "$(sudo docker ps -q -f name=$NGINX_CONTAINER_NAME)" ]; then
    echo "Stopping existing NGINX container..."
    sudo docker stop $NGINX_CONTAINER_NAME
    echo "Removing existing NGINX container..."
    sudo docker rm $NGINX_CONTAINER_NAME
fi

CERBOT_CONTAINER_NAME=cerbot
# 기존 cerbot 컨테이너를 중지하고 제거
if [ "$(sudo docker ps -q -f name=$CERBOT_CONTAINER_NAME)" ]; then
    echo "Stopping existing CERBOT container..."
    sudo docker stop $CERBOT_CONTAINER_NAME
    echo "Removing existing CERBOT container..."
    sudo docker rm $CERBOT_CONTAINER_NAME
fi
echo "> Dev DEPLOY_JAR 배포" >> $LOG_PATH/deploy.log



# Docker Compose 빌드
sudo docker-compose build

# 나머지 컨테이너를 Docker Compose을 사용하여 실행
sudo docker-compose up -d