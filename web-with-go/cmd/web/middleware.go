package main

import (
	"fmt"
	"net/http"
	"github.com/justinas/nosurf"
)

func WriteToConsole(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		fmt.Println("Hit the page")
		next.ServeHTTP(w, r)
	})
}

//adds CSRF protection to all POST req
func NoSurf(next http.Handler) http.Handler {
	csrfHandler := nosurf.New(next)

	csrfHandler.SetBaseCookie(http.Cookie{ //token generated is available on per page bases
		HttpOnly: true,
		Path:     "/", //makes cookie available to the entire site
		Secure:   app.InProduction,
		SameSite: http.SameSiteLaxMode,
	})
	return csrfHandler
}

//loads and saves session on every req
func SessonLoad(next http.Handler) http.Handler {
	return session.LoadAndSave(next)
}
