pipeline {
    agent any

    environment {
        IMAGE_NAME = "1ms24mc102/maven-app"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/VamshiKumar-25/maven-app'
            }
        }

        stage('Build Maven Project') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${IMAGE_NAME}:latest")
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerHub') {
                        dockerImage.push("latest")
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
