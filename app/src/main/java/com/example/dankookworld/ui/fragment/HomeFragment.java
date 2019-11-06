package com.example.dankookworld.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.example.dankookworld.R;

public class HomeFragment extends Fragment {

//    private HomeViewModel homeViewModel;
    private ViewFlipper vf;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        vf = view.findViewById(R.id.vf);
        Button b1 = view.findViewById(R.id.b1);
        Button b2 = view.findViewById(R.id.b2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.showPrevious();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.showNext();
            }
        });
        return view;
    }
}