node {
    stage ('clone') {
        checkout scm
    }
    stage ('npm build'){
        env.NODEJS_HOME = "${tool 'Node 6.x'}"
        env.PATH="${env.NODEJS_HOME}/bin:${env.PATH}"
        sh 'npm install ./todo-web/src/front'
        sh 'npm run build ./todo-web/src/front'
    }
    stage('build') {
        sh './gradlew clean build'
    }
}