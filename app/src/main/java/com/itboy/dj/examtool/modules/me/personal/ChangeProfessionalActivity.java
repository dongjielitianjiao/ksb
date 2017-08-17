package com.itboy.dj.examtool.modules.me.personal;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.FirstProBean;
import com.itboy.dj.examtool.api.bean.ProfessionalBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.MyGridView;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ChangeProfessionalActivity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.first_profess)
    MyGridView firstProfess;
    @BindView(R.id.second_profess)
    MyGridView secondProfess;
    @BindView(R.id.third_profess)
    MyGridView thirdProfess;
    @BindView(R.id.four_profess)
    MyGridView fourProfess;
    @BindView(R.id.save)
    Button save;

    private static List<FirstProBean> firstProBeens = new ArrayList<>();
    private static List<FirstProBean> secondProBeens = new ArrayList<>();
    private static List<FirstProBean> threeProBeens = new ArrayList<>();
    private static List<FirstProBean> fourProBeens = new ArrayList<>();
    private static List<FirstProBean> threeBens = new ArrayList<>();
    private static List<FirstProBean> fourBens = new ArrayList<>();


    private String firstName, firstId;
    private String secondName, sencondId;
    private String threeName, threeId;
    private String fourName, fourId;
    public static List<ProfessionalBean.DataBean> AlldataBeen = new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_change_professional;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        baseTitleName.setText("考试报名");
        final String token = (String) SharedPreferencesUtils.getParam(ChangeProfessionalActivity.this, "Token", "");
        RetrofitService.getPrfessional("", token)
                .observeOn(Schedulers.io())
                .flatMap(new Func1<ProfessionalBean, Observable<ProfessionalBean.DataBean>>() {
                    @Override
                    public Observable<ProfessionalBean.DataBean> call(ProfessionalBean professionalBean) {
                        return Observable.from(professionalBean.getData());
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<List<ProfessionalBean.DataBean>>(ChangeProfessionalActivity.this) {
                    @Override
                    protected void _onNext(final List<ProfessionalBean.DataBean> dataBeen) {

                        firstProBeens.clear();
                        secondProBeens.clear();
                        threeProBeens.clear();
                        fourProBeens.clear();

                        Iterator it = dataBeen.iterator();
                        while (it.hasNext()) {
                            ProfessionalBean.DataBean next = (ProfessionalBean.DataBean) it.next();
                            AlldataBeen.add(next);
                        }


                        final int size = dataBeen.size();

                        //专业--------------------------------------------------------------
                        try {
                            FirstProBean firstProBean = null;
                            for (int i = 0; i < size; i++) {
                                String type = dataBeen.get(i).getType();
                                String id = dataBeen.get(i).getId();
                                int originalId = dataBeen.get(i).getOriginalId();
                                String name = dataBeen.get(i).getName();
                                if ("profession".equals(type)) {  //选择
                                    firstProBean = new FirstProBean(name, id, originalId);
                                    firstProBeens.add(firstProBean);
                                }
                            }
                            //MOREN
                            firstName = firstProBeens.get(0).getName();
                            firstId = firstProBeens.get(0).getOriginalId() + "";// 更改
                            firstProfess.setAdapter(new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, firstProBeens) {
                                @Override
                                protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                    viewHolder.setText(R.id.sales_tx, item.getName());
                                }
                            });
                            firstProfess.setItemChecked(0, true);
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }


                        //职业默认展示第一个 -------------------------------------------
                        try {
                            final String firstid = firstProBeens.get(0).getId();
                            FirstProBean firstProBean1 = null;
                            for (int i = 0; i < size; i++) {
                                String stype = dataBeen.get(i).getType();
                                String sid = dataBeen.get(i).getId();
                                int originalId = dataBeen.get(i).getOriginalId();
                                String pid = (String) dataBeen.get(i).getPid();
                                String sname = dataBeen.get(i).getName();
                                if ("work".equals(stype) && firstid.equals(pid)) {  //筛选出来第二个
                                    firstProBean1 = new FirstProBean(sname, sid, originalId);
                                    secondProBeens.add(firstProBean1);
                                }
                            }
                            //MOREN
                            secondName = secondProBeens.get(0).getName();
                            sencondId = secondProBeens.get(0).getOriginalId() + ""; //

                            secondProfess.setAdapter(new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, secondProBeens) {
                                @Override
                                protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                    viewHolder.setText(R.id.sales_tx, item.getName());
                                }

                            });
                            secondProfess.setItemChecked(0, true);

                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();

                        }


                        //工种默认选择第一个

                        try {
                            String secondid = secondProBeens.get(0).getId();
                            FirstProBean firstProBean2 = null;
                            for (int i = 0; i < size; i++) {
                                String ttype = dataBeen.get(i).getType();
                                String tid = dataBeen.get(i).getId();
                                String tpid = (String) dataBeen.get(i).getPid();
                                String tname = dataBeen.get(i).getName();
                                int originalId = dataBeen.get(i).getOriginalId();
                                if ("work".equals(ttype) && secondid.equals(tpid)) {  //筛选出来第三个
                                    firstProBean2 = new FirstProBean(tname, tid, originalId);
                                    threeProBeens.add(firstProBean2);//.............
                                    threeBens.add(firstProBean2);
                                }
                            }
                            //MOREN
                            threeName = threeProBeens.get(0).getName();//......................
                            threeId = threeProBeens.get(0).getOriginalId() + "";//更改//..................................


                            thirdProfess.setAdapter(new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, threeProBeens) {
                                @Override
                                protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                    viewHolder.setText(R.id.sales_tx, item.getName());
                                }
                            });
                            thirdProfess.setItemChecked(0, true);
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();

                        }
                        //等级默认设置第一个 ----------------------------------------------------------
                        try {
                            String threeid = threeProBeens.get(0).getId(); //............................

                            FirstProBean firstProBean3 = null;
                            for (int i = 0; i < size; i++) {
                                String ttype = dataBeen.get(i).getType();
                                String tid = dataBeen.get(i).getId();
                                String tpid = (String) dataBeen.get(i).getPid();
                                int originalId = dataBeen.get(i).getOriginalId();
                                String tname = dataBeen.get(i).getName();
                                if ("rank".equals(ttype) && threeid.equals(tpid)) {  //筛选出来第三个
                                    firstProBean3 = new FirstProBean(tname, tid, originalId);
                                    fourProBeens.add(firstProBean3);
                                    fourBens.add(firstProBean3);
                                }

                            }
                            //MOREN
                            if (fourProBeens.size() == 0) {
                                fourId = "" + 0;

                                fourName = "暂无数据";
                            } else {
                                fourId = fourProBeens.get(0).getId();
                                fourName = fourProBeens.get(0).getName();
                            }


                            fourProfess.setAdapter(new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, fourProBeens) {
                                @Override
                                protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                    viewHolder.setText(R.id.sales_tx, item.getName());
                                }
                            });
                            fourProfess.setItemChecked(0, true);
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();

                        }

                        //  点击事件的处理
                        firstProfess.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                final String firstid = firstProBeens.get(position).getId();
                                firstName = firstProBeens.get(position).getName();
                                firstId = firstProBeens.get(position).getOriginalId() + "";//更改
                                FirstProBean firstProBean = null;
                                secondProBeens.clear();
                                for (int i = 0; i < size; i++) {
                                    String stype = dataBeen.get(i).getType();
                                    String sid = dataBeen.get(i).getId();
                                    int originalId = dataBeen.get(i).getOriginalId();
                                    String pid = (String) dataBeen.get(i).getPid();
                                    String sname = dataBeen.get(i).getName();
                                    if ("work".equals(stype) && firstid.equals(pid)) {  //筛选出来第二个
                                        firstProBean = new FirstProBean(sname, sid, originalId);
                                        secondProBeens.add(firstProBean);
                                    }
                                }
                                if (secondProBeens.size() == 0) {
                                    firstProBean = new FirstProBean("暂无数据", "暂无数据", 0);
                                    secondProBeens.add(firstProBean);
                                }
                                secondName = secondProBeens.get(0).getName();
                                sencondId = secondProBeens.get(0).getOriginalId() + "";//更改


                                CommonAdapter<FirstProBean> secondAdapter = new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, secondProBeens) {
                                    @Override
                                    protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                        viewHolder.setText(R.id.sales_tx, item.getName());
                                    }
                                };
                                secondProfess.setAdapter(secondAdapter);
                                secondAdapter.notifyDataSetChanged();
                                secondProfess.setItemChecked(0, true);

                                //当第二个无数据时，不用判断3和4 了
                                if (secondProBeens.size() == 1 && "暂无数据".equals(secondProBeens.get(0).getName())) {
                                    secondName = "暂无数据";
                                    sencondId = 0 + ""; //更改
                                    threeName = "暂无数据";
                                    threeId = 0 + "";
                                    fourName = "暂无数据";
                                    fourId = 0 + "";
                                    thirdProfess.setAdapter(new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, secondProBeens) {
                                        @Override
                                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                            viewHolder.setText(R.id.sales_tx, item.getName());
                                        }
                                    });

                                    thirdProfess.setItemChecked(0, true);
                                    fourProfess.setAdapter(new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, secondProBeens) {
                                        @Override
                                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                            viewHolder.setText(R.id.sales_tx, item.getName());
                                        }
                                    });
                                    fourProfess.setItemChecked(0, true);

                                    return;

                                }


                                //工种默认选择第一个
                                threeBens = new ArrayList<FirstProBean>();
                                try {
                                    String secondid = secondProBeens.get(0).getId();
                                    FirstProBean firstProBean2 = null;
                                    threeBens.clear();///
                                    for (int i = 0; i < size; i++) {
                                        String ttype = dataBeen.get(i).getType();
                                        String tid = dataBeen.get(i).getId();
                                        String tpid = (String) dataBeen.get(i).getPid();
                                        int originalId = dataBeen.get(i).getOriginalId();
                                        String tname = dataBeen.get(i).getName();
                                        if ("work".equals(ttype) && secondid.equals(tpid)) {  //筛选出来第三个
                                            firstProBean2 = new FirstProBean(tname, tid, originalId);
                                            threeBens.add(firstProBean2);///
                                        }
                                    }
                                    threeName = threeBens.get(0).getName();///
                                    threeId = threeBens.get(0).getOriginalId() + "";//genggai

                                    if (threeBens.size() == 0) {///
                                        firstProBean2 = new FirstProBean("暂无数据", "暂无数据", 0);
                                        threeBens.add(firstProBean2);///
                                    }
                                    CommonAdapter threeAdapter = new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, threeBens) {
                                        @Override
                                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                            viewHolder.setText(R.id.sales_tx, item.getName());
                                        }
                                    };
                                    threeAdapter.notifyDataSetChanged();
                                    thirdProfess.setAdapter(threeAdapter);
                                    thirdProfess.setItemChecked(0, true);

                                } catch (IndexOutOfBoundsException e) {

                                }
                                //如果第三个没有数据，则不用判断第四个了
                                if (threeBens.size() == 1 && "暂无数据".equals(threeBens.get(0).getName())) {///
                                    threeName = "暂无数据";
                                    threeId = "" + 0;
                                    fourName = "暂无数据";
                                    fourId = 0 + "";
                                    fourProfess.setAdapter(new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, threeBens) {///
                                        @Override
                                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                            viewHolder.setText(R.id.sales_tx, item.getName());
                                        }
                                    });
                                    fourProfess.setItemChecked(0, true);
                                    return;
                                }


                                //等级默认设置第一个 ----------------------------------------------------------
                                // CommonAdapter fourAdapter = null;

                                try {
                                    String threeid = threeBens.get(0).getId(); ///XXXXXXXXXX
                                    FirstProBean firstProBean3 = null;
                                    ///4
                                    fourBens = new ArrayList<FirstProBean>();
                                    fourBens.clear();

                                    for (int i = 0; i < size; i++) {
                                        String ttype = dataBeen.get(i).getType();
                                        String tid = dataBeen.get(i).getId();
                                        String tpid = (String) dataBeen.get(i).getPid();
                                        String tname = dataBeen.get(i).getName();
                                        int originalId = dataBeen.get(i).getOriginalId();
                                        if ("rank".equals(ttype) && threeid.equals(tpid)) {  //筛选出来第三个
                                            firstProBean3 = new FirstProBean(tname, tid, originalId);
                                            fourBens.add(firstProBean3); ///4
                                        }
                                    }
                                    if (fourBens.size() == 0) { ///4
                                        firstProBean3 = new FirstProBean("暂无数据", "暂无数据", 0);
                                        fourBens.add(firstProBean3); ///4
                                    }
                                    fourId = fourBens.get(0).getOriginalId() + "";///4
                                    fourName = fourBens.get(0).getName();///4
                                    CommonAdapter fourAdapter = new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, fourBens) {///4
                                        @Override
                                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                            viewHolder.setText(R.id.sales_tx, item.getName());
                                        }
                                    };
                                    if (fourBens.size() == 1 && fourBens.get(0).getName().equals("暂无数据")) {
                                        fourName = "暂无数据";
                                        fourId = "" + 0;
                                    }
                                    fourProfess.setAdapter(fourAdapter);
                                    fourAdapter.notifyDataSetChanged();
                                    fourProfess.setItemChecked(0, true);


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                //-------------------------------------------------------


                            }

                        });


                        secondProfess.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String sencondid = secondProBeens.get(position).getId();
                                secondName = secondProBeens.get(position).getName();
                                sencondId = secondProBeens.get(position).getOriginalId() + "";//更改

                                FirstProBean firstProBean = null;
                               /* List<FirstProBean> three1Bens = new ArrayList<FirstProBean>();
                                three1Bens.clear();
                                //   threeProBeens.clear();//---*/

                                threeBens = new ArrayList<FirstProBean>();
                                threeBens.clear();
                                for (int i = 0; i < size; i++) {
                                    String ttype = dataBeen.get(i).getType();
                                    String tid = dataBeen.get(i).getId();
                                    String tpid = (String) dataBeen.get(i).getPid();
                                    int originalId = dataBeen.get(i).getOriginalId();
                                    String tname = dataBeen.get(i).getName();
                                    if ("work".equals(ttype) && sencondid.equals(tpid)) {  //筛选出来第三个
                                        firstProBean = new FirstProBean(tname, tid, originalId);
                                        threeBens.add(firstProBean);//--
                                    }
                                }
                                if (threeBens.size() == 0) { //--
                                    firstProBean = new FirstProBean("暂无数据", "暂无数据", 0);
                                    threeBens.add(firstProBean);//--
                                }
                                threeName = threeBens.get(0).getName();//-
                                threeId = threeBens.get(0).getOriginalId() + "";//-
                                CommonAdapter<FirstProBean> thadapter = new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, threeBens) {//-
                                    @Override
                                    protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                        viewHolder.setText(R.id.sales_tx, item.getName());
                                    }
                                };
                                thirdProfess.setAdapter(thadapter);
                                thadapter.notifyDataSetChanged();
                                thirdProfess.setItemChecked(0, true);
                                //如果第三个没数据就不用判断后面的了
                                if (threeBens.size() == 1 && "暂无数据".equals(threeBens.get(0).getName())) {//-
                                    threeName = "暂无数据";
                                    threeId = "" + 0;
                                    fourName = "暂无数据";
                                    fourId = "" + 0;
                                    fourProfess.setAdapter(new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, threeBens) {//-
                                        @Override
                                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                            viewHolder.setText(R.id.sales_tx, item.getName());
                                        }
                                    });
                                    fourProfess.setItemChecked(0, true);
                                    return;
                                }


                                //等级默认设置第一个
                                try {
                                    String threeid = threeBens.get(0).getId();//-
                                    FirstProBean firstProBean3 = null;
                               /*     List<FirstProBean> four11Bens = new ArrayList<FirstProBean>();
                                    four11Bens.clear();
                                    //fourProBeens.clear();*/
                                    fourBens = new ArrayList<FirstProBean>();
                                    fourBens.clear();
                                    for (int i = 0; i < size; i++) {
                                        String ttype = dataBeen.get(i).getType();
                                        String tid = dataBeen.get(i).getId();
                                        String tpid = (String) dataBeen.get(i).getPid();
                                        int originalId = dataBeen.get(i).getOriginalId();
                                        String tname = dataBeen.get(i).getName();
                                        if ("rank".equals(ttype) && threeid.equals(tpid)) {  //筛选出来第三个
                                            firstProBean3 = new FirstProBean(tname, tid, originalId);
                                            fourBens.add(firstProBean3); //-----1
                                        }
                                    }
                                    if (fourBens.size() == 0) { ////--1
                                        firstProBean3 = new FirstProBean("暂无数据", "暂无数据", 0);
                                        fourBens.add(firstProBean3); ////---1
                                    }
                                    fourId = fourBens.get(0).getOriginalId() + ""; ////-1
                                    fourName = fourBens.get(0).getName();/////-1
                                    CommonAdapter<FirstProBean> fuadapter = new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, fourBens) {
                                        @Override
                                        protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                            viewHolder.setText(R.id.sales_tx, item.getName());
                                        }
                                    };
                                    fourProfess.setAdapter(fuadapter);
                                    fuadapter.notifyDataSetChanged();
                                    fourProfess.setItemChecked(0, true);


                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                }


                            }
                        });


                        //   final List<FirstProBean> lastfourbeans = new ArrayList<FirstProBean>();

                        thirdProfess.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // String threeid = threeProBeens.get(position).getId();
                                String threeid = threeBens.get(position).getId();
                                FirstProBean firstProBean = null;
                                //  lastfourbeans.clear();
                                //  fourProBeens.clear();
                                fourBens = new ArrayList<FirstProBean>();
                                fourBens.clear();
                                for (int i = 0; i < size; i++) {
                                    String ftype = dataBeen.get(i).getType();
                                    String fid = dataBeen.get(i).getId();
                                    String fpid = (String) dataBeen.get(i).getPid();
                                    int originalId = dataBeen.get(i).getOriginalId();
                                    String fname = dataBeen.get(i).getName();
                                    if ("rank".equals(ftype) && threeid.equals(fpid)) {  //筛选出来第三个
                                        firstProBean = new FirstProBean(fname, fid, originalId);
                                        fourBens.add(firstProBean);//////0
                                    }
                                }
                                if (fourBens.size() == 0) { /////0
                                    firstProBean = new FirstProBean("暂无数据", "暂无数据", 0);
                                    fourBens.add(firstProBean);/////0
                                }

                                threeName = threeBens.get(position).getName(); ///0
                                threeId = threeBens.get(position).getOriginalId() + "";//genggai
                                fourId = fourBens.get(0).getOriginalId() + "";//genggai
                                fourName = fourBens.get(0).getName();////0
                                CommonAdapter<FirstProBean> lastfuadapter = new CommonAdapter<FirstProBean>(ChangeProfessionalActivity.this, R.layout.item_first_profe, fourBens) {///0
                                    @Override
                                    protected void convert(ViewHolder viewHolder, FirstProBean item, int position) {
                                        viewHolder.setText(R.id.sales_tx, item.getName());
                                    }
                                };

                                fourProfess.setAdapter(lastfuadapter);
                                lastfuadapter.notifyDataSetChanged();
                                fourProfess.setItemChecked(0, true);
                            }
                        });

                        fourProfess.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                fourId = fourBens.get(position).getOriginalId() + "";
                                fourName = fourBens.get(position).getName();
                            }
                        });


                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });


    }

    @Override
    protected void updateViews(boolean isRefresh) {


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  RetrofitService.changeProfessional()

                Intent intent = new Intent();
                intent.putExtra("ftid", firstId);
                intent.putExtra("ftnm", firstName);
                intent.putExtra("secid", sencondId);
                intent.putExtra("secnm", secondName);
                intent.putExtra("thrid", threeId);
                intent.putExtra("thrnm", threeName);
                intent.putExtra("fourid", fourId);
                intent.putExtra("fournm", fourName);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }


}
