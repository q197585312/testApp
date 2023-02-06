package nanyang.com.dig88.Lottery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nanyang.com.dig88.Entity.LotteryPromptBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ICountLotteryBet;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.DecimalUtils;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2016/2/22.
 */
public abstract class LotteryBaseAdapter<T> extends QuickBaseAdapter<T> {
    private final int layoutResId;
    ICountLotteryBet iCountLotteryBet;
    Context context;
    private String promptTotal = "";
    private String promptSingle = "";
    /**
     * 单个限额提示Map
     */
    private Map<String, Map<Integer, String>> singleLimitPromptMap = new HashMap<>();
    /**
     * 总限额提示Map
     */
    private Map<String, String> totalLimitPromptMap = new HashMap<>();
    /**
     * 每期的每个类型的历史总共下注统计map
     */
    private Map<String, Float> totalTypeMap = new HashMap<>();
    /**
     * 每期的每个类型的当前每个位置下注
     */
    private Map<String, Map<Integer, Float>> typePositionMap = new HashMap<>();
    private List<LotteryLimitBean> limitList;
    public LotteryBaseAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
        this.layoutResId = layoutResId;
        this.context = context;
    }

    public String getPromptSingle() {
        return promptSingle;
    }

    public String getPromptTotal() {
        return promptTotal;
    }

    public void setiCountLotteryBet(ICountLotteryBet iCountLotteryBet) {
        this.iCountLotteryBet = iCountLotteryBet;
    }

    public Map<String, Map<Integer, String>> getSingleLimitPromptMap() {
        return singleLimitPromptMap;
    }

    public void setSingleLimitPromptMap(Map<String, Map<Integer, String>> singleLimitPromptMap) {
        this.singleLimitPromptMap = singleLimitPromptMap;
    }

    public Map<String, String> getTotalLimitPromptMap() {
        return totalLimitPromptMap;
    }

    public void setTotalLimitPromptMap(Map<String, String> totalLimitPromptMap) {
        this.totalLimitPromptMap = totalLimitPromptMap;
    }

    public Map<String, Float> getTotalTypeMap() {
        return totalTypeMap;
    }

    public void setTotalTypeMap(Map<String, Float> totalTypeMap) {
        this.totalTypeMap = totalTypeMap;
    }

    public Map<String, Map<Integer, Float>> getTypePositionMap() {
        return typePositionMap;
    }

    public void setTypePositionMap(Map<String, Map<Integer, Float>> typePositionMap) {
        this.typePositionMap = typePositionMap;
    }


    public List<LotteryLimitBean> getLimitList() {
        return limitList;
    }

    public void setLimitList(List<LotteryLimitBean> limitList) {
        this.limitList = limitList;
    }

    protected void initData() {
        setList(initList());
    }

    protected abstract List<T> initList();

    protected abstract void clearListMoney();

    public String countMoney(int postion, String money, LotteryLimitBean limit, int positionId, String discount, String kei) {
        if (money.equals("") || limit == null)
            return "";
        T item = getItem(postion);
        String countStr = "";
        totalTypeMap = new HashMap<>();
        if (null != AppTool.getObjectData(context, limit.getPeriod()))
            totalTypeMap = (Map<String, Float>) AppTool.getObjectData(context, limit.getPeriod());
        float totalF = 0f;
        if (totalTypeMap.containsKey(limit.getTypeKey())) {
            totalF = totalTypeMap.get(limit.getTypeKey());
        }
        Map<Integer, Float> tmap = new HashMap<>();
        if (typePositionMap.containsKey(limit.getTypeKey()))
            tmap = typePositionMap.get(limit.getTypeKey());
        tmap.put(postion * 10 + positionId, Float.valueOf(money));
        typePositionMap.put(limit.getTypeKey(), tmap);
        totalF = totalF + countTypeTotal(tmap);
        String singlePrompt = "";
        String totalPrompt = "";
        totalTypeMap.put(limit.getTypeKey(), totalF);
        if (Float.valueOf(limit.getMinLimit()) > Float.valueOf(money)) {
            singlePrompt = context.getString(R.string.betting_limit_less_than_nominal) + "[" + limit.getMinLimit() + "-" + limit.getMaxLimit() + "]";
        } else if (Float.valueOf(limit.getMaxLimit()) < Float.valueOf(money)) {
            singlePrompt = context.getString(R.string.betting_limit_more_than_nominal) + "[" + limit.getMinLimit() + "-" + limit.getMaxLimit() + "]";
        }
        if (Float.valueOf(limit.getTotalLimit()) < totalF) {
            totalPrompt = context.getString(R.string.betting_total_limit_more_than_nominal) + "[" + limit.getMinLimit() + "-" + limit.getTotalLimit() + "]";
        }
        totalLimitPromptMap.put(limit.getTypeKey(), totalPrompt);
        Map<Integer, String> positionPromptMap = new HashMap<>();
        if (singleLimitPromptMap.containsKey(limit.getTypeKey())) {
            positionPromptMap = singleLimitPromptMap.get(limit.getTypeKey());
        }
        positionPromptMap.put(postion * 10 + positionId, singlePrompt);
        singleLimitPromptMap.put(limit.getTypeKey(), positionPromptMap);

        updatePrompt();
        countStr = countItem(money, item, postion, positionId, discount, kei);
        return countStr;
    }

    public String countMoney(int postion, String money, LotteryLimitBean limit, int positionId, String discount) {
        return countMoney(postion, money, limit, positionId, discount, "0");
    }

    public void updatePrompt() {
        if (iCountLotteryBet != null) {
            checkLegal();
            LotteryPromptBean bean = new LotteryPromptBean(promptSingle, promptTotal);
            iCountLotteryBet.updatePrompt(bean);
        }
    }


    protected String countItem(String money, T item, int position, int positionId, String discount, String kei) {
        String count = "";
        double kv ;
        if (kei == null || kei.equals("")) {
            kv = 0;
        } else {
            try {
                kv = DecimalUtils.mul(Double.valueOf(money), (1 - Double.valueOf(discount) / 100) * Math.abs(Double.valueOf(kei)) / 100);
            } catch (Exception e) {
                kv = 0;
            }
        }
        if (!money.equals("") && discount != null && !discount.equals("")) {
            double d = DecimalUtils.mul(Double.valueOf(money), 1 - Double.valueOf(discount) / 100) + kv;
            count = DecimalUtils.decimalFormat(d, "0.00");
        }
        return count;
    }

    private float countTypeTotal(Map<Integer, Float> tmap) {
        float ft = 0f;
        Iterator<Map.Entry<Integer, Float>> it = tmap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Float> ent = it.next();
            ft += ent.getValue();
        }
        return ft;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder(context, parent, layoutResId);
        convert(holder, getItem(position), position);
        return holder.getView();
    }

    protected boolean checkLegal() {
        promptTotal = "";

        Iterator<Map.Entry<String, String>> totalIt = getTotalLimitPromptMap().entrySet().iterator();
        while (totalIt.hasNext()) {
            Map.Entry<String, String> t = totalIt.next();
            if (!t.getValue().equals("")) {
                promptTotal = t.getValue();
//                return false;
            }
        }
        promptSingle = "";
        Iterator<Map.Entry<String, Map<Integer, String>>> singleIt = getSingleLimitPromptMap().entrySet().iterator();
        while (singleIt.hasNext()) {
            Map.Entry<String, Map<Integer, String>> en = singleIt.next();
            Iterator<Map.Entry<Integer, String>> itm = en.getValue().entrySet().iterator();
            while (itm.hasNext()) {
                Map.Entry<Integer, String> enm = itm.next();
                if (!enm.getValue().equals("")) {
                    promptSingle = enm.getValue();
                    return false;
                }
            }
        }
        return true;
    }

    protected abstract void countTotal();

}
