package be.heh.catechtest.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import be.heh.catechtest.Models.User;
import be.heh.catechtest.Models.UserAccessBDD;
import be.heh.catechtest.R;

import java.util.ArrayList;

public class CreateUserActivity extends AppCompatActivity {
    private EditText create_nom;
    private EditText create_prenom;
    private EditText create_login;
    private EditText create_password;
    private EditText confirm_password;
    private Button Bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        create_nom = (EditText) findViewById(R.id.create_nom);
        create_prenom = (EditText) findViewById(R.id.create_prenom);
        create_login = (EditText) findViewById(R.id.create_login);
        create_password = (EditText) findViewById(R.id.create_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        Bt_submit = (Button) findViewById(R.id.Bt_submit);
    }

    public void onMainClickManager(View v) {

        if(testNom() && testPrenom() && testLogin() && testPassword() && testConfirmPassword()){

            EditText create_nom=findViewById(R.id.create_nom);
            String sNom = create_nom.getText().toString();

            EditText create_prenom=(EditText)findViewById(R.id.create_prenom);
            String sPrenom = create_prenom.getText().toString();

            EditText create_login=(EditText)findViewById(R.id.create_login);
            String sLogin = create_login.getText().toString();

            EditText create_password=(EditText)findViewById(R.id.create_password);
            String sPassword = create_password.getText().toString();

            UserAccessBDD userDB = new UserAccessBDD(this);
            userDB.openForRead();
            ArrayList<User> tab_user = userDB.getAllUser();
            userDB.Close();
            userDB.openForWrite();
            User u;

            if(tab_user.isEmpty()){
                u = new User(sNom,sPrenom,sPassword,sLogin,"SUPERADMIN");

            }
            else{
                u=new User(sNom,sPrenom,sPassword,sLogin,"READ");
            }

            userDB.insertUser(u);
            userDB.Close();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public boolean testNom(){
        EditText create_nom=findViewById(R.id.create_nom);
        String sNom = create_nom.getText().toString();
        if(sNom.matches("")){
            create_nom.setError("Vous n'avez pas entr?? de nom");
            return false;
        }
        else{
            return true;
        }
    }
    public boolean testPrenom(){
        EditText create_prenom=(EditText)findViewById(R.id.create_prenom);
        String sPrenom = create_prenom.getText().toString();
        if(sPrenom.matches("")){
            create_prenom.setError("Vous n'avez pas entr?? de pr??nom");
            return false;
        }
        else{
            return true;
        }
    }
    public boolean testLogin(){
        EditText create_login=(EditText)findViewById(R.id.create_login);
        String sLogin = create_login.getText().toString();
        if(sLogin.matches("")){
            create_login.setError("Vous n'avez pas entr?? de mail");
            return false;

        }
        else{
            int i=0;
            UserAccessBDD userDB = new UserAccessBDD(this);
            userDB.openForRead();
            ArrayList<User> tab_user = userDB.getAllUser();
            for(User u : tab_user){
                if(u.getLogin().equals(sLogin)){
                    i=1;
                    break;
                }
            }
            userDB.Close();
            if(i==1){
                create_login.setError("Le mail existe d??j?? dans la BDD");
                return false;
            }
            else{
                return true;
            }

        }
    }
    public boolean testPassword(){
        EditText create_password=(EditText)findViewById(R.id.create_password);
        String sPassword = create_password.getText().toString();
        if(sPassword.matches("")){
            create_password.setError("Vous n'avez pas entr?? de mot de passe");
            return false;
        }
        else{
            if(sPassword.length()<4){
                create_password.setError("Le mot de passe n'est pas assez long (4 caract??res minimum)");
                return false;
            }
            else {
                return true;
            }
        }
    }

    public boolean testConfirmPassword(){
        EditText create_password=(EditText)findViewById(R.id.create_password);
        EditText confirm_password=(EditText)findViewById(R.id.confirm_password);
        String sConfPwd = confirm_password.getText().toString();
        String sPwd = create_password.getText().toString();
        if(sConfPwd.matches("")){
            confirm_password.setError("Vous n'avez pas entr?? de confirmation de mot de passe");
            return false;
        }
        else{
            if(!(sConfPwd.equals(sPwd))){
                confirm_password.setError("Les mots de passes ne correspondent pas");
                return false;
            }
            else{
                return true;
            }

        }
    }
}