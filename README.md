# CICDTesting

A microservice dedicated to converting the raw data exports from Actigraph into Parquet files for downstream visualization and processing.

## Recommended Development Tools

1. IntelliJ IDEA (https://www.jetbrains.com/idea/)
Please contact the party assigned in this document for licenses if you have not already had them provisioned: https://vivosense.atlassian.net/l/cp/mnMKd0Tw

## Dev Environment Setup

|   Environment Variable    |                     Default Value                      |
|:-------------------------:|:------------------------------------------------------:|
|    ACCESS_TOKEN_KEY_ID    |        **check for tokens key id guide below**         |
|     APPLICATION_PORT      |                          8088                          |
|     AWS_ACCESS_KEY_ID     |                 **retrieve from aws**                  |
|   AWS_COGNITO_CLIENT_ID   |            **retrieve from aws management**            |
| AWS_DATA_LAKE_BUCKET_NAME |                falcon-dev-dp-data-lake                 |
|        AWS_REGION         |                       us-west-2                        |
|   AWS_SECRET_ACCESS_KEY   |                 **retrieve from aws**                  |
|     AWS_SESSION_TOKEN     |                 **retrieve from aws**                  |
|     AWS_USER_POOL_ID      |            **retrieve from aws management**            |
|        ENVIRONMENT        |                          dev                           |
|      ID_TOKEN_KEY_ID      |        **check for tokens key id guide below**         |
|    IS_STARTED_LOCALLY     |                          true                          |
|     KAFKA_BROKER_URL      |            **retrieve from aws management**            |
|     KAFKA_BROKER_PORT     |                          9098                          |
|      KAFKA_GROUP_ID       |                "ActigraphMicroservice"                 |
|    STUDY_SERVICE_LINK     |                 http://localhost:8081/                 |
|      ATHENA_DATABASE      |            **retrieve from aws management**            |
|    QUERY_SAVE_LOCATION    |            **retrieve from aws management**            |
|         STUDY_ID          |                  **unique per study**                  |
| AWS_DATA_POND_BUCKET_NAME |                  **unique per study**                  |
|   STATUS_DATABASE_NAME    |    The name of this data pipeline's status database    |
|     MICROSERVICE_NAME     | The name identifier of the microservice using this lib |

Configuration steps using IntelliJ:
1. Open the project
1. Navigate to Application.kt to find our main function for this application
1. On the second menu bar from the top, right-hand side is a dropdown to select configuration. If you have not yet configured this project, it will say Current File.
1. Select Edit Configurations
1. Click + on the upper left of the popup window
1. From the dropdown, select ktor
1. In Ktor Configuration tab select `...` next to entry for Ktor main class
1. From the popup, select ApplicationKt
1. In Ktor Configuration tab select the page icon in Environment variables
1. Enter the above table into the popup (see below for where to find missing data in aws)


To access AWS VPN for the first time:
1. Request access if you have not already been granted by party assigned here: https://vivosense.atlassian.net/l/cp/mnMKd0Tw
1. Follow the steps in this confluence document to setup AWS VPN: https://vivosense.atlassian.net/l/cp/z3i0kBkq
1. (Optional) Consider pinning the VPN Client, you're going to be using it frequently!
1. Navigate to https://d-92676e5e6f.awsapps.com/start#/
1. Under Dev, select Management console
1. In the top search bar, type Client VPN endpoints
1. Under Features, select Client VPN endpoints
1. Select the falcon-vpn-endpoint configuration
1. Click in the upper right Download client configuration
1. Open the AWS VPN Client
1. From the File menu chose Add Profile
1. Choose Add Profile
1. Fill in Display Name of your choice and select the configuration file you downloaded earlier
1. Save
1. Select the new Profile from the main AWS VPN Client window and click connect


To get missing environment variables from AWS VPN:
1. Connect to falcon-vpn-endpoint using AWS VPN Client (see above if not yet set up with VPN access)
1. Navigate to https://d-92676e5e6f.awsapps.com/start#/
1. Under Dev, select Command line or programmatic access
1. Find the three items for environment variables under Option 3
1. Be aware that these items change periodically, see https://docs.aws.amazon.com/singlesignon/latest/userguide/howtogetcredentials.html for more information


To configure project with internal falcon library:
 * If you don't want to expand library locally:
    1. Use "Authenticating with a personal access token" part of next guide (https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-with-a-personal-access-token)
    2. For previous guide use "Vivosense" as OWNER and "Falcon-Backend"
    3. For previous guide use your github username as USERNAME and generated access token as TOKEN. How to generate access token, you can check here
       (https://docs.github.com/en/enterprise-server@3.4/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)
    4. Reload all maven dependencies for current project.
 * If want to expand library locally, check README.md here (https://github.com/VivoSense/Falcon-Backend)

### Tokens Key id guide (there are 2 ways described, not 2 steps. Use one of the provided guides to get keys that you need)
1. Make request to  [https://cognito-idp.{AWS_REGION}.amazonaws.com/{AWS_USER_POOL_ID}/.well-known/jwks.json]. Replace {AWS_REGION} and {AWS_USER_POOL_ID} with corresponding values.
2. Decode any access token and any id token, that you received from Falcon's cognito, using https://jwt.io/ or similar tool. You can find key id value in the header, field name is "kid".

## Runtime
Run using the run configuration above for a dev server. The application will run on `http://localhost:8088/`.
