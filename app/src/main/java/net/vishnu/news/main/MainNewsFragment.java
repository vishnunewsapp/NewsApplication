package net.vishnu.news.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.vishnu.news.R;
import net.vishnu.news.adapter.NewsPagerAdapter;
import net.vishnu.news.enums.NewsSourceEnum;
import net.vishnu.news.fragment.NewsPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class MainNewsFragment extends Fragment {

    NewsPagerAdapter newsPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        viewPager = view.findViewById(R.id.pager);
        tabLayout = view.findViewById(R.id.tabs);

        setUpViewPager();

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setUpViewPager() {
        List<Fragment> newsPagerFragmentList = new ArrayList<>();
        for (int pos = 0; pos < 7; pos++) {
            NewsPagerFragment newsPagerFragment = getNewsPagerFragment(pos);
            newsPagerFragmentList.add(newsPagerFragment);
        }
        newsPagerAdapter = new NewsPagerAdapter(getActivity().getSupportFragmentManager(), newsPagerFragmentList);
        viewPager.setAdapter(newsPagerAdapter);
    }

    private NewsPagerFragment getNewsPagerFragment(int position) {
        String category = NewsSourceEnum.getNewsSourceEnum(position).name().toLowerCase();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        NewsPagerFragment newsPagerFragment = new NewsPagerFragment();
        newsPagerFragment.setArguments(bundle);
        return newsPagerFragment;
    }
}
