package com.android.epicboost.guide.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.epicboost.guide.R;
import com.android.epicboost.guide.adapters.RecyclerViewAdapter;
import com.android.epicboost.guide.databinding.FragmentHomeBinding;
import com.android.epicboost.guide.model.Item;
import com.android.epicboost.guide.listeners.onClickItemListener;
import com.android.epicboost.guide.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements onClickItemListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerViewAdapter Adapter;
    private List<Item> Items;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setHasOptionsMenu(true);
        Items =new ArrayList<>();

        Adapter = new RecyclerViewAdapter(requireActivity(), Items,this);
        binding.rvMain.setAdapter(Adapter);
        homeViewModel.getItemMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                Items.clear();
                Items.addAll(items);
                Adapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_app_bar,menu);
        MenuItem item= menu.findItem(R.id.search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setQueryHint(MainActivity.resourceInstance.getString(R.string.searchPlaceHolder));
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)requireActivity()).binding.searchHelperText.setVisibility(View.GONE);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                homeViewModel.callAPI(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length()>3)
                    homeViewModel.callAPI(newText);
                if (newText.length()==0)
                    homeViewModel.cleanResult();
                    return true;
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view, int itemId) {
        Bundle bundle = new Bundle();
        bundle.putInt("itemId",itemId);
        Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_movie_details,bundle);
    }
}