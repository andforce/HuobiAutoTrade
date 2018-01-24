package com.andforce.network.Retrofit2;

import com.andforce.network.HuobiRequest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andforce on 2018/1/23.
 */
public class HuobiRetrofit {

    private void request(HuobiRequest huobiRequest){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(huobiRequest.url() + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        HuobiService huobiApi = retrofit.create(HuobiService.class);
//
//        Call<Account> call = huobiApi.readAccountInfo(huobiRequest.parameters());
//
//        call.enqueue(new Callback<Account>() {
//            @Override
//            public void onResponse(Call<Account> call, Response<Account> response) {
//
//                //print(response.body().getData().get(0).getId());
//
//            }
//
//            @Override
//            public void onFailure(Call<Account> call, Throwable t) {
//
//            }
//        });
    }
}
