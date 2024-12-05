def execute() {
    echo 'Building the application...'
    sh 'mvn clean package'
    sh 'mvn package'
}
return this
