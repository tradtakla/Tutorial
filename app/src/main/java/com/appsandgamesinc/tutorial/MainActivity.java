package com.appsandgamesinc.tutorial;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private TextView label;
    private EditText text;
    private Button button;

    private TextView textView2;
    public TextView tvProfile;
    private EditText etName;
    private EditText etPass;
    private Button nextBtn;
    public String prefs_username = "prefsUsername";
    public String prefs_password = "prefsPassword";
    public String username;
    public String password;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initVars();

    }



    public void doLogin()
    {
        String username = "user";
        String password = "123456789";
        if (etName.getText().toString().equals(username) && etPass.getText().toString().equals(password))
        {
            CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
            if (checkBox.isChecked())
                rememberMe(username, password); //save username and password
            //show logout activity

            Intent intent = new Intent(MainActivity.this, Slider.class);
            //intent.putExtra("username", etName.getText().toString());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show();
        }


    }


    private void getUser()
    {
        SharedPreferences pref = getSharedPreferences(prefs_username, MODE_PRIVATE);
        username = pref.getString(prefs_username, null);
        password = pref.getString(prefs_password, null);


    }

    private void rememberMe(String user, String pass)
    {
        getSharedPreferences(prefs_username, MODE_PRIVATE)
                .edit()
                .putString(prefs_username, user)
                .putString(prefs_password, password)
                .commit();
    }


    private boolean getResult()
    {
        boolean valid = true;
        if (etName.getText().toString().trim().length() == 0)
        {
            valid = false;
            etName.setError("Please enter a name.");
        }

        if (etPass.getText().toString().length() < 8)
        {
            valid = false;
            etPass.setError("Less than 8 characters.");
        }

        return valid;
    }

    private void initVars()
    {
        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        etName = (EditText) findViewById(R.id.etName);
        etPass = (EditText) findViewById(R.id.etPass);
        nextBtn = (Button) findViewById(R.id.nextBtn);

        nextBtn.setEnabled(false);

        nextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                doLogin();
            }
        });

        etName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (getResult())
                {
                    nextBtn.setEnabled(true);
                }
                else
                {
                    nextBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        etPass.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (getResult())
                {
                    nextBtn.setEnabled(true);
                }
                else
                {
                    nextBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        textView2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }
        });


    }
}
