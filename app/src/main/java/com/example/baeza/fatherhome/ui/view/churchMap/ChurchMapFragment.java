package com.example.baeza.fatherhome.ui.view.churchMap;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.Manifest;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.helper.MessageEvent;
import com.example.baeza.fatherhome.ui.manager.MapChurchGroupsManager;
import com.example.baeza.fatherhome.ui.presenter.MapChurchGroupsPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class ChurchMapFragment extends Fragment implements OnMapReadyCallback, MapChurchGroupsManager.View, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {//}, LocationListener {

    private List<Church> mChurchList;
    private List<GrowthGroup> mGrowthGroupList;
    private MapChurchGroupsPresenter mPresenter;

    private GoogleMap mGoogleMap;
    SupportMapFragment mMapFragment;

    //Need to add the key in the manifest and the user's permission
    public ChurchMapFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_church_map, container, false);
        ButterKnife.bind(this, view);

        mChurchList = new ArrayList<>();
        mGrowthGroupList = new ArrayList<>();

        mPresenter = new MapChurchGroupsPresenter(this, getContext());
        mPresenter.getChurchListModel();
        mPresenter.getGrowthGroupModel();

        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
        mMapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mGoogleMap.clear();
        mGoogleMap = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

        if (mChurchList != null && mChurchList.size() > 0) {
            for (int i = 0; i < mChurchList.size(); i++) {
                googleMap.addMarker(new MarkerOptions()
                        .position(getPosition(mChurchList.get(i).getLat(), mChurchList.get(i).getLon()))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        .snippet(mChurchList.get(i).getTime())
                        .title(mChurchList.get(i).getAddress()));
            }
        }
        if (mGrowthGroupList != null && mGrowthGroupList.size() > 0) {
            for (int i = 0; i < mGrowthGroupList.size(); i++) {
                googleMap.addMarker(new MarkerOptions()
                        .position(getPosition(mGrowthGroupList.get(i).getLat(), mGrowthGroupList.get(i).getLon()))
                        .snippet( mGrowthGroupList.get(i).getPhone())
                        .title(mGrowthGroupList.get(i).getName()+" "+mGrowthGroupList.get(i).getTime()));
            }
        }

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.753329, 37.621048), 10));
        showLocationInMap();
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(MessageEvent event){
//        showLocationInMap();
//    }

    private void showLocationInMap() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.setOnMyLocationButtonClickListener(this);
        mGoogleMap.setOnMyLocationClickListener(this);
    }

    private LatLng getPosition(String lat, String lon) {
        return new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
    }

    @Override
    public void getChurchListView(List<Church> churchList) {
        this.mChurchList = churchList;
    }

    @Override
    public void getGrowthGroupView(List<GrowthGroup> growthGroupList) {
        this.mGrowthGroupList = growthGroupList;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onStart(){
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop(){
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }
}
