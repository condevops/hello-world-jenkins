

pipeline 
{
	environment 
	{
		GITHUB_CONTEXT = 'pipeline/pull-requests/hello-world-jenkins'
    }
	
	agent 
	{
		label 'maven-8-debian'
	}
	
	stages 
	{
		stage("environment")
		{
			steps 
			{
				sh "env | sort"
			}
		}

		stage("build")
		{
			steps
			{
				sh "mvn -B verify"
			}
		}
	}
	post 
	{
		always 
		{
			echo "asdf: $currentBuild"
			
			junit(testResults: 'target/surefire-reports/TEST-*.xml')
			jacoco()
		}
		failure
		{
			setGitHubPullRequestStatus context: 'pipeline/pull-requests/hello-world-jenkins', message: 'Build #${currentBuild.getNumber} complete', state: 'FAILURE'
			slackSend channel: '#jenkins-beta-builds', message: 'Deploy Job $currentBuild.getNumber failed!', tokenCredentialId: 'slack-token'			
		}
		success
		{
			setGitHubPullRequestStatus context: 'pipeline/pull-requests/hello-world-jenkins', message: 'Build #${currentBuild.getNumber()} complete', state: 'SUCCESS'
			slackSend channel: '#jenkins-beta-builds', message: 'Deploy Job $currentBuild.getNumber completed!', tokenCredentialId: 'slack-token'
		}
	}
}

//setGitHubPullRequestStatus context: '', message: 'Build #${BUILD_NUMBER} complete', state: 'SUCCESS'


//node("maven-8-debian")
//{
//    String mvnGoal = "verify"
//
//    if (env.IS_M2RELEASEBUILD)
//    {
//        mvnGoal = "-Dresume=false release:prepare release:perform"
//    }
//
//    stage("checkout")
//    {
//        checkout scm
//    }
//
//    stage("environment")
//    {
//        sh "env | sort"
//    }
//
//    stage("build")
//    {
//        sh "mvn -B " + mvnGoal
//    }
//
//    stage("publish")
//    {
//        step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
//        step([$class: 'JacocoPublisher', execPattern: 'target/jacoco.exec'])
//    }
//}
