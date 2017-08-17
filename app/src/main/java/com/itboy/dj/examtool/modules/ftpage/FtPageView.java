package com.itboy.dj.examtool.modules.ftpage;

import com.itboy.dj.examtool.api.bean.LocalImgTitle;
import com.itboy.dj.examtool.api.bean.NewsBean;
import com.itboy.dj.examtool.api.bean.Noticebean;
import com.itboy.dj.examtool.modules.base.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */

public interface FtPageView extends IBaseView {

    void loadBanner(NewsBean strbanner);

    void loadEightBtn(List<LocalImgTitle> localImgTitles);

    void  loadHotNews(List<Noticebean.DataBean> dataBeen);
}
