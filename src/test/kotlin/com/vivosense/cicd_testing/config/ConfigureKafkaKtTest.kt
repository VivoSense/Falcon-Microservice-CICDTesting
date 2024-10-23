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

}