package com.example.gaurav.kotlin_navigationdrawer.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gaurav.kotlin_navigationdrawer.R;
import com.example.gaurav.kotlin_navigationdrawer.Utills.ConnectionDetector;
import com.example.gaurav.kotlin_navigationdrawer.activity.HomeAdapter;
import com.example.gaurav.kotlin_navigationdrawer.databinding.FragmentHomeBinding;
import com.example.gaurav.kotlin_navigationdrawer.model.APIResponse;
import com.example.gaurav.kotlin_navigationdrawer.model.Contact;
import com.example.gaurav.kotlin_navigationdrawer.settingStructure.ApiClient;
import com.example.gaurav.kotlin_navigationdrawer.settingStructure.ApiInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    HomeAdapter mAdapter;
    List<Contact> contactList;
    private boolean isInternetConnected;
    private static String TAG = HomeFragment.class.getSimpleName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_home, container, false);

        contactList = new ArrayList<>();

        isInternetConnected = new ConnectionDetector(getActivity()).isConnectingToInternet();
        if (isInternetConnected) {
            getContactData();
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
        }
        return binding.getRoot();
    }


    private void getContactData() {

        try {
            if (isInternetConnected) {
                final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<APIResponse> saleCall = apiInterface.getContactsData();
                Log.e(TAG, saleCall.request().url().toString());

                saleCall.enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        Log.e(TAG, String.valueOf(response.raw()));
                        Log.e(TAG, new Gson().toJson(response.body()));

                        if (response.isSuccessful()) {
                            try {
                                APIResponse example = response.body();
                                contactList = example.getContacts();

                                mAdapter = new HomeAdapter(contactList,getActivity());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                binding.recyclerView.setLayoutManager(mLayoutManager);
                                binding.recyclerView.setNestedScrollingEnabled(false);
                                binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
                                binding.recyclerView.setAdapter(mAdapter);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            // do nothing
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {
                        // do nothing
                    }
                });
            } else {
                // do nothing
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}




