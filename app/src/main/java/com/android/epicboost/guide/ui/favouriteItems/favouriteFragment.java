package com.android.epicboost.guide.ui.favouriteItems;

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

import com.android.epicboost.guide.factories.FavouriteItemsViewModelFactory;
import com.android.epicboost.guide.adapters.GridRecyclerViewAdapter;
import com.android.epicboost.guide.adapters.GridSpacingItemDecoration;
import com.android.epicboost.guide.ui.MainActivity;
import com.android.epicboost.guide.utils.PreferenceUtil;
import com.android.epicboost.guide.R;
import com.android.epicboost.guide.databinding.FragmentNotificationsBinding;
import com.android.epicboost.guide.model.ItemDetails;
import com.android.epicboost.guide.listeners.onClickItemListener;

import java.util.ArrayList;
import java.util.List;

public class favouriteFragment extends Fragment implements onClickItemListener {

    private favouriteViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private GridRecyclerViewAdapter Adapter;
    private List<ItemDetails> items;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this, new FavouriteItemsViewModelFactory(PreferenceUtil.getInstance(getContext()).getFavouriteMovieList())).get(favouriteViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        items =new ArrayList<>();
        binding.rvFav.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL,false));
        binding.rvFav.addItemDecoration(new GridSpacingItemDecoration(3, 50, true));

        ((MainActivity)requireActivity()).binding.searchHelperText.setVisibility(View.GONE);

        Adapter = new GridRecyclerViewAdapter(requireActivity(), items,this);
        binding.rvFav.setAdapter(Adapter);

        notificationsViewModel.getMovieListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<ItemDetails>>() {
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
    public void onClick(View view, int movieId) {
        Bundle bundle = new Bundle();
        bundle.putInt("itemId",movieId);
        Navigation.findNavController(view).navigate(R.id.action_navigation_notifications_to_movie_details,bundle);
    }
}
