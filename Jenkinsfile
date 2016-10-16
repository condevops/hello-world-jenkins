node("java8-mvn-slave")
{
    String mvnGoal = "verify"

    if (env.IS_M2RELEASEBUILD)
    {
        mvnGoal = "-Dresume=false release:prepare release:perform"
    }

    stage("checkout")
    {
        checkout scm
    }

    stage("environment")
    {
        sh "env | sort"
    }

    stage("build")
    {
        sh "mvn -B " + mvnGoal
    }

    stage("publish")
    {
        step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
        step([$class: 'JacocoPublisher', execPattern: 'target/jacoco.exec'])
    }
}
