#!/bin/bash

# /home/sussa/Desktop/WorkSpace/marketduck/
# /home/sussa/log/marketduck/
BUILD=/home/sussa/Desktop/WorkSpace/marketduck
DEV_JAR=marketduck-0.0.1-SNAPSHOT.jar
LOG_PATH=/home/sussa/log/marketduck
DEPLOY_PATH=/home/sussa/Desktop/WorkSpace/marketduck/

cd $BUILD

git checkout dev

git pull origin dev

chmod 700 gradlew

sudo chmod -R 755 .

./gradlew clean build

BUILD_JAR=$(ls /home/sussa/Desktop/WorkSpace/marketduck/build/libs/marketduck-0.0.1-SNAPSHOT.jar)

echo "> 현재 시간: $(date)" >> $LOG_PATH/deploy.log

echo "> dev build 파일명: $DEV_JAR" >> $LOG_PATH/deploy.log

echo "> dev build 파일 복사" >> $LOG_PATH/deploy.log

EXIST_FILE=$DEPLOY_PATH$DEV_JAR
sudo rm -f $EXIST_FILE
sudo cp $BUILD_JAR $EXIST_FILE

APP_NAME=marketduck
CONTAINER_NAME=marketduck-app

# MySQL 컨테이너 이름
MYSQL_CONTAINER_NAME=mysql

# Redis 컨테이너 이름
REDIS_CONTAINER_NAME=redis_boot
REDIS_DEV_CONTAINER_NAME=redis_boot_dev

# Step 3: Stop and remove existing container if it exists
if [ "$(sudo docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "Stopping existing container..."
    sudo docker stop $CONTAINER_NAME
    echo "Removing existing container..."
    sudo docker rm $CONTAINER_NAME
fi

# 기존 MySQL 컨테이너를 중지하고 제거
if [ "$(docker ps -q -f name=$MYSQL_CONTAINER_NAME)" ]; then
    echo "Stopping existing MySQL container..."
    docker stop $MYSQL_CONTAINER_NAME
    echo "Removing existing MySQL container..."
    docker rm $MYSQL_CONTAINER_NAME
fi

# 기존 Redis 컨테이너를 중지하고 제거
if [ "$(docker ps -q -f name=$REDIS_CONTAINER_NAME)" ]; then
    echo "Stopping existing Redis container..."
    docker stop $REDIS_CONTAINER_NAME
    echo "Removing existing Redis container..."
    docker rm $REDIS_CONTAINER_NAME
fi

# 기존 Redis Dev 컨테이너를 중지하고 제거
if [ "$(docker ps -q -f name=$REDIS_DEV_CONTAINER_NAME)" ]; then
    echo "Stopping existing Redis Dev container..."
    docker stop $REDIS_DEV_CONTAINER_NAME
    echo "Removing existing Redis Dev container..."
    docker rm $REDIS_DEV_CONTAINER_NAME
fi

echo "> Dev DEPLOY_JAR 배포"    >> $LOG_PATH/deploy.log

# Step 4: Build Docker image
sudo docker-compose build

# Step 5: Run Docker containers using Docker Compose
sudo docker-compose up -d
