node {
    download = true
    stage ('clone') {
        checkout scm
    }
    stage('build') {
        sh './gradlew clean build'
    }
}