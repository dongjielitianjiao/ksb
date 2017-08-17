package com.itboy.dj.examtool.modules.ftpage.exam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.utils.EditTextUtils;

import java.util.List;


/**
 * Created by liuweiqiang on 2016/9/7.  判断题的适配器
 */
public class FillBlankAdapter extends RecyclerView.Adapter<FillBlankAdapter.MyViewHolder> {

    private Context mContext;
   // private String[] mInfos;
   // private String[] mHints;
    private List<String> mInfos;
    private List<String> mHints;
    private List<String> userWrite;
    private int itemPosition;
    public interface SaveEditListener{
        //1.当前题的索引 2.用户填写的位置
        void SaveEdit(int itemPostion,int position, String string);
    }

    public FillBlankAdapter(List<String> mInfos, List<String> mHints, List<String> userWrite, int itemposition, Context mContext) {
        this.mInfos=mInfos;
        this.mHints=mHints;
        this.itemPosition=itemposition;
        this.mContext = mContext;
        this.userWrite = userWrite;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.exam_fill_blank_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

      //  holder.jg_name.setText(mInfos.get(position));
        holder.jg_name_et.setHint(mHints.get(position));
        holder.jg_name_et.setText(userWrite.get(position));
        //添加editText的监听事件
        holder.jg_name_et.addTextChangedListener(new TextSwitcher(holder));
        //通过设置tag，防止position紊乱
        holder.jg_name_et.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mInfos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

       // TextView jg_name;//
        EditText jg_name_et;//填写项

        public MyViewHolder(View view)
        {
            super(view);
       //     jg_name = (TextView) view.findViewById(jg_name);
            jg_name_et= (EditText) view.findViewById(R.id.jg_name_et);
            EditTextUtils.setEditTextInhibitInputSpace(jg_name_et);
            EditTextUtils.setEditTextInhibitInputSpeChat(jg_name_et);
        }
    }

    //自定义EditText的监听类
    class TextSwitcher implements TextWatcher {

        private MyViewHolder mHolder;

        public TextSwitcher(MyViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(final Editable s) {
            //用户输入完毕后，处理输入数据，回调给主界面处理

            if(s!=null){
                mListener.onItemClick(itemPosition,Integer.parseInt(mHolder.jg_name_et.getTag().toString()), String.valueOf(s));
            }else {
                mListener.onItemClick(itemPosition,Integer.parseInt(mHolder.jg_name_et.getTag().toString()), "未选择");
            }



 /*           RxTextView.textChanges(mHolder.jg_name_et).debounce(1500, TimeUnit.MILLISECONDS).map(new Func1<CharSequence, String>() {
                @Override
                public String call(CharSequence charSequence) {

                    return charSequence.toString();
                }
            }).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {

                    Log.d("TextSwitcher", s);


                }
            });*/


            SaveEditListener listener= new SaveEditListener() {
                @Override
                public void SaveEdit(int itemPostion, int position, String string) {

                }
            };
            if(s!=null){
                listener.SaveEdit(itemPosition,Integer.parseInt(mHolder.jg_name_et.getTag().toString()),s.toString());
            }else {
                listener.SaveEdit(itemPosition,Integer.parseInt(mHolder.jg_name_et.getTag().toString()),"未选择");
            }


        }
    }

    private OnItemClickListener mListener;

    public  void  setOnItemClickListener(OnItemClickListener listener){
        this.mListener=listener;
    }
    public  interface  OnItemClickListener{
          void onItemClick(int itemPostion, int position, String string);
    }

}
