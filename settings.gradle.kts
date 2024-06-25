plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "grida"

include("grida-apis")
include("grida-apis:apis-core")
include("grida-apis:trial-user-app")
include("grida-apis:end-user-app")
include("grida-apis:sso-app")

include("grida-clients")
include("grida-clients:storage-client")
include("grida-clients:ai-client")

include("grida-domains")
include("grida-domains:domain")
include("grida-domains:domain-rds")
include("grida-domains:domain-image")
include("grida-domains:domain-ai")

include("grida-core")
include("grida-core:core-api")

include("grida-common")
include("grida-core")
