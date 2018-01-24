package com.andforce.assets;

/**
 * Created by andforce on 2018/1/24.
 */
public class Account {

    private long mAccountId = -1;

    public Account(long accountId) {
        mAccountId = accountId;
    }

    public long getAccountId() {
        return mAccountId;
    }
}
