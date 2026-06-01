pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/faizdafedar3/SeleniumFramework.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
}
