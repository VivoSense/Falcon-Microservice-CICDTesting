package com.vivosense.cicd_testing.utils

import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables
import uk.org.webcompere.systemstubs.jupiter.SystemStub
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension

@ExtendWith(SystemStubsExtension::class)
class EnvVarsKtTest {
    @SystemStub
    private val environmentVariables: EnvironmentVariables? = null

    @Test
    fun envVarsInitializesUsingValuesFromEnvVars() {
        //arrange
        val expectedPort = 1234
        val expectedIsStartedLocally = true
        val expectedAwsRegion = "Blue"
        val expectedCognitoClientId = "Purple"
        val expectedUserPoolId = "Yellow"
        val expectedEnvironment = "Maroon"
        val expectedInputEventKey = "Violet"
        val expectedOutputEventKey = "White"
        val expectedStudyId = "Ruby"
        val expectedPondName = "Aqua"

        //act
        environmentVariables?.set("APPLICATION_PORT", expectedPort.toString())
        environmentVariables?.set("IS_STARTED_LOCALLY", expectedIsStartedLocally.toString())
        environmentVariables?.set("AWS_REGION", expectedAwsRegion)
        environmentVariables?.set("AWS_COGNITO_CLIENT_ID", expectedCognitoClientId)
        environmentVariables?.set("AWS_USER_POOL_ID", expectedUserPoolId)
        environmentVariables?.set("ENVIRONMENT", expectedEnvironment)
        environmentVariables?.set("INPUT_EVENT_KEY", expectedInputEventKey)
        environmentVariables?.set("OUTPUT_EVENT_KEY", expectedOutputEventKey)
        environmentVariables?.set("STUDY_ID", expectedStudyId)
        environmentVariables?.set("AWS_DATA_POND_BUCKET_NAME", expectedPondName)



        //assert
        assertNotNull(EnvVars)
        assertNotNull(EnvVars.Application)
        assertNotNull(EnvVars.AWS)
        assertNotNull(EnvVars.Events)
        assertEquals(expectedPort, EnvVars.Application.port)
        assertEquals(expectedIsStartedLocally, EnvVars.Application.isStartedLocally)
        assertEquals(expectedAwsRegion, EnvVars.AWS.region)
        assertEquals(expectedCognitoClientId, EnvVars.AWS.cognitoClientId)
        assertEquals(expectedUserPoolId, EnvVars.AWS.userPoolId)
        assertEquals(expectedEnvironment, EnvVars.AWS.environment)
        assertEquals(expectedInputEventKey, EnvVars.Events.inputEventKey)
        assertEquals(expectedOutputEventKey, EnvVars.Events.outputEventKey)
        assertEquals(expectedPondName, EnvVars.AWS.dataPondBucketName)
        assertEquals(expectedStudyId, EnvVars.Application.studyId)
    }
}
