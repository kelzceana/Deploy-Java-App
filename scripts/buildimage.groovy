def execute() {
    echo 'Building docker image'
    withCredentials([usernamePassword(credentialsId: 'dockerhub',
                                      passwordVariable: 'PASS',
                                      usernameVariable: 'USER')]) {
        sh '''
                docker build -t kelzceana/java-app:1.1 .
                echo $PASS | docker login -u $USER --password-stdin
                docker push kelzceana/java-app:1.1
                '''
                                                  }
}
return this
