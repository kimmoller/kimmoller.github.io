#!/bin/bash

echo "Undeploying iam-project..."
docker-compose down 

echo
echo -n "Waiting for containers to stop"
until [ `docker network inspect iam-project | grep -c "\"Containers\": {}"` -eq 1 ]; do
	echo -n "."
	sleep 1
done

echo
echo "Deleting network iam-project..."
docker network rm iam-project 1>/dev/null

exit 0