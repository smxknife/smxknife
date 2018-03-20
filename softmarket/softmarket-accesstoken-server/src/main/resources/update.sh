#!/bin/bash

comm_name=accesstoken
repo_name=smxknife
docker stop $comm_name
docker rm $comm_name
docker rmi $repo_name/$comm_name
docker build -t $repo_name/$comm_name .
docker run --name $comm_name --network softmarket -d $repo_name/$comm_name