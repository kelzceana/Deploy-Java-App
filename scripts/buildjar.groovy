def execute() {
    echo 'Building the application...'
    sh 'mvn package'
}
return this
