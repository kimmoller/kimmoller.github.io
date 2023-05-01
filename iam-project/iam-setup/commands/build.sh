#!/bin/bash

REPOS=(
	"identity-service"
	"change-listener"
	"keycloak-public-handler"
)

findRepo() {
	local SEARCH_PATH="$1"
	local REPO="$2"
	local REPO_PATH=`find $SEARCH_PATH -name "*$REPO"`
	if [[ $REPO_PATH == "" ]]; then
		echo "Could not find $REPO anywhere under $SEARCH_PATH"
		exit 1
	else
		echo $REPO_PATH
	fi
}


gitActions() {
	local REPO="$1"
	local REPO_PATH="$2"
	local CWD=`pwd`
	cd $REPO_PATH && local BRANCH_NAME=`git rev-parse --abbrev-ref HEAD`
	echo "Pulling $REPO ($BRANCH_NAME)"
	git pull -q; cd $CWD
}

jibActions() {
	local REPO="$1"
	local REPO_PATH="$2"
	ls $REPO_PATH/gradlew &>/dev/null
	if [[ $? -eq 0 ]]; then
		$REPO_PATH/gradlew -p $REPO_PATH jibDockerBuild -Djib.to.image=$REPO-local -Djib.from.image=registry://openjdk:17  -Djib.container.creationTime=USE_CURRENT_TIMESTAMP --stacktrace
	fi
}

dockerActions() {
	local REPO="$1"
	local REPO_PATH="$2"
	ls $REPO_PATH/gradlew &>/dev/null
	if [[ $? -gt 0 ]]; then
		docker build --tag $REPO-local $REPO_PATH
	fi
}

if [[ -z $2 ]]; then
	SEARCH_PATH="$1"
	for REPO in "${REPOS[@]}"; do
		REPO_PATH=`findRepo $SEARCH_PATH $REPO`
		gitActions $REPO $REPO_PATH
		jibActions $REPO $REPO_PATH
		# yarnActions $REPO_PATH
		dockerActions $REPO $REPO_PATH
	done
else
	SEARCH_PATH="$1"
	REPO="$2"
	IS_ALLOWED=0
	for R in "${REPOS[@]}"; do
		if [[ "$R" == "$REPO" ]]; then
			IS_ALLOWED=1
		fi
	done
	if [[ IS_ALLOWED -eq 0 ]]; then
		echo "Repo \"$REPO\" not allowed, not listed"
		exit 1
	fi
	REPO_PATH=`findRepo $SEARCH_PATH $REPO`
	gitActions $REPO $REPO_PATH
	jibActions $REPO $REPO_PATH
	# yarnActions $REPO_PATH
	dockerActions $REPO $REPO_PATH
fi