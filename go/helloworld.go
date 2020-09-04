package main

import (
	"fmt"
	"github.com/mingliangguo/go-playground/my"
)

func main() {
  fmt.Println("Hello world!")
  
  fmt.Println("HOME is:", my.ReadEnv("HOME"));

  fmt.Println("vals is:", my.GetAllEnvs());
}
