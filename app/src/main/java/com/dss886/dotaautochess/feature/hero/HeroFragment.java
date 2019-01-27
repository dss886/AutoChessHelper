package com.dss886.dotaautochess.feature.hero;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dss886.dotaautochess.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/25.
 */
public class HeroFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        // Item caching of RecyclerView may cause reusing item in a wrong way,
        // I haven't found a suitable solution, so set it to zero...
        recyclerView.setItemViewCacheSize(0);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        HeroAdapter heroAdapter = new HeroAdapter();
        recyclerView.setAdapter(heroAdapter);

        return view;
    }

}
