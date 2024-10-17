package com.vivosense.cicd_testing.routing

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.vivosense.falconlib.utils.AuthenticationType
import com.vivosense.falconlib.utils.extensions.authenticatedUser
import com.vivosense.lib_falcon_data_pipeline.models.request.AlgorithmExecutionRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun Application.configureInternalRouting() {
    val objectMapper: ObjectMapper = ObjectMapper().apply {
        propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
    }

    /**
     * The logger used to log messages related to the operations performed by the internal routes.
     */
    val logger: Logger = LoggerFactory.getLogger("InternalRouting")

    routing {
        authenticate(AuthenticationType.COGNITO) {
            route("/cicd_testing/testing") {

                post("/run"){


                }
            }
        }
    }
}