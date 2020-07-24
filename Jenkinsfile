node {
    stage ('clone') {
        checkout scm
    }
    stage ('npm build'){
        sh 'npm install ./todo-web/src/front'
        sh 'npm run build ./todo-web/src/front'
    }
    stage('build') {
        sh './gradlew clean build'
    }
}