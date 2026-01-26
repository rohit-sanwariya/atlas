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
            docker compose -f docker-compose.prod.yml down
            docker compose -f docker-compose.prod.yml up -d
            '''
            }
}
    }
}
