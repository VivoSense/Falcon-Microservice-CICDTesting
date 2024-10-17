package com.vivosense.cicd_testing.config

import com.vivosense.falconlib.utils.dynamo
import com.vivosense.lib_falcon_data_pipeline.repository.StatusRepository
import com.vivosense.lib_falcon_data_pipeline.service.StatusService
import com.vivosense.lib_falcon_message_bus.service.MessageBusProducer
import com.vivosense.lib_falcon_message_bus.utils.KafkaProperties
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.slf4j.Logger
import org.slf4j.LoggerFactory


/**
 * Configures Dependency Injection (DI) for the application using Koin.
 * Sets up the DynamoDB table, waits for it to become active, and initializes services and repositories.
 */
fun configureDI(): KoinApplication {
    val logger: Logger = LoggerFactory.getLogger("DependencyInjection")
    val dynamo = dynamo()

    return startKoin {
        modules(
            module {
                single { KafkaProperties() }
                single { MessageBusProducer(get()) }
                single { StatusRepository(dynamo) }
                single { StatusService(get()) }
            }
        )
    }
}
