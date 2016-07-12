package com.example.encho.fragmentbasic.total;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Admin on 2016-07-12.
 */

//현재 이 프래그먼트를 담고있는  Activity를 호출하는 콜백메소드를 구현한다(두가지 방법)

public class SListFragment extends Fragment {
    public SListFragment() {
        super();
    }
    // 1. 이벤트 리스너를 등록하고 Activity에서 구현하는 방법
    public interface MessageCallBack {
        public void receiveMessage(String messageToFragment);
    }
    MessageCallBack callBack;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof MessageCallBack){
            callBack = (MessageCallBack)context;
        }
    }

    @Nullable
    ListView listView;
    ArrayAdapter<String> mAdapter;
    Activity ownerActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.second_list_fragment_layout, container, false);
        listView = (ListView)v.findViewById(R.id.second_list);
        mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemvalue = (String) listView.getItemAtPosition(position);
                ownerActivity  = getActivity();
                if (ownerActivity instanceof AnotherActivity){
                    ((AnotherActivity)ownerActivity).callFromFragment(itemvalue);
                }
                if (ownerActivity instanceof MainActivity){
                    ((MainActivity) ownerActivity).callFromFragment(itemvalue);
                }
                //ownerActivity.callFromFragment(itemvalue);
//                //액티비티로 보낸다
//                if(callBack != null) {
//                    callBack.receiveMessage(itemvalue);
//                }
            }
        });
        insertItem();
        return v;
    }
    private void insertItem() {
        for(int i = 0 ; i < 50 ; i++){
            mAdapter.add(" 아이템 -> " + i);
        }
    }
}
