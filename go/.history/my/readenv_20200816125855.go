package my

import (
	"os"
)

func setEnv(key, value string) {
    os.Setenv(key, value)
}

func ReadEnv(key string) {
    return os.Getenv(key)
}