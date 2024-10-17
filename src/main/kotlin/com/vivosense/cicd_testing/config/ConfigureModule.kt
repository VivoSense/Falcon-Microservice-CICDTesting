package com.vivosense.cicd_testing.config

import com.vivosense.cicd_testing.routing.configureAdditionalRouting
import com.vivosense.cicd_testing.routing.configureInternalRouting
import com.vivosense.falconlib.plugins.*
import io.ktor.server.application.*

/**
 * Configures the application module by setting up plugins, routing, and Dependency Injection (DI).
 *
 * @receiver The [Application] instance that will be configured.
 */
fun Application.configureModule(){
    //DI
    configureDI()

    //plugins
    configureDefaultHeaders()
    configureDoubleReceive()
    configureResponseExceptionHandler()
    configureSecurity()
    configureSerialization()

    //routing
    configureAdditionalRouting()
    configureInternalRouting()
}
