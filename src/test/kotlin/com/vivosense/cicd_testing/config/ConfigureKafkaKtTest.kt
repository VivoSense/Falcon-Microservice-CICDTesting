package com.vivosense.cicd_testing.config

import com.vivosense.cicd_testing.utils.EnvVars
import com.vivosense.lib_falcon_data_pipeline.models.dynamo.StatusModel
import com.vivosense.lib_falcon_data_pipeline.service.StatusService
import com.vivosense.lib_falcon_message_bus.service.MessageBusConsumer
import com.vivosense.lib_falcon_message_bus.service.MessageBusProducer
import io.ktor.server.testing.*
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class ConfigureKafkaKtTest {
    @AfterEach
    fun teardown() {
        unmockkAll()
        stopKoin()
    }

    @Test
    fun `configureKafka() sets up producer and consumer as expected`() = testApplication {
        //arrange
        val expectedQueue = listOf(StatusModel(request = "carrot"))
        val messageConsumerMock = mockk<MessageBusConsumer>() {
            every { listen(any()) } just runs
        }
        val messageProducerMock = mockk<MessageBusProducer>() {
            every { createTopics(any()) } just runs
        }
        val statusService = mockk<StatusService>() {
            every { getQueued(any()) } returns expectedQueue
        }
        mockkObject(EnvVars.Events)
        every { EnvVars.Events.outputEventKey } returns "red"
        every { EnvVars.Events.inputEventKey } returns "blue"

        //act
        application {
            startKoin { modules(
                module {
                    single { messageConsumerMock }
                    single { messageProducerMock }
                    single { statusService }
                }
            ) }


            //assert
            val captured = slot<List<String>>()
            verify {
                messageConsumerMock.listen(capture(captured))
                messageProducerMock.createTopics(any())
            }

            Assertions.assertNotNull(captured.captured)
            Assertions.assertEquals(expectedQueue.map { x -> x.request }, captured.captured)
        }
    }
}