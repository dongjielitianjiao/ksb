package com.itboy.dj.examtool.modules.ftpage.apply;

import com.itboy.dj.examtool.api.bean.KaoPeiDetail;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */

public class GradeGroup1 {

    public String id,name;
    public boolean checked;
    public  List<KaoPeiDetail.DataBean> children;

    public GradeGroup1(String gradename, String _gradeid, boolean checked, List<KaoPeiDetail.DataBean> children){
        this.name=gradename;
        setChecked(checked);
        // this.children=children;
        this.id=_gradeid;
        addchilddata(children);

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public void setChecked(boolean b){
        checked=b;
        if(children!=null&&children.size()>0){//若children不为空，循环设置children的checked
            for(KaoPeiDetail.DataBean each : children){
                each.checked=checked;
            }

        }

    }

    public boolean getChecked(){
        return checked;
    }

    public void addchilddata(List<KaoPeiDetail.DataBean> children){
        this.children=children;

    }



}
