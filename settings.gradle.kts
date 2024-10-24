rootProject.name = "grida"

include("grida-clients")
include("grida-clients:feign-config")
include("grida-clients:s3-client")
include("grida-clients:openai-client")
include("grida-clients:kakao-client")

include("grida-core")
include("grida-core:core-api")
include("grida-core:core-domain")

include("grida-storage")
include("grida-storage:rds-storage")

include("grida-support")
include("grida-support:monitoring")
include("grida-support:logging")

include("grida-common")
