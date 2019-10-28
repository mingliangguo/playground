package my

import "testing"

func TestEnv(t *testing.T) {
	want := "1"
	my.Setenv("foo", "1")
	if got := my.Getenv("foo"); got != want {
		t.Errorf("Getenv returns %q, want is: %q", got, want)
	}
	
}