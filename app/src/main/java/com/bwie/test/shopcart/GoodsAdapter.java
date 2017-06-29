package com.bwie.test.shopcart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Date：2017/6/29
 * author: 曹政杰Administrator.
 * function：
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {
    private Context context;
    //数据集合
    private List<GoodEntity> list;
    //计数器
    private OnGoodsChange onGoodsChange;

    public GoodsAdapter(Context context, List<GoodEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.goods_item,null));
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_item, parent, false);
        MyViewHolder vh = new MyViewHolder(root);
        return vh;
      //  return holder;
    }
    //适配数据
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.cbGoods.setChecked(list.get(position).isCheck());
        holder.tvGoodsName.setText(list.get(position).getGoodsName());
        holder.tvPrice.setText(list.get(position).getPrice());
        holder.imgGoods.setBackgroundResource(list.get(position).getImgId());
        //设置商品的最大数量
        holder.cavGoodCount.setGoods_storage(5);
        //设置计数器显示的购买单品数量
        holder.cavGoodCount.setAmount(list.get(position).getCount());
        holder.cavGoodCount.setOnAmountChangeListener(new CountActionView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {

                list.get(position).setCount(amount);
                onGoodsChange.choseCountChange();
            }
        });
        holder.cbGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setCheck(holder.cbGoods.isChecked());
                onGoodsChange.choseChange();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox cbGoods;

        ImageView imgGoods;

        TextView tvGoodsName;

        TextView tvPrice;

        CountActionView cavGoodCount;
        public MyViewHolder(View view) {
            super(view);

            cbGoods= (CheckBox) view.findViewById(R.id.cb_goods);
            imgGoods= (ImageView) view.findViewById(R.id.img_goods);
            tvGoodsName= (TextView) view.findViewById(R.id.tv_goodsName);
            tvPrice= (TextView) view.findViewById(R.id.tv_price);
            cavGoodCount= (CountActionView) view.findViewById(R.id.cav_goodCount);
        }
    }

    public interface OnGoodsChange{
        void choseChange();
        void choseCountChange();
        void goodsItemChange();


    }
    public void setOnGoosChang(OnGoodsChange onGoosChang){
        this.onGoodsChange=onGoosChang;

    }
}
