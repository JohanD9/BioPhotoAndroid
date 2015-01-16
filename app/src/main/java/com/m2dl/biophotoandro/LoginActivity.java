package com.m2dl.biophotoandro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    private ImageView connectButton;
    private EditText editUsername;
    private AlertDialog.Builder connectAlert;

    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Mise en place du message d'alerte en cas d'erreur dans la connexion
        connectAlert = new AlertDialog.Builder(LoginActivity.this);
        connectAlert.setTitle("Erreur");
        connectAlert.setIcon(R.drawable.ic_menu_cancel);
        connectAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        //Récupértion du bouton de connexion et du champs texte
        connectButton = (ImageView) findViewById(R.id.imageViewLaunch);
        editUsername = (EditText) findViewById(R.id.editTextUsername);

        connectButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                username = editUsername.getText().toString();
                connectAlert.setMessage("Votre nom d'utilisateur doit comporter au minimum 4 charactères");
                if (username.toString().length() < 4 || username.isEmpty()) {
                    connectAlert.show();
                } else {
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    i.putExtra("username", username);
                    startActivity(i);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
