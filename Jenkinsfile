node {
    stage ('clone') {
        checkout scm
        git branch: 'dev'
    }
    stage('build') {
        sh './gradlew clean build'
    }
}