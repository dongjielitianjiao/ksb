package com.itboy.dj.examtool.modules.me.personal;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ChangeAdressActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.show_address)
    TextView showAddress;
    @BindView(R.id.choose_address)
    RelativeLayout chooseAddress;
    @BindView(R.id.detail_adr)
    EditText detailAdr;
    @BindView(R.id.complete_adr)
    Button completeAdr;


    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private boolean isLoaded = false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_change_adress;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("修改地址");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final String token = (String) SharedPreferencesUtils.getParam(ChangeAdressActivity.this, "Token", "");
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        final Observable<ArrayList<JsonBean>> map = Observable.just(JsonData).subscribeOn(Schedulers.io())
                .map(new Func1<String, ArrayList<JsonBean>>() {
                    @Override
                    public ArrayList<JsonBean> call(String s) {
                        return parseData(s);
                    }
                });
        map.subscribe(new Subscriber<ArrayList<JsonBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ArrayList<JsonBean> jsonBean) {
                options1Items = jsonBean;
                for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
                    ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                    ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                    for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                        String CityName = jsonBean.get(i).getCityList().get(c).getName();
                        CityList.add(CityName);//添加城市

                        ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                        //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                        if (jsonBean.get(i).getCityList().get(c).getArea() == null
                                || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                            City_AreaList.add("");
                        } else {

                            for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                                String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                                City_AreaList.add(AreaName);//添加该城市所有地区数据
                            }
                        }
                        Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                    }

                    /**
                     * 添加城市数据
                     */
                    options2Items.add(CityList);

                    /**
                     * 添加地区数据
                     */
                    options3Items.add(Province_AreaList);
                }
            }
        });

        chooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPickerView();
            }
        });
        completeAdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String showStr = showAddress.getText().toString();
                if (TextUtils.isEmpty(showStr.trim())) {
                    LToast.show(context, "请选择地址");
                    return;
                }
                String detailStr = detailAdr.getText().toString();
                if (TextUtils.isEmpty(detailStr.trim())) {
                    LToast.show(context, "请填写详细地址");
                    return;
                }

                final String adr = showStr + detailStr;
                RetrofitService.changeAdr(adr, token).subscribe(new RxSubscribe<JsonObject>(context) {
                    @Override
                    protected void _onNext(JsonObject jsonObject) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                            String result = jsonObject1.optString("result");
                            if ("ok".equals(result)) {
                                Intent intent = new Intent();
                                intent.putExtra("adress", adr);
                                setResult(RESULT_OK, intent);
                                LToast.show(context, "修改成功");
                                SharedPreferencesUtils.getParam(ChangeAdressActivity.this,"jobaddress",adr);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LToast.show(context, message);
                    }
                });


            }
        });


    }


    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                showAddress.setText(tx);

            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //   mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

}
