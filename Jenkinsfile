def COLOR_MAP = [
    'SUCCESS': 'good',
    'FAILURE': 'danger'
]
pipeline {
    agent any
    tools {
        maven 'maven3.9'
    }
    stages {
        stage('Init') {
            steps {
                script {
                    buildimage = load 'scripts/buildimage.groovy'
                    buildjar = load 'scripts/buildjar.groovy'
                }
            }
        }
        stage('Build Jar') {
            steps {
                script {
                    buildjar.execute()
                }
            }
        }
        stage('Build image') {
            steps {
                script {
                    buildimage.execute()
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'deploying application...'
                script {
                    def dockerComposeCmd = 'docker-compose -f docker-compose.yaml up -d'
                    sshagent(['ec2-server-key']) {
                        sh 'scp docker-compose.yaml ec2-user@3.91.234.236:/home/ec2-user'
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.91.234.236 ${dockerComposeCmd}"
                    }
                }
            }
        }
    }
    post {
        always {
            slackSend(
                channel: '#automation-builds',
                color: COLOR_MAP[currentBuild.currentResult],
                message: "Build ${env.BUILD_NUMBER} was ${currentBuild.currentResult}. More details at ${env.BUILD_URL}"
            )
        }
    }
}
