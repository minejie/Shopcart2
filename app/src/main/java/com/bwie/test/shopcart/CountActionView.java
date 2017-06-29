package com.bwie.test.shopcart;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Date：2017/6/29
 * author: 曹政杰Administrator.
 * function：
 */

public class CountActionView extends LinearLayout implements View.OnClickListener,TextWatcher {
    private static final String TAG = "AmountView";

    public int getAmount() {
        return amount;
    }
    //设置数量
    public void setAmount(int amount) {
        this.amount = amount;
        if (etAmount!=null){
            etAmount.setText(amount+"");
        }
    }

    private int amount = 1; //购买数量
    private int goods_storage = 1; //商品库存


    private OnAmountChangeListener mListener;


    private TextView etAmount;
    private Button btnDecrease;
    private Button btnIncrease;


    public CountActionView(Context context) {
        this(context, null);
    }


    public CountActionView(Context context, AttributeSet attrs) {
        super(context, attrs);


        LayoutInflater.from(context).inflate(R.layout.count_action, this);
        etAmount = (TextView) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

//初始化控件参数
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, 60);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
        obtainStyledAttributes.recycle();


        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }


        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }
    }


    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }


    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }

    //设置点击监听 等于一不能减 大于storage不能加
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                etAmount.setText(amount + "");
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
            }
        }


        etAmount.clearFocus();


        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


    }
//当text改变时调用提供的接口方法，对外部调用

    @Override
    public void afterTextChanged(Editable s) {
//              if (s.toString().isEmpty())
//                      return;
//              amount = Integer.valueOf(s.toString());
//               if (amount > goods_storage) {
//                      etAmount.setText(goods_storage + "");
//                       return;
//                  }
//
//
//              if (mListener != null) {
//                      mListener.onAmountChange(this, amount);
//                   }
    }



    //外部调用接口 数据改变时调用
    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }

}
