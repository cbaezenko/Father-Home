package com.example.baeza.fatherhome.ui.view.churchMap;

//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.baeza.fatherhome.R;
//import com.example.baeza.fatherhome.ui.helper.MessageEvent;

//import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
//import timber.log.Timber;

public class ChurchMapActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


//    private final int MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 122;
//    private final int MY_PERMISSION_REQUEST_ACCESS_COARSE_LOCATION = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_church_map);

        ButterKnife.bind(this);

        setToolbar(getString(R.string.church_map_toolbar_title));

        ChurchMapFragment churchMapFragment = new ChurchMapFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, churchMapFragment)
                .commit();
    }

    @Override
    public void onResume(){
        super.onResume();
//        requestPermissions();
    }

    private void setToolbar(String toolbarTitle) {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(toolbarTitle);
    }

//    private void requestPermissions(){
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.READ_CONTACTS)) {
//
//                // Show an expanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION: {
//                //if request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //permission was granted!
//
////                    EventBus.getDefault().post(new MessageEvent());
//
//                }else {
//                    //permission denied
//                }
//            }
//            case MY_PERMISSION_REQUEST_ACCESS_COARSE_LOCATION:{
//                //if request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //permission was granted!
//                }else {
//                    //permission denied
//                }
//            }
//        }
//    }

}
