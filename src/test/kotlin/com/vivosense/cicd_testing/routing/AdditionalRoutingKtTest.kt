package com.vivosense.cicd_testing.routing

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.koin.core.context.stopKoin

class AdditionalRoutingKtTest {
    private fun ApplicationTestBuilder.configureTestApplication() {
        application {
            configureAdditionalRouting()
        }
    }

    @AfterEach
    fun teardown() {
        unmockkAll()
        stopKoin()
    }

    @Test
    fun additionalRoutingGetHealthReturnOKStatus() = testApplication {
        //arrange
        val expectedResponseCode = HttpStatusCode.OK
        configureTestApplication()

        //act
        val response = client.get("/health")

        //assert
        Assertions.assertEquals(expectedResponseCode, response.status)
    }

    @Test
    fun additionalRoutingOptionsHealthReturnOKStatus() = testApplication {
        //arrange
        val expectedResponseCode = HttpStatusCode.OK
        configureTestApplication()

        //act
        val response = client.options("/health")

        //assert
        Assertions.assertEquals(expectedResponseCode, response.status)
    }

    @Test
    fun additionalRoutingOptionsReturnOKStatus() = testApplication {
        //arrange
        val expectedResponseCode = HttpStatusCode.OK
        configureTestApplication()

        //act
        val response = client.options("")

        //assert
        Assertions.assertEquals(expectedResponseCode, response.status)
    }


}
