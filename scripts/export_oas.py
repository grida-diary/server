import json
import os
import requests
import sys
import time

if len(sys.argv) == 1:
    print("no system arguments")
    exit()

GH_TOKEN = sys.argv[1]

GH_API_BASED_HEADERS = {
    "Authorization": "Bearer {}".format(GH_TOKEN),
    "X-GitHub-Api-Version": "2022-11-28",
    "Accept": "application/vnd.github+json"
}

os.system("gradle :grida-core:core-api:clean")
os.system("gradle :grida-core:core-api:openapi3")

with open("./grida-core/core-api/build/api-spec/openapi3.json", "r") as oas:
    json_data = json.load(oas)
    print(json_data)

    create_issue_endpoint = "https://api.github.com/repos/grida-diary/api-docs/issues"

    body = {
        "title": "update api docs " + time.strftime('%Y-%m-%d %H:%M:%S'),
        "body": json.dumps(json_data, ensure_ascii=False, indent="\t")
    }

    response = requests.post(
        create_issue_endpoint,
        headers=GH_API_BASED_HEADERS,
        data=json.dumps(body)
    ).json()

    print(response)
