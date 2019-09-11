package com.langt.zjgx.home;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.langt.zjgx.R;
import com.langt.zjgx.base.BaseActivity;
import com.langt.zjgx.base.BasePresenter;
import com.langt.zjgx.home.adapter.DiscountCouponListAdapter;
import com.langt.zjgx.home.model.ShopBean;
import com.langt.zjgx.widget.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 优惠券列表页面
 */
public class DiscountCouponListActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<ShopBean> itemList;
    private DiscountCouponListAdapter adapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_discount_coupon_list;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerDecoration(context, ContextCompat.getColor(this, R.color.global_divide_line_color), 5));
        itemList = new ArrayList<>();
        adapter = new DiscountCouponListAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i < 3; i++) {
            itemList.add(new ShopBean());
        }
        adapter.notifyDataSetChanged();
    }
}
