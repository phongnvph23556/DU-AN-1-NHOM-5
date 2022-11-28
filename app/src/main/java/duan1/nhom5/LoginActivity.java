package duan1.nhom5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtName, edtpass;
    CheckBox checkBoxuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnloginn);
        edtName = findViewById(R.id.edtName);
        edtpass = findViewById(R.id.edtPass);
        checkBoxuser = findViewById(R.id.checkuser);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtName.getText().toString().equals("admin") && edtpass.getText().toString().equals("admin")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Bạn nhập sai account ADMIN, Mời bạn nhập lại",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void rememberUser(String Username, String pass, boolean status){
        SharedPreferences sharedPreferences = 
    }
}