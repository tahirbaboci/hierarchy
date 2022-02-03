#!/bin/bash

curl  -v --header "Content-Type: application/json" \
      --request GET \
      -u theUsername:thePassword \
      http://localhost:8080/employee/structuredHierarchy \
      | jq
#If you don't have jq installed at your pc, please comment the line above