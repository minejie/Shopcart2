package com.bwie.test.shopcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements GoodsAdapter.OnGoodsChange{

    @BindView(R.id.btn_edit)
    Button mBtnEdit;
    @BindView(R.id.rec_goods)
    RecyclerView mRecGoods;
    @BindView(R.id.cb_allChose)
    CheckBox mCbAllChose;
    @BindView(R.id.tv_all_price)
    TextView mTvAllPrice;
    @BindView(R.id.btn_ok)
    Button mBtnOk;
    @BindView(R.id.rl_check_money)
    RelativeLayout mRlCheckMoney;
    @BindView(R.id.btn_delete)
    Button mBtnDelete;
    @BindView(R.id.btn_favor)
    Button mBtnFavor;
    @BindView(R.id.rl_edit)
    RelativeLayout mRlEdit;

    private List<GoodEntity> goodsList=new ArrayList<>();
    private GoodsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }
    private void initData() {
        goodsList.add(new GoodEntity(R.drawable.empty_cart,"我的购物车","$120.0",1,false));
        goodsList.add(new GoodEntity(R.drawable.empty_cart,"我的购物车","$120.0",1,false));
        goodsList.add(new GoodEntity(R.drawable.empty_cart,"我的购物车","$120.0",3,false));
        goodsList.add(new GoodEntity(R.drawable.empty_cart,"我的购物车","$120.0",1,false));
        goodsList.add(new GoodEntity(R.drawable.empty_cart,"我的购物车","$120.0",1,false));
        goodsList.add(new GoodEntity(R.drawable.empty_cart,"我的购物车","$120.0",1,false));
        adapter = new GoodsAdapter(this, goodsList);
        //设置布局管理器
        mRecGoods.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        mRecGoods.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnGoosChang(this);
        mRecGoods.setAdapter(adapter);

    }


    @OnClick({R.id.btn_edit, R.id.cb_allChose, R.id.btn_ok, R.id.btn_delete, R.id.btn_favor})
    public void onClick(View view) {
        switch (view.getId()) {
            //点击“编辑”切换结算 和 删除两个界面 通过判断文字内容
            case R.id.btn_edit:
                Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
                if (mBtnEdit.getText().toString().equals("编辑")){
                    mRlCheckMoney.setVisibility(View.GONE);
                    mRlEdit.setVisibility(View.VISIBLE);
                    mBtnEdit.setText("完成");
                }else {
                    mBtnEdit.setText("编辑");
                    mRlCheckMoney.setVisibility(View.VISIBLE);
                    mRlEdit.setVisibility(View.GONE);
                    mTvAllPrice.setText(allPrice());
                }
                break;
            //点击全选 设置 数据的选中状态 刷新适配器 更新UI
            case R.id.cb_allChose:
                if (mCbAllChose.isChecked()){
                    for (GoodEntity goods:goodsList) {
                        goods.setCheck(true);
                    }
                    adapter.notifyDataSetChanged();
                    mTvAllPrice.setText(allPrice());
                }else {
                    for (GoodEntity goods:goodsList) {
                        goods.setCheck(false);
                    }
                    adapter.notifyDataSetChanged();
                    mTvAllPrice.setText("合计:$0.0");
                }

                break;
            case R.id.btn_ok:
                Toast.makeText(this, allPrice(), Toast.LENGTH_SHORT).show();
                break;
            //删除操作
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_favor:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //选择更新 更新价钱 和全选状态
    @Override
    public void choseChange() {
        mTvAllPrice.setText(allPrice());
        mCbAllChose.setChecked(isAllChose());
    }
    //数量更新 更新价钱
    @Override
    public void choseCountChange() {
        mTvAllPrice.setText(allPrice());

    }

    @Override
    public void goodsItemChange() {

    }

    /**
     * 返回选中商品的总价
     * @return
     */
    private String allPrice(){
        float sum=0f;
        for (GoodEntity goods:goodsList) {
            if (goods.isCheck()){
                Float price = Float.valueOf(goods.getPrice().replace("$", ""));
                sum+=price*goods.getCount();
            }
        }
        return "合计:$"+sum;
    }

    /**
     * 根据选择状态 删除数据 更新UI
     */
    private void  delete(){
        if (mCbAllChose.isChecked()){
            goodsList.clear();
        }else {
            List<GoodEntity> good=new ArrayList<>();
            for (GoodEntity goods:goodsList) {
                if (goods.isCheck()){
                    good.add(goods);
                }
            }
            goodsList.removeAll(good);
            adapter.notifyDataSetChanged();
        }

    }

    /**
     * 每次选择 接口调用时  判断自条目是否全选 更新全选按钮状态
     * @return
     */
    public boolean isAllChose() {
        boolean allChose=false;
        for (GoodEntity goods:goodsList) {
            if (!goods.isCheck()){
                allChose=true;
            }
        }
        if (allChose){
            allChose=false;
        }else {
            allChose=true;
        }
        return allChose;
    }
}
