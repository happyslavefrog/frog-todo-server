node {
    stage ('clone') {
        checkout scm
    }
    stage ('sumodule update'){
        sh 'git submodule update --init --recursive'
    }
    stage('build') {
        sh './gradlew clean build'
    }
}