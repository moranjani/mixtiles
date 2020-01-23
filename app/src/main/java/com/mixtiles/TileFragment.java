package com.mixtiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixtiles.databinding.TileFragmentBinding;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class TileFragment extends Fragment implements UserClickListener {
    private TileFragmentBinding mBinding;

    public static TileFragment newInstance() {
        return new TileFragment();
    }

    private TileViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding =  DataBindingUtil.inflate(inflater, R.layout.tile_fragment, container, false);
        mBinding.setClickCallback(this);
        mBinding.setLifecycleOwner(getActivity());
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(TileViewModel.class);
        mBinding.setVm(mViewModel);
        mBinding.setClickCallback(this);

        loadImage();
    }

    @Override
    public void onTileClicked(View v) {
        loadImage();

    }

    private void loadImage() {
        String url = mViewModel.getImageUrl();
        Picasso.get().invalidate(url);
        Picasso.get().load(url).into(mBinding.tile);
    }


}
