pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Image') {
            steps {
                sh '''
                  docker build -t atlas-app:latest .
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                  docker compose down
                  docker compose up -d
                '''
            }
        }
    }
}
