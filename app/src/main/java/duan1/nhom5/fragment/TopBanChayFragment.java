package duan1.nhom5.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import duan1.nhom5.Adapter.TopAdapter;
import duan1.nhom5.DAO.TopDAO;
import duan1.nhom5.Entity.Top;
import duan1.nhom5.MainActivity;
import duan1.nhom5.R;


public class TopBanChayFragment extends Fragment {
    ImageView backtop;

    RecyclerView recyclerView;
    List<Top> list = new ArrayList<>();
    TopDAO topDAO;
    TopAdapter adapter;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_top_ban_chay, container, false);
        backtop=v.findViewById(R.id.backtop);


        recyclerView = v.findViewById(R.id.rcv_top10);
        topDAO = new TopDAO(getActivity());
        list.addAll(topDAO.selectAll());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new TopAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);

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