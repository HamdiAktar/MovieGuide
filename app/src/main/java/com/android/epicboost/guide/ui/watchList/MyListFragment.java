package com.android.epicboost.guide.ui.watchList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.epicboost.guide.adapters.GridRecyclerViewAdapter;
import com.android.epicboost.guide.adapters.GridSpacingItemDecoration;
import com.android.epicboost.guide.factories.MyListItemsViewModelFactory;
import com.android.epicboost.guide.ui.MainActivity;
import com.android.epicboost.guide.utils.PreferenceUtil;
import com.android.epicboost.guide.R;
import com.android.epicboost.guide.databinding.FragmentDashboardBinding;
import com.android.epicboost.guide.model.ItemDetails;
import com.android.epicboost.guide.listeners.onClickItemListener;

import java.util.ArrayList;
import java.util.List;

public class MyListFragment extends Fragment implements onClickItemListener {

    private myListViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private GridRecyclerViewAdapter Adapter;
    private List<ItemDetails> items;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this,new MyListItemsViewModelFactory(PreferenceUtil.getInstance(getContext()).getMyList())).get(myListViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        items =new ArrayList<>();
        binding.rvFav.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL,false));
        binding.rvFav.addItemDecoration(new GridSpacingItemDecoration(3, 50, true));
        ((MainActivity)requireActivity()).binding.searchHelperText.setVisibility(View.GONE);
        Adapter = new GridRecyclerViewAdapter(requireActivity(), items,this);
        binding.rvFav.setAdapter(Adapter);

        dashboardViewModel.getItemListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<ItemDetails>>() {
            @Override
            public void onChanged(List<ItemDetails> itemDetails) {
                items.clear();
                items.addAll(itemDetails);
                Adapter.notifyDataSetChanged();
            }
        });

        return root;
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
        Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_movie_details,bundle);
    }
}