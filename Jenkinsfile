pipeline {
    agent any

    tools {
        gradle 'Gradle_7.4.2'
    }

    environment {
        SONARQUBE_SCANNER_HOME = tool name: 'SonarQube Scanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Andreyome/stage3-module5-task.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                bat 'gradle clean build'
            }
        }

        stage('SonarQube Analysis') {
            steps {
            bat 'gradle sonarqube'
            }
        }

        stage('Test') {
            steps {
                bat 'gradle test jacocoTestReport'
            }
        }
    }
}