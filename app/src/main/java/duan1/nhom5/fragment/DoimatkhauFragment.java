package duan1.nhom5.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import duan1.nhom5.DAO.NhanVienDAO;
import duan1.nhom5.Entity.NhanVien;
import duan1.nhom5.R;


public class DoimatkhauFragment extends Fragment {
    ImageView backdoimk;
    Button btnluu, btnhuy;
    EditText edpass, edpassmoi, edrepass;
    NhanVienDAO nhanVienDAO;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doimatkhau, container, false);
        btnluu = v.findViewById(R.id.btnluu);
        btnhuy = v.findViewById(R.id.btnhuy);

        edpass = v.findViewById(R.id.edMatkhau);
        edpassmoi = v.findViewById(R.id.edPassmoi);
        edrepass = v.findViewById(R.id.ednhaplai);
        backdoimk = v.findViewById(R.id.backdoimk);
        nhanVienDAO=new NhanVienDAO(getActivity());
        backdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CaiDatFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.manhinh, fragment).commit();
            }
        });

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = preferences.getString("USERNAME", "");
                if (validate() > 0) {
                     String mkmoi=edpassmoi.getText().toString();
                    NhanVien nhanVien = nhanVienDAO.getTaiKhoan(user);
                    nhanVien.setMatKhauNV(edpassmoi.getText().toString());

                    if (nhanVienDAO.changepass(user,mkmoi)) {
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edpass.setText("");
                        edpassmoi.setText("");
                        edrepass.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edpass.setText("");
                edpassmoi.setText("");
                edrepass.setText("");
            }
        });
        return v;
    }

    public int validate() {
        int check = 1;
        if (edpass.getText().length() == 0 || edpassmoi.getText().length() == 0 || edrepass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            //đọc user,pass trong SharePreferences
            SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String matkhaucu = preferences.getString("PASSWORD", "");
            String matkhaumoi = edpassmoi.getText().toString();
            String matkhaumoi2 = edrepass.getText().toString();
            if (!matkhaucu.equals(edpass.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;

            }
            if (!matkhaumoi.equals(matkhaumoi2)) {
                Toast.makeText(getContext(), "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;

            }
        }
        return check;
    }
}