
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
                withSonarQubeEnv('Sonar Qube') {
                    bat "${SONARQUBE_SCANNER_HOME}/bin/sonar-scanner"
                }
            }
        }

        stage('Test') {
            steps {
                bat 'gradle test jacocoTestReport'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline succeeded.'
        }
        failure {
            mail to: 'your-email@example.com',
                 subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Something went wrong with ${env.JOB_NAME}."
        }
    }
    node {
      stage('SCM') {
        checkout scm
      }
      stage('SonarQube Analysis') {
        withSonarQubeEnv() {
          bat "./gradlew sonar"
        }
      }
    }
}
node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      bat "./gradlew sonar"
    }
  }
}
