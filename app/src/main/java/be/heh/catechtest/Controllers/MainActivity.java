package be.heh.catechtest.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import be.heh.catechtest.Models.User;
import be.heh.catechtest.Models.UserAccessBDD;
import be.heh.catechtest.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText et_main_login;
    private EditText et_main_password;
    private Button Bt_connect;
    private Button Bt_new_user;
    private MonAppContext ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = (MonAppContext) this.getApplicationContext() ;

        et_main_login = (EditText) findViewById(R.id.et_main_login);
        et_main_password = (EditText) findViewById(R.id.et_main_password);
        Bt_connect = (Button) findViewById(R.id.Bt_connect);
        Bt_new_user = (Button) findViewById(R.id.Bt_new_user);



        Bt_connect.setEnabled(false);


        et_main_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Bt_connect.setEnabled(s.toString().length() > 3);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void onMainClickManager(View v) {
        switch (v.getId()) {
            case R.id.Bt_connect:

                boolean connexion=false;
                UserAccessBDD userDB = new UserAccessBDD(this);
                userDB.openForRead();
                ArrayList<User> tab_user = userDB.getAllUser();

                EditText et_main_login=(EditText)findViewById(R.id.et_main_login);
                String sLogin = et_main_login.getText().toString();

                EditText password=(EditText)findViewById(R.id.et_main_password);
                String sPassword = password.getText().toString();

                for(User u : tab_user){
                    if(u.getLogin().equals(sLogin) && u.getPassword().equals(sPassword)){
                        connexion=true;
                        ctx.setIdLoginConnected(u.getId());
                        ctx.setUserRole(u.getRole());
                        break;
                    }
                }
                if(connexion==true){
                    Toast.makeText(this, "Connexion réussie", Toast.LENGTH_LONG).show();

                    Intent intentMain = new Intent(this, ConnexS7Activity.class);
                    startActivity(intentMain);
                }
                else{
                    Toast.makeText(this, "Connexion ratée", Toast.LENGTH_LONG).show();
                }
                break;


            case R.id.Bt_new_user:
                Intent intentUser = new Intent(this, c.Controllers.CreateUserActivity.class);
                startActivity(intentUser);
                break;

        }
    }
}
