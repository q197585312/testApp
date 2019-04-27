package com.nanyang.app.Utils;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanyang.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdw on 2016/3/29.
 */
public class FlowLayout extends ViewGroup implements View.OnClickListener {


    /**
     * 所有子View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();

    /**
     * 记录每一行的最大高度
     */
    private  List<Integer> mLineHeight = new ArrayList<>();


    /**
     * 标签点击的回调
     */
    private FlowLayoutListener mFlowLayoutListener;

    public FlowLayout(Context context) {
        super(context , null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        mAllViews.clear();
        mLineHeight.clear();

        // 获取当前父容器给当前控件的大小和模式
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //每一行的List
        List<View> lineView = new ArrayList<>();

        //   Log.e(TAG, sizeWidth + "," + sizeHeight);

        // 如果是warp_content情况下，记录宽和高
        int width = 0;
        int height = 0;

        /**
         * 记录每一行的宽度，width不断取最大宽度
         */
        int lineWidth = 0;
        /**
         * 每一行的高度，累加至height
         */
        int lineHeight = 0;

        /**
         * 当前控件的宽度
         */
        int cCount = getChildCount();

        // 遍历每个子元素
        for (int i = 0; i < cCount; i++)
        {
            View child = getChildAt(i);
            // 测量每一个child的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 得到child的lp
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();

            // 当前子空间实际占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            // 当前子空间实际占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin
                    + lp.bottomMargin;
            /**
             * 如果加入当前child，则超出最大宽度，则的到目前最大宽度给width，类加height 然后开启新行
             */
            if (lineWidth + childWidth > sizeWidth)
            {

                width = Math.max(lineWidth,width);// 取最大的

                lineWidth = childWidth; // 重新开启新行，开始记录

                //记录View
                mAllViews.add(lineView);
                lineView = new ArrayList<>();
                lineView.add(child);

                // 叠加当前高度，
                height += lineHeight;
                mLineHeight.add(lineHeight);

                // 开启记录下一行的高度
                lineHeight = childHeight;
            } else
            // 否则累加值lineWidth,lineHeight取最大高度
            {
                lineView.add(child);
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
            if (i == cCount - 1)
            {
                width = Math.max(width, lineWidth);
                height += lineHeight;
                mAllViews.add(lineView);
                mLineHeight.add(lineHeight);
            }

        }
        //设置当前控件的宽高
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth
                : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
                : height);
    }



    public void setFlowLayoutListener(FlowLayoutListener flowLayoutListener){
        mFlowLayoutListener = flowLayoutListener;
        for (int i = 0;i<getChildCount();i++){
            getChildAt(i).setOnClickListener(this);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();
        int lineHeight = 0;
        int left = 0;
        int top = 0;
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++)
        {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = mLineHeight.get(i);

            // 遍历当前行所有的View
            for (int j = 0; j < lineViews.size(); j++)
            {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE)
                {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();

                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc =lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.rightMargin
                        + lp.leftMargin;
            }
            left = 0;
            top += lineHeight;
        }
    }


    /**
     * 添加数据
     */
    public void addData(List<String> datas){
        for(String data: datas){
            addTextView(data);
        }
        requestLayout();
    }



    /**
     * 动态添加布局
     * @param str
     */
    private void addTextView(String str) {
        TextView child = new TextView(getContext());
        MarginLayoutParams params = new MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT);
        params.setMargins(15, 15, 15, 15);
        child.setLayoutParams(params);
        child.setBackgroundResource(R.drawable.shape_text_border);
        child.setText(str);
        child.setTextSize(18);
        child.setTextColor(Color.BLACK);
        //initEvents(child);
        this.addView(child);

    }

    @Override
    public void onClick(View v) {
        for (int i = 0;i<getChildCount();i++){
            if(getChildAt(i)==v){
                mFlowLayoutListener.onItemClick(v,i);
                break;
            }
        }
    }

    /**
     * 标签点击的回调
     */
    public interface  FlowLayoutListener{
        void onItemClick(View view, int poition);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
