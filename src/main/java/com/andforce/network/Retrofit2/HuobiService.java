package com.andforce.network.retrofit2;

import com.andforce.bean.account.Account;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * Created by andforce on 2018/1/23.
 */
public interface HuobiService {

    @GET("/v1/account/accounts")
    Call<Account> readAccountInfo(@QueryMap Map<String, String> parameters);

}
