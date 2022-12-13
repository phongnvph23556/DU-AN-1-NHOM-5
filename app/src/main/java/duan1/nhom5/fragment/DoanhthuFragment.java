package duan1.nhom5.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import duan1.nhom5.DAO.TopDAO;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;

public class DoanhthuFragment extends Fragment {
    ImageView backk;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    TopDAO topDAO;

    EditText edttuNgay, edtdenNgay;
    Button btnDoanhThu;
    TextView tvDoanhThu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doanhthu, container, false);
        backk = v.findViewById(R.id.backdoanhthu);

        edttuNgay = v.findViewById(R.id.edt_tungay);
        edtdenNgay = v.findViewById(R.id.edt_denngay);
        btnDoanhThu = v.findViewById(R.id.btn_doanhthu);
        tvDoanhThu = v.findViewById(R.id.tv_doanhthu);

        topDAO = new TopDAO(getActivity());
        Calendar calendar = Calendar.getInstance();

        edttuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        edttuNgay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                datePickerDialog.show();
            }
        });
        edtdenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        edtdenNgay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                datePickerDialog.show();
            }
        });

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int doanhThu = topDAO.getDoanhThu(edttuNgay.getText().toString(), edtdenNgay.getText().toString());
                tvDoanhThu.setText("Doanh thu: " + doanhThu + " VND");
            }
        });

        backk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;

    }
}