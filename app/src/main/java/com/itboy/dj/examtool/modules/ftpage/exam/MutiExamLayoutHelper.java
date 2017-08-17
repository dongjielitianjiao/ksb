package com.itboy.dj.examtool.modules.ftpage.exam;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.rxbus.RxBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * //考试工具类
 */

public class MutiExamLayoutHelper {


    private ExamTypebean examTypebean;
    private Context mContext;

    //填空题
    private List<String> infos = new ArrayList<>();
    private List<String> hints = new ArrayList<>();
    private List<String> userWirte = new ArrayList<>();

    //每个选项对应的question
    private static List<ExamTypebean.DataBean.ItemsBean.QuestionOptionsBean> questionOptionsBeen = new ArrayList<>();


    private List<String> ABCS = new ArrayList<>();

    //保存答案
    public SparseArray<SaveExamBean> examsSparseArray = new SparseArray<>();

    public MutiExamLayoutHelper(ExamTypebean examTypebean, Context context) {
        this.examTypebean = examTypebean;
        this.mContext = context;
        initData(examTypebean);
    }

    //用户直接跳着走，有些位置则没有初始化，需要先默认给一个未选择的状态
    private void initData(ExamTypebean examTypebean) {
        List<ExamTypebean.DataBean.ItemsBean> items = examTypebean.getData().getItems();
        for (int i = 0; i < items.size(); i++) {
            int problemId = items.get(i).getProblemId();
            examsSparseArray.put(i, new SaveExamBean("未选择", problemId));
            RxBus.getDefault().postSticky(new NotDataChange("ok"));
        }
    }


    //得到/单选/多选/判断的问题
    private List<ExamTypebean.DataBean.ItemsBean.QuestionOptionsBean> getQuestion(int position, String logo) {
        List<ExamTypebean.DataBean.ItemsBean> items = examTypebean.getData().getItems();
        List<ExamTypebean.DataBean.ItemsBean.QuestionOptionsBean> list = new ArrayList<>();
        list = items.get(position).getQuestionOptions();
        questionOptionsBeen = list;
        ABCS.clear();
        //多选的话，需要动态添加选选项的标题
        if ("multi".equals(logo)) {
            int m = 64;
            for (int i = 0; i < questionOptionsBeen.size(); i++) {
                m++;
                char c = (char) m;
                ABCS.add(c + "");

            }
        }
        return questionOptionsBeen;
    }


    //得到填空的个数
    private void getFillBlankItemNums(int position) {
        Double fillBlankNumber = (Double) examTypebean.getData().getItems().get(position).getFillBlankNumber();
        int i = fillBlankNumber.intValue();
        infos.clear();
        hints.clear();
        for (int i1 = 0; i1 < i; i1++) {
            infos.add("答案" + (i1 + 1));
            hints.add("答案" + (i1 + 1));

        }

    }

    //前3个为单选/多选判断,第四个专门为了填空写的
    public void getViewAnddatas(ViewGroup container, final int ption, List<Integer> list, List<String> strs) {
        String tag = examTypebean.getData().getItems().get(ption).getObjectiveType();
        container.removeAllViews();
        LayoutInflater from = LayoutInflater.from(container.getContext());
        ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = null;
        //单选 多选 填空  判断
        switch (tag) {
            //单选
            case "single":
                getQuestion(ption, "multi");
                view = from.inflate(R.layout.exam_single_redo, null);
                view.setLayoutParams(params1);
                //问题
                TextView question = (TextView) view.findViewById(R.id.questi);
                String text = examTypebean.getData().getItems().get(ption).getText();//问题
                question.setText(text);


                //适配器
                RecyclerView recyclerViewSingle = (RecyclerView) view.findViewById(R.id.single_rec);
                LinearLayoutManager layoutManagerSingle = new LinearLayoutManager(container.getContext());
                recyclerViewSingle.setLayoutManager(layoutManagerSingle);
                final SingAdapter singAdapter = new SingAdapter();
                recyclerViewSingle.setAdapter(singAdapter);
                for (int i = 0; i < list.size(); i++) {
                    Integer integer = list.get(i);
                    singAdapter.setCheckItem(integer);
                }

     /*           final int problemid = examTypebean.getData().getItems().get(ption).getProblemId();
                SaveExamBean saveExamBean = new SaveExamBean("未选择", problemid);
                examsSparseArray.put(ption, saveExamBean);*/
                final int problemid = examTypebean.getData().getItems().get(ption).getProblemId();

                recyclerViewSingle.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        singAdapter.setCheckItem(position);

                        SaveExamBean saveExamBean = new SaveExamBean((position + 1) + "", problemid);
                        examsSparseArray.put(ption, saveExamBean);
                        RxBus.getDefault().postSticky(new NotDataChange("ok"));
                        mListener.onItemClick(ption, 0);
                    }
                });


                container.addView(view);
                break;
            //判断
            case "judgement":
                getQuestion(ption, "multi");
                view = from.inflate(R.layout.exam_judge_redo, null);
                view.setLayoutParams(params1);
                TextView question1 = (TextView) view.findViewById(R.id.questi);
                question1.setText(examTypebean.getData().getItems().get(ption).getText());
                RecyclerView recyclerViewJudge = (RecyclerView) view.findViewById(R.id.judge_rec);


                LinearLayoutManager layoutManagerJudge = new LinearLayoutManager(container.getContext());
                recyclerViewJudge.setLayoutManager(layoutManagerJudge);
                final JudgeAdapter judgeAdapter = new JudgeAdapter();
                recyclerViewJudge.setAdapter(judgeAdapter);
                for (int i = 0; i < list.size(); i++) {
                    Integer integer = list.get(i);
                    judgeAdapter.setCheckItem(integer);
                }
/*                final int problemid1 = examTypebean.getData().getItems().get(ption).getProblemId();
                SaveExamBean saveExamBean1 = new SaveExamBean("未选择", problemid1);
                examsSparseArray.put(ption, saveExamBean1);*/
                final int problemid1 = examTypebean.getData().getItems().get(ption).getProblemId();

                recyclerViewJudge.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        judgeAdapter.setCheckItem(position);

                        SaveExamBean saveExamBean = new SaveExamBean((position + 1) + "", problemid1);
                        examsSparseArray.put(ption, saveExamBean);
                        RxBus.getDefault().postSticky(new NotDataChange("ok"));
                        mListener.onItemClick(ption, 0);
                    }
                });


                container.addView(view);
                break;
            //多选
            case "multi":
                getQuestion(ption, "multi");
                view = from.inflate(R.layout.exam_muti, null);
                view.setLayoutParams(params1);
                TextView question2 = (TextView) view.findViewById(R.id.questi);
                question2.setText(examTypebean.getData().getItems().get(ption).getText());


                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.muti_rec);
                LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
                recyclerView.setLayoutManager(layoutManager);
                MutiAdapter mutiAdapter = new MutiAdapter();
                recyclerView.setAdapter(mutiAdapter);
                mutiAdapter.pos = ption;
                mutiAdapter.setSeclect(list);
                //默认给一个未选择的数据
       /*         final int problemid2 = examTypebean.getData().getItems().get(ption).getProblemId();
                SaveExamBean saveExamBean2 = new SaveExamBean("未选择", problemid2);
                examsSparseArray.put(ption, saveExamBean2);*/
                container.addView(view);
                break;
            //填空
            case "fill_blank":
                getFillBlankItemNums(ption);
                final Map<Integer, String> map = new HashMap<Integer, String>();
                final List<String> strings = new ArrayList<String>();
                view = from.inflate(R.layout.exam_fill_blank, null);
                view.setLayoutParams(params1);
                TextView question3 = (TextView) view.findViewById(R.id.questi);
                question3.setText(examTypebean.getData().getItems().get(ption).getText());


                final RecyclerView fillblankRec = (RecyclerView) view.findViewById(R.id.fill_blank_rec);
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(container.getContext());
                fillblankRec.setLayoutManager(layoutManager1);

           /*     final int problemid3 = examTypebean.getData().getItems().get(ption).getProblemId();
                SaveExamBean saveExamBean3 = new SaveExamBean("未选择", problemid3);
                examsSparseArray.put(ption, saveExamBean3);*/
                //  final int problemid3 = examTypebean.getData().getItems().get(ption).getProblemId();
                userWirte.clear();

                if (strs.get(0).equals("未选择")) {
                    int size = infos.size();
                    for (int i = 0; i < size; i++) {
                        userWirte.add("答案1");
                    }
                } else {
                    for (int i = 0; i < strs.size(); i++) {
                        userWirte.add(strs.get(i));
                    }
                }
                FillBlankAdapter fillBlankAdapter = new FillBlankAdapter(infos, hints, userWirte, ption, mContext);
                fillBlankAdapter.notifyDataSetChanged();

                RxBus.getDefault().toObservable(FillPostDataAndPos.class).subscribe(new Subscriber<FillPostDataAndPos>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FillPostDataAndPos mutiPostDataAndPos) {
                        strings.clear();
                        final int itemPt = mutiPostDataAndPos.getItemPt();
                        int posit = mutiPostDataAndPos.getPosit();
                        String data = mutiPostDataAndPos.getData();

                        map.put(posit, data);

                        for (Map.Entry<Integer, String> entruy : map.entrySet()) {
                            String value = entruy.getValue();
                            strings.add(value);
                        }
                        Collections.reverse(strings);


                        String flStr = listToString(strings, ',');

                        final int problemid4 = examTypebean.getData().getItems().get(itemPt).getProblemId();
                        SaveExamBean fillaveExamBean1 = new SaveExamBean(flStr, problemid4);
                        examsSparseArray.put(itemPt, fillaveExamBean1);
                        RxBus.getDefault().postSticky(new NotDataChange("ok"));
                        mListener.onItemClick(itemPt, 0);

                    }
                });


                fillBlankAdapter.setOnItemClickListener(new FillBlankAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int itemPostion, int position, String anString) {
                        if (TextUtils.isEmpty(anString)) {
                            RxBus.getDefault().postSticky(new FillPostDataAndPos(itemPostion, position, "未选择"));
                        } else {
                            //只会执行这个地方
                            RxBus.getDefault().postSticky(new FillPostDataAndPos(itemPostion, position, anString));

                        }


                    }
                });

                fillblankRec.setAdapter(fillBlankAdapter);
                container.addView(view);
                break;

        }


    }


    //单选
    class SingAdapter extends BaseQuickAdapter<ExamTypebean.DataBean.ItemsBean.QuestionOptionsBean, BaseViewHolder> {
        private int checkItemPosition = -1;

        public void setCheckItem(int position) {
            checkItemPosition = position;
            notifyDataSetChanged();

        }


        public SingAdapter() {
            super(R.layout.exam_single_item, questionOptionsBeen);
        }

        @Override
        protected void convert(final BaseViewHolder helper, ExamTypebean.DataBean.ItemsBean.QuestionOptionsBean item) {


            final int position = helper.getAdapterPosition();
            helper.setText(R.id.exam_choose_text, ABCS.get(position));
            helper.setText(R.id.single_text, questionOptionsBeen.get(position).getText());
            if (checkItemPosition != -1) {
                if (checkItemPosition == position) {
                    helper.setBackgroundRes(R.id.exam_choose_text, R.drawable.exam_choose_select);
                    helper.setTextColor(R.id.exam_choose_text, R.color.color_black);
                } else {
                    helper.setBackgroundRes(R.id.exam_choose_text, R.drawable.exam_choose_normal);
                    helper.setTextColor(R.id.exam_choose_text, R.color.color_white);
                }
            }


        }
    }

    //判断
    class JudgeAdapter extends BaseQuickAdapter<ExamTypebean.DataBean.ItemsBean.QuestionOptionsBean, BaseViewHolder> {
        private int checkItemPosition = -1;
        private List<String> yesOrNo = new ArrayList<>(Arrays.asList("是", "否"));

        public void setCheckItem(int position) {
            checkItemPosition = position;
            notifyDataSetChanged();
        }

        public JudgeAdapter() {
            super(R.layout.exam_single_item, questionOptionsBeen);
        }

        @Override
        protected void convert(final BaseViewHolder helper, ExamTypebean.DataBean.ItemsBean.QuestionOptionsBean item) {


            final int position = helper.getAdapterPosition();
            helper.setText(R.id.exam_choose_text, ABCS.get(position));
            helper.setText(R.id.single_text, yesOrNo.get(position));
            if (checkItemPosition != -1) {
                if (checkItemPosition == position) {
                    helper.setBackgroundRes(R.id.exam_choose_text, R.drawable.exam_choose_select);
                    helper.setTextColor(R.id.exam_choose_text, R.color.color_black);
                } else {
                    helper.setBackgroundRes(R.id.exam_choose_text, R.drawable.exam_choose_normal);
                    helper.setTextColor(R.id.exam_choose_text, R.color.color_white);
                }
            }


        }


    }


    //多选的答案展示
    class MutiAdapter extends BaseQuickAdapter<ExamTypebean.DataBean.ItemsBean.QuestionOptionsBean, BaseViewHolder> {
        public int pos = 0;
        final List<String> strings = new ArrayList<>();
        private List<Integer> checkItemPosition = new ArrayList<>();

        public void setSeclect(List<Integer> pos) {
            checkItemPosition = pos;
            notifyDataSetChanged();
        }

        public MutiAdapter() {
            super(R.layout.exam_muti_item, questionOptionsBeen);
        }


        @Override
        protected void convert(BaseViewHolder helper, ExamTypebean.DataBean.ItemsBean.QuestionOptionsBean item) {

            helper.setText(R.id.muti_text, item.getText());

            final int position = helper.getAdapterPosition();
            helper.setText(R.id.exam_choose_text, ABCS.get(position));
            helper.setText(R.id.muti_text, questionOptionsBeen.get(position).getText());

            final TextView iv = helper.getView(R.id.exam_choose_text);
            for (int i = 0; i < checkItemPosition.size(); i++) {
                if (checkItemPosition.get(i) == position) {
                    iv.setSelected(true);
                }
            }
            final int problemId = examTypebean.getData().getItems().get(pos).getProblemId();

            //做数据的处理操作
            final List<SaveExamBean> saveExamBeen11 = new ArrayList<SaveExamBean>();
            if (iv.isSelected()) {
                SaveExamBean saveExamBean1 = new SaveExamBean((position + 1) + "", problemId);
                saveExamBeen11.add(saveExamBean1);
                for (int i = 0; i < saveExamBeen11.size(); i++) {
                    strings.add(saveExamBeen11.get(i).getAnswer());
                }

            }

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SaveExamBean saveExamBean1 = null;
                    if (iv.isSelected()) {
                        iv.setSelected(false);
                        Iterator<String> iterator = strings.iterator();
                        while (iterator.hasNext()) {
                            String next = iterator.next();
                            String s = (position + 1) + "";
                            if (s.equals(next)) {
                                iterator.remove();
                            }
                        }
                    } else {
                        iv.setSelected(true);
                        saveExamBean1 = new SaveExamBean((position + 1) + "", problemId);
                        saveExamBeen11.add(saveExamBean1);
                        strings.add(saveExamBeen11.get(0).getAnswer());
                    }

                    SaveExamBean mutiSaveExamBean1 = null;
                    if (strings.size() > 0) {
                        String mutiStr = listToString(strings, ',');
                        Log.d("MUTI", mutiStr);
                        mutiSaveExamBean1 = new SaveExamBean(mutiStr, problemId);
                        examsSparseArray.put(pos, mutiSaveExamBean1);
                        mListener.onItemClick(pos, 0);
                    } else {
                        mutiSaveExamBean1 = new SaveExamBean("未选择", problemId);
                        examsSparseArray.put(pos, mutiSaveExamBean1);
                        mListener.onItemClick(pos, -1);
                    }

                    //写在这里可能有问题（如果写在外贸就）
                    RxBus.getDefault().postSticky(new NotDataChange("ok"));

                }
            });


        }


    }


    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }


    //通着popwindow更新
    private OnPostClickListener mListener;

    public void setOnPositionClickListener(OnPostClickListener listener) {
        this.mListener = listener;
    }

    public interface OnPostClickListener {
        void onItemClick(int postWind, int tag);
    }


}
