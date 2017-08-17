package com.itboy.dj.examtool.modules.ftpage.exam;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.google.gson.Gson;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.SubmitAnswers;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;
import com.itboy.dj.examtool.widget.popwindow.ChooseExamWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import okhttp3.RequestBody;
import rx.Subscriber;

public class ExamActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.exam_which)
    TextView examWhich;
    @BindView(R.id.exam_total)
    TextView examTotal;
    @BindView(R.id.choose_test_rt)
    RelativeLayout chooseTestRt;
    @BindView(R.id.qwe)
    RelativeLayout qwe;
    @BindView(R.id.time_show)
    TextView timeView;
    @BindView(R.id.submit_exam)
    TextView submitExam;
    @BindView(R.id.next_question)
    Button nextQuestion;
    @BindView(R.id.container)
    LinearLayout container;

    //用户手动选择
    private ChooseExamWindow popupWindow;
    private List<String> list = new ArrayList<>();

    //考试工具类
    private MutiExamLayoutHelper mutiExamLayoutHelper;

    //转换为上传的答案
    private List<SubmitAnswers> submitAnswerses = new ArrayList<>();
    public SparseArray<SaveExamBean> examsSparseArray1 = new SparseArray<>();
    private ExamTypebean itemsBeen = new ExamTypebean();

    //查看是否写完
    public SparseArray<Integer> finishNums = new SparseArray<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int index = (int) msg.obj;
            examWhich.setText(list.get(index));
            String which = examWhich.getText().toString();
            int chooseWhich = Integer.parseInt(which) - 1;
            SaveExamBean saveExamBean = examsSparseArray1.get(chooseWhich); //拿到当前位置的答案
            String answer = saveExamBean.getAnswer();

            String objectiveType = itemsBeen.getData().getItems().get(chooseWhich).getObjectiveType();
            switch (objectiveType) {
                case "single":
                    //如果是未选择什么也不用做
                    if ("未选择".equals(answer)) {

                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(-1), Arrays.asList("未选择"));
                    } else {
                        int i = Integer.parseInt(answer) - 1;
                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(i), Arrays.asList("未选择"));
                    }

                    break;
                case "judgement":
                    if ("未选择".equals(answer)) {
                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(-1), Arrays.asList("未选择"));
                    } else {
                        int i = Integer.parseInt(answer) - 1;
                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(i), Arrays.asList("未选择"));
                    }
                    break;
                case "multi":
                    if ("未选择".equals(answer)) {
                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(-1), Arrays.asList("未选择"));
                    } else {

                        String[] strings = convertStrToArray(answer);
                        List<String> list1 = Arrays.asList(strings);
                        List<Integer> list2 = new ArrayList<>();
                        int size = list1.size();
                        for (int i = 0; i < size; i++) {
                            String s = list1.get(i);
                            int i1 = Integer.parseInt(s) - 1;
                            list2.add(i1);

                        }

                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, list2, Arrays.asList("未选择"));

                    }
                    break;
                case "fill_blank":

                    if ("未选择".equals(answer)) {
                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(-1), Arrays.asList("未选择"));
                    } else {
                        String[] strings = convertStrToArray(answer);
                        List<String> strings1 = Arrays.asList(strings);
                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(-1), strings1);
                    }
                    break;


            }


        }

    };
    private String token;

    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split(","); //拆分字符为"/" ,然后把结果交给数组strArray
        return strArray;
    }


    static int minute = -1;
    static int second = -1;
    Timer timer;
    TimerTask timerTask;
    Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            if (minute == 0) {
                if (second == 0) {
                    //这里当时间完成做的操作
                    timeView.setText("Time out !");




                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    if (timerTask != null) {
                        timerTask = null;
                    }


                } else {
                    second--;
                    if (second >= 10) {
                        timeView.setText("0" + minute + ":" + second);
                    } else {
                        timeView.setText("0" + minute + ":0" + second);
                    }
                }
            } else {
                if (second == 0) {
                    second = 59;
                    minute--;
                    if (minute >= 10) {
                        timeView.setText(minute + ":" + second);
                    } else {
                        timeView.setText("0" + minute + ":" + second);
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        if (minute >= 10) {
                            timeView.setText(minute + ":" + second);
                        } else {
                            timeView.setText("0" + minute + ":" + second);
                        }
                    } else {
                        if (minute >= 10) {
                            timeView.setText(minute + ":0" + second);
                        } else {
                            timeView.setText("0" + minute + ":0" + second);
                        }
                    }
                }
            }
        }

        ;
    };
    private String paperId;
    private String examId1;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_exam;
    }

    @Override
    protected void initInjector() {

    }
private boolean  isCanjao=false;
    @Override
    protected void initViews() {
        //数据请求
        token = (String) SharedPreferencesUtils.getParam(ExamActivity.this, "Token", "");
        //倒计时
        if (minute == -1 && second == -1) {

            minute = 60; //倒计时的时间（分钟）
            second = 00; //（秒）
        }
        timeView.setText(minute + ":" + second);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0;
                handler1.sendMessage(msg);
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);


        Intent intent = getIntent();
        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        final String examId = intent.getStringExtra("examId");
        String examCourseId = intent.getStringExtra("examCourseId");

        map.put("examId", RequestBody.create(null, examId));
        map.put("examCourseId", RequestBody.create(null, examCourseId));
        RetrofitService.getExamPaper1(map, token).subscribe(new Subscriber<ExamTypebean>() {


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ExamTypebean examTypebean) {
                itemsBeen = examTypebean;

                paperId = examTypebean.getData().getPaperId() + "";
                examId1 = examTypebean.getData().getExamId() + "";
                List<ExamTypebean.DataBean.ItemsBean> items = examTypebean.getData().getItems();
                final int size = items.size();

                //标题栏显示总题数
                examTotal.setText(size + "");
                examWhich.setText(1 + "");
                //弹窗选择
                for (int i1 = 1; i1 <= size; i1++) {
                    list.add(i1 + "");
                }
                popupWindow = new ChooseExamWindow(ExamActivity.this, handler, R.layout.tiku_adapter, list);
                popupWindow.synData(list);
                chooseTestRt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.showPopupWindow(qwe, false);
                    }
                });

                /*一进来用如下方式 初始化popwindow的数据*/
                //不能直接弹出一个popwindow,会报错。 这里用一个控件 延迟100ms执行弹出效果
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.showPopupWindow(qwe, false);
                    }
                };
                examTotal.postDelayed(runnable, 100);
                //弹出后用如下方式 关闭popwindow即可，这样 我们的popwindow就初始化成功了
                CountDownTimer timer = new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        popupWindow.dismiss();
                    }
                };
                timer.start();

                mutiExamLayoutHelper = new MutiExamLayoutHelper(examTypebean, ExamActivity.this);

                //当用户做操作后，更新数据源
                RxBus.getDefault().toObservableSticky(NotDataChange.class).subscribe(new Subscriber<NotDataChange>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NotDataChange notDataChange) {
                        examsSparseArray1 = mutiExamLayoutHelper.examsSparseArray;
                    }
                });

                //通知更新
                mutiExamLayoutHelper.setOnPositionClickListener(new MutiExamLayoutHelper.OnPostClickListener() {
                    @Override
                    public void onItemClick(int postWind, int tag) {
                        popupWindow.setCheckItem(postWind, tag);
                        finishNums.put(postWind, postWind);
                    }


                });


                mutiExamLayoutHelper.getViewAnddatas(container, 0, Arrays.asList(-1), Arrays.asList("未选择"));
                //根据标题栏的索引来装载数据,防止错误

                nextQuestion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String which = examWhich.getText().toString();
                        int chooseWhich = Integer.parseInt(which) - 1;
                        if (chooseWhich == size - 1) {

                        } else {
                            ++chooseWhich;
                            examWhich.setText(chooseWhich + 1 + "");
                            //  mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich,Arrays.asList(-1));
                            SaveExamBean saveExamBean = examsSparseArray1.get(chooseWhich); //拿到当前位置的答案
                            String answer = saveExamBean.getAnswer();
                            String objectiveType = itemsBeen.getData().getItems().get(chooseWhich).getObjectiveType();
                            switch (objectiveType) {
                                case "single":
                                    //如果是未选择什么也不用做
                                    if ("未选择".equals(answer)) {

                                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(-1), Arrays.asList("未选择"));
                                    } else {
                                        int i = Integer.parseInt(answer) - 1;
                                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(i), Arrays.asList("未选择"));
                                    }

                                    break;
                                case "judgement":
                                    if ("未选择".equals(answer)) {
                                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(-1), Arrays.asList("未选择"));
                                    } else {
                                        int i = Integer.parseInt(answer) - 1;
                                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(i), Arrays.asList("未选择"));
                                    }
                                    break;
                                case "multi":
                                    if ("未选择".equals(answer)) {
                                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(-1), Arrays.asList("空白"));
                                    } else {

                                        String[] strings = convertStrToArray(answer);
                                        List<String> list1 = Arrays.asList(strings);
                                        List<Integer> list2 = new ArrayList<>();
                                        int size = list1.size();
                                        for (int i = 0; i < size; i++) {
                                            String s = list1.get(i);
                                            int i1 = Integer.parseInt(s) - 1;
                                            list2.add(i1);

                                        }
                                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, list2, Arrays.asList("未选择"));

                                    }
                                    break;
                                case "fill_blank":

                                    if ("未选择".equals(answer)) {
                                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(-1), Arrays.asList("未选择"));
                                    } else {
                                        String[] strings = convertStrToArray(answer);
                                        List<String> strings1 = Arrays.asList(strings);
                                        mutiExamLayoutHelper.getViewAnddatas(container, chooseWhich, Arrays.asList(-1), strings1);
                                    }
                                    break;


                            }


                        }
                    }
                });


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*     SparseArray<SaveExamBean> examsSparseArray = mutiExamLayoutHelper.examsSparseArray;
                int size = examsSparseArray.size();
                String   hintStr=null;
                //存储答案
                for (int i = 0; i < size; i++) {
                    submitAnswerses.add(new SubmitAnswers(examsSparseArray.get(i).getAnswer(), examsSparseArray.get(i).getProblem() + ""));
                }
                Gson gson = new Gson();
                final String answerJSONs = gson.toJson(submitAnswerses);
                Log.d("ExamActivity", answerJSONs);
                SharedPreferencesUtils.setParam(context, "DoExamAnswer", answerJSONs);*/
                finish();


            }
        });


        //交卷
        submitExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseArray<SaveExamBean> examsSparseArray = mutiExamLayoutHelper.examsSparseArray;
                int size1 = finishNums.size();
                int size = examsSparseArray.size();
                 String   hintStr=null;
                if(size==size1){
                    hintStr="确定交卷？";
                }else {
                    hintStr="试卷未做完，是否交卷？";
                }
                //存储答案
                for (int i = 0; i < size; i++) {
                    submitAnswerses.add(new SubmitAnswers(examsSparseArray.get(i).getAnswer(), examsSparseArray.get(i).getProblem() + ""));
                }
                Gson gson = new Gson();
                final String answerJSONs = gson.toJson(submitAnswerses);


                final NormalDialog dialog = new NormalDialog(context);
                dialog.isTitleShow(false)//
                        .bgColor(Color.parseColor("#FFFFFF"))//
                        .cornerRadius(5)//
                        .content(hintStr)//
                        .contentGravity(Gravity.CENTER)//
                        .contentTextColor(Color.parseColor("#000000"))//
                        .dividerColor(Color.parseColor("#222222"))//
                        .btnTextSize(15.5f, 15.5f)//
                        .btnTextColor(Color.parseColor("#000000"), Color.parseColor("#000000"))//
                        .btnPressColor(Color.parseColor("#2B2B2B"))//
                        .widthScale(0.75f)//
                        .heightScale(0.30f)
                        .show();

                dialog.setOnBtnClickL(
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                dialog.dismiss();
                            }
                        },

                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                //交卷
                                final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                                map.put("examId", RequestBody.create(null, examId1));
                                map.put("paperId", RequestBody.create(null, paperId));
                                map.put("records", RequestBody.create(null, answerJSONs));
                                RetrofitService.postAnswer(map, token).subscribe(new RxSubscribe<ExamResult>(context) {


                                    @Override
                                    protected void _onNext(ExamResult examResult) {
                                        if(examResult.getResult().equals("ok")){
                                            Intent intent1 = new Intent(ExamActivity.this, ExamResultNoPassActivity.class);
                                            Double score = examResult.getData().getScore();
                                            String timeString = (60 - minute) + ":" + (60 - second); //掉时间
                                            int scores = score.intValue(); //分数
                                            boolean passed = examResult.getData().isPassed(); //是否通过
                                            int examId2 = examResult.getData().getExamId(); //试卷id
                                            int examCourseId1 = examResult.getData().getExamCourseId();//课程id
                                            intent1.putExtra("score", scores);
                                            intent1.putExtra("ispas", passed);
                                            intent1.putExtra("timeString", timeString); //用时
                                            intent1.putExtra("examid", examId2);
                                            intent1.putExtra("examcourseId", examCourseId1);
                                            startActivity(intent1);
                                            finish();
                                        }

                                    }

                                    @Override
                                    protected void _onError(String message) {
                                        LToast.show(context,message);
                                    }

                                });


                                dialog.dismiss();
                            }
                        });


            }
        });


    }



    @Override
    protected void updateViews(boolean isRefresh) {


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
