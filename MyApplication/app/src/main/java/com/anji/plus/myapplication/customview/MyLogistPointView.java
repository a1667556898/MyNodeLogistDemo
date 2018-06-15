package com.anji.plus.myapplication.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by SummerChen on 2018/6/8.
 * 物流节点view
 */

public class MyLogistPointView extends View {

    private Paint mPaint;//划线的画笔
    private Paint mTextPaint;//划字的画笔
    private Paint mBigPaint;//划大圆的画笔
    private ArrayList<MyLogistPointBean> myLogistPointBeans = new ArrayList<>();
    private float nodeRadius = 12;//小圆点半径
    private float bigNodeRadius = 14;//大圆点半径
    private float nodeInterval = 53;//节点间隔
    private int bottom = 0;//距离底部的距离 防止遮盖
    private int textSize = 26;//字体的大小
    private int bigtextSize = 30;//字体的大小

    public MyLogistPointView(Context context) {
        super(context);
        init(context);
    }


    public MyLogistPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyLogistPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        nodeInterval = dip2px(context, 53);
        bottom = dip2px(context, 10);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#A8A8A8"));
        mPaint.setStrokeWidth(1);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        textSize = dip2px(context, 12);
        bigtextSize = dip2px(context, 13);
    }

    public void setMyLogistPointBeans(ArrayList<MyLogistPointBean> myLogistPointBeans) {
        this.myLogistPointBeans = myLogistPointBeans;
        if (null == myLogistPointBeans || myLogistPointBeans.size() == 0) {
            return;
        }
          //给他个大小 如果是相对布局 下面用Relativelayout.pararm
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) this.getLayoutParams();
        params.height = (int) (myLogistPointBeans.size() * nodeInterval)+bottom ;
        this.setLayoutParams(params);
        //重新绘制
        invalidate();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //如果为空 不绘制
        if (null == myLogistPointBeans || myLogistPointBeans.size() == 0) {
            return;
        }
        //否则开始绘制
        canvas.translate(getWidth() / 3, getHeight() - bottom);

        for (int i = 0; i < myLogistPointBeans.size(); i++) {
            String textTime = myLogistPointBeans.get(i).getTime();
            String textState = myLogistPointBeans.get(i).getState();

            Rect rect = new Rect();
            mTextPaint.getTextBounds(textState, 0, textState.length(), rect);
            int textHeight = rect.height();
            int textTimeWidth = rect.width();
            //最后一个
            if (i == myLogistPointBeans.size() - 1) {

                mTextPaint.setTextSize(bigtextSize);
                mTextPaint.setColor(Color.parseColor("#040404"));
                //绘制线 +20是给上面留点空
                canvas.drawLine(0, -i * nodeInterval - bottom, 0, (-1 - i) * nodeInterval+20, mPaint);
                mPaint.setColor(Color.parseColor("#7A89FD"));
                //绘制圆
                canvas.drawCircle(0, (-i) * nodeInterval - bottom, bigNodeRadius, mPaint);
                //绘制左边文字
                canvas.drawText(textTime, -100, (-i) * nodeInterval - bottom + (textHeight / 2), mTextPaint);
                //绘制右边文字
                canvas.drawText(textState, 30, (-i) * nodeInterval - bottom + (textHeight / 2), mTextPaint);
            } else {
                mPaint.setColor(Color.parseColor("#A8A8A8"));
                mTextPaint.setColor(Color.parseColor("#A8A8A8"));

                //绘制线
                canvas.drawLine(0, -i * nodeInterval - bottom, 0, (-i - 1) * nodeInterval - bottom, mPaint);
                //绘制圆
                canvas.drawCircle(0, -i * nodeInterval - bottom, nodeRadius, mPaint);
                //绘制左边文字
                mTextPaint.setTextSize(textSize);
                canvas.drawText(textTime, -100, (-i) * nodeInterval - bottom + (textHeight / 2), mTextPaint);
                //绘制右边文字
                mTextPaint.setTextSize(bigtextSize);
                canvas.drawText(textState, 30, (-i) * nodeInterval - bottom + (textHeight / 2), mTextPaint);
            }
        }
        canvas.save();

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
