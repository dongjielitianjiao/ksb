package com.itboy.dj.examtool;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.ExamPaper;
import com.itboy.dj.examtool.api.bean.SubmitAnswers;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.popwindow.ChooseExamWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.RequestBody;

import static com.itboy.dj.examtool.R.id.exam_which;
import static com.itboy.dj.examtool.R.id.radio_B;

//CTRL+ALT+M  提取方法
//Ctrl + Alt + N  把CTRL+ALT+M 还原
//shift+f6  修改变量名字
//CTRL+ALT+V   生成变量
//Ctrl + Alt + P 把方法内的某个变量提为方法参数
//trl + Alt + Shift + T 万能重构
//ctrl+alt+T  if else;  try catch  runnable;
public class DoExamActivity extends BaseActivity {
    @BindView(R.id.activity_do_exam)
    RelativeLayout activityDoExam;
    @BindView(R.id.back)
    RelativeLayout back; //返回键
    @BindView(exam_which)
    TextView examWhich; //当前是哪一题
    @BindView(R.id.exam_total)
    TextView examTotal; //总的题数
    @BindView(R.id.choose_test_rt)
    RelativeLayout chooseTestRt; //点击手动选择做题
    @BindView(R.id.container)
    LinearLayout container; //展示问题的容器
    @BindView(R.id.time_show)
    TextView timeView; //倒计时
    @BindView(R.id.submit_exam)
    TextView submitExam; //立即交卷
    @BindView(R.id.next_question)
    Button nextQuestion; //下一题
    @BindView(R.id.qwe)
    RelativeLayout qwe;

    //用户手动选择
    private ChooseExamWindow popupWindow;
    private List<String> list = new ArrayList<>();

    //用户点击下一题，没有选择相关选项，则存储一个默认值；
    private String checked = "NoChoose";
    //保存答案
    private SparseArray<ExamsBean> examsBeanSparseArray = new SparseArray<>();
    //转换为上传的答案
    private List<SubmitAnswers> submitAnswerses = new ArrayList<>();

    private List<ExamPaper.DataBean.ItemsBean> testDatas = new ArrayList<>();

    private TextView question;
    private TextView content_a;
    private TextView content_b;
    private TextView content_c;
    private TextView content_d;
    private RadioGroup radioGroup;
    private RadioButton radio_a;
    private RadioButton radio_b;
    private RadioButton radio_c;
    private RadioButton radio_d;


    private String examId1;
    private String paperId;


    static int minute = -1;
    static int second = -1;
    Timer timer;
    TimerTask timerTask;
    Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            System.out.println("handle!");
            if (minute == 0) {
                if (second == 0) {
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


    //提出的选择答案
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            radioGroup.check(-1);
            int index = (int) msg.obj;
            examWhich.setText(list.get(index));
            String which = examWhich.getText().toString();
            int chooseWhich = Integer.parseInt(which) - 1;

            //还原答案
            ExamsBean examsBean = examsBeanSparseArray.get(chooseWhich);
            if (examsBean != null) {
                String answer = examsBean.getAnswer();
                if (!answer.equals("NoChoose")) {
                    switch (answer) {
                        case "1":
                            radio_a.setChecked(true);
                            break;
                        case "2":
                            radio_b.setChecked(true);
                            break;
                        case "3":
                            radio_c.setChecked(true);
                            break;
                        case "4":
                            radio_d.setChecked(true);
                            break;
                    }
                }
            }
            question.setText(testDatas.get(chooseWhich).getText());
            List<ExamPaper.DataBean.ItemsBean.QuestionOptionsBean> questionOptions1 = testDatas.get(chooseWhich).getQuestionOptions();
            int size2 = questionOptions1.size();
            if (size2 == 4) {
                content_a.setText(questionOptions1.get(0).getText());
                content_b.setText(questionOptions1.get(1).getText());
                content_c.setText(questionOptions1.get(2).getText());
                content_d.setText(questionOptions1.get(3).getText());
            } else if (size2 == 2) {
                content_a.setText(questionOptions1.get(0).getText());
                content_b.setText(questionOptions1.get(1).getText());
                content_c.setVisibility(View.INVISIBLE);
                content_d.setVisibility(View.INVISIBLE);
                radio_c.setVisibility(View.INVISIBLE);
                radio_d.setVisibility(View.INVISIBLE);
            }


        }

    };


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_do_exam;
    }

    @Override
    protected void initInjector() {

    }

    //initViews
    @Override
    protected void initViews() {
        //   Log.d("DoExamActivity", "1111");

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_exam);
        ButterKnife.bind(this);
        //进来直接初始化布局
        final View inflate = LayoutInflater.from(DoExamActivity.this).inflate(R.layout.item_do_exam_layout, null);
        ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        inflate.setLayoutParams(params1);
        question = (TextView) inflate.findViewById(R.id.questi);
        content_a = (TextView) inflate.findViewById(R.id.content_A);
        content_b = (TextView) inflate.findViewById(R.id.content_B);
        content_c = (TextView) inflate.findViewById(R.id.content_C);
        content_d = (TextView) inflate.findViewById(R.id.content_D);
        radioGroup = (RadioGroup) inflate.findViewById(R.id.radio_group);
        radio_a = (RadioButton) inflate.findViewById(R.id.radio_A);
        radio_b = (RadioButton) inflate.findViewById(radio_B);
        radio_c = (RadioButton) inflate.findViewById(R.id.radio_C);
        radio_d = (RadioButton) inflate.findViewById(R.id.radio_D);
        container.addView(inflate);

        //倒计时
        if (minute == -1 && second == -1) {
            Intent intent = getIntent();

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


        //数据请求
        final String token = (String) SharedPreferencesUtils.getParam(DoExamActivity.this, "Token", "");
        Intent intent = getIntent();
        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        final String examId = intent.getStringExtra("examId");
        String examCourseId = intent.getStringExtra("examCourseId");
        map.put("examId", RequestBody.create(null, examId));
        map.put("examCourseId", RequestBody.create(null, examCourseId));


        RetrofitService.getExamPaper(map, token).subscribe(new RxSubscribe<ExamPaper>(DoExamActivity.this) {
            @Override
            protected void _onNext(ExamPaper examPaper) {
                testDatas = examPaper.getData().getItems();
                examId1 = examPaper.getData().getExamId() + "";
                paperId = examPaper.getData().getPaperId() + "";
                final int size = testDatas.size();
                //标题栏显示总题数
                examTotal.setText(size + "");
                examWhich.setText(1 + "");
                //弹窗选择
                for (int i1 = 1; i1 <= size; i1++) {
                    list.add(i1 + "");
                }
                popupWindow = new ChooseExamWindow(DoExamActivity.this, handler, R.layout.tiku_adapter, list);
                popupWindow.synData(list);
                chooseTestRt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.showPopupWindow(qwe, false);
                    }
                });

                /*一进来用如下方式 初始化popwindow的数据*/
                //不能直接弹出一个popwindow;会报错。 这里用一个控件 延迟100ms执行弹出效果
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


                //默认展示第一个问题
                question.setText("1." + testDatas.get(0).getText());
                List<ExamPaper.DataBean.ItemsBean.QuestionOptionsBean> questionOptions = testDatas.get(0).getQuestionOptions();  //默认展示第一个选项
                int size1 = questionOptions.size(); //每天一个问题的选项
                if (size1 == 4) {
                    content_a.setText(questionOptions.get(0).getText());
                    content_b.setText(questionOptions.get(1).getText());
                    content_c.setText(questionOptions.get(2).getText());
                    content_d.setText(questionOptions.get(3).getText());
                } else if (size1 == 2) {
                    content_a.setText(questionOptions.get(0).getText());
                    content_b.setText(questionOptions.get(1).getText());
                    content_c.setVisibility(View.INVISIBLE);
                    content_d.setVisibility(View.INVISIBLE);
                    radio_c.setVisibility(View.INVISIBLE);
                    radio_d.setVisibility(View.INVISIBLE);
                }
                //下一题的监听事件
                nextQuestion.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        //根据标题栏的索引来装载数据,防止错误
                        String which = examWhich.getText().toString();
                        int chooseWhich = Integer.parseInt(which) - 1;
                        //改变题目
                        if (chooseWhich == size - 1) {

                        } else {
                            //点击下一题时,更新数据
                            ++chooseWhich;
                            examWhich.setText(chooseWhich + 1 + "");
                            question.setText(chooseWhich + 1 + "." + testDatas.get(chooseWhich).getText());
                            List<ExamPaper.DataBean.ItemsBean.QuestionOptionsBean> questionOptions1 = testDatas.get(chooseWhich).getQuestionOptions();
                            int size2 = questionOptions1.size();
                            if (size2 == 4) {
                                content_a.setText(questionOptions1.get(0).getText());
                                content_b.setText(questionOptions1.get(1).getText());
                                content_c.setText(questionOptions1.get(2).getText());
                                content_d.setText(questionOptions1.get(3).getText());
                            } else if (size2 == 2) {
                                content_a.setText(questionOptions1.get(0).getText());
                                content_b.setText(questionOptions1.get(1).getText());
                                content_c.setVisibility(View.INVISIBLE);
                                content_d.setVisibility(View.INVISIBLE);
                                radio_c.setVisibility(View.INVISIBLE);
                                radio_d.setVisibility(View.INVISIBLE);
                            }
                            //还原单选按钮状态和置空上一题的答案（小于总题数的时候，最后一道题 点击下一题就不用还原了）
                            radioGroup.check(-1); //这里会强行执行radiogroup的change监听方法；所以不用那个方法去监听
                            checked = "NoChoose";


                            //点击下一题的时候会回显答案
                            String which1 = examWhich.getText().toString();
                            int currentWhich = Integer.parseInt(which1);
                            ExamsBean examsBean = examsBeanSparseArray.get(currentWhich - 1);
                            if (examsBean != null) {
                                String answer = examsBean.getAnswer();
                                if (!answer.equals("NoChoose")) {
                                    switch (answer) {
                                        case "1":
                                            radio_a.setChecked(true);
                                            break;
                                        case "2":
                                            radio_b.setChecked(true);
                                            break;
                                        case "3":
                                            radio_c.setChecked(true);
                                            break;
                                        case "4":
                                            radio_d.setChecked(true);
                                            break;
                                    }
                                }
                            }

                        }


                    }
                });

                radio_a.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String which = examWhich.getText().toString();
                        int chooseWhich = Integer.parseInt(which) - 1;
                        String text = content_a.getText().toString();
                        String text1 = content_b.getText().toString();
                        String text2 = content_c.getText().toString();
                        String text3 = content_d.getText().toString();
                        String quest = question.getText().toString();
                        checked = "1";
                        if (popupWindow != null) {
                            popupWindow.setCheckItem(chooseWhich,0);
                        }
                        String problemId = testDatas.get(chooseWhich).getProblemId() + "";
                        examsBeanSparseArray.put(chooseWhich, new ExamsBean(chooseWhich, quest, problemId, text, text1, text2, text3, checked));

                    }
                });


                radio_b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String which = examWhich.getText().toString();
                        int chooseWhich = Integer.parseInt(which) - 1;
                        String text = content_a.getText().toString();
                        String text1 = content_b.getText().toString();
                        String text2 = content_c.getText().toString();
                        String text3 = content_d.getText().toString();
                        String quest = question.getText().toString();
                        checked = "2";
                        if (popupWindow != null) {
                            popupWindow.setCheckItem(chooseWhich,0);
                        }
                        String problemId = testDatas.get(chooseWhich).getProblemId() + "";
                        examsBeanSparseArray.put(chooseWhich, new ExamsBean(chooseWhich, quest, problemId, text, text1, text2, text3, checked));
                    }
                });

                radio_c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String which = examWhich.getText().toString();
                        int chooseWhich = Integer.parseInt(which) - 1;
                        String text = content_a.getText().toString();
                        String text1 = content_b.getText().toString();
                        String text2 = content_c.getText().toString();
                        String text3 = content_d.getText().toString();
                        String quest = question.getText().toString();
                        checked = "3";
                        if (popupWindow != null) {
                            popupWindow.setCheckItem(chooseWhich,0);
                        }

                        String problemId = testDatas.get(chooseWhich).getProblemId() + "";
                        examsBeanSparseArray.put(chooseWhich, new ExamsBean(chooseWhich, quest, problemId, text, text1, text2, text3, checked));
                    }
                });

                radio_d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String which = examWhich.getText().toString();
                        int chooseWhich = Integer.parseInt(which) - 1;
                        String text = content_a.getText().toString();
                        String text1 = content_b.getText().toString();
                        String text2 = content_c.getText().toString();
                        String text3 = content_d.getText().toString();
                        String quest = question.getText().toString();
                        checked = "4";
                        if (popupWindow != null) {
                            popupWindow.setCheckItem(chooseWhich,0);
                        }
                        String problemId = testDatas.get(chooseWhich).getProblemId() + "";
                        examsBeanSparseArray.put(chooseWhich, new ExamsBean(chooseWhich, quest, problemId, text, text1, text2, text3, checked));
                    }
                });




            }

            @Override
            protected void _onError(String message) {

            }


        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


    }


    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask = null;
        }
        minute = -1;
        second = -1;
        super.onDestroy();
    }


}
