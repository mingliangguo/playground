package main

import (
	"encoding/json"
	"fmt"
)

func main() {
  jsonStr := `
{
  "name": "data-feed-22d2g",
  "config": {
    "connector.class": "com.ibm.eventstreams.connect.cossink.COSSinkConnector",
    "key.converter": "org.apache.kafka.connect.storage.StringConverter"
  },
  "tasks": [
    {
      "connector": "data-feed-22d2g",
      "task": 0
    }
  ],
  "type": "sink"
}
`
	str := []byte(jsonStr)
	var connector map[string]interface{}

  err := json.Unmarshal(str, &connector)
  if err != nil {
    fmt.Println("err is:", err)
  }

	fmt.Println(connector)
  fmt.Println("config is:", connector["config"], "task is:", connector["tasks"])

	fmt.Println("Hello, playground 2")
}

