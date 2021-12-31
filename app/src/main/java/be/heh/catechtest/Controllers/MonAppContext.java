package be.heh.catechtest.Controllers;

import android.app.Application;

public class MonAppContext extends Application {

    // variables globales de l'application
    private int idLoginConnected;
    public int getIdLoginConnected () { return idLoginConnected; }
    public void setIdLoginConnected (int id) { idLoginConnected = id; }

    private String userRole;
    public String getUserRole () { return userRole; }
    public void setUserRole (String id) { userRole = id; }

    // initialisation du Contexte
    @Override public void onCreate(){
        super.onCreate();
        idLoginConnected = -1; // mon nom est personne
        userRole = "READ";
    }
}
