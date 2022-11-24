package duan1.nhom5.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import duan1.nhom5.MainActivity;
import duan1.nhom5.R;

public class DoanhthuFragment extends Fragment {
    ImageView backk;

    public DoanhthuFragment() {
        // Required empty public constructor
    }


    public static DoanhthuFragment newInstance() {
        DoanhthuFragment fragment = new DoanhthuFragment();
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
        View v= inflater.inflate(R.layout.fragment_doanhthu, container, false);
        backk=v.findViewById(R.id.backdoanhthu);
        backk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        return v;

    }
}