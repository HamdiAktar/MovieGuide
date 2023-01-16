package com.android.epicboost.guide.ui.itemDetails;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.epicboost.guide.ui.MainActivity;
import com.android.epicboost.guide.factories.ItemDetailsViewModelFactory;
import com.android.epicboost.guide.utils.PreferenceUtil;
import com.android.epicboost.guide.R;
import com.android.epicboost.guide.databinding.MovieDetailsFragmentBinding;
import com.android.epicboost.guide.model.ItemDetails;
import com.squareup.picasso.Picasso;

public class itemDetailsFragment extends Fragment {

    private ItemDetailsViewModel mViewModel;
    private MovieDetailsFragmentBinding binding;
    private ItemDetails itemDetails;
    private int itemId;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            itemId =arguments.getInt("itemId");
        }
        mViewModel = new ViewModelProvider(this,new ItemDetailsViewModelFactory(itemId)).get(ItemDetailsViewModel.class);
        binding = MovieDetailsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setHasOptionsMenu(true);
        itemDetails =new ItemDetails();
        Toolbar toolbar = ((MainActivity)requireActivity()).binding.toolbar;
        toolbar.setTitle(itemDetails.title);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.favorite:
                        if(PreferenceUtil.getInstance(getContext()).isFavouriteMovie(itemId)){
                            PreferenceUtil.getInstance(getContext()).removeFavSharedPref(itemId);
                            Toast.makeText(getActivity(), itemDetails.getName()+" is removed from Favourite",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            PreferenceUtil.getInstance(getContext()).addMovieToFavourite(itemId);
                            Toast.makeText(getActivity(), itemDetails.getName()+" is added to Favourite",Toast.LENGTH_SHORT).show();
                        }
                        requireActivity().invalidateOptionsMenu();
                        break;
                    case R.id.save_to_mylist:
                        if(PreferenceUtil.getInstance(getContext()).isMyListMovie(itemId)){
                            PreferenceUtil.getInstance(getContext()).removeListSharedPref(itemId);
                            Toast.makeText(getActivity(), itemDetails.getName()+" is removed from your List",Toast.LENGTH_SHORT).show();
                        }

                        else{
                            PreferenceUtil.getInstance(getContext()).addMovieToMyList(itemId);
                            Toast.makeText(getActivity(), itemDetails.getName()+" is added to your List",Toast.LENGTH_SHORT).show();
                        }
                        requireActivity().invalidateOptionsMenu();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        mViewModel.getItemDetails().observe(getViewLifecycleOwner(), new Observer<ItemDetails>() {
            @Override
            public void onChanged(ItemDetails itemDetail) {
                itemDetails =itemDetail;
                toolbar.setTitle(itemDetails.getName());
                updateUI();
            }
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movie_details_toolbar,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem favItem = menu.findItem(R.id.favorite);
        MenuItem myListItem = menu.findItem(R.id.save_to_mylist);
        if (PreferenceUtil.getInstance(getContext()).isFavouriteMovie(itemId))
            favItem.setIcon(R.drawable.ic_baseline_favorite_24);
        else favItem.setIcon(R.drawable.ic_baseline_favorite_border_24);
        if (PreferenceUtil.getInstance(getContext()).isMyListMovie(itemId))
            myListItem.setIcon(R.drawable.ic_baseline_bookmark_added_24);
        else myListItem.setIcon(R.drawable.ic_baseline_bookmark_border_24);

    }

    private void updateUI(){

        Picasso.get().load(itemDetails.getPoster_path()).into(binding.poster);
        binding.title.setText(itemDetails.getName());
        binding.details.setText(itemDetails.getOverview());
        binding.duration.setText(itemDetails.getDetailedInfo2());
        binding.PGrating.setText(itemDetails.getDetailedInfo3());
        binding.releaseData.setText((itemDetails.getDetailedInfo1()));
        StringBuilder genre= new StringBuilder("");
        for (int i = 0; i< itemDetails.getGenres().size() && i<3; i++){
            genre.append(itemDetails.genres.get(i).name+"");
            if(itemDetails.getGenres().size() == i-1 || i == 2)
                continue;
            if(itemDetails.getGenres().size()>1)
                genre.append(", ");

        }
        binding.genre.setText(genre);
        binding.ratingBar.setRating((float) (itemDetails.vote_average/2));
        ImageView[] imageViews= new ImageView[]{binding.actorImage1,binding.actorImage2,binding.actorImage3,binding.actorImage4};
        TextView[] nameViews= new TextView[]{binding.actorName1,binding.actorName2,binding.actorName3,binding.actorName4};
        for (int i = 0; i< itemDetails.getCast().size() && i<4; i++){
            Picasso.get().load(itemDetails.getCast().get(i).getProfile_path()).into(imageViews[i]);
            nameViews[i].setText(itemDetails.getCast().get(i).name);
        }

    }



}