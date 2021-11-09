package com.app.savemoney.callbacks;

import com.app.savemoney.model.User;

public interface UserCallBack {
    public void onCheckExits(boolean check);
    public void onCheckLogin(User user);
}
