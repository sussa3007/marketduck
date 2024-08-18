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
CONTAINER_NAME=marketduck-container
# Step 3: Stop and remove existing container if it exists
if [ "$(sudo docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "Stopping existing container..."
    sudo docker stop $CONTAINER_NAME
    echo "Removing existing container..."
    sudo docker rm $CONTAINER_NAME
fi

echo "> Dev DEPLOY_JAR 배포"    >> $LOG_PATH/deploy.log

# Step 4: Build Docker image
sudo docker-compose build

# Step 5: Run Docker containers using Docker Compose
sudo docker-compose up -d
