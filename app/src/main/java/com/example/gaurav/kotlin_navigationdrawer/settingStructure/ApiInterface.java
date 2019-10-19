package com.example.gaurav.kotlin_navigationdrawer.settingStructure;


import com.example.gaurav.kotlin_navigationdrawer.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    /*Api for data display*/
    @GET(".")
    Call<APIResponse> getContactsData();

}