package com.itboy.dj.examtool.modules.ftpage.apply;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
//未使用
public class GradeGroup {

    public String id,name;
    public boolean checked;
    public  List<ClassListItem> children;

    public GradeGroup(String gradename,String _gradeid,boolean checked,List<ClassListItem> children){
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
            for(ClassListItem each : children){
                each.checked=checked;
            }

        }

    }

    public boolean getChecked(){

        return checked;

    }


    public void addchilddata(List<ClassListItem> children){
        this.children=children;

    }



}
