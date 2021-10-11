package com.imdigitalashish.aespasswordencrypter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    EditText et_key;
    EditText et_value;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_key = findViewById(R.id.key_et);
        et_value = findViewById(R.id.msg_et);
        message = findViewById(R.id.tv_msg);

    }

    public void encrypt(View view) throws GeneralSecurityException {

        String encrypted = AESCrypt.encrypt(et_key.getText().toString(), et_value.getText().toString());
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("label", encrypted);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
        et_value.setText("");
        et_key.setText("");
        message.setText(String.format("You Encrypted key is: %s", encrypted));

    }

    public void decrypt(View view) throws GeneralSecurityException {
        String decrypted = AESCrypt.decrypt(et_key.getText().toString(), et_value.getText().toString());
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("label", decrypted);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
        et_value.setText("");
        et_key.setText("");
        message.setText(String.format("You Decrypted message is: %s", decrypted));


    }
}