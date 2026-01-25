pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Image') {
            steps {
                sh 'docker build -t atlas:${GIT_COMMIT} .'
            }
        }

        stage('Push Image') {
            steps {
                sh '''
                    docker tag atlas:${GIT_COMMIT} your-docker-user/atlas:latest
                    docker push your-docker-user/atlas:latest
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker compose up -d'
            }
        }
    }
}
