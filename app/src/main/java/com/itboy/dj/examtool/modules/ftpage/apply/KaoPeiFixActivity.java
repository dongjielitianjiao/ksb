package com.itboy.dj.examtool.modules.ftpage.apply;

import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.FirstProBean;
import com.itboy.dj.examtool.api.bean.KaoPeiDetail;
import com.itboy.dj.examtool.api.bean.KaopeiFirstBean;
import com.itboy.dj.examtool.api.bean.ProfessionalBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;
import com.yyydjk.library.DropDownMenu;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.RequestBody;
import rx.Subscriber;

/*开始写的KaoPeiMsgActivity类有BUG*/
public class KaoPeiFixActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.apply_enrol)
    Button applyEnrol;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
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
    private List<KaopeiFirstBean.DataBean> data = new ArrayList<>();
    public int flag = 0;
    private String token;
    private ExpandableAdapter1 expandableAdapter;

    public List<KaoPeiDetail.DataBean> nextShowData = new ArrayList<>();//需要传到下一个界面的集合
    // private List<GradeGroup> groupData = new ArrayList<>();
    // private List<ClassListItem> classListItems = new ArrayList<>();
    private List<GradeGroup1> groupData1 = new ArrayList<>();
    private List<KaoPeiDetail.DataBean> classListItems1 = new ArrayList<>();
    private int mGroupPosition;
    private ExpandableListView contentView;
    private int mChildPosition;
    private List<KaoPeiDetail.DataBean> childData;
    //用这个
    private SparseArray<List<KaoPeiDetail.DataBean>> sparseArray = new SparseArray<>();



    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_kao_pei_fix;
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
        contentView = new ExpandableListView(this);
        contentView.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);
        contentView.setGroupIndicator(null);
        contentView.setSelector(R.color.kaopeifix);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

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
        token = (String) SharedPreferencesUtils.getParam(KaoPeiFixActivity.this, "Token", "");
        //请求数据
        data(map, contentView);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);


        // 有时候用户直接点击第二三四，所以进来初始化一个
        firstName = fistArr.get(0).getName();
        secondName = secArr.get(0).getName();
        threeName = thrArr.get(0).getName();
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
                    groupData1.clear();
                    expandableAdapter.notifyDataSetChanged();
                    firstProBean = new FirstProBean("暂无数据", "暂无数据", 0);
                    secArr.add(firstProBean);
                    String[] headers2 = {firstName, "暂无数据", "暂无数据", "暂无数据"};
                    dropDownMenu.setTabMenuViewTitle(Arrays.asList(headers2));
                    final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                    map.put("professionId", RequestBody.create(null, firstId + ""));

                    data(map, contentView);


                    //当只有一级的时候,二三四都设置一个暂无数据选择
                    CommonAdapter secAdapter = new CommonAdapter<FirstProBean>(KaoPeiFixActivity.this, R.layout.item_kaopai, secArr) {
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
                    thrView.setAdapter(new CommonAdapter<FirstProBean>(KaoPeiFixActivity.this, R.layout.item_kaopai, thrArr) {
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    });
                    fourView.setAdapter(new CommonAdapter<FirstProBean>(KaoPeiFixActivity.this, R.layout.item_kaopai, thrArr) {///
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    });
                    return;
                }


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
                    fourView.setAdapter(new CommonAdapter<FirstProBean>(KaoPeiFixActivity.this, R.layout.item_kaopai, fourArr) {
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    });
                }


            }
        });


        //第二个的监听
        secView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           /*     if(chooseItion<=2&&chooseItion!=0){
                    chooseItion=2;
                }else {
                    LToast.show(context,"请先选择专业");
                    return;
                }*/


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
                    groupData1.clear();
                    expandableAdapter.notifyDataSetChanged();
                    String[] headers1 = {firstName, secondName, "暂无数据", "暂无数据"};
                    dropDownMenu.setTabMenuViewTitle(Arrays.asList(headers1));
                    final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                    map.put("professionId", RequestBody.create(null, firstId + ""));
                    map.put("workId", RequestBody.create(null, secondId + ""));
                    data(map, contentView);
                    firstProBean = new FirstProBean("暂无数据", "暂无数据", 0);
                    thrArr.add(firstProBean);//--
                    CommonAdapter<FirstProBean> thadapter = new CommonAdapter<FirstProBean>(KaoPeiFixActivity.this, R.layout.item_kaopai, thrArr) {//-
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    };
                    thrView.setAdapter(thadapter);
                    thadapter.notifyDataSetChanged();
                    fourView.setAdapter(new CommonAdapter<FirstProBean>(KaoPeiFixActivity.this, R.layout.item_kaopai, thrArr) {//-
                        @Override
                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                            viewHolder.setText(R.id.first_text, item.getName());
                        }
                    });

                }

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
                CommonAdapter<FirstProBean> fuadapter = new CommonAdapter<FirstProBean>(KaoPeiFixActivity.this, R.layout.item_kaopai, fourArr) {
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
             /*   if(chooseItion<=3&&chooseItion!=0){
                    chooseItion=3;
                }else {
                    LToast.show(context,"请先选择职业");
                   return;
            }
*/

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
                    groupData1.clear();
                    expandableAdapter.notifyDataSetChanged();
                    String[] headers = {firstName, secondName, threeName, "暂无数据"};
                    dropDownMenu.setTabMenuViewTitle(Arrays.asList(headers));
                    final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                    map.put("professionId", RequestBody.create(null, firstId + ""));
                    map.put("workId", RequestBody.create(null, threeId + ""));
                    data(map, contentView);
                    firstProBean = new FirstProBean("暂无数据", "暂无数据", 0);
                    fourArr.add(firstProBean);
                    CommonAdapter<FirstProBean> lastfuadapter = new CommonAdapter<FirstProBean>(KaoPeiFixActivity.this, R.layout.item_kaopai, fourArr) {///0
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
             /*   if(chooseItion<=4&&chooseItion!=0){
                    chooseItion=4;
                }else {
                    LToast.show(context,"请先选择工种");
                    return;
                }*/


                groupData1.clear();
                expandableAdapter.notifyDataSetChanged();
                fourName = fourArr.get(position).getName();
                fourId = fourArr.get(position).getOriginalId() + "";
                dropDownMenu.setTabText(position == 0 ? headers[3] : fourArr.get(position).getName());
                dropDownMenu.closeMenu();
                final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                map.put("professionId", RequestBody.create(null, firstId + ""));
                map.put("workId", RequestBody.create(null, threeId + ""));
                map.put("rankId", RequestBody.create(null, fourId + ""));
                data(map, contentView);


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
                Intent intent1 = new Intent(KaoPeiFixActivity.this, WriteEnrolMsgActivity.class);
                intent1.putExtra("nextShowData", (Serializable) nextShowData);
                startActivity(intent1);


            }
        });

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    /*请求数据*/
    private void data(Map<String, RequestBody> map, final ExpandableListView contentView) {
        RetrofitService
                .getKaopei(map, token)
                .compose(KaoPeiFixActivity.this.<KaopeiFirstBean>bindToLife())
                .subscribe(new Subscriber<KaopeiFirstBean>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(KaopeiFirstBean kaopeiFirstBean) {
                        data.clear();
                        data = kaopeiFirstBean.getData();
                        int size = data.size();
                        GradeGroup1 gradeGroup = null;
                        for (int i = 0; i < size; i++) {
                            gradeGroup = new GradeGroup1(data.get(i).getExamName(), data.get(i).getExamId() + "", false, classListItems1);
                            groupData1.add(gradeGroup);
                        }
                        expandableAdapter = new ExpandableAdapter1(KaoPeiFixActivity.this, groupData1);
                        contentView.setAdapter(expandableAdapter);
                        contentView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                            @Override
                            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                                //  List<ClassListItem> childData = new ArrayList<>();
                                childData = new ArrayList<>();
                                String examID = groupData1.get(groupPosition).getId() + "";
                                getChildData(examID, childData, groupPosition);     //获取子菜单数据
                                groupData1.get(groupPosition).addchilddata(childData);   //加载子菜单数据
                                if (groupData1.get(groupPosition).checked) {
                                    for (int j = 0; j < groupData1.get(groupPosition).children.size(); j++)
                                        groupData1.get(groupPosition).children.get(j).checked = true;
                                }

                                return false;
                            }
                        });
                        contentView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                            @Override
                            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                                nextShowData.clear();
                                List<KaoPeiDetail.DataBean> dataBeen = sparseArray.get(groupPosition);
                                KaoPeiDetail.DataBean dataBean = dataBeen.get(0);
                                nextShowData.add(dataBean);
                                setItemChecked(groupPosition, childPosition);
                                return false;
                            }
                        });

                    }

                    @Override
                    public void onCompleted() {


                    }

                });
    }

    //设置子条目选中状态
    private void setItemChecked(int groupPosition, int childPosition) {
        if (contentView == null) {
            return;
        }
        this.mGroupPosition = groupPosition;
        this.mChildPosition = childPosition;
        int numberOfGroupThatIsOpened = 0;
        for (int i = 0; i < groupPosition; i++) {
            if (contentView.isGroupExpanded(i)) {
                numberOfGroupThatIsOpened += expandableAdapter.getChildrenCount(i);
            }
        }
        int position = numberOfGroupThatIsOpened + groupPosition
                + childPosition + 1;
        if (!contentView.isItemChecked(position)) {
            contentView.setItemChecked(position, true);
        }
    }


    //请求子条目的数据
    private void getChildData(final String examId, final List<KaoPeiDetail.DataBean> childData, final int groupid) {
        RetrofitService.getKaoPerDetail(examId, token).subscribe(new Subscriber<KaoPeiDetail>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(KaoPeiDetail kaoPeiDetail) {
                childData.clear();
                List<KaoPeiDetail.DataBean> data = kaoPeiDetail.getData();
                KaoPeiDetail.DataBean dataBean = data.get(0);
                //   ClassListItem item = new ClassListItem(dataBean.getExamName(), dataBean.getCreateDate(), dataBean.getExamStartTime(), dataBean.getExamEndTime(), dataBean.getExamTarget(), dataBean.getTrainModel(), false);
                childData.add(dataBean);
                sparseArray.put(groupid, data);
                expandableAdapter.notifyDataSetChanged();


            }
        });
    }

}
