package duan1.nhom5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import duan1.nhom5.DAO.AdminDAO;
import duan1.nhom5.Entity.Admin;

public class SignupActivity extends AppCompatActivity {

    private Button btndangki,btnhuy;
    private EditText edten,edmatkhau,ednhaplai;
    private AdminDAO adminDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btndangki = findViewById(R.id.btndki);
        btnhuy=findViewById(R.id.btnhuydki);
        edten=findViewById(R.id.ed_Name);
        edmatkhau = findViewById(R.id.ed_Pass);
        ednhaplai=findViewById(R.id.ed_RePass);
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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