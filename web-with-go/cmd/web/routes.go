package main

import (
	//"github.com/bmizerany/pat"
	"github.com/go-chi/chi/v5"
	"github.com/go-chi/chi/v5/middleware"
	"net/http"
	"web-with-go/pkg/config"
	"web-with-go/pkg/handlers"
)

func routes(app *config.AppConfig) http.Handler {
	//	mux := pat.New()
	//
	//	mux.Get("/", http.HandlerFunc(handlers.Repo.Home))
	//	mux.Get("/about", http.HandlerFunc(handlers.Repo.About))

	mux := chi.NewRouter()

	//middleware
	mux.Use(middleware.Recoverer)
	mux.Use(NoSurf)
	mux.Use(SessonLoad)

	mux.Get("/", handlers.Repo.Home)
	mux.Get("/about", handlers.Repo.About)

	return mux
}
