package com.example.openclassroom;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.openclassroom.model.User;

public class MainActivity extends AppCompatActivity {

    // Pour chaque élément graphique préfixe "m" bonne pratique : member: données membres (s : static)
    private TextView mTextView;
    private EditText mEditText;
    private Button mButton;
    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupère l'élément via son identifiant
        mTextView = findViewById(R.id.text);
        mEditText = findViewById(R.id.field);
        mButton = findViewById(R.id.button);

        // Désactive le bouton
        mButton.setEnabled(false);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mButton.setEnabled(s.toString().length() > 0);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mUser.setFirstName(mEditText.getText().toString());
                        Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                        startActivity(gameActivity);
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


}
