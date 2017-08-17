package com.itboy.dj.examtool.modules.ftpage.legalprotect;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.LegalRightAdapter;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.widget.tabContainer.TabContainerView1;
import com.itboy.dj.examtool.widget.tabContainer.listener.OnTabSelectedListener;
import com.itboy.dj.examtool.widget.tabContainer.widget.Tab;

import butterknife.BindView;
import rx.Subscriber;
import rx.Subscription;

/*我要维权*/
public class LegalLightProjectActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.megame_bot_table)
    TabContainerView1 megameBotTable;
    @BindView(R.id.i_legalright_zixun)
    Button iLegalrightZixun; //我要维权/咨询
    private String[] itemNames = {"法律援助", "意外保险", "媒体援助"};
    private Subscription subscription;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_legal_light_project;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("我要维权");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iLegalrightZixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LegalLightProjectActivity.this, WeiquanAndZixunActivity.class);
                startActivity(intent);
            }
        });




        megameBotTable.setAdapter(new LegalRightAdapter(getSupportFragmentManager(),
                new Fragment[]{new LegalHelpFragment(), new AccidentInsuranceFragment(), new MediaHelpFragment()}, itemNames));


        megameBotTable.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {

            }
        });

        megameBotTable.setOffscreenPageLimit(itemNames.length);


        subscription = RxBus.getDefault().toObservableSticky(TyPeBean.class).subscribe(new Subscriber<TyPeBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TyPeBean tyPeBean) {
                switch (tyPeBean.getType()){
                    case "0":
                        megameBotTable.setCurrentItem(0);
                        break;
                    case "1":
                        megameBotTable.setCurrentItem(1);
                        break;
                    case "2":
                        megameBotTable.setCurrentItem(2);
                        break;


                }
            }
        });

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
