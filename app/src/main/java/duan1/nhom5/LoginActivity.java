package duan1.nhom5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView textView;
    EditText edtName, edtpass;
    CheckBox checkBoxuser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnloginn);
        edtName = findViewById(R.id.edtName);
        edtpass = findViewById(R.id.edtPass);
        textView = findViewById(R.id.ed_backlai);
        checkBoxuser = findViewById(R.id.checkuser);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Manhinhchao.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);


                if(edtName.getText().toString().equals("admin") && edtpass.getText().toString().equals("admin")){
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    rememberUser(edtName, edtpass, checkBoxuser.isChecked());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Bạn nhập sai account ADMIN, Mời bạn nhập lại",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
    public void rememberUser(EditText Username, EditText pass, boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE.txt",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        if (!status){
            editor.clear();
        }else {
            //thêm data vào file
            editor.putString("USERNAME", String.valueOf(Username));
            editor.putString("PASSWORD", String.valueOf(pass));
            editor.putBoolean("REMEMBER", status);
        }
        //lưu lại
        editor.commit();
    }
}