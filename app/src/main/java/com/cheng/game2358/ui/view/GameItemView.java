package com.cheng.game2358.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.ScaleAnimation;


public class GameItemView extends View {
    private static final String TAG = "GameItemView";
    private static final int MAX_VALUE = 16384;
    private static final SparseIntArray mBgColors = new SparseIntArray();
    private static final SparseIntArray mTextColors = new SparseIntArray();
    private static final SparseArray<String> mHaTexts = new SparseArray<>();


    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mViewWidth = 0;
    private int mTranslate = 0;

    private boolean mAnimating = true;

    /** 初始化颜色 */
    static {
        mBgColors.put(0     , Color.parseColor("#000000"));
        mBgColors.put(2     , Color.parseColor("#eee4da"));
        mBgColors.put(3     , Color.parseColor("#eddfc3"));
        mBgColors.put(5     , Color.parseColor("#f2b179"));
        mBgColors.put(8    , Color.parseColor("#f59d65"));
        mBgColors.put(13    , Color.parseColor("#f57f60"));
        mBgColors.put(21    , Color.parseColor("#f5623d"));
        mBgColors.put(34   , Color.parseColor("#edcf72"));
        mBgColors.put(55   , Color.parseColor("#edcc61"));
        mBgColors.put(89   , Color.parseColor("#edc850"));
        mBgColors.put(144  , Color.parseColor("#edc53f"));
        mBgColors.put(233  , Color.parseColor("#edc22e"));
        mBgColors.put(377  , Color.parseColor("#07B940"));
        mBgColors.put(610  , Color.parseColor("#8A2BE2"));
        mBgColors.put(987 , Color.parseColor("#F5DEB3"));
        mBgColors.put(1597 , Color.parseColor("#BC8F8F"));
        mBgColors.put(2584 , Color.parseColor("#40E0D0"));
        mBgColors.put(4181 , Color.parseColor("#EE82EE"));
        mBgColors.put(6765 , Color.parseColor("#E6E6FA"));
        mBgColors.put(10946 , Color.parseColor("#3CB371"));
        mBgColors.put(17711 , Color.parseColor("#00FF7F"));
        mBgColors.put(28657 , Color.parseColor("#2E8B57"));
        mBgColors.put(46368 , Color.parseColor("#ADFF2F"));
        mBgColors.put(75025 , Color.parseColor("#FAFAD2"));
        mBgColors.put(121393 , Color.parseColor("#F4A460"));

        mTextColors.put(2     , Color.parseColor("#8b8279"));
        mTextColors.put(3     , Color.parseColor("#776e65"));
        mTextColors.put(5     , Color.parseColor("#f9f6f2"));
        mTextColors.put(8    , Color.parseColor("#f9f5f1"));
        mTextColors.put(13    , Color.parseColor("#f9f6f2"));
        mTextColors.put(21    , Color.parseColor("#f8f5ef"));
        mTextColors.put(34   , Color.parseColor("#f8f5ee"));
        mTextColors.put(55  , Color.parseColor("#f6efd9"));
        mTextColors.put(89   , Color.parseColor("#f8f5f1"));
        mTextColors.put(144  , Color.parseColor("#f9f6f2"));
        mTextColors.put(233  , Color.parseColor("#BC8F8F"));
        mTextColors.put(377  , Color.parseColor("#f9f6f2"));
        mTextColors.put(610  , Color.parseColor("#8B4513"));
        mTextColors.put(987 , Color.parseColor("#f9f6f2"));
        mTextColors.put(1597 , Color.parseColor("#EEE8AA"));
        mTextColors.put(2584 , Color.parseColor("#7FFF00"));
        mTextColors.put(4181 , Color.parseColor("#5F9EA0"));
        mTextColors.put(6765 , Color.parseColor("#FFF0F5"));
        mTextColors.put(10946 , Color.parseColor("#9370DB"));
        mTextColors.put(17711 , Color.parseColor("#800000"));
        mTextColors.put(28657 , Color.parseColor("#B22222"));
        mTextColors.put(46368 , Color.parseColor("#FFEBCD"));
        mTextColors.put(75025 , Color.parseColor("#00FF00"));
        mTextColors.put(121393 , Color.parseColor("#FFDAB9"));

        mHaTexts.put(0     , "");
        mHaTexts.put(2     , "子鼠");
        mHaTexts.put(3     , "丑牛");
        mHaTexts.put(5     , "寅虎");
        mHaTexts.put(8    , "卯兔");
        mHaTexts.put(13    , "辰龙");
        mHaTexts.put(21    , "巳蛇");
        mHaTexts.put(34   , "午马");
        mHaTexts.put(55   , "未羊");
        mHaTexts.put(89   , "申猴");
        mHaTexts.put(144  , "酉鸡");
        mHaTexts.put(233  , "戌狗");
        mHaTexts.put(377  , "亥猪");
        mHaTexts.put(610  , "Rat charm");
        mHaTexts.put(987 , "Ox patient");
        mHaTexts.put(1597 , "Tiger sensitive");
        mHaTexts.put(2584 , "Rabbit articulate");
        mHaTexts.put(4181 , "Dragon healthy");
        mHaTexts.put(6765 , "Snake deep");
        mHaTexts.put(10946 , "Horse popular");
        mHaTexts.put(17711, "Goat elegant");
        mHaTexts.put(28657, "Monkey clever");
        mHaTexts.put(46368, "Rooster deep thinkers");
        mHaTexts.put(75025, "Dog loyalty");
        mHaTexts.put(121393, "Pig chivalrous");
    }

    /** 主画笔 */
    private Paint mPaint;

    /** 背景颜色 */
    private int mBgColor;
    /** 圆角大小 */
    private int mRadius = 5;

    /** item的数字*/
    private int mNumber;
    /** 数字转化为String类型*/
    private String mText;
    /** 绘制文字的画笔 */
    private Paint mTextPaint;
    /** 字体大小 */
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 23, getResources().getDisplayMetrics());
    /** 文字范围高度 */
    private int mTextHeight;
    /** 文字范围宽度 */
    private int mTextWidth;
    /** ha模式 */
    private boolean isHa;

    public GameItemView(Context context) {
        super(context);
        initView();
    }

    /** 初始化View */
    private void initView() {
        /** 初始化背景颜色 */
        mBgColor = mBgColors.get(0);

        /** 初始化主画笔 */
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);

        /** 初始化文字画笔 */
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setFakeBoldText(true);
//        mPaint.setTypeface(Typeface.DEFAULT_BOLD);

    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        if (mViewWidth == 0) {
//            mViewWidth = getMeasuredWidth();
//            if (mViewWidth > 0) {
////                mPaint = getPaint();
//                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,
//                        new int[] { 0x33006400, 0x8B0000, 0x33f5f5f5 },null, Shader.TileMode.CLAMP);
//                mTextPaint.setShader(mLinearGradient);
//                mGradientMatrix = new Matrix();
//            }
//        }
//    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawNumber(canvas);
    }

    /** 绘制背景 */
    private void drawBackground(Canvas canvas) {
        if(getNumber()>0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawRoundRect(0, 0, getWidth(), getHeight(), mRadius, mRadius, mPaint);
            } else {
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
            }
        }
    }

    /** 绘制数字*/
    private void drawNumber(Canvas canvas){
//        if (mAnimating && mGradientMatrix != null) {
//            mTranslate += mViewWidth / 10;
//            if (mTranslate > 2 * mViewWidth) {
//                mTranslate = -mViewWidth;
//            }
//            mGradientMatrix.setTranslate(mTranslate, 0);
//            mLinearGradient.setLocalMatrix(mGradientMatrix);
//            postInvalidateDelayed(36);
//        }

        if(getNumber()>0){
            if (isHa) {
                String[] strs = mText.split("\n");
                float x = 1.0F * (getWidth() - mTextWidth) / 2.0F;
                float y = 1.0F * (getHeight() - mTextHeight * strs.length) / 2.0F + this.mTextHeight;
                for (int i = 0; i < strs.length; i++)
                    canvas.drawText(strs[i], x, y + i * this.mTextHeight, this.mTextPaint);
            } else {
                float x = (getWidth()-mTextWidth)*1.0f/2;
                float y = getHeight()*1.0f/2+mTextHeight*1.0f/2;
                canvas.drawText(this.mText, x, y, this.mTextPaint);
            }
        }
    }

    /** 设置数字*/
    public void setNumber(int number) {
        /** 为0则隐藏 */
        if(number==0) {
            mText = isHa ? mHaTexts.get(number) : "";
            setVisibility(INVISIBLE);
        } else {
            mText = isHa ? mHaTexts.get(number) : String.valueOf(number);
            setVisibility(VISIBLE);
            if(mNumber<=0) {
                ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, getWidth() / 2, getHeight() / 2);
                setAnimation(sa);
                sa.setDuration(100);
                startAnimation(sa);
            }
        }
        this.mNumber = number;
        if(mNumber > MAX_VALUE) mText = "??\n??";

        /** 根据数字设置背景颜色 */
        mBgColor = mBgColors.get(number, mBgColors.get(0));
        mPaint.setColor(mBgColor);

        /** 根据数字设置文字画笔 */
        if(number>1000){
            mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 9, getResources().getDisplayMetrics());
        }else if(number>100){
            mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics());
        } else {
            mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 17, getResources().getDisplayMetrics());
        }
        if(isHa) {
            mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 11, getResources().getDisplayMetrics());
        }
        mTextPaint.setTextSize(mTextSize);
        Paint.FontMetricsInt textMetrics = mTextPaint.getFontMetricsInt();
        mTextHeight = (int) Math.ceil(textMetrics.leading-textMetrics.ascent);
        mTextWidth = (int) mTextPaint.measureText(mText);
        if (isHa) mTextWidth = (int) mTextPaint.measureText(mText);
//            mTextWidth /= 2;
        mTextPaint.setColor(mTextColors.get(number, mTextColors.get(2)));

        /** 重绘 */
        invalidate();
    }

    public int getNumber(){
        return mNumber;
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
        invalidate();
    }

    public void setHa(boolean isHa) {
        this.isHa = isHa;
        setNumber(mNumber);
    }
}
