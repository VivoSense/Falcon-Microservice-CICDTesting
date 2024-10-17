package com.vivosense.cicd_testing

import com.vivosense.cicd_testing.config.configureModule
import com.vivosense.cicd_testing.utils.EnvVars
import io.ktor.server.engine.*
import io.ktor.server.jetty.*

/**
 * The main function that starts an embedded server with [Jetty] as the
 * server factory using the application engine environment.
 */
fun main() {
    embeddedServer(
        factory = Jetty,
        environment = applicationEngineEnvironment {
            connector { port = EnvVars.Application.port }
            module { configureModule() }
        }
    ).start(wait = true)
}
