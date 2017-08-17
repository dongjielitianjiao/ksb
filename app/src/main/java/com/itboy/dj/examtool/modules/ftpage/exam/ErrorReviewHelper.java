package com.itboy.dj.examtool.modules.ftpage.exam;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
//错题工具类
public class ErrorReviewHelper {

    private ErrorReviewBean errorReviewBean;
    private Context mContext;
    //答案
    private static List<ErrorReviewBean.DataBean.ItemsBean.QuestionOptionsBean> questionOptionsBeen = new ArrayList<>();

    private List<String> ABCS = new ArrayList<>();
    private List<Integer> answers = new ArrayList<>();
    private List<String> fillBankleranswers = new ArrayList<>();

    public ErrorReviewHelper(ErrorReviewBean errorReviewBean, Context context) {
        this.errorReviewBean = errorReviewBean;
        this.mContext = context;
    }

    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split(","); //拆分字符为"/" ,然后把结果交给数组strArray
        return strArray;
    }

    private List<ErrorReviewBean.DataBean.ItemsBean.QuestionOptionsBean> getQuestionOptionsBeen(int post, String logo, String type) {
        List<ErrorReviewBean.DataBean.ItemsBean> items = errorReviewBean.getData().getItems();
        List<ErrorReviewBean.DataBean.ItemsBean.QuestionOptionsBean> list = new ArrayList<>();
        list = items.get(post).getQuestionOptions();
        questionOptionsBeen = list;

        ABCS.clear();
        //生成ABCDE集合
        if ("multi".equals(logo)) {
            int m = 64;
            for (int i = 0; i < questionOptionsBeen.size(); i++) {
                m++;
                char c = (char) m;
                ABCS.add(c + "");

            }
        }

       //填充选择了的答案
        if ("nofillbank".equals(type)) {
            answers.clear();
            ErrorReviewBean.DataBean.ItemsBean itemsBean = items.get(post);
            String answer = itemsBean.getAnswer();

            if ("未选择".equals(answer)) {

            } else {
                String[] strings = convertStrToArray(answer);
                List<String> list1 = Arrays.asList(strings);
                int size = list1.size();
                for (int i = 0; i < size; i++) {
                    String s = list1.get(i);
                    int i1 = Integer.parseInt(s) - 1;
                    answers.add(i1);
                }
            }

        }else {
            fillBankleranswers.clear();
            ErrorReviewBean.DataBean.ItemsBean itemsBean = items.get(post);
            String fillBankStr = itemsBean.getAnswer();
            Double fillBlankNumber = (Double) itemsBean.getFillBlankNumber();
            int i2 = fillBlankNumber.intValue();
            if ("未选择".equals(fillBankStr)) {
                for (int i1 = 0; i1 < i2; i1++) {
                    fillBankleranswers.add("未填写");
                }
            }else {
                String[] strings = convertStrToArray(fillBankStr);
                List<String> list1 = Arrays.asList(strings);
                int size = list1.size();
                for (int i = 0; i < size; i++) {
                    String s = list1.get(i);
                    fillBankleranswers.add(s);
                }
            }


        }


        return questionOptionsBeen;
    }

    public void ShowError(ViewGroup container, int PosTion) {
        String tag = errorReviewBean.getData().getItems().get(PosTion).getObjectiveType();
        container.removeAllViews();
        LayoutInflater from = LayoutInflater.from(container.getContext());
        ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = null;
        switch (tag) {
            case "single":
                getQuestionOptionsBeen(PosTion, "multi", "nofillbank");
                view = from.inflate(R.layout.error_exam_single_redo, null);
                view.setLayoutParams(params1);

                TextView question = (TextView) view.findViewById(R.id.questi);
                List<ErrorReviewBean.DataBean.ItemsBean> items = errorReviewBean.getData().getItems();

                String text = items.get(PosTion).getText();//问题
                question.setText(text);
                RecyclerView recyclerViewSingle = (RecyclerView) view.findViewById(R.id.single_rec);
                LinearLayoutManager layoutManagerSingle = new LinearLayoutManager(container.getContext());
                recyclerViewSingle.setLayoutManager(layoutManagerSingle);
                final SingAdapter singAdapter = new SingAdapter();
                recyclerViewSingle.setAdapter(singAdapter);
                singAdapter.setSeclect(answers);

                ErrorReviewBean.DataBean.ItemsBean itemsBean = errorReviewBean.getData().getItems().get(PosTion);
                String rightAnswer = itemsBean.getRightAnswer();
                TextView rightText = (TextView) view.findViewById(R.id.answer);
                rightText.setText(rightAnswer);


                container.addView(view);
                break;
            case "judgement":
                getQuestionOptionsBeen(PosTion, "multi", "nofillbank");
                view = from.inflate(R.layout.error_exam_judge_redo, null);
                view.setLayoutParams(params1);
                TextView question1 = (TextView) view.findViewById(R.id.questi);
                question1.setText(errorReviewBean.getData().getItems().get(PosTion).getText());
                RecyclerView recyclerViewJudge = (RecyclerView) view.findViewById(R.id.judge_rec);

                LinearLayoutManager layoutManagerJudge = new LinearLayoutManager(container.getContext());
                recyclerViewJudge.setLayoutManager(layoutManagerJudge);
                final JudgeAdapter judgeAdapter = new JudgeAdapter();
                recyclerViewJudge.setAdapter(judgeAdapter);
                judgeAdapter.setSeclect(answers);

                ErrorReviewBean.DataBean.ItemsBean itemsBean1 = errorReviewBean.getData().getItems().get(PosTion);
                String rightAnswer1 = itemsBean1.getRightAnswer();
                TextView rightText1 = (TextView) view.findViewById(R.id.answer);
                rightText1.setText(rightAnswer1);
                container.addView(view);

                break;
            case "multi":
                getQuestionOptionsBeen(PosTion, "multi", "nofillbank");
                view = from.inflate(R.layout.error_exam_muti, null);
                view.setLayoutParams(params1);
                TextView question2 = (TextView) view.findViewById(R.id.questi);
                question2.setText(errorReviewBean.getData().getItems().get(PosTion).getText());

                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.muti_rec);
                LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
                recyclerView.setLayoutManager(layoutManager);
                MutiAdapter mutiAdapter = new MutiAdapter();
                recyclerView.setAdapter(mutiAdapter);
                mutiAdapter.setSeclect(answers);

                ErrorReviewBean.DataBean.ItemsBean itemsBean2 = errorReviewBean.getData().getItems().get(PosTion);
                String rightAnswer2 = itemsBean2.getRightAnswer();
                TextView rightText2 = (TextView) view.findViewById(R.id.answer);
                rightText2.setText(rightAnswer2);
                container.addView(view);
                break;
            case "fill_blank":
                getQuestionOptionsBeen(PosTion, "multi", "fillbank");
                view = from.inflate(R.layout.error_exam_fillbank, null);
                view.setLayoutParams(params1);
                TextView question3 = (TextView) view.findViewById(R.id.questi);
                question3.setText(errorReviewBean.getData().getItems().get(PosTion).getText());
                final RecyclerView fillblankRec = (RecyclerView) view.findViewById(R.id.fll_rec);
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(container.getContext());
                fillblankRec.setLayoutManager(layoutManager1);
                fillblankRec.setAdapter(new FillBankAdapter());
                ErrorReviewBean.DataBean.ItemsBean itemsBean3 = errorReviewBean.getData().getItems().get(PosTion);
                String rightAnswer3 = itemsBean3.getRightAnswer();
                TextView rightText3 = (TextView) view.findViewById(R.id.answer);
                rightText3.setText(rightAnswer3);
                container.addView(view);
                break;


        }
    }


    class SingAdapter extends BaseQuickAdapter<ErrorReviewBean.DataBean.ItemsBean.QuestionOptionsBean, BaseViewHolder> {


        private List<Integer> checkItemPosition = new ArrayList<>();

        public void setSeclect(List<Integer> pos) {
            checkItemPosition = pos;
            notifyDataSetChanged();
        }

        public SingAdapter() {
            super(R.layout.error_exam_single_item, questionOptionsBeen);
        }

        @Override
        protected void convert(final BaseViewHolder helper, ErrorReviewBean.DataBean.ItemsBean.QuestionOptionsBean item) {


            final int position = helper.getAdapterPosition();
            helper.setText(R.id.exam_choose_text, ABCS.get(position));
            helper.setText(R.id.single_text, questionOptionsBeen.get(position).getText());
            final TextView iv = helper.getView(R.id.exam_choose_text);

            for (int i = 0; i < checkItemPosition.size(); i++) {
                if (checkItemPosition.get(i) == position) {
                    iv.setSelected(true);
                }
            }


        }


    }

    //判断
    class JudgeAdapter extends BaseQuickAdapter<ErrorReviewBean.DataBean.ItemsBean.QuestionOptionsBean, BaseViewHolder> {

        private List<String> yesOrNo = new ArrayList<>(Arrays.asList("是", "否"));

        private List<Integer> checkItemPosition = new ArrayList<>();

        public void setSeclect(List<Integer> pos) {
            checkItemPosition = pos;
            notifyDataSetChanged();
        }

        public JudgeAdapter() {
            super(R.layout.error_exam_single_item, questionOptionsBeen);
        }

        @Override
        protected void convert(final BaseViewHolder helper, ErrorReviewBean.DataBean.ItemsBean.QuestionOptionsBean item) {

            final int position = helper.getAdapterPosition();
            helper.setText(R.id.exam_choose_text, ABCS.get(position));
            helper.setText(R.id.single_text, yesOrNo.get(position));
            final TextView iv = helper.getView(R.id.exam_choose_text);
            for (int i = 0; i < checkItemPosition.size(); i++) {
                if (checkItemPosition.get(i) == position) {
                    iv.setSelected(true);
                }
            }

        }


    }

    //多选的答案展示
    class MutiAdapter extends BaseQuickAdapter<ErrorReviewBean.DataBean.ItemsBean.QuestionOptionsBean, BaseViewHolder> {
        private List<Integer> checkItemPosition = new ArrayList<>();

        public void setSeclect(List<Integer> pos) {
            checkItemPosition = pos;
            notifyDataSetChanged();
        }

        public MutiAdapter() {
            super(R.layout.exam_muti_item, questionOptionsBeen);
        }


        @Override
        protected void convert(BaseViewHolder helper, ErrorReviewBean.DataBean.ItemsBean.QuestionOptionsBean item) {

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
        }


    }

    //填空
    class FillBankAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public FillBankAdapter() {
            super(R.layout.error_exam_fillbank_item, fillBankleranswers);
        }


        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.fillbank, item);


        }

    }



}






















