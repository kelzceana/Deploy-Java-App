def execute() {
    echo 'Building docker image'
    withCredentials([usernamePassword(credentialsId: 'dockerhub',
                                      passwordVariable: 'PASS',
                                      usernameVariable: 'USER')]) {
        sh '''
                docker build -t kelzceana/java-maven-app:1.0 .
                echo $PASS | docker login -u $USER --password-stdin
                docker push kelzceana/java-maven-app:1.0
                '''
                                                  }
}
return this
