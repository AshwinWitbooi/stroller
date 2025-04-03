pipeline {
    agent any

    tools {
        // Install the Maven version configured as "maven_3_8_6" and add it to the path.
        maven "maven_3_8_6"
    }
    
    environment {
        GITHUB_CREDENTIALS = 'GitHub credential'  // Jenkins credentials ID
        REPO_URL = 'https://github.com/AshwinWitbooi/stroller.git'  // GitHub repository URL
        DOCKER_CONTAINER = 'stroller' //
    }
    
    stages {
        stage('Code_Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/test_branch']], // Specify the branch
                    userRemoteConfigs: [[
                        url: "${REPO_URL}",
                        credentialsId: "${GITHUB_CREDENTIALS}"  // Use the credentials defined
                    ]]
                ])
            }
        }
        stage('Build') {
            steps {
              // Execute clean and build
                bat "mvn -Dmaven.test.skip=true clean compile"
            }
        }
        stage('Test') {
            steps {
                // Execute application test
                bat "mvn test"
            }
        }
    	stage('Package') {
            steps {
                // Package application as jar
                bat "mvn package"
            }
        }
        stage('Stop and Remove Container') {
            steps {
                bat """
					set CONTAINER_NAME=${DOCKER_CONTAINER}
					
					REM Check if the container is running
					docker ps --filter "name=%CONTAINER_NAME%" --format "{{.Names}}" | findstr /I "%CONTAINER_NAME%" >nul
					
					REM Check the error level
					if %errorlevel%==0 (
					    echo Container "%CONTAINER_NAME%" is running.
					    docker stop "%CONTAINER_NAME%
					    echo Container "%CONTAINER_NAME%" stopped.
					    docker rm "%CONTAINER_NAME%
					    echo Container "%CONTAINER_NAME%" removed.
					)
					REM exit successful      
					exit 0           
                """   
            }
        }
        stage('Remove or Create Image') {
            steps {
            	bat """
	            	SET IMAGE_NAME=${DOCKER_CONTAINER}:latest
	
					docker images --format "{{.Repository}}:{{.Tag}}" | findstr /I "%IMAGE_NAME%" > nul
					if %ERRORLEVEL% EQU 0 (
					    echo Docker image %IMAGE_NAME% exists.
					    docker rmi %IMAGE_NAME%
					    echo Docker image %IMAGE_NAME%" removed and create new image
					    docker build -t %IMAGE_NAME% .
					    exit 0
					)else (
					    echo Create new image
					    docker build -t %IMAGE_NAME% .				
					)
            	"""
            }
        }
        stage('Run Container Detached Mode') {
            steps {
                // Run Spring boot application Docker container in detached mode
                bat "docker run -d -p 10100:8080 e SPRING_PROFILES_ACTIVE=dev --name=${DOCKER_CONTAINER} ${DOCKER_CONTAINER}"
            }
        }
    }
}
