pipeline {
    agent any
    stages {
        stage('Run Tests') {
            steps {
                sh './mvnw clean test'
            }
            post {
                always {
                    junit '**/surefire-reports/*.xml'
                    cucumber buildStatus: 'null', customCssFiles: '', customJsFiles: '', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: 'target/cucumber.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
                }
            }
        }
        stage('Send Email') {
            steps {
                emailext subject: 'Cucumber Report',
                          body: 'Please find the cucumber report attached.',
                          to: 'suchnadrareddy12@gmail.com',
                          attachmentsPattern: '**/target/cucumber.json'
            }
        }
    }
}
