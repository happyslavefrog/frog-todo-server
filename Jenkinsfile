node {
    stage ('clone') {
        checkout scm
    }
    stage ('npm build'){
        nodejs(nodeJSInstallationName: 'node') {
            sh 'pwd'
            sh 'ls -al'
            sh 'cd ./todo-web/src/front'
            sh 'pwd'
            sh 'npm install'
            sh 'npm run build'
        }
    }
    stage('build') {
        sh './gradlew clean build'
    }
}