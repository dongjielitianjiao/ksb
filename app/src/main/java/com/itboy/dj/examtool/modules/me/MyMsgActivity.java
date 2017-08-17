package com.itboy.dj.examtool.modules.me;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.adapter.MenuAdapter;
import com.itboy.dj.examtool.adapter.OnItemClickListener;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.MsgBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.utils.RecycleViewDivider;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;

/*我的消息*/
public class MyMsgActivity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.base_title_name)
    TextView baseTitleName;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView msgRec;
    private List<MsgBean.DataBean.RowsBean> rows;
    private MenuAdapter mMenuAdapter;

    private int index = 1;
    private int page_count; //总的页数
    private int item_size = 7;//每页的条数
    private int mCurrentCounter = 0; //当前个数

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_my_msg;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        baseTitleName.setText("我的消息");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        msgRec.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        msgRec.addItemDecoration(new RecycleViewDivider(MyMsgActivity.this, LinearLayoutManager.HORIZONTAL, R.drawable.recycle_divider));// 添加分割线。
        // 设置菜单创建器。
        msgRec.setSwipeMenuCreator(swipeMenuCreator);


        //MyMsgActivity
        final String token = (String) SharedPreferencesUtils.getParam(MyMsgActivity.this, "Token", "");
        RetrofitService.messages(index + "", item_size + "", "user_exam_message", token).subscribe(new Subscriber<MsgBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MsgBean msgBean) {
                rows = msgBean.getData().getRows();
                mMenuAdapter = new MenuAdapter(rows);
                msgRec.setAdapter(mMenuAdapter);
                int total = msgBean.getData().getTotal();
                page_count = total % item_size == 0 ? total / item_size : total / item_size + 1; //拿到总的页数

                msgRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
                   @Override
                   public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                       if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                           if(index<page_count){
                                index += 1;
                                RetrofitService.messages(index + "", item_size + "", "user_exam_message", token).subscribe(new Subscriber<MsgBean>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(MsgBean msgBean) {
                                        List<MsgBean.DataBean.RowsBean> rows1 = msgBean.getData().getRows();
                                        int size = rows1.size();
                                        for (int i = 0; i < size; i++) {
                                            rows.add(rows1.get(i));
                                        }
                                        mMenuAdapter.notifyDataSetChanged();
                                    }
                                });
                            }



                       }
                   }
               });







                mMenuAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String messageId = rows.get(position).getMessageId()+"";
                        Intent intent=new Intent(MyMsgActivity.this,MsgDeatilActivity.class);
                        intent.putExtra("msgid",messageId);
                        startActivity(intent);

                    }
                });


                // 设置菜单Item点击监听。
                msgRec.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
                    @Override
                    public void onItemClick(Closeable closeable, final int adapterPosition, int menuPosition, int direction) {
                        closeable.smoothCloseMenu();// 关闭被点击的菜单。
                        if (menuPosition == 0) {// 删除按钮被点击。
                            String messageId = rows.get(adapterPosition).getMessageId()+"";
                            RetrofitService.deleteMsg(messageId,token).subscribe(new Subscriber<JsonObject>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(JsonObject jsonObject) {
                                    try {
                                        JSONObject jsonObject1=new JSONObject(jsonObject.toString());
                                        String result = jsonObject1.optString("result");
                                        if("ok".equals(result)){
                                            rows.remove(adapterPosition);
                                            mMenuAdapter.notifyItemRemoved(adapterPosition);
                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        }
                    }
                });

            }
        });

    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;


            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(MyMsgActivity.this)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。


            }
        }
    };



    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView
         *                        #RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            if (menuPosition == 0) {// 删除按钮被点击。
                rows.remove(adapterPosition);
                mMenuAdapter.notifyItemRemoved(adapterPosition);

                //     mMenuAdapter.notifyItemRemoved(adapterPosition);
            }
        }
    };

    @Override
    protected void updateViews(boolean isRefresh) {

    }


}
