package handlers

import (
	"net/http"
	"web-with-go/pkg/config"
	"web-with-go/pkg/models"
	"web-with-go/pkg/render"
)

//Repository structure is used when we connect the databases, it is used to share the connection pool

//Repo the repository used by the handlers
var Repo *Repository

type Repository struct {
	App *config.AppConfig
}

//creates a new repository
func NewRepo(a *config.AppConfig) *Repository {
	return &Repository{
		App: a,
	}
}

//Sets the repository for new handlers
func NewHandlers(r *Repository) {
	Repo = r
}

//putting the reveivers will grant to the persmission to access everything inside the repository
func (m *Repository) Home(w http.ResponseWriter, r *http.Request) {
	remoteIP := r.RemoteAddr
	m.App.Session.Put(r.Context(), "remote_ip", remoteIP)
	render.RenderTemplate(w, "home.page.html", &models.TemplateData{})
}

func (m *Repository) About(w http.ResponseWriter, r *http.Request) {
	//perform a logic here
	stringMap := make(map[string]string)
	stringMap["test"] = "Hello World!"

	remoteIP := m.App.Session.GetString(r.Context(), "remote_ip")
	stringMap["remote_ip"] = remoteIP

	render.RenderTemplate(w, "about.page.html", &models.TemplateData{
		StringMap: stringMap,
	})
}
