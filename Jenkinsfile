pipeline {
    agent any

    environment {
        IMAGE_NAME = "1ms24mc102/maven-app"
    }

    stages {

        stage('Build Maven Project') {
            steps {
                dir('maven-app') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('maven-app') {
                    script {
                        def dockerImage = docker.build("${IMAGE_NAME}:latest")
                        env.DOCKER_IMAGE = dockerImage.id
                    }
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerHub') {
                        docker.image("${IMAGE_NAME}:latest").push("latest")
                    }
                }
            }
        }
    }

    post {
        success {
            echo "Spring Boot Docker image built & pushed successfully!"
        }
        failure {
            echo "Pipeline failed!"
        }
        always {
            cleanWs()
        }
    }
}
