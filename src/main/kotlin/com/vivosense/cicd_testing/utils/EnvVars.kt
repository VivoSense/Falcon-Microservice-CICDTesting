package com.vivosense.cicd_testing.utils


/**
 * An object representing environment variables required by actigraph-converter-service.
 */
object EnvVars {

    /**
     * An object representing application-specific environment variables.
     */
    object Application {

        /**
         * The port on which the application runs.
         */
        val port: Int
            get() = System.getenv()["APPLICATION_PORT"]?.toInt() ?: 8080

        /**
         * If the system is running locally
         */
        val isStartedLocally: Boolean
            get() = System.getenv()["IS_STARTED_LOCALLY"]?.toBoolean() ?: false

        /**
         * The place in the S3 bucket to save query outputs for the
         * algorithm
         */
        val querySaveLocation: String
            get() = System.getenv()["QUERY_SAVE_LOCATION"]!!

        /**
         * ID of the study running this microservice
         */
        val studyId: String
            get() = System.getenv()["STUDY_ID"]!!

    }

    /**
     * An object representing AWS-specific environment variables.
     */
    object AWS {

        /**
         * The AWS region in which the resources are located.
         */
        val region: String
            get() = System.getenv()["AWS_REGION"]!!

        /**
         * The name of the AWS S3 data pond bucket used by the application.
         */
        val dataPondBucketName: String
            get() = System.getenv()["AWS_DATA_POND_BUCKET_NAME"]!!

        /**
         * The AWS identifier for cognito client.
         */
        val cognitoClientId: String
            get() = System.getenv()["AWS_COGNITO_CLIENT_ID"]!!

        /**
         * The identifier of user pool in specified cognito client.
         */
        val userPoolId: String
            get() = System.getenv("AWS_USER_POOL_ID")!!

        val environment: String
            get() = System.getenv("ENVIRONMENT")!!

        /**
         * The name of the athena database which this microservice will upload data to.
         */
        val athenaDatabase: String
            get() = System.getenv("ATHENA_DATABASE_NAME")!!
    }

    object Events {
        /**
         * The kafka event this microservice should subscribe to be queued off
         */
        val inputEventKey: String
            get() = System.getenv("INPUT_EVENT_KEY")!!

        /**
         * The kafka event to fire when the conversion is complete
         */
        val outputEventKey: String
            get() = System.getenv("OUTPUT_EVENT_KEY")!!
    }
}
