package com.nanyang.app.main.home.sport.main;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */

public abstract class SportAdapterHelper<B extends SportInfo> implements IAdapterHelper<B> {

    public Map<Boolean, String> additionMap;


    public BaseRecyclerAdapter<B> getBaseRecyclerAdapter() {
        return baseRecyclerAdapter;
    }

    public BaseRecyclerAdapter<B> baseRecyclerAdapter;

    public ItemCallBack<B> getBack() {
        return back;
    }

    protected ItemCallBack<B> back;

    @Override
    public void onConvert(MyRecyclerViewHolder holder, int position, B item) {

    }

    public boolean isContracted() {
        return isContracted;
    }

    boolean isContracted;

    public void bindAdapter(BaseRecyclerAdapter<B> baseRecyclerAdapter) {
        this.baseRecyclerAdapter = baseRecyclerAdapter;
        isContracted = false;
    }


    public interface ItemCallBack<B> {
        B getItem(int position);

        /**
         * @param v    点击的view
         * @param item
         * @param type 下注的类型 ou，hdp，over，under，_par
         * @param isHf 是否是半场
         * @param odds 下注赔率
         */
        void clickOdds(TextView v, B item, String type, boolean isHf, String odds, int oid, String sc, boolean hasPar);

        void clickView(View v, B item, int position);

    }

    public <I extends ItemCallBack<B>> void setItemCallBack(I back) {
        this.back = back;
    }

    public void setAdditionMap(Map<Boolean, String> additionMap) {
        this.additionMap = additionMap;
    }

    public boolean updateContractedMatch(MyRecyclerViewHolder helper, B item) {
        Boolean aBoolean = contractedMap.get(item.getModuleId());
        View ll_match_content = helper.getView(R.id.ll_match_content);
        TextView module_League_child_count_tv = helper.getView(R.id.module_League_child_count_tv);
        if ((aBoolean == null && !isContracted()) || (aBoolean != null && !aBoolean)) {
            ll_match_content.setVisibility(View.VISIBLE);

            module_League_child_count_tv.setVisibility(View.GONE);
            return false;
        } else {
            if (item.getType() == SportInfo.Type.ITME) {
                module_League_child_count_tv.setVisibility(View.GONE);
            } else {
                module_League_child_count_tv.setText(item.getChildCount() + "");
                module_League_child_count_tv.setVisibility(View.VISIBLE);
            }

            ll_match_content.setVisibility(View.GONE);
            return true;
        }
    }

    public void setClickOneTeam(MyRecyclerViewHolder helper, final B item) {
        View matchTitleLl = helper.getView(R.id.module_match_title_bg);
        if(matchTitleLl==null)
            matchTitleLl=helper.getView(R.id.module_match_title_ll);
        matchTitleLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOneTeamContracted(item);
            }
        });
    }


    Map<String, Boolean> contractedMap = new HashMap<>();


    public void clickOneTeamContracted(B item) {
        String moduleId = item.getModuleId();
        Boolean isContracted = contractedMap.get(moduleId);
        if (isContracted == null)
            isContracted = false;
        boolean b = !isContracted;
        contractedMap.put(moduleId, b);
        getBaseRecyclerAdapter().notifyDataSetChanged();
        LogUtil.d("contractedMap", "getModuleId:" + moduleId + ",isContracted:" + b);
    }

    public boolean clickAllContracted() {
        isContracted = !isContracted;
        List<B> bs = getBaseRecyclerAdapter().getmDatas();
        if (bs == null)
            return false;
        for (B b : bs) {
            contractedMap.put(b.getModuleId(), isContracted);
        }
        getBaseRecyclerAdapter().notifyDataSetChanged();
        return isContracted;
    }
}
