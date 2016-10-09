node("java8-mvn-slave")
{
    stage "Build and Verify"
    checkout scm
    sh 'mvn -B help:active-profiles verify'
}
