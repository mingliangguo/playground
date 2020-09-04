package my

import (
	"os"
	"strconv"
)

func setEnv(key, value string) {
    os.Setenv(key, value)
}

func ReadEnv(key string) string {
    return os.Getenv(key)
}

func GetAllEnvs() map[string]string {
  vals := map[string]string{}

  vals["pid"] = strconv.Itoa(os.Getpid())
  return vals
}
