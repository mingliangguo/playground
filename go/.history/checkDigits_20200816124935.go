package main

import "fmt"

func main() {
  var num = 2222345678910
  var digits [10]int

  for ;num > 0; {
    var remainder = num % 10
    digits[remainder]++
    num = num / 10
  }
  fmt.Println(digits)
}
