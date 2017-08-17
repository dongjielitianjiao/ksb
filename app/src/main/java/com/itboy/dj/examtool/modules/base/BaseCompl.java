package com.itboy.dj.examtool.modules.base;

import android.content.Context;

/**
 * Created by Administrator on 2017/4/5.
 */

public abstract class BaseCompl  {
  public   Context context;

    public BaseCompl(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
