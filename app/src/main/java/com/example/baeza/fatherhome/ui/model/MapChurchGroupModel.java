package com.example.baeza.fatherhome.ui.model;

import android.content.Context;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.manager.MapChurchGroupsManager;
import com.example.baeza.fatherhome.ui.view.churchMap.Church;
import com.example.baeza.fatherhome.ui.view.churchMap.GrowthGroup;

import java.util.ArrayList;
import java.util.List;

public class MapChurchGroupModel implements MapChurchGroupsManager.Model {

    private MapChurchGroupsManager.Presenter mPresenter;
    private Context mContext;

    private String[] groupLatArray;
    private String[] groupLonArray;
    private String[] groupNameArray;
    private String[] groupPhoneArray;
    private String[] groupTimeArray;

    private String[] churchAddressArray;
    private String[] churchLatArray;
    private String[] churchLonArray;
    private String[] churchTimeArray;

    public MapChurchGroupModel(MapChurchGroupsManager.Presenter presenter, Context context){
        this.mPresenter = presenter;
        this.mContext = context;

        groupLatArray = mContext.getResources().getStringArray(R.array.group_lat_array);
        groupLonArray = mContext.getResources().getStringArray(R.array.group_lon_array);
        groupNameArray = mContext.getResources().getStringArray(R.array.group_name_array);
        groupPhoneArray = mContext.getResources().getStringArray(R.array.group_phone_array);
        groupTimeArray = mContext.getResources().getStringArray(R.array.group_time_array);

        churchAddressArray = mContext.getResources().getStringArray(R.array.church_address_array);
        churchLatArray = mContext.getResources().getStringArray(R.array.church_lat_array);
        churchLonArray = mContext.getResources().getStringArray(R.array.church_lon_array);
        churchTimeArray = mContext.getResources().getStringArray(R.array.church_time_array);
    }

    @Override
    public void getChurchListModel() {
        mPresenter.getChurchListView(buildChurchList());
    }

    @Override
    public void getGrowthGroupModel() {
        mPresenter.getGrowthGroupView(buildGrowthGroup());
    }

    private List<Church> buildChurchList(){
        List<Church> churchList = new ArrayList<>();
        for(int i = 0; i < churchAddressArray.length; i++){
            churchList.add(new Church(churchAddressArray[i],
                    churchLatArray[i],
                    churchLonArray[i],
                    churchTimeArray[i]));
        }
        return churchList;
    }
    private List<GrowthGroup> buildGrowthGroup(){
        List<GrowthGroup> growthGroupList = new ArrayList<>();
        for(int i =0; i < groupNameArray.length ; i++){
            growthGroupList.add(new GrowthGroup(groupLatArray[i],
                    groupLonArray[i],
                    groupNameArray[i],
                    groupPhoneArray[i],
                    groupTimeArray[i]));
        }
        return  growthGroupList;
    }
}
