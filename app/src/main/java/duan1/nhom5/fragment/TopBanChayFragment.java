package duan1.nhom5.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class TopBanChayFragment extends Fragment {
    ImageView backtop;



    public TopBanChayFragment() {
        // Required empty public constructor
    }

    public static TopBanChayFragment newInstance() {
        TopBanChayFragment fragment = new TopBanChayFragment();

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
        View v= inflater.inflate(R.layout.fragment_top_ban_chay, container, false);
        backtop=v.findViewById(R.id.backtop);
        backtop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}