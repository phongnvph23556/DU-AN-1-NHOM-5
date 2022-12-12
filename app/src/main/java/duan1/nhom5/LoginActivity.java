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

import duan1.nhom5.DAO.AdminDAO;
import duan1.nhom5.DAO.NhanVienDAO;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView textView;
    EditText edtName, edtpass;
    CheckBox checkBoxuser;
    AdminDAO adminDAO;
    NhanVienDAO nhanVienDAO;

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
        adminDAO = new AdminDAO(this);
        nhanVienDAO=new NhanVienDAO(this);

        //đọc user,pass trong sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = sharedPreferences.getString("USERNAME", "");
        String pass = sharedPreferences.getString("PASSWORD", "");
        Boolean rememb = sharedPreferences.getBoolean("REMEMBER", false);

        edtName.setText(user);
        edtpass.setText(pass);
        checkBoxuser.setChecked(rememb);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtName.getText().toString();
                String pass = edtpass.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = nhanVienDAO.checkusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        rememberUser(user, pass, checkBoxuser.isChecked());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!status) {
            //xóa tình trạng lưu trước đó
            editor.clear();
        } else {
            //lưu dữ liệu
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD", p);
            editor.putBoolean("REMEMBER", status);
        }
        //lưu lại toàn bộ dữ liệu
        editor.commit();

    }


}