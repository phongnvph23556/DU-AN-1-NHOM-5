package duan1.nhom5.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import duan1.nhom5.R;


public class ManHinhChaoFragment extends Fragment {

    public ManHinhChaoFragment() {
        // Required empty public constructor
    }

    public static ManHinhChaoFragment newInstance() {
        ManHinhChaoFragment fragment = new ManHinhChaoFragment();
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
        return inflater.inflate(R.layout.fragment_man_hinh_chao, container, false);
    }
}