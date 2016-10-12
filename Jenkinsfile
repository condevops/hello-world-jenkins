node("java8-mvn-slave")
{
    String mvnGoal = "verify";
    String branch = env.BRANCH_NAME;

    if if (branch == null || "master".equals(branch))
    {
        mvnGoal = "deploy";
    }

    stage("initialize")
    {
        sh "env | sort"

        checkout scm
        sh "mvn -B dependency:go-offline help:active-profiles"
    }

    stage("build")
    {
        sh "mvn " + mvnGoal;
    }

    stage("publish")
    {
        step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
        step([$class: 'JacocoPublisher', execPattern: 'target/jacoco.exec'])
    }
}
