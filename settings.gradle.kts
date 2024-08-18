rootProject.name = "grida"

include("grida-clients")
include("grida-clients:storage-client")
include("grida-clients:openai-client")
include("grida-clients:kakao-client")

include("grida-core")
include("grida-core:core-api")
include("grida-core:core-domain")

include("grida-database")
include("grida-database:database-rds")

include("grida-common")
