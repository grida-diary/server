plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "grida"

include("grida-clients")
include("grida-clients:storage-client")
include("grida-clients:ai-client")

include("grida-core")
include("grida-core:core-api")
include("grida-core:core-domain")

include("grida-database")
include("grida-database:database-rds")

include("grida-common")
