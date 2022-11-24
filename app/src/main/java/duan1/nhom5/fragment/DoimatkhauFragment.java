package duan1.nhom5.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import duan1.nhom5.R;


public class DoimatkhauFragment extends Fragment {
    ImageView backdoimk;




    public static DoimatkhauFragment newInstance() {
        DoimatkhauFragment fragment = new DoimatkhauFragment();
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
        View v= inflater.inflate(R.layout.fragment_doimatkhau, container, false);
        backdoimk=v.findViewById(R.id.backdoimk);
        backdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CaiDatFragment();
                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.manhinh,fragment).commit();
            }
        });
        return v;
    }
}