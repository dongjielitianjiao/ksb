package com.itboy.dj.examtool.modules.me;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.modules.base.BaseActivity;

import butterknife.BindView;

/*提现申请*/
public class BeRealMoneyApplyActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.money_num)
    EditText moneyNum; //提现金额
    @BindView(R.id.user_card_name)
    EditText userCardName; // 户名
    @BindView(R.id.open_bank_num)
    EditText openBankNum; //开行号
    @BindView(R.id.bank_card_num)
    EditText bankCardNum; //银行卡号
    @BindView(R.id.apply_btn)
    Button applyBtn; //提交申请


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_be_real_money_apply;
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
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


}
