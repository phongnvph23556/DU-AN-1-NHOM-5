package duan1.nhom5;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import duan1.nhom5.DAO.AdminDAO;
import duan1.nhom5.Entity.Admin;
import duan1.nhom5.Entity.LoaiSanPham;

public class SignupActivity extends AppCompatActivity {

    private Button btndangki,btnhuy;
    private EditText edten,edmatkhau,ednhaplai;
    private AdminDAO adminDAO;
    private Admin admin;
    private TextView textView;
    ArrayList<Admin> list;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btndangki = findViewById(R.id.btndki);
        btnhuy=findViewById(R.id.btnhuydki);

        edten=findViewById(R.id.edt_taikhoan);
        edmatkhau = findViewById(R.id.ed_Pass);
        ednhaplai=findViewById(R.id.ed_RePass);

        textView = findViewById(R.id.edquaylai);

        adminDAO=new AdminDAO(this);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan=edten.getText().toString();
                String pass=edmatkhau.getText().toString();
                String repass=ednhaplai.getText().toString();
                if(taikhoan.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }else {
                    if(pass.equals(repass)){
                        Boolean check_user=adminDAO.checkTaiKhoan(taikhoan);
                        if(check_user==false){
                            Boolean insert=adminDAO.insert(taikhoan,pass);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplicationContext(),"Đăng kí thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"Tài khoản đã tồn tại! vui lòng đăng nhập",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,Manhinhchao.class);
                startActivity(intent);
            }
        });
    }

}