node{
    stage('git clone'){
        //check CODE
        git credentialsId: 'c9b13f30-aeb4-478c-a494-9c0e8b918f98', url: 'git@github.com:fasimito/aws-assignments.git'
    }

    stage('run test'){
        dir('./'){
            sh '''
            mvn test
            '''
        }
    }

    stage('mvn build'){
        dir('./'){
            sh '''
            mvn clean package
		    '''
        }
    }

    stage('docker images build'){
		sh '''
		# define the container's  work dir
		WORK_DIR=/var/web/app/$JOB_NAME/
		# get the image ID of My Job.
		IMAGE_ID=$(docker images | grep "$JOB_NAME" | awk \'{print $3}\')
		# construct docker image
		if [ -n "$IMAGE_ID" ]; then
			echo "the $JOB_NAME image is already exist，the id is: $IMAGE_ID"
		else
			echo "the $JOB_NAME image does not exist， start construct the image"
			docker build --build-arg work_dir=$WORK_DIR -t $JOB_NAME .
		fi
		'''
	}

	stage('docker compose deploy'){
		sh '''
		#container ID
		CONTAINER_ID=$(docker ps | grep "$JOB_NAME" | awk \'{print $1}\')
		if [ -n "$CONTAINER_ID" ]; then
			echo "the container $JOB_NAME ia already exist，the containerId is :$CONTAINER_ID,restart the docker container ..."
			docker-compose restart
			echo "the container $JOB_NAME restart successfully"
		else
			echo "the container $JOB_NAME does not exist，use 'docker-compose run' create the container..."
			docker-compose up -d
			echo "the container $JOB_NAME create successfully"
		fi
		'''
	}
}