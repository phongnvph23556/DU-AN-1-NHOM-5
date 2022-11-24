package duan1.nhom5.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import duan1.nhom5.R;


public class DangKiFragment extends Fragment {

    public DangKiFragment() {
        // Required empty public constructor
    }

    public static DangKiFragment newInstance() {
        DangKiFragment fragment = new DangKiFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dnag_ki, container, false);
    }
}