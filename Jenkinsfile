node {
    stage ('clone') {
        checkout scm
    }
    stage ('npm build'){
        nodejs(nodeJSInstallationName: 'node') {
            sh 'npm install ./todo-web/src/front'
            sh 'npm run build ./todo-web/src/front'
        }
    }
    stage('build') {
        sh './gradlew clean build'
    }
}