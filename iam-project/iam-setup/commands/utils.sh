#!/bin/bash

create_iam_network() {
	echo "Creating network: iam-project"
	docker network create --opt encrypted --driver bridge --attachable iam-project &>/dev/null
	if [[ $? -gt 0 ]]; then
		echo "Network iam-project already exists"
	else
		echo "Done..."
	fi
}