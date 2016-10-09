node("java8-mvn-slave")
{
    stage("checkout")
    {
        checkout scm
    }

    stage("dependencies")
    {
        sh "mvn -B dependency:go-offline"
    }

    stage("active profiles")
    {
        sh "mvn -B help:active-profiles"
    }

    stage("build")
    {
        sh "mvn -B compile-test"
    }

    stage("tests")
    {
        sh "mvn -B verify"
    }
}
