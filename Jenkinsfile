pipeline {
    agent any

    tools {
        // Install the Maven version configured as "maven_3_8_6" and add it to the path.
        maven "maven_3_8_6"
    }
    
    environment {
        GITHUB_CREDENTIALS = 'GitHub credential'  // Jenkins credentials ID
        REPO_URL = 'https://github.com/AshwinWitbooi/stroller.git'  // GitHub repository URL
        APP_NAME = 'stroller' //
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
					set CONTAINER_NAME=${APP_NAME}
					
					REM Check if the container is running
					docker ps --filter "name=%CONTAINER_NAME%" --format "{{.Names}}" | findstr /I "%CONTAINER_NAME%" >nul
					
					REM Check the error level
					if %errorlevel%==0 (
					    echo Container "%CONTAINER_NAME%" is NOT running.
					) else (
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
        stage('Run Container Detached Mode') {
            steps {
                // Run Spring boot application Docker container in detached mode
                bat "docker run -d -p 10100:8080 --name ${APP_NAME} -e SPRING_PROFILES_ACTIVE=dev ${APP_NAME}"
            }
        }
    }
}
