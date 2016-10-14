node("java8-mvn-slave")
{
    String mvnGoal = "verify";
//    String branch = env.BRANCH_NAME;
//
//    if (branch == null || "master".equals(branch))
//    {
//        mvnGoal = "deploy";
//    }

    stage("initialize")
    {
        sh "env | sort"

        checkout scm
        sh "mvn -B dependency:go-offline help:active-profiles"
    }

    stage("build")
    {
        sh "mvn -B " + mvnGoal;
    }

    stage("publish")
    {
        step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
        step([$class: 'JacocoPublisher', execPattern: 'target/jacoco.exec'])
    }
}
