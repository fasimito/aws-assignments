node{
    stage('git clone'){
        //check CODE
        git credentialsId: '0753f728-46f3-4d67-b913-bfe696653c3d', url: 'https://github.com/fasimito/aws-assignments.git'
    }

    stage('run test'){
        dir('./'){
            sh '''
            mvn test
            '''
        }
    }

    stage('run build'){
        dir('./'){
            sh '''
            mvn clean package -Dmaven.test.skip=true
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
			CONTAINER_ID=$(docker ps | grep "$JOB_NAME" | awk \'{print $1}\')
			if [ -n "$CONTAINER_ID" ]; then
			    docker rm -f $CONTAINER_ID
			fi
			docker rmi -f $IMAGE_ID
		else
			echo "the $JOB_NAME image does not exist， start construct the image"
		fi
		docker build --build-arg work_dir=$WORK_DIR -t $JOB_NAME .
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
			echo "the container $JOB_NAME does not exist，use 'docker-compose up' create the container..."
			docker-compose up -d
			echo "the container $JOB_NAME create successfully"
		fi
		'''
	}
}