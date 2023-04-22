#!/bin/bash

if [[ -z $1 ]]; then
	echo "Usage: ./local-iam stop [service]"
	exit 0
fi

SERVICE="$1"
docker-compose stop $SERVICE