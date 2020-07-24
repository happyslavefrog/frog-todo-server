node {
    stage ('clone') {
        checkout scm
    }
    stage ('npm build'){
        'npm install ./todo-web/src/front'
        'npm run build ./todo-web/src/front'
    }
    stage('build') {
        sh './gradlew clean build'
    }
}