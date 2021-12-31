package be.heh.catechtest.Models;

public class User {
    private int id;
    private String nom;
    private String password;
    private String login;
    private String prenom;
    private String role;
    public User(){}

    public User(String nom, String prenom, String password, String login, String role){

        this.nom = nom;
        this.prenom=prenom;
        this.role=role;
        this.password = password;
        this.login = login;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) { this.login = login; }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID : " + getId() + "\n"
                + "Nom : " + getNom() + "\n"
                + "Prenom: "+getPrenom()+"\n"
                + "Password : " + getPassword() + "\n"
                + "Login : " + getLogin() +"\n"
                + "Role: "+getRole());
        return sb.toString();
    }
}
