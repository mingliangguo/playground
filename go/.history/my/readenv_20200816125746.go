package my

import (
	"fmt"
	"os"
	"strings"
)

func setEnv(key, value string) {

}

func ReadEnv(key string) {
    fmt.Println("FOO:", os.Getenv("FOO"))
    fmt.Println("BAR:", os.Getenv("BAR"))

    fmt.Println()
    for _, e := range os.Environ() {
        pair := strings.SplitN(e, "=", 2)
        fmt.Println(pair[0])
    }
}