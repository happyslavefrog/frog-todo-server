node {
    stage ('clone') {
        checkout scm
        git checkout dev
    }
    stage('build') {
        sh './gradlew clean build'
    }
}