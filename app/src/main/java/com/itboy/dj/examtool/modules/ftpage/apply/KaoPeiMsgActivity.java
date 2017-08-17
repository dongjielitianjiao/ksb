package com.itboy.dj.examtool.modules.ftpage.apply;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.ExpandableItemTwoAdapter;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.FirstProBean;
import com.itboy.dj.examtool.api.bean.KaoPeiDetail;
import com.itboy.dj.examtool.api.bean.KaoPeiTwo;
import com.itboy.dj.examtool.api.bean.KaopeiFirstBean;
import com.itboy.dj.examtool.api.bean.KaopeiOne;
import com.itboy.dj.examtool.api.bean.ProfessionalBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;
import com.yyydjk.library.DropDownMenu;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.RequestBody;
import rx.Subscriber;

/*这个界面没有用哦 亲*/

public class KaoPeiMsgActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @BindView(R.id.apply_enrol)
    Button applyEnrol;


    private List<View> popupViews = new ArrayList<>();
    private String firstName;
    private String firstId;
    private String secondName;
    private String secondId;
    private String threeName;
    private String threeId;
    private String fourName;
    private String fourId;
    private ArrayList<MultiItemEntity> res = new ArrayList<>();
    private List<KaopeiFirstBean.DataBean> data;
    public int flag = 0;
    private String token;

    public static List<KaoPeiDetail.DataBean> nextShowData = new ArrayList<>();//需要传到下一个界面的集合

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_kao_pei_msg;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("考培信息");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();

        //拿到上个界面传递的四组数据 所选择的某个item的数据
        String ftid = intent.getStringExtra("ftid");
        String ftnm = intent.getStringExtra("ftnm");
        if (TextUtils.isEmpty(ftnm) || "null".equals(ftnm)) {
            ftnm = "暂无数据";
        }

        String secid = intent.getStringExtra("secid");
        String secnm = intent.getStringExtra("secnm");
        if (TextUtils.isEmpty(secnm) || "null".equals(secnm)) {
            secnm = "暂无数据";

        }
        String thrid = intent.getStringExtra("thrid");
        String thrnm = intent.getStringExtra("thrnm");
        if (TextUtils.isEmpty(thrnm) || "null".equals(thrnm)) {
            thrnm = "暂无数据";

        }
        final String fourid = intent.getStringExtra("fourid");
        String fournm = intent.getStringExtra("fournm");
        if (TextUtils.isEmpty(fournm) || "null".equals(fournm)) {
            fournm = "暂无数据";

        }

        //默认选择的名字
        final String headers[] = {ftnm, secnm, thrnm, fournm};
        //拿到上个界面选择的四组数据
        final List<FirstProBean> fistArr = (List<FirstProBean>) getIntent().getSerializableExtra("fistArr");
        final List<FirstProBean> secArr = (List<FirstProBean>) getIntent().getSerializableExtra("secArr");
        final List<FirstProBean> thrArr = (List<FirstProBean>) getIntent().getSerializableExtra("threeArr");
        final List<FirstProBean> fourArr = (List<FirstProBean>) getIntent().getSerializableExtra("fourArr");
        final List<ProfessionalBean.DataBean> dataBeen = ApplyChooseProfessionActivity.AlldataBeen;
        final int size1 = dataBeen.size();
//默认展示
        //一级列表的数据
        final ListView fistView = new ListView(this);
        CommonAdapter firstAdapter = new CommonAdapter<FirstProBean>(this, R.layout.item_kaopai, fistArr) {
            @Override
            protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                viewHolder.setText(R.id.first_text, item.getName());
            }
        };

        fistView.setAdapter(firstAdapter);
        popupViews.add(fistView);


//二级列表的数据
        final ListView secView = new ListView(this);
        CommonAdapter secAdapter = new CommonAdapter<FirstProBean>(this, R.layout.item_kaopai, secArr) {
            @Override
            protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {

                viewHolder.setText(R.id.first_text, item.getName());
            }
        };
        secView.setAdapter(secAdapter);
        popupViews.add(secView);

//三级列表的数据
        final ListView thrView = new ListView(this);
        CommonAdapter thrAdapter = new CommonAdapter<FirstProBean>(this, R.layout.item_kaopai, thrArr) {
            @Override
            protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {

                viewHolder.setText(R.id.first_text, item.getName());
            }
        };
        thrView.setAdapter(thrAdapter);
        popupViews.add(thrView);

        //四级列表的数据
        final ListView fourView = new ListView(this);
        CommonAdapter fourAdapter = new CommonAdapter<FirstProBean>(this, R.layout.item_kaopai, fourArr) {
            @Override
            protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                viewHolder.setText(R.id.first_text, item.getName());
            }
        };

        fourView.setAdapter(fourAdapter);
        popupViews.add(fourView);

        final RecyclerView contentView = new RecyclerView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setLayoutManager(new LinearLayoutManager(KaoPeiMsgActivity.this));
        contentView.addItemDecoration(new RecycleViewDivider(KaoPeiMsgActivity.this, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));
        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("professionId", RequestBody.create(null, ftid + ""));
        //当第二个workid有数据 但是第三个无数据时，传第二个
        if ((!("暂无数据".equals(secnm))) && "暂无数据".equals(thrnm)) {
            map.put("workId", RequestBody.create(null, secid + ""));
            // 当第二个workid有数据 但是第三个也有workid数据时，传第三个
        } else if ((!("暂无数据".equals(secnm))) && (!("暂无数据".equals(thrnm)))) {
            map.put("workId", RequestBody.create(null, thrid + ""));

        } else {
            //否则不传workId
        }
        //等级id 有数据时 ，需要传等级id
        if (!"暂无数据".equals(fournm)) {
            map.put("rankId", RequestBody.create(null, fourid + ""));

        }
        token = (String) SharedPreferencesUtils.getParam(KaoPeiMsgActivity.this, "Token", "");
        RetrofitService.getKaopei(map, token).compose(this.<KaopeiFirstBean>bindToLife()).subscribe(new Subscriber<KaopeiFirstBean>() {
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(KaopeiFirstBean kaopeiFirstBean) {
                data = kaopeiFirstBean.getData();
                int size = data.size();
                for (int i = 0; i < size; i++) {
                    String examTarget = data.get(i).getExamName()+"";
                    String examId = data.get(i).getExamId() + "";
                    KaopeiOne kaopeiOne = new KaopeiOne(examTarget, examId);
                    res.add(kaopeiOne);
                }


            }

            @Override
            public void onCompleted() {
                final ExpandableItemTwoAdapter adapter1 = new ExpandableItemTwoAdapter(res);
                contentView.setAdapter(adapter1);
                contentView.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, final int position) {
                        int itemViewType = adapter.getItemViewType(position);
                        if (itemViewType == ExpandableItemTwoAdapter.TYPE_LEVEL_0) {
                            MultiItemEntity multiItemEntity = res.get(position);
                            final KaopeiOne kaopeiOne = (KaopeiOne) multiItemEntity;
                            RetrofitService.getKaoPerDetail(kaopeiOne.getEpOneId(), token).subscribe(new Subscriber<KaoPeiDetail>() {
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(KaoPeiDetail kaoPeiDetail) {
                                    List<KaoPeiDetail.DataBean> data = kaoPeiDetail.getData();
                                    nextShowData.clear();
                                    Iterator<KaoPeiDetail.DataBean> iterator = data.iterator();
                                    while (iterator.hasNext()) {
                                        KaoPeiDetail.DataBean kaoPeodataBean = iterator.next();
                                        nextShowData.add(kaoPeodataBean);
                                    }


                                    int size = data.size();
                                    KaoPeiTwo kaoPeiTwo = null;
                                    for (int i = 0; i < size; i++) {
                                        kaoPeiTwo = new KaoPeiTwo();
                                        kaoPeiTwo.setBaomingObject(data.get(i).getExamName());
                                        kaoPeiTwo.setFirstTime(data.get(i).getExamStartTime());
                                        kaoPeiTwo.setLastTime(data.get(i).getExamEndTime());
                                        kaoPeiTwo.setPublishTime(data.get(i).getCreateDate());
                                        kaoPeiTwo.setTrainAdr(data.get(i).getTrainModel());
                                        kaoPeiTwo.setTwoTitle(data.get(i).getExamTarget());
                                        if (flag == 0) {
                                            kaopeiOne.addSubItem(position, kaoPeiTwo);
                                            flag = 1;
                                        }
                                    }

                                }
                            });
                        }


                        if (itemViewType == ExpandableItemTwoAdapter.TYPE_LEVEL_1) {
                            MultiItemEntity multiItemEntity = res.get(position);
                            KaoPeiTwo kaopeiTwo = (KaoPeiTwo) multiItemEntity;
                            adapter1.setCheckItem(position);
                        }


                    }
                });


            }


        });


        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);



        //1级的点击事件
        fistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                firstName = fistArr.get(position).getName();
                firstId = fistArr.get(position).getOriginalId() + "";
                String ftId = fistArr.get(position).getId();//用作筛选
                String[] headers1 = {firstName, "请选择", "请选择", "请选择"};
                dropDownMenu.setTabMenuViewTitle(Arrays.asList(headers1));
                dropDownMenu.closeMenu();
                //筛选2级
                //  final String firstid = fistArr.get(position).getId();
                FirstProBean firstProBean = null;
                secArr.clear();
                for (int i = 0; i < size1; i++) {
                    String stype = dataBeen.get(i).getType();
                    String sid = dataBeen.get(i).getId();
                    String pid = (String) dataBeen.get(i).getPid();
                    int originalId = dataBeen.get(i).getOriginalId();
                    String sname = dataBeen.get(i).getName();
                    if ("work".equals(stype) && ftId.equals(pid)) {  //筛选出来第二个
                        firstProBean = new FirstProBean(sname, sid, originalId);
                        secArr.add(firstProBean);

                    }
                }
                //只有一级的时候 直接进行内容部分的请求，二三四设置为暂无数据
                if (secArr.size() == 0) {
                    firstProBean = new FirstProBean("暂无数据", "暂无数据", 0);
                    secArr.add(firstProBean);
                    String[] headers2 = {firstName, "暂无数据", "暂无数据", "暂无数据"};
                    dropDownMenu.setTabMenuViewTitle(Arrays.asList(headers2));

                    final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                    map.put("professionId", RequestBody.create(null, firstId + ""));
                    RetrofitService
                            .getKaopei(map, token)
                            .compose(KaoPeiMsgActivity.this.<KaopeiFirstBean>bindToLife())
                            .subscribe(new Subscriber<KaopeiFirstBean>() {
                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(KaopeiFirstBean kaopeiFirstBean) {
                                    data.clear();
                                    res.clear();
                                    data = kaopeiFirstBean.getData();
                                    int size = data.size();
                                    for (int i = 0; i < size; i++) {
                                        String examTarget =  data.get(i).getExamName();
                                        String examId = data.get(i).getExamId() + "";
                                        KaopeiOne kaopeiOne = new KaopeiOne(examTarget, examId);
                                        res.add(kaopeiOne);
                                    }
                                }

                                @Override
                                public void onCompleted() {
                                    final ExpandableItemTwoAdapter adapter2 = new ExpandableItemTwoAdapter(res);
                                    contentView.setAdapter(adapter2);
                                    contentView.addOnItemTouchListener(new OnItemClickListener() {
                                        @Override
                                        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, final int position) {
                                            int itemViewType = adapter.getItemViewType(position);
                                            if (itemViewType == ExpandableItemTwoAdapter.TYPE_LEVEL_0) {
                                                MultiItemEntity multiItemEntity = res.get(position);
                                                final KaopeiOne kaopeiOne = (KaopeiOne) multiItemEntity;
                                                //拿到1级的数据请求2级
                                                RetrofitService.getKaoPerDetail(kaopeiOne.getEpOneId(), token).subscribe(new Subscriber<KaoPeiDetail>() {
                                                    @Override
                                                    public void onCompleted() {
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {

                                                    }

                                                    @Override
                                                    public void onNext(KaoPeiDetail kaoPeiDetail) {
                                                        List<KaoPeiDetail.DataBean> data = kaoPeiDetail.getData();

                                                        nextShowData.clear();
                                                        Iterator<KaoPeiDetail.DataBean> iterator = data.iterator();
                                                        while (iterator.hasNext()) {
                                                            KaoPeiDetail.DataBean kaoPeodataBean = iterator.next();
                                                            nextShowData.add(kaoPeodataBean);
                                                        }

                                                        int size = data.size();
                                                        KaoPeiTwo kaoPeiTwo = null;
                                                        for (int i = 0; i < size; i++) {
                                                            kaoPeiTwo = new KaoPeiTwo();
                                                            kaoPeiTwo.setBaomingObject(data.get(i).getExamName());
                                                            kaoPeiTwo.setFirstTime(data.get(i).getExamStartTime());
                                                            kaoPeiTwo.setLastTime(data.get(i).getExamEndTime());
                                                            kaoPeiTwo.setPublishTime(data.get(i).getCreateDate());
                                                            kaoPeiTwo.setTrainAdr(data.get(i).getTrainModel());
                                                            kaoPeiTwo.setTwoTitle(data.get(i).getExamTarget());
                                                            if (flag == 0) {
                                                                kaopeiOne.addSubItem(position, kaoPeiTwo);
                                                                flag = 1;
                                                            }
                                                        }

                                                    }
                                                });
                                            }
                                            if (itemViewType == ExpandableItemTwoAdapter.TYPE_LEVEL_1) {
                                                MultiItemEntity multiItemEntity = res.get(position);
                                                KaoPeiTwo kaopeiTwo = (KaoPeiTwo) multiItemEntity;
                                                adapter2.setCheckItem(position);
                                            }


                                        }
                                    });


                                }

                            });


                    //当只有一级的时候,二三四都设置一个暂无数据选择
                    CommonAdapter secAdapter = new CommonAdapter<FirstProBean>(KaoPeiMsgActivity.this, R.layout.item_kaopai, secArr) {
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    };
                    secView.setAdapter(secAdapter);
                    thrView.setAdapter(secAdapter);
                    fourView.setAdapter(secAdapter);
                    secAdapter.notifyDataSetChanged();



                }




                //如果二级有数据  就筛选3级了筛选三级
                String secondid = secArr.get(0).getId();
                FirstProBean firstProBean2 = null;
                thrArr.clear();///
                for (int i = 0; i < size1; i++) {
                    String ttype = dataBeen.get(i).getType();
                    String tid = dataBeen.get(i).getId();
                    String tpid = (String) dataBeen.get(i).getPid();
                    String tname = dataBeen.get(i).getName();
                    int originalId = dataBeen.get(i).getOriginalId();
                    if ("work".equals(ttype) && secondid.equals(tpid)) {  //筛选出来第三个
                        firstProBean2 = new FirstProBean(tname, tid, originalId);
                        thrArr.add(firstProBean2);///
                    }
                }
                if (thrArr.size() == 0) {
                    firstProBean2 = new FirstProBean("暂无数据", "暂无数据", 0);
                    thrArr.add(firstProBean2);
                    thrView.setAdapter(new CommonAdapter<FirstProBean>(KaoPeiMsgActivity.this, R.layout.item_kaopai, thrArr) {
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    });
                    fourView.setAdapter(new CommonAdapter<FirstProBean>(KaoPeiMsgActivity.this, R.layout.item_kaopai, thrArr) {///
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    });
                    return;
                }

                /*//如果三级没数据，四级就不用做了
                if (thrArr.size() == 1 && "暂无数据".equals(thrArr.get(0).getName())) {///
                    return;
                }*/


                //四级
                String threeid = thrArr.get(0).getId(); ///
                FirstProBean firstProBean3 = null;
                fourArr.clear();
                for (int i = 0; i < size1; i++) {
                    String ttype = dataBeen.get(i).getType();
                    String tid = dataBeen.get(i).getId();
                    String tpid = (String) dataBeen.get(i).getPid();
                    String tname = dataBeen.get(i).getName();
                    int originalId = dataBeen.get(i).getOriginalId();
                    if ("rank".equals(ttype) && threeid.equals(tpid)) {  //筛选出来第三个
                        firstProBean3 = new FirstProBean(tname, tid, originalId);
                        fourArr.add(firstProBean3); ///4
                    }
                }

                //四级加一个暂无数据
                if (fourArr.size() == 0) { ///4
                    firstProBean3 = new FirstProBean("暂无数据", "暂无数据", 0);
                    fourArr.add(firstProBean3); ///4
                    fourView.setAdapter(new CommonAdapter<FirstProBean>(KaoPeiMsgActivity.this, R.layout.item_kaopai, fourArr) {
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    });
                }


            }
        });


        secView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                secondName = secArr.get(position).getName();
                secondId = secArr.get(position).getOriginalId() + "";
                String sdid = secArr.get(position).getId();
                String[] headers = {firstName, secondName, "请选择", "请选择"};
                dropDownMenu.setTabMenuViewTitle(Arrays.asList(headers));
                dropDownMenu.closeMenu();
                FirstProBean firstProBean = null;
                thrArr.clear();
                for (int i = 0; i < size1; i++) {
                    String ttype = dataBeen.get(i).getType();
                    String tid = dataBeen.get(i).getId();
                    String tpid = (String) dataBeen.get(i).getPid();
                    String tname = dataBeen.get(i).getName();
                    int originalId = dataBeen.get(i).getOriginalId();
                    if ("work".equals(ttype) && sdid.equals(tpid)) {  //筛选出来第三个
                        firstProBean = new FirstProBean(tname, tid, originalId);
                        thrArr.add(firstProBean);//--
                    }
                }
                //如果只有前二级 ，则请求2级
                if (thrArr.size() == 0) { //--
                    String[] headers1 = {firstName, secondName, "暂无数据", "暂无数据"};
                    dropDownMenu.setTabMenuViewTitle(Arrays.asList(headers1));

                    final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                    map.put("professionId", RequestBody.create(null, firstId + ""));
                    map.put("workId", RequestBody.create(null, secondId + ""));
                    RetrofitService
                            .getKaopei(map, token)
                            .compose(KaoPeiMsgActivity.this.<KaopeiFirstBean>bindToLife())
                            .subscribe(new Subscriber<KaopeiFirstBean>() {
                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(KaopeiFirstBean kaopeiFirstBean) {
                                    data.clear();
                                    res.clear();
                                    data = kaopeiFirstBean.getData();
                                    int size = data.size();
                                    for (int i = 0; i < size; i++) {
                                        String examTarget = (String) data.get(i).getExamName();
                                        String examId = data.get(i).getExamId() + "";
                                        KaopeiOne kaopeiOne = new KaopeiOne(examTarget, examId);
                                        res.add(kaopeiOne);
                                    }
                                }

                                @Override
                                public void onCompleted() {
                                    final ExpandableItemTwoAdapter adapter2 = new ExpandableItemTwoAdapter(res);
                                    contentView.setAdapter(adapter2);
                                    contentView.addOnItemTouchListener(new OnItemClickListener() {
                                        @Override
                                        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, final int position) {
                                            int itemViewType = adapter.getItemViewType(position);
                                            if (itemViewType == ExpandableItemTwoAdapter.TYPE_LEVEL_0) {
                                                MultiItemEntity multiItemEntity = res.get(position);
                                                final KaopeiOne kaopeiOne = (KaopeiOne) multiItemEntity;
                                                RetrofitService.getKaoPerDetail(kaopeiOne.getEpOneId(), token).subscribe(new Subscriber<KaoPeiDetail>() {
                                                    @Override
                                                    public void onCompleted() {
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {

                                                    }

                                                    @Override
                                                    public void onNext(KaoPeiDetail kaoPeiDetail) {

                                                        List<KaoPeiDetail.DataBean> data = kaoPeiDetail.getData();

                                                        nextShowData.clear();
                                                        Iterator<KaoPeiDetail.DataBean> iterator = data.iterator();
                                                        while (iterator.hasNext()) {
                                                            KaoPeiDetail.DataBean kaoPeodataBean = iterator.next();
                                                            nextShowData.add(kaoPeodataBean);
                                                        }
                                                        int size = data.size();
                                                        KaoPeiTwo kaoPeiTwo = null;
                                                        for (int i = 0; i < size; i++) {
                                                            kaoPeiTwo = new KaoPeiTwo();
                                                            kaoPeiTwo.setBaomingObject(data.get(i).getExamName());
                                                            kaoPeiTwo.setFirstTime(data.get(i).getExamStartTime());
                                                            kaoPeiTwo.setLastTime(data.get(i).getExamEndTime());
                                                            kaoPeiTwo.setPublishTime(data.get(i).getCreateDate());
                                                            kaoPeiTwo.setTrainAdr(data.get(i).getTrainModel());
                                                            kaoPeiTwo.setTwoTitle(data.get(i).getExamTarget());
                                                            if (flag == 0) {
                                                                kaopeiOne.addSubItem(position, kaoPeiTwo);
                                                                flag = 1;
                                                            }
                                                        }

                                                    }
                                                });
                                            }
                                            if (itemViewType == ExpandableItemTwoAdapter.TYPE_LEVEL_1) {
                                                MultiItemEntity multiItemEntity = res.get(position);
                                                KaoPeiTwo kaopeiTwo = (KaoPeiTwo) multiItemEntity;
                                                adapter2.setCheckItem(position);
                                            }


                                        }
                                    });


                                }

                            });


                    firstProBean = new FirstProBean("暂无数据", "暂无数据", 0);
                    thrArr.add(firstProBean);//--
                    CommonAdapter<FirstProBean> thadapter = new CommonAdapter<FirstProBean>(KaoPeiMsgActivity.this, R.layout.item_kaopai, thrArr) {//-
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    };
                    thrView.setAdapter(thadapter);
                    thadapter.notifyDataSetChanged();
                    fourView.setAdapter(new CommonAdapter<FirstProBean>(KaoPeiMsgActivity.this, R.layout.item_kaopai, thrArr) {//-
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    });

                }

                //如果第三个没有数据
       /*         if (thrArr.size() == 1 && "暂无数据".equals(thrArr.get(0).getName())) {//-
                    fourView.setAdapter(new CommonAdapter<FirstProBean>(KaoPeiMsgActivity.this, R.layout.item_kaopai, thrArr) {//-
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    });
                    return;
                }*/

                String threeid = thrArr.get(0).getId();//-
                FirstProBean firstProBean3 = null;
                fourArr.clear();

                for (int i = 0; i < size1; i++) {
                    String ttype = dataBeen.get(i).getType();
                    String tid = dataBeen.get(i).getId();
                    String tpid = (String) dataBeen.get(i).getPid();
                    String tname = dataBeen.get(i).getName();
                    int originalId = dataBeen.get(i).getOriginalId();
                    if ("rank".equals(ttype) && threeid.equals(tpid)) {  //筛选出来第三个
                        firstProBean3 = new FirstProBean(tname, tid, originalId);
                        fourArr.add(firstProBean3); //-----1
                    }
                }
                if (fourArr.size() == 0) { ////--1
                    firstProBean3 = new FirstProBean("暂无数据", "暂无数据", 0);
                    fourArr.add(firstProBean3); ////---1
                }
                CommonAdapter<FirstProBean> fuadapter = new CommonAdapter<FirstProBean>(KaoPeiMsgActivity.this, R.layout.item_kaopai, fourArr) {
                    @Override
                    protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                        viewHolder.setText(R.id.first_text, item.getName());
                    }
                };
                fourView.setAdapter(fuadapter);
                fuadapter.notifyDataSetChanged();


            }
        });


        thrView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                threeName = thrArr.get(position).getName();
                threeId = thrArr.get(position).getOriginalId() + "";
                String thid = thrArr.get(position).getId();
                dropDownMenu.closeMenu();
                FirstProBean firstProBean = null;
                String[] headers2 = {firstName, secondName, threeName, "请选择"};
                dropDownMenu.setTabMenuViewTitle(Arrays.asList(headers2));
                fourArr.clear();
                for (int i = 0; i < size1; i++) {
                    String ftype = dataBeen.get(i).getType();
                    String fid = dataBeen.get(i).getId();
                    String fpid = (String) dataBeen.get(i).getPid();
                    String fname = dataBeen.get(i).getName();
                    int originalId = dataBeen.get(i).getOriginalId();
                    if ("rank".equals(ftype) && thid.equals(fpid)) {  //筛选出来第三个
                        firstProBean = new FirstProBean(fname, fid, originalId);
                        fourArr.add(firstProBean);//////0
                    }
                }
                if (fourArr.size() == 0) {
                    String[] headers = {firstName, secondName, threeName, "暂无数据"};
                    dropDownMenu.setTabMenuViewTitle(Arrays.asList(headers));
                    final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                    map.put("professionId", RequestBody.create(null, firstId + ""));
                    map.put("workId", RequestBody.create(null, threeId + ""));

                    RetrofitService
                            .getKaopei(map, token)
                            .compose(KaoPeiMsgActivity.this.<KaopeiFirstBean>bindToLife())
                            .subscribe(new Subscriber<KaopeiFirstBean>() {
                                @Override
                                public void onError(Throwable e) {
                                }

                                @Override
                                public void onNext(KaopeiFirstBean kaopeiFirstBean) {
                                    data.clear();
                                    res.clear();
                                    data = kaopeiFirstBean.getData();
                                    int size = data.size();
                                    for (int i = 0; i < size; i++) {
                                        String examTarget = (String) data.get(i).getExamName();
                                        String examId = data.get(i).getExamId() + "";
                                        KaopeiOne kaopeiOne = new KaopeiOne(examTarget, examId);
                                        res.add(kaopeiOne);
                                    }
                                }

                                @Override
                                public void onCompleted() {
                                    final ExpandableItemTwoAdapter adapter2 = new ExpandableItemTwoAdapter(res);
                                    contentView.setAdapter(adapter2);
                                    contentView.addOnItemTouchListener(new OnItemClickListener() {
                                        @Override
                                        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, final int position) {
                                            int itemViewType = adapter.getItemViewType(position);
                                            if (itemViewType == ExpandableItemTwoAdapter.TYPE_LEVEL_0) {
                                                MultiItemEntity multiItemEntity = res.get(position);
                                                final KaopeiOne kaopeiOne = (KaopeiOne) multiItemEntity;
                                                RetrofitService.getKaoPerDetail(kaopeiOne.getEpOneId(), token).subscribe(new Subscriber<KaoPeiDetail>() {
                                                    @Override
                                                    public void onCompleted() {
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {

                                                    }

                                                    @Override
                                                    public void onNext(KaoPeiDetail kaoPeiDetail) {
                                                        List<KaoPeiDetail.DataBean> data = kaoPeiDetail.getData();
                                                        nextShowData.clear();
                                                        Iterator<KaoPeiDetail.DataBean> iterator = data.iterator();
                                                        while (iterator.hasNext()) {
                                                            KaoPeiDetail.DataBean kaoPeodataBean = iterator.next();
                                                            nextShowData.add(kaoPeodataBean);
                                                        }
                                                        int size = data.size();
                                                        KaoPeiTwo kaoPeiTwo = null;
                                                        for (int i = 0; i < size; i++) {
                                                            kaoPeiTwo = new KaoPeiTwo();
                                                            kaoPeiTwo.setBaomingObject(data.get(i).getExamName());
                                                            kaoPeiTwo.setFirstTime(data.get(i).getExamStartTime());
                                                            kaoPeiTwo.setLastTime(data.get(i).getExamEndTime());
                                                            kaoPeiTwo.setPublishTime(data.get(i).getCreateDate());
                                                            kaoPeiTwo.setTrainAdr(data.get(i).getTrainModel());
                                                            kaoPeiTwo.setTwoTitle(data.get(i).getExamTarget());
                                                            if (flag == 0) {
                                                                kaopeiOne.addSubItem(position, kaoPeiTwo);
                                                                flag = 1;
                                                            }
                                                        }

                                                    }
                                                });
                                            }
                                            if (itemViewType == ExpandableItemTwoAdapter.TYPE_LEVEL_1) {
                                                MultiItemEntity multiItemEntity = res.get(position);
                                                KaoPeiTwo kaopeiTwo = (KaoPeiTwo) multiItemEntity;
                                                adapter2.setCheckItem(position);
                                            }


                                        }
                                    });


                                }

                            });


                    firstProBean = new FirstProBean("暂无数据", "暂无数据", 0);
                    fourArr.add(firstProBean);
                    CommonAdapter<FirstProBean> lastfuadapter = new CommonAdapter<FirstProBean>(KaoPeiMsgActivity.this, R.layout.item_kaopai, fourArr) {///0
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    };
                    fourView.setAdapter(lastfuadapter);
                    lastfuadapter.notifyDataSetChanged();
                }


            }
        });

        fourView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fourName = fourArr.get(position).getName();
                fourId = fourArr.get(position).getOriginalId() + "";
                dropDownMenu.setTabText(position == 0 ? headers[3] : fourArr.get(position).getName());
                dropDownMenu.closeMenu();
                final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                map.put("professionId", RequestBody.create(null, firstId + ""));
                map.put("workId", RequestBody.create(null, threeId + ""));
                map.put("rankId", RequestBody.create(null, fourId + ""));
                RetrofitService
                        .getKaopei(map, token)
                        .compose(KaoPeiMsgActivity.this.<KaopeiFirstBean>bindToLife())
                        .subscribe(new Subscriber<KaopeiFirstBean>() {
                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(KaopeiFirstBean kaopeiFirstBean) {
                                data.clear();
                                res.clear();
                                data = kaopeiFirstBean.getData();
                                int size = data.size();
                                for (int i = 0; i < size; i++) {
                                    String examTarget = (String) data.get(i).getExamName();
                                    String examId = data.get(i).getExamId() + "";
                                    KaopeiOne kaopeiOne = new KaopeiOne(examTarget, examId);
                                    res.add(kaopeiOne);
                                }
                            }

                            @Override
                            public void onCompleted() {
                                final ExpandableItemTwoAdapter adapter2 = new ExpandableItemTwoAdapter(res);
                                contentView.setAdapter(adapter2);
                                contentView.addOnItemTouchListener(new OnItemClickListener() {
                                    @Override
                                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, final int position) {
                                        int itemViewType = adapter.getItemViewType(position);
                                        if (itemViewType == ExpandableItemTwoAdapter.TYPE_LEVEL_0) {
                                            MultiItemEntity multiItemEntity = res.get(position);
                                            final KaopeiOne kaopeiOne = (KaopeiOne) multiItemEntity;
                                            RetrofitService.getKaoPerDetail(kaopeiOne.getEpOneId(), token).subscribe(new Subscriber<KaoPeiDetail>() {
                                                @Override
                                                public void onCompleted() {
                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }

                                                @Override
                                                public void onNext(KaoPeiDetail kaoPeiDetail) {
                                                    List<KaoPeiDetail.DataBean> data = kaoPeiDetail.getData();

                                                    nextShowData.clear();
                                                    Iterator<KaoPeiDetail.DataBean> iterator = data.iterator();
                                                    while (iterator.hasNext()) {
                                                        KaoPeiDetail.DataBean kaoPeodataBean = iterator.next();
                                                        nextShowData.add(kaoPeodataBean);
                                                    }

                                                    int size = data.size();
                                                    KaoPeiTwo kaoPeiTwo = null;
                                                    for (int i = 0; i < size; i++) {
                                                        kaoPeiTwo = new KaoPeiTwo();
                                                        kaoPeiTwo.setBaomingObject(data.get(i).getExamName());
                                                        kaoPeiTwo.setFirstTime(data.get(i).getExamStartTime());
                                                        kaoPeiTwo.setLastTime(data.get(i).getExamEndTime());
                                                        kaoPeiTwo.setPublishTime(data.get(i).getCreateDate());
                                                        kaoPeiTwo.setTrainAdr(data.get(i).getTrainModel());
                                                        kaoPeiTwo.setTwoTitle(data.get(i).getExamTarget());
                                                        if (flag == 0) {
                                                            kaopeiOne.addSubItem(position, kaoPeiTwo);
                                                            flag = 1;
                                                        }
                                                    }

                                                }
                                            });
                                        }
                                        if (itemViewType == ExpandableItemTwoAdapter.TYPE_LEVEL_1) {
                                            MultiItemEntity multiItemEntity = res.get(position);
                                            KaoPeiTwo kaopeiTwo = (KaoPeiTwo) multiItemEntity;
                                            adapter2.setCheckItem(position);
                                        }


                                    }
                                });


                            }

                        });


            }
        });


        //报名去到下一步
        applyEnrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nextShowData.size() == 0) {
                    LToast.show(context, "请选择考试科目");
                    return;
                }



            }
        });

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kao_pei_msg);
        ButterKnife.bind(this);
    }
*/


}
