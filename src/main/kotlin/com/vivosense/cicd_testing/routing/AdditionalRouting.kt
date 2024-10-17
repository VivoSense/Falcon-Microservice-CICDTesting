package com.vivosense.cicd_testing.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Configures additional routing by defining several GET and OPTIONS endpoints for health checks and CORS configuration.
 */
fun Application.configureAdditionalRouting() {
    routing {
        get("/health") { call.respond(HttpStatusCode.OK) }
        options("/health") { call.respond(HttpStatusCode.OK) }
        options { call.respond(HttpStatusCode.OK) }

        route("/cicd_testing") {
            options("/*") { call.respond(HttpStatusCode.OK) }
            get("/health") { call.respond(HttpStatusCode.OK) }
            route("/internal"){
                options("/*") { call.respond(HttpStatusCode.OK) }
            }
        }
    }
}
