#!/bin/bash

comm_name=softmarket
repo_name=smxknife
docker stop $comm_name
docker rm $comm_name
docker rmi $repo_name/$comm_name
docker build -t $repo_name/$comm_name .
docker run -p 80:10001 --name $comm_name -d $repo_name/$comm_name