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
<<<<<<< HEAD
=======

    @SuppressLint("MissingInflatedId")
>>>>>>> 2222e52f14caf366fb5f6cd7584c445de7e0dd06
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnloginn);
        edtName = findViewById(R.id.edtName);
        edtpass = findViewById(R.id.edtPass);
        textView = findViewById(R.id.ed_backlai);
        checkBoxuser = findViewById(R.id.checkuser);
<<<<<<< HEAD


=======
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Manhinhchao.class);
                startActivity(intent);
            }
        });
>>>>>>> 2222e52f14caf366fb5f6cd7584c445de7e0dd06
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

<<<<<<< HEAD
=======
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

>>>>>>> 2222e52f14caf366fb5f6cd7584c445de7e0dd06

                if(edtName.getText().toString().equals("admin") && edtpass.getText().toString().equals("admin")){
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Bạn nhập sai account ADMIN, Mời bạn nhập lại",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public void remnberup(String u,String p,boolean status){
        SharedPreferences shPe=   getSharedPreferences("ADMIN",MODE_PRIVATE);
        SharedPreferences.Editor editor=shPe.edit();
        if (status==false){
            editor.clear();
        } else {
            editor.putString("USERNAME",u);
            editor.putString("PASWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
    public void chk(View view){
        String ten=edtName.getText().toString();
        String paa=edtpass.getText().toString();
        boolean status=checkBoxuser.isChecked();
        remnberup(ten,paa,status);
    }


}