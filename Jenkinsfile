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
          emailext attachLog: true, to: 'reddysuchi197@gmail.com, reddysuchi197@gmail.com', subject: "Test Report - ${currentBuild.fullDisplayName}", mimeType: 'text/html', body: 'Please find the attached test reports.', replyTo: 'noreply@yourdomain.com'
        }
      }
    }
  }
}
