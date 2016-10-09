node("java8-mvn-slave")
{
    stage("initialize")
    {
        sh "env | sort"

        checkout scm
        sh "mvn -B dependency:go-offline help:active-profiles"
    }

    stage("build")
    {
        sh "mvn -B test-compile"
    }

    stage("tests")
    {
        sh "mvn -B verify"
    }

    stage("publish")
    {
        step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
        step([$class: 'JacocoPublisher', execPattern: 'target/jacoco.exec'])
    }
}
