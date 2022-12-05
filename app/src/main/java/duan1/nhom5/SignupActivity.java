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
import duan1.nhom5.DAO.NhanVienDAO;
import duan1.nhom5.Entity.Admin;
import duan1.nhom5.Entity.LoaiSanPham;
import duan1.nhom5.Entity.NhanVien;

public class SignupActivity extends AppCompatActivity {

    private Button btndangki,btnhuy;
    private EditText edten,edmatkhau,ednhaplai;
    private TextView textView;
    ArrayList<Admin> list;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btndangki = findViewById(R.id.btndki);
        btnhuy=findViewById(R.id.btnhuydki);
        edten=findViewById(R.id.ed_Name);
        edmatkhau = findViewById(R.id.ed_Pass);
        textView = findViewById(R.id.edquaylai);
        ednhaplai=findViewById(R.id.ed_RePass);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,Manhinhchao.class);
                startActivity(intent);
            }
        });
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
;
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
    public int validate() {
        int check = 1;
        if (edten.getText().length() == 0 || edmatkhau.getText().length() == 0 || ednhaplai.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}