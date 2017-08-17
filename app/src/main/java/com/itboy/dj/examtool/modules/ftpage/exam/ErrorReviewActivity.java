package com.itboy.dj.examtool.modules.ftpage.exam;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;

/*错题回顾*/
public class ErrorReviewActivity extends BaseActivity {

    @BindView(R.id.rec_error)
    RecyclerView recError;
    @BindView(R.id.back)
    RelativeLayout back; //返回键
    @BindView(R.id.error_container)
    LinearLayout errorContainer; //动态布局

    @BindView(R.id.next_error_question)
    Button nextErrorQuestion; //下一个错题
    @BindView(R.id.num)
    TextView num;
    private List<String> list = new ArrayList<>();

    private ErrorReviewHelper errorReviewHelper;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_error_review;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        num.setVisibility(View.GONE);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recError.setLayoutManager(manager);


        final String token = (String) SharedPreferencesUtils.getParam(ErrorReviewActivity.this, "Token", "");

        Intent intent = getIntent();
        int examid = intent.getIntExtra("examid", 0);
        int examcourseId = intent.getIntExtra("examcourseId", 0);
        RetrofitService.getErrorExamPaper(examid + "", examcourseId + "", token).subscribe(new Subscriber<ErrorReviewBean>() {


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ErrorReviewBean jsonObject) {


                List<ErrorReviewBean.DataBean.ItemsBean> items = jsonObject.getData().getItems();
                final int size = items.size();
                for (int i = 1; i <= size; i++) {
                    list.add(i + "");
                }
                num.setText(1 + "");

                ErrorAdapter errorAdapter = new ErrorAdapter(R.layout.item_exam_oval_choose, items);
                recError.setAdapter(errorAdapter);


                errorReviewHelper = new ErrorReviewHelper(jsonObject, ErrorReviewActivity.this);
                errorReviewHelper.ShowError(errorContainer, 0);
                recError.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        num.setText((position + 1) + "");
                        errorReviewHelper.ShowError(errorContainer, position);
                    }
                });
                nextErrorQuestion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String which = num.getText().toString();
                        int chooseWhich = Integer.parseInt(which) - 1;
                        if (chooseWhich == size - 1) {

                        } else {
                            ++chooseWhich;
                            num.setText((chooseWhich + 1) + "");
                            errorReviewHelper.ShowError(errorContainer, chooseWhich);

                        }

                    }
                });
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
    protected void updateViews(boolean isRefresh) {

    }



    class ErrorAdapter extends BaseQuickAdapter<ErrorReviewBean.DataBean.ItemsBean, BaseViewHolder> {
        public ErrorAdapter(int layoutResId, List<ErrorReviewBean.DataBean.ItemsBean> data) {
            super(layoutResId, data);
        }
     /*   public ErrorAdapter() {
            super(R.layout.item_exam_oval_choose, list);
        }*/

        @Override
        protected void convert(BaseViewHolder helper, ErrorReviewBean.DataBean.ItemsBean item) {

            int position = helper.getAdapterPosition();
            helper.setText(R.id.exam_choose_text, list.get(position));
            boolean isTrue = item.isIsTrue();
            if (isTrue) {
                helper.setBackgroundRes(R.id.exam_choose_text, R.drawable.exam_choose_select);
                helper.setTextColor(R.id.exam_choose_text, R.color.color_white);
            } else {
                helper.setBackgroundRes(R.id.exam_choose_text, R.drawable.exam_choose_select_error);
                helper.setTextColor(R.id.exam_choose_text, R.color.color_white);
            }
        }

    }


}
