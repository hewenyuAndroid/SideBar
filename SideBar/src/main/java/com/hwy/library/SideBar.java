package com.hwy.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: hewenyu
 * 日期: 2018/7/19 9:42
 * 说明:
 */
public class SideBar extends View {

    /**
     * 默认的索引栏的宽度
     */
    private static final int DEFAULT_SIDEBAR_WIDTH = 40;
    /**
     * 索引栏默认的左右/内外间距
     */
    private static final int DEFAULT_SPACING = 0;

    /**
     * 索引栏默认的背景色
     */
    private static final int DEFAULT_SIDEBAR_COLOR = Color.GRAY;

    /**
     * 默认的字符大小
     */
    private static final int DEFAULT_LETTER_SIZE = 20;

    /**
     * 索引栏默认的字符颜色
     */
    private static final int DEFAULT_SIDEBAR_LETTER_COLOR = Color.BLACK;

    /**
     * 索引栏字符默认选中的颜色
     */
    private static final int DEFAULT_SIDEBAR_LETTER_SELECT_COLOR = Color.RED;

    /**
     * 索引栏、弹窗文字默认的宽度
     */
    private static final float DEFAULT_LETTER_WIDTH = 1f;

    /**
     * 弹窗与控件宽度默认的百分比
     */
    private static final float DEFAULT_DIALOG_SIZE_PERCENT = 0.12f;

    /**
     * 弹窗默认的圆角半径
     */
    private static final int DEFAULT_DIALOG_CORNER = 10;

    /**
     * 弹窗字符的颜色
     */
    private static final int DEFAULT_DIALOG_LETTER_COLOR = Color.WHITE;

    private static final float DEFAULT_DIALOG_HORIZONTAL_PERCENT = 0.5f;

    // ---------------------

    /**
     * 索引栏、索引栏字符、弹窗、弹窗字符的画笔
     */
    private Paint mSideBarPaint, mSideBarLetterPaint, mDialogPaint, mDialogLetterPaint;

    /**
     * 索引栏的宽度
     */
    private int mSideBarWidth = DEFAULT_SIDEBAR_WIDTH;

    /**
     * 索引栏的左右上下间距
     */
    private int mSideBarMarginTop = DEFAULT_SPACING;
    private int mSideBarMarginBottom = DEFAULT_SPACING;
    private int mSideBarPaddingTop = DEFAULT_SPACING;
    private int mSideBarPaddingBottom = DEFAULT_SPACING;
    private int mSideBarSpacing = DEFAULT_SPACING;

    /**
     * 索引栏的位置
     */
    private SideBarPosition mSideBarPosition = SideBarPosition.RIGHT;

    /**
     * 索引栏默认颜色
     */
    private int mSideBarNormalColor = DEFAULT_SIDEBAR_COLOR;
    /**
     * 索引栏选中的颜色
     */
    private int mSideBarPressColor = DEFAULT_SIDEBAR_COLOR;

    /**
     * 是否显示索引栏默认的背景色
     */
    private boolean showSideBarNormalColor = false;

    /**
     * 索引栏两端的形状
     */
    private SideBarCap mSideBarCap = SideBarCap.ROUND;

    /**
     * 索引栏上文字的颜色
     */
    private int mSideBarLetterSize = DEFAULT_LETTER_SIZE;

    /**
     * 索引栏默认的文字颜色
     */
    private int mSideBarLetterNormalColor = DEFAULT_SIDEBAR_LETTER_COLOR;

    /**
     * 索引栏被选中的文字的颜色
     */
    private int mSideBarLetterSelectColor = DEFAULT_SIDEBAR_LETTER_SELECT_COLOR;

    /**
     * 索引栏字符默认的宽度
     */
    private float mSideBarLetterNormalWidth = DEFAULT_LETTER_WIDTH;

    /**
     * 索引栏按压时字符的宽度
     */
    private float mSideBarLetterPressWidth = DEFAULT_LETTER_WIDTH;

    // ------------

    /**
     * 弹窗默认的背景色
     */
    private int mDialogColor = DEFAULT_SIDEBAR_COLOR;

    /**
     * 弹窗与控件宽度的百分比
     */
    private float mDialogSizePercent = DEFAULT_DIALOG_SIZE_PERCENT;

    /**
     * 弹窗的形状
     */
    private DialogShape mDialogShape = DialogShape.SQUARE;

    /**
     * 弹窗的圆角半径（square时有效）
     */
    private int mDialogCorner = DEFAULT_DIALOG_CORNER;

    /**
     * 弹窗字符的大小
     */
    private int mDialogLetterSize = DEFAULT_LETTER_SIZE;

    /**
     * 弹窗字符的颜色
     */
    private int mDialogLetterColor = DEFAULT_DIALOG_LETTER_COLOR;

    /**
     * 弹窗字符的宽度
     */
    private float mDialogLetterWidth = DEFAULT_LETTER_WIDTH;

    /**
     * 弹窗的位置是否固定
     */
    private boolean dialogIsFixed = true;

    /**
     * 弹窗的背景色集合
     */
    private List<Integer> mDialogColors;

    /**
     * 弹窗中心在控件的水平位置
     */
    private float mDialogHorizontalPercent = DEFAULT_DIALOG_HORIZONTAL_PERCENT;

    private int mWidth, mHeight;

    /**
     * 索引栏每个字符的高度
     */
    private float mItemHeight;

    /**
     * 当前选中字符的位置
     */
    private int mCurrPosition = -1;

    /**
     * 当前弹窗中心的 y 值
     */
    private float mCurrY;

    /**
     * 是否触发了sidebar的按压事件
     */
    private boolean isPress = false;

    /**
     * 索引的字符串数组
     */
    private String[] mLetters = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"
            , "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    public SideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SideBar);
        mSideBarWidth = typedArray.getDimensionPixelSize(R.styleable.SideBar_sideBarWidth, DEFAULT_SIDEBAR_WIDTH);
        mSideBarMarginTop = typedArray.getDimensionPixelSize(R.styleable.SideBar_sideBarMarginTop, DEFAULT_SPACING);
        mSideBarMarginBottom = typedArray.getDimensionPixelSize(R.styleable.SideBar_sideBarMarginBottom, DEFAULT_SPACING);
        mSideBarPaddingTop = typedArray.getDimensionPixelSize(R.styleable.SideBar_sideBarPaddingTop, DEFAULT_SPACING);
        mSideBarPaddingBottom = typedArray.getDimensionPixelSize(R.styleable.SideBar_sideBarPaddingBottom, DEFAULT_SPACING);
        mSideBarSpacing = typedArray.getDimensionPixelSize(R.styleable.SideBar_sideBarSpacing, DEFAULT_SPACING);
        mSideBarPosition = SideBarPosition
                .values()[typedArray.getInt(R.styleable.SideBar_sideBarPosition, SideBarPosition.RIGHT.getIndex())];
        mSideBarNormalColor = typedArray.getColor(R.styleable.SideBar_sideBarNormalColor, DEFAULT_SIDEBAR_COLOR);
        mSideBarPressColor = typedArray.getColor(R.styleable.SideBar_sideBarPressColor, DEFAULT_SIDEBAR_COLOR);
        showSideBarNormalColor = typedArray.getBoolean(R.styleable.SideBar_showSideBarNormalColor, false);
        mSideBarCap = SideBarCap
                .values()[typedArray.getInt(R.styleable.SideBar_sideBarCap, SideBarCap.ROUND.getIndex())];
        mSideBarLetterSize = typedArray.getDimensionPixelSize(R.styleable.SideBar_sideBarLetterSize, DEFAULT_LETTER_SIZE);
        mSideBarLetterNormalColor = typedArray.getColor(R.styleable.SideBar_sideBarLetterNormalColor, DEFAULT_SIDEBAR_LETTER_COLOR);
        mSideBarLetterSelectColor = typedArray.getColor(R.styleable.SideBar_sideBarLetterSelectColor, DEFAULT_SIDEBAR_LETTER_SELECT_COLOR);
        mSideBarLetterNormalWidth = typedArray.getDimension(R.styleable.SideBar_sideBarLetterNormalWidth, DEFAULT_LETTER_WIDTH);
        mSideBarLetterPressWidth = typedArray.getDimension(R.styleable.SideBar_sideBarLetterPressWidth, DEFAULT_LETTER_WIDTH);
        mDialogColor = typedArray.getColor(R.styleable.SideBar_dialogColor, DEFAULT_SIDEBAR_COLOR);
        mDialogSizePercent = typedArray.getFloat(R.styleable.SideBar_dialogSizePercent, DEFAULT_DIALOG_SIZE_PERCENT);
        mDialogShape = DialogShape
                .values()[typedArray.getInt(R.styleable.SideBar_dialogShape, DialogShape.CIRCLE.getIndex())];
        mDialogCorner = typedArray.getDimensionPixelSize(R.styleable.SideBar_dialogCorner, DEFAULT_DIALOG_CORNER);
        mDialogLetterSize = typedArray.getDimensionPixelSize(R.styleable.SideBar_dialogLetterSize, DEFAULT_LETTER_SIZE);
        mDialogLetterColor = typedArray.getColor(R.styleable.SideBar_dialogLetterColor, DEFAULT_DIALOG_LETTER_COLOR);
        mDialogLetterWidth = typedArray.getDimension(R.styleable.SideBar_dialogLetterWidth, DEFAULT_LETTER_WIDTH);
        dialogIsFixed = typedArray.getBoolean(R.styleable.SideBar_dialogIsFixed, true);
        mDialogHorizontalPercent = typedArray.getFloat(R.styleable.SideBar_dialogHorizontalPercent, DEFAULT_DIALOG_HORIZONTAL_PERCENT);
        typedArray.recycle();

        checkParams();

        init();

    }

    /**
     * 检查参数
     */
    private void checkParams() {
        if (mDialogSizePercent > 1 || mDialogSizePercent < 0) { // 弹窗百分比
            mDialogSizePercent = DEFAULT_DIALOG_SIZE_PERCENT;
        }
        if (mDialogHorizontalPercent > 1 || mDialogHorizontalPercent < 0) { // 弹窗水平方向的位置比例
            mDialogHorizontalPercent = DEFAULT_DIALOG_HORIZONTAL_PERCENT;
        }

        if (mSideBarMarginBottom < 0) {
            mSideBarMarginBottom = DEFAULT_SPACING;
        }

        if (mSideBarMarginTop < 0) {
            mSideBarMarginTop = DEFAULT_SPACING;
        }

        if (mSideBarPaddingTop < 0) {
            mSideBarPaddingTop = DEFAULT_SPACING;
        }

        if (mSideBarPaddingBottom < 0) {
            mSideBarPaddingBottom = DEFAULT_SPACING;
        }

        if (mSideBarSpacing < 0) {
            mSideBarSpacing = DEFAULT_SPACING;
        }

    }

    private void init() {

        mSideBarPaint = getPaint();
        mSideBarPaint.setColor(mSideBarNormalColor);

        mSideBarLetterPaint = getPaint();
        mSideBarLetterPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mSideBarLetterPaint.setStrokeWidth(mSideBarLetterNormalWidth);
        mSideBarLetterPaint.setTextSize(mSideBarLetterSize);
        mSideBarLetterPaint.setTextAlign(Paint.Align.CENTER);

        mDialogPaint = getPaint();
        mDialogPaint.setColor(mDialogColor);

        mDialogLetterPaint = getPaint();
        mDialogLetterPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mDialogLetterPaint.setColor(mDialogLetterColor);
        mDialogLetterPaint.setTextSize(mDialogLetterSize);
        mDialogLetterPaint.setStrokeWidth(mDialogLetterWidth);
        mDialogLetterPaint.setTextAlign(Paint.Align.CENTER);

        mDialogColors = new ArrayList<>();

    }

    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        // 计算每个字符的高度
        mItemHeight = (mHeight - mSideBarMarginTop - mSideBarMarginBottom - mSideBarPaddingTop - mSideBarPaddingBottom) / (float) mLetters.length;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Rect rect = calculateSideBarPos();

        float x = -1;
        float y = -1;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                if (x < rect.left || x > rect.right || y < rect.top || y > rect.bottom) {   // 不处理此次触摸事件
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x = -1;
                y = -1;
                break;
            case MotionEvent.ACTION_CANCEL:
                x = -1;
                y = -1;
                break;
        }

        if (x != -1 && y != -1) {
            isPress = true;
            int lastPos = mCurrPosition;
            calculatePosition(y);
            if (mCurrPosition != -1
                    && lastPos != mCurrPosition) {  // 这里记录上次的position防止同一个字符多次传出

                if (onLetterUpdateListener != null) {
                    onLetterUpdateListener.onLetterUpdate(mLetters[mCurrPosition]);
                }

            }

        } else {    // 不处理此次触摸事件
            isPress = false;
            mCurrPosition = -1;
            mCurrY = -1;
        }
        // 重绘
        invalidate();
        return true;
    }

    /**
     * 计算触摸的位置
     *
     * @param y
     */
    private void calculatePosition(float y) {
        mCurrPosition = -1;

        float startY = mSideBarMarginTop + mSideBarPaddingTop;
        float endY = mHeight - mSideBarMarginBottom - mSideBarPaddingBottom;

        // 当前的y的位置
        mCurrY = y;
        if (mCurrY <= startY) {
            mCurrY = startY;
        } else if (mCurrY >= endY) {
            mCurrY = endY;
        }

        for (int i = 0; i < mLetters.length; i++) {
            if (y >= startY && y < startY + mItemHeight) {
                mCurrPosition = i;
                break;
            }
            startY += mItemHeight;
        }

        // sidebar 范围之外的y值，取sidebar两端的字符
        if (mCurrPosition == -1) {
            if (y <= startY && mLetters.length > 0) {
                mCurrPosition = 0;
            } else if (y >= endY && mLetters.length > 0) {
                mCurrPosition = mLetters.length - 1;
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制索引栏背景
        drawSideBar(canvas);
        // 绘制索引栏字符
        drawSideBarLetter(canvas);
        // 绘制弹窗
        drawDialog(canvas);

    }

    /**
     * 绘制字符弹窗
     *
     * @param canvas
     */
    private void drawDialog(Canvas canvas) {
        if (!isPress || mCurrPosition == -1) { // 不显示弹窗
            return;
        }

        // 计算弹窗的半径
        float dialogRadius = ((mWidth - mSideBarWidth - mSideBarSpacing) * mDialogSizePercent) / 2;

        // 默认圆心
        float cx = 0;
        float cy = mHeight / 2;

        if (mSideBarPosition == SideBarPosition.LEFT) {
            cx = (mWidth - mSideBarWidth - mSideBarSpacing - dialogRadius * 2) * mDialogHorizontalPercent;
            cx += dialogRadius + mSideBarWidth + mSideBarSpacing;

        } else if (mSideBarPosition == SideBarPosition.RIGHT) {
            cx = (mWidth - mSideBarWidth - mSideBarSpacing - dialogRadius * 2) * mDialogHorizontalPercent;
            cx += dialogRadius;
        }

        if (!dialogIsFixed) {
            // 弹窗位置不固定
            cy = mCurrY;
            if (cy + dialogRadius > mHeight) {
                cy = mHeight - dialogRadius;
            }
            if (cy - dialogRadius < 0) {
                cy = dialogRadius;
            }
        }

        float startX = cx - dialogRadius;
        float startY = cy - dialogRadius;
        float endX = cx + dialogRadius;
        float endY = cy + dialogRadius;

        if (mDialogColors.isEmpty()) {
            mDialogPaint.setColor(mDialogColor);
        } else {
            mDialogPaint.setColor(
                    mDialogColors.get(
                            mCurrPosition % mDialogColors.size()));
        }

        if (mDialogShape == DialogShape.SQUARE) {   // 方形弹窗

            Path path = new Path();

            path.moveTo(startX, startY - mDialogCorner);
            path.arcTo(
                    new RectF(startX, startY, startX + mDialogCorner * 2, startY + mDialogCorner * 2),
                    180, 90, false
            );
            path.lineTo(endX - mDialogCorner, startY);
            path.arcTo(
                    new RectF(endX - mDialogCorner * 2, startY, endX, startY + mDialogCorner * 2),
                    270, 90, false
            );
            path.lineTo(endX, endY - mDialogCorner);
            path.arcTo(
                    new RectF(endX - mDialogCorner * 2, endY - mDialogCorner * 2, endX, endY),
                    0, 90, false
            );
            path.lineTo(startX + mDialogCorner, endY);
            path.arcTo(
                    new RectF(startX, endY - mDialogCorner * 2, startX + mDialogCorner * 2, endY),
                    90, 90, false
            );
            path.lineTo(startX, startY + mDialogCorner);

            canvas.drawPath(path, mDialogPaint);

        } else if (mDialogShape == DialogShape.CIRCLE) {    // 圆形弹窗

            canvas.drawCircle(cx, cy, dialogRadius, mDialogPaint);

        }

        // 绘制弹窗字符
        String letter = mLetters[mCurrPosition];
        float baseLine = cy + calculatePaintBaseLine(mDialogLetterPaint);
        canvas.drawText(letter, cx, baseLine, mDialogLetterPaint);

    }

    /**
     * 绘制索引栏的字符
     *
     * @param canvas
     */
    private void drawSideBarLetter(Canvas canvas) {
        Rect rect = calculateSideBarPos();

        if (isPress) {  // 根据按压状态设置字符的宽度
            mSideBarLetterPaint.setStrokeWidth(mSideBarLetterPressWidth);
        } else {
            mSideBarLetterPaint.setStrokeWidth(mSideBarLetterNormalWidth);
        }

        // 获取字符画笔默认的基线
        float paintBaseLine = calculatePaintBaseLine(mSideBarLetterPaint);

        // 字符的高度的起始位置
        float startY = rect.top + mSideBarPaddingTop;

        for (int i = 0; i < mLetters.length; i++) {
            String letter = mLetters[i];

            if (isPress && mCurrPosition == i) {    // 当前字符高亮
                mSideBarLetterPaint.setColor(mSideBarLetterSelectColor);
            } else {    // 默认字符颜色
                mSideBarLetterPaint.setColor(mSideBarLetterNormalColor);
            }

            // 绘制字符
            canvas.drawText(letter, rect.left + mSideBarWidth / 2, startY + mItemHeight / 2 + paintBaseLine, mSideBarLetterPaint);

            startY += mItemHeight;
        }

    }

    /**
     * 绘制索引栏的背景
     *
     * @param canvas
     */
    private void drawSideBar(Canvas canvas) {

        if (!isPress    //默认状态
                && !showSideBarNormalColor) {  // 不显示默认状态的sidebar背景
            return;
        }

        Rect rect = calculateSideBarPos();
        Path path = new Path();
        if (mSideBarCap == SideBarCap.ROUND) {
            // 圆形头
            int radius = mSideBarWidth / 2;
            path.moveTo(rect.left, rect.top - radius);
            path.arcTo(new RectF(rect.left, rect.top, rect.right, rect.top + 2 * radius), 180, 180, false);
            path.lineTo(rect.right, rect.bottom - radius);
            path.arcTo(new RectF(rect.left, rect.bottom - 2 * radius, rect.right, rect.bottom), 0, 180, false);
            path.lineTo(rect.left, rect.top - radius);

        } else if (mSideBarCap == SideBarCap.NORMAL) {
            // 方形头
            path.moveTo(rect.left, rect.top);
            path.lineTo(rect.right, rect.top);
            path.lineTo(rect.right, rect.bottom);
            path.lineTo(rect.left, rect.bottom);
            path.lineTo(rect.left, rect.top);
        }

        if (isPress) {
            // 按压的状态的颜色
            mSideBarPaint.setColor(mSideBarPressColor);
        } else {
            // 默认状态的颜色
            mSideBarPaint.setColor(mSideBarNormalColor);
        }

        canvas.drawPath(path, mSideBarPaint);
    }

    /**
     * 计算SideBar的位置
     *
     * @return
     */
    private Rect calculateSideBarPos() {
        Rect rect = new Rect();

        rect.top = mSideBarMarginTop;
        rect.bottom = mHeight - mSideBarMarginBottom;

        switch (mSideBarPosition) {
            case LEFT:
                rect.left = mSideBarSpacing;
                rect.right = mSideBarSpacing + mSideBarWidth;
                break;
            case RIGHT:
                rect.left = mWidth - mSideBarSpacing - mSideBarWidth;
                rect.right = mWidth - mSideBarSpacing;
                break;
            default:
                rect.left = mWidth - mSideBarSpacing - mSideBarWidth;
                rect.right = mWidth - mSideBarSpacing;
                break;
        }

        return rect;
    }

    /**
     * 计算画笔的基线
     *
     * @param paint
     * @return
     */
    private float calculatePaintBaseLine(Paint paint) {
        Paint.FontMetrics metrics = paint.getFontMetrics();
        return Math.abs(metrics.descent + metrics.ascent) / 2;
    }


    /**
     * 添加弹窗的背景色
     *
     * @param colors
     */
    public void addDialogColors(int... colors) {
        for (int color : colors) {
            mDialogColors.add(color);
        }
    }

    /**
     * 设置索引字符集
     *
     * @param letters
     */
    public void setLetters(String[] letters) {
        if (letters != null && letters.length > 0) {
            mLetters = letters;
            requestLayout();
        }
    }

    /**
     * 清空弹窗的背景色列表，使用单一的弹窗背景色
     */
    public void clearDialogColors() {
        mDialogColors.clear();
    }

    public void setSideBarWidth(int sideBarWidth) {
        mSideBarWidth = sideBarWidth;
        invalidate();
    }

    public int getSideBarWidth() {
        return mSideBarWidth;
    }

    public void setSideBarNormalColor(int color) {
        mSideBarNormalColor = color;
    }

    public int getSideBarNormalColor() {
        return mSideBarNormalColor;
    }

    public void setSideBarPressColor(int color) {
        mSideBarPressColor = color;
    }

    public int getSideBarPressColor() {
        return mSideBarPressColor;
    }

    public void setSideBarMarginTop(int marginTop) {
        mSideBarMarginTop = marginTop;
        checkParams();
        requestLayout();
    }

    public int getSideBarMarginTop() {
        return mSideBarMarginTop;
    }

    public void setSideBarMarginBottom(int marginBottom) {
        mSideBarMarginBottom = marginBottom;
        checkParams();
        requestLayout();
    }

    public int getSideBarMarginBottom() {
        return mSideBarMarginBottom;
    }

    public void setSideBarPaddingTop(int paddingTop) {
        mSideBarPaddingTop = paddingTop;
        checkParams();
        requestLayout();
    }

    public int getSideBarPaddingTop() {
        return mSideBarPaddingTop;
    }

    public void setSideBarPaddingBottom(int paddingBottom) {
        mSideBarPaddingBottom = paddingBottom;
        checkParams();
        requestLayout();
    }

    public int getSideBarPaddingBottom() {
        return mSideBarPaddingBottom;
    }

    public void setSideBarSpacing(int spacing) {
        mSideBarSpacing = spacing;
        checkParams();
    }

    public int getSideBarSpacing() {
        return mSideBarSpacing;
    }

    public void setSideBarLetterNormalColor(int color) {
        mSideBarLetterNormalColor = color;
        invalidate();
    }

    public int getSideBarLetterNormalColor() {
        return mSideBarLetterNormalColor;
    }

    public void setSideBarLetterSelectColor(int color) {
        mSideBarLetterSelectColor = color;
    }

    public int getSideBarLetterSelectColor() {
        return mSideBarLetterSelectColor;
    }

    public void setSideBarLetterNormalWidth(float width) {
        mSideBarLetterNormalWidth = width;
        invalidate();
    }

    public float getSideBarLetterNormalWidth() {
        return mSideBarLetterNormalWidth;
    }

    public void setSideBarLetterPressWidth(float width) {
        mSideBarLetterPressWidth = width;
    }

    public float getSideBarLetterPressWidth() {
        return mSideBarLetterPressWidth;
    }

    public void setSideBarLetterSize(int letterSize) {
        mSideBarLetterSize = letterSize;
        mSideBarLetterPaint.setTextSize(mSideBarLetterSize);
    }

    public int getSideBarLetterSize() {
        return mSideBarLetterSize;
    }

    public void setSideBarCap(SideBarCap cap) {
        mSideBarCap = cap;
        invalidate();
    }

    public SideBarCap getSideBarCap() {
        return mSideBarCap;
    }

    public void setSideBarPosition(SideBarPosition position) {
        mSideBarPosition = position;
        invalidate();
    }

    public SideBarPosition getSideBarPosition() {
        return mSideBarPosition;
    }

    public void setShowSideBarNormalColor(boolean isShow) {
        showSideBarNormalColor = isShow;
        invalidate();
    }

    public boolean isShowSideBarNormalColor() {
        return showSideBarNormalColor;
    }

    public void setDialogColor(int color) {
        mDialogColor = color;
        mDialogPaint.setColor(mDialogColor);
    }

    public int getDialogColor() {
        return mDialogColor;
    }

    public void setDialogShape(DialogShape shape) {
        mDialogShape = shape;
    }

    public DialogShape getDialogShape() {
        return mDialogShape;
    }

    public void setDialogCorner(int corner) {
        mDialogCorner = corner;
    }

    public int getDialogCorner() {
        return mDialogCorner;
    }

    public void setDialogLetterColor(int color) {
        mDialogLetterColor = color;
        mDialogLetterPaint.setColor(mDialogLetterColor);
    }

    public int getDialogLetterColor() {
        return mDialogLetterColor;
    }

    public void setDialogLetterSize(int letterSize) {
        mDialogLetterSize = letterSize;
        mDialogLetterPaint.setTextSize(mDialogLetterSize);
    }

    public int getDialogLetterSize() {
        return mDialogLetterSize;
    }

    public void setDialogLetterWidth(float letterWidth) {
        mDialogLetterWidth = letterWidth;
        mDialogLetterPaint.setStrokeWidth(mDialogLetterWidth);
    }

    public float getDialogLetterWidth() {
        return mDialogLetterWidth;
    }

    public void setDialogHorizontalPercent(float percent) {
        mDialogHorizontalPercent = percent;
        checkParams();
    }

    public float getDialogHorizontalPercent() {
        return mDialogHorizontalPercent;
    }

    public void setDialogSizePercent(float percent) {
        mDialogSizePercent = percent;
        checkParams();
    }

    public float getDialogSizePercent() {
        return mDialogSizePercent;
    }

    public void setDialogIsFixed(boolean isFixed) {
        dialogIsFixed = isFixed;
    }

    public boolean isDialogIsFixed() {
        return dialogIsFixed;
    }

    // ---------------------

    private OnLetterUpdateListener onLetterUpdateListener;

    public void setOnLetterUpdateListener(OnLetterUpdateListener onLetterUpdateListener) {
        this.onLetterUpdateListener = onLetterUpdateListener;
    }

    /**
     * 字符切换监听
     */
    public interface OnLetterUpdateListener {

        void onLetterUpdate(String letter);

    }

    /**
     * 索引栏的位置
     */
    public enum SideBarPosition {
        LEFT(0), RIGHT(1);

        private int mIndex;

        SideBarPosition(int index) {
            mIndex = index;
        }

        public int getIndex() {
            return mIndex;
        }

    }

    /**
     * 索引栏两端的样式
     */
    public enum SideBarCap {
        NORMAL(0), ROUND(1);

        private int mIndex;

        SideBarCap(int index) {
            mIndex = index;
        }

        public int getIndex() {
            return mIndex;
        }
    }

    /**
     * 字符谈拆给你的形状
     */
    public enum DialogShape {
        CIRCLE(0), SQUARE(1);

        private int mIndex;

        DialogShape(int index) {
            mIndex = index;
        }

        public int getIndex() {
            return mIndex;
        }

    }

}
