package duan1.nhom5.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import duan1.nhom5.LoginActivity;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class CaiDatFragment extends Fragment {
    TextView doimk, dangxuat;
    ImageView backcaidat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cai_dat, container, false);
        doimk = v.findViewById(R.id.doimk);
        dangxuat = v.findViewById(R.id.dangxuat);
        backcaidat = v.findViewById(R.id.backcaidat);
        backcaidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DoimatkhauFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.manhinh, fragment).commit();
            }
        });
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn đăng xuất không?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ProgressDialog dialog1 = new ProgressDialog(getActivity());
                        dialog1.setTitle("Vui lòng chờ");
                        dialog1.setMessage("Đang đăng xuất");
                        dialog1.show();
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                            }
                        }, 3000);
                    }
                });
                builder.setNegativeButton("Hủy", null);
                builder.show();

            }
        });
        return v;

    }
}