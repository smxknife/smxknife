#!/bin/bash

comm_name=usermgr
repo_name=smxknife
docker stop $comm_name
docker rm $comm_name
docker rmi $repo_name/$comm_name
docker build -t $repo_name/$comm_name .
docker run -p 10002:10002 --name $comm_name --network softmarket -d $repo_name/$comm_name