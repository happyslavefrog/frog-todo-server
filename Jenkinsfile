node {
    stage ('clone') {
        checkout scm
    }
    stage ('npm build'){
        nodejs(nodeJSInstallationName: 'node') {
            sh 'npm install ./todo-web/src/front'
            sh 'npm --prefix ./todo-web/src/front run build'
        }
    }
    stage('build') {
        sh './gradlew clean build'
    }
}