node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh "./gradlew sonar"
    }
  }
}
pipeline {
    agent any

    tools {
        gradle 'Gradle_7.4.2'
    }

    environment {
        SONARQUBE_SCANNER_HOME = tool 'SonarQube Scanner'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Andreyome/stage3-module5-task.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'gradle clean build'
            }
        }

        stage('Code Analysis') {
            steps {
                withSonarQubeEnv('SonarQube Server') {
                    sh 'gradle sonarqube'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }

        stage('Test') {
            steps {
                sh 'gradle test jacocoTestReport'
            }
        }
    }

    post {
        always {
            jacoco execPattern: '**/build/jacoco/*.exec', classPattern: '**/build/classes', sourcePattern: '**/src/main/java', inclusionPattern: '**/*.class', exclusionPattern: '**/build/**/*.class'
        }
        failure {
            mail to: 'your-email@example.com',
                 subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Something went wrong with ${env.JOB_NAME}."
        }
    }
}