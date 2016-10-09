node("java8-mvn-slave")
{
    git url: 'ssh://git@github.com:condevops/hello-world-jenkins.git'
    sh 'mvn -B dependency:go-offline'
    sh 'mvn -B help:active-profiles verify'
}
