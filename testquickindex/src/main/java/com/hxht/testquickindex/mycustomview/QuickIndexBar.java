package com.hxht.testquickindex.mycustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View {

    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E",
            "F", "j", "H", "I", "G",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y",
            "Z"
    };
    private int cellWindth;
    private float cellHeight;
    private Paint paint;

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics()));
    }

    /**
     * 绘制控件
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // 父类什么也没做，还是我们自己实现吧
        // super.onDraw(canvas);

        //画出二十六个英文字母
        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];
            //拿到字符串的宽度
            float textWidth = paint.measureText(letter);

            //拿到字符串的高度
            Rect rect = new Rect();
            paint.getTextBounds(letter, 0, letter.length(), rect);
            int textHeight = rect.height();

            //计算drawText的横纵坐标
            //文本的横坐标 = 每个单元的宽度/2 - 字符串的宽度/2
            //文本的纵坐标 = 每个单元的高度/2 + 字符串的高度/2 + 每个单元的高度 * 单元的个数
            int X = (int) (cellWindth / 2 - textWidth / 2);
            int Y = (int) (cellHeight / 2 + textHeight / 2 + cellHeight * i);


            paint.setColor(touchIndex == i?Color.RED:Color.WHITE);

            canvas.drawText(letter, X, Y, paint);
        }
    }

    /**
     * 在xml加载完成后调用此方法：
     * 用来找到this的子View
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     * 在此方法中拿到this本控件的宽高值
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //父类什么也没做，待我们自己去实现吧
        //super.onSizeChanged(w, h, oldw, oldh);

        //拿到控件的总高度(在此处控件由于是match_parent,故控件的高度就是屏幕的高度)
        int measuredHeight = getMeasuredHeight();

        //拿到控件的宽度，也即每个单元格的宽度
        cellWindth = getMeasuredWidth();
        //拿到控件中二十六个英文字母每个单元格的高度
        cellHeight = measuredHeight * 1.0f / LETTERS.length;
    }

    int touchIndex = -1;

    public interface OnUpdateLetterListener {
        void updateLetter(String letter);
    }

    private OnUpdateLetterListener onUpdateLetterListener;

    public OnUpdateLetterListener getOnUpdateLetterListener() {
        return onUpdateLetterListener;
    }

    public void setOnUpdateLetterListener(OnUpdateLetterListener onUpdateLetterListener) {
        this.onUpdateLetterListener = onUpdateLetterListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (MotionEventCompat.getActionMasked(event)) {
            case MotionEvent.ACTION_DOWN:
                float downY = event.getY();
                int downIndex = (int) (downY / cellHeight);
                if (touchIndex != downIndex) {
                    //代码检查----增加代码的健壮性
                    //说明角标有效
                    if (downIndex > 0 && downIndex < LETTERS.length) {
                        String letter = LETTERS[downIndex];
                        if (onUpdateLetterListener != null){
                            onUpdateLetterListener.updateLetter(letter);
                        }
                    }
                    touchIndex = downIndex;
                }

                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();
                int moveIndex = (int) (moveY / cellHeight);
                if (touchIndex != moveIndex) {
                    //代码检查----增加代码的健壮性
                    //说明角标有效
                    if (moveIndex > 0 && moveIndex < LETTERS.length) {

                        String moveLetter = LETTERS[moveIndex];
                        if (onUpdateLetterListener != null){
                            onUpdateLetterListener.updateLetter(moveLetter);
                        }
                    }
                    touchIndex = moveIndex;
                }

                break;

            case MotionEvent.ACTION_UP:
                touchIndex = -1;
                break;
        }
        invalidate();
        return true;
    }
}
