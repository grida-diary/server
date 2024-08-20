rootProject.name = "grida"

include("grida-clients")
include("grida-clients:s3-client")
include("grida-clients:openai-client")
include("grida-clients:kakao-client")

include("grida-core")
include("grida-core:core-api")
include("grida-core:core-domain")

include("grida-database")
include("grida-storage:rds-storage")

include("grida-common")
