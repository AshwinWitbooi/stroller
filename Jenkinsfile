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
        stage('Stop Container') {
            steps {
                // Stop container and always exit with 0 continue pipeline
                bat "docker stop ${DOCKER_CONTAINER}"       
                bat "exit 0"      
            }
        }
        stage('Remove Container') {
            steps {
                // Remove container and always exit with 0 continue pipeline
                bat "docker rm ${DOCKER_CONTAINER} | exit 0"                
            }
        }
        stage('Remove Image') {
            steps {
                // Remove Docker image and always exit with 0 continue pipeline
                bat "docker rmi ${DOCKER_CONTAINER} | exit 0"
            }
        }
        stage('Create Image') {
            steps {
                // Build Docker image
                bat "docker build -t ${DOCKER_CONTAINER} ."
            }
        }
        stage('Run Container Detached Mode') {
            steps {
                // Run Spring boot application Docker container in detached mode
                bat "docker run -d -p 10100:8080 e SPRING_PROFILES_ACTIVE=dev ${DOCKER_CONTAINER}"
            }
        }

    }
}
