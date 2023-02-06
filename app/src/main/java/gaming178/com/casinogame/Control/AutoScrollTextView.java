package gaming178.com.casinogame.Control;
import android.content.Context;  
import android.graphics.Canvas;  
import android.graphics.Paint;  
import android.os.Parcel;  
import android.os.Parcelable;  
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class AutoScrollTextView extends TextView implements OnClickListener {

	public final static String TAG = AutoScrollTextView.class.getSimpleName();  
    public boolean isStarting = false;// �Ƿ�ʼ����
    private float textLength = 0f;// �ı�����
    private float viewWidth = 0f;
    private float step = 0f;// ���ֵĺ����
    private float y = 0f;// ���ֵ������
    private float temp_view_plus_text_length = 0.0f;// ���ڼ������ʱ����
    private float temp_view_plus_two_text_length = 0.0f;// ���ڼ������ʱ����
    private Paint paint = null;// ��ͼ��ʽ  
    private CharSequence text = "";// �ı�����  
    private float speed = 0.5f;  
    private int textColor=0xFF000000;  
      
    public AutoScrollTextView(Context context) {
        super(context);
        initView();
    }
  
    public AutoScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
  
    public AutoScrollTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }
  
    public int getTextColor() {
        return textColor;
    }
  
    public void setTextColor(int color) {
        this.textColor = color;
    }
  
    public float getSpeed() {
        return speed;
    }
  
    public void setSpeed(float speed) {
        this.speed = speed;
    }
  
    /** */  

    /**
     * ��ʼ���ؼ� 
     */  
    private void initView() {  
        setOnClickListener(this);  
    }  
  
    /** */  
    /** 
     * �ı���ʼ����ÿ�θ���ı����ݻ����ı�Ч���֮����Ҫ���³�ʼ��һ��! 
     */  
    public void init(float width/*WindowManager windowManager*/) {  
        text=super.getText();  
        paint = super.getPaint();  
//      Paint paint=new Paint();  
        text = getText().toString();  
        textLength = paint.measureText(text.toString());  
//      viewWidth = getWidth();  
//      if (viewWidth == 0) {  
//          if (windowManager != null) {  
//              Display display = windowManager.getDefaultDisplay();  
//              viewWidth = display.getWidth();  
//          }  
//      }  
        viewWidth=width;  
        step = textLength;  
        temp_view_plus_text_length = viewWidth + textLength;  
        temp_view_plus_two_text_length = viewWidth + textLength * 2;  
        y = getTextSize() + getPaddingTop();  
        paint.setColor(textColor);  
    }  
  
    @Override  
    public Parcelable onSaveInstanceState() {  
        Parcelable superState = super.onSaveInstanceState();  
        SavedState ss = new SavedState(superState);  
  
        ss.step = step;  
        ss.isStarting = isStarting;  
  
        return ss;  
  
    }  
  
    @Override  
    public void onRestoreInstanceState(Parcelable state) {  
        if (!(state instanceof SavedState)) {  
            super.onRestoreInstanceState(state);  
            return;  
        }  
        SavedState ss = (SavedState) state;  
        super.onRestoreInstanceState(ss.getSuperState());  
  
        step = ss.step;  
        isStarting = ss.isStarting;  
  
    }  
  
  /**
     * ��ʼ����
     */
    public void startScroll() {
        isStarting = true;
        invalidate();
    }

    /** */  

  /**
     * ֹͣ����
     */
    public void stopScroll() {
        isStarting = false;
        invalidate();
    }

    /** */  

  @Override
    public void onDraw(Canvas canvas) {
//      super.onDraw(canvas);
        if(canvas == null || text == null || "".equals(text))
        	return ;
        canvas.drawText(text,0,text.length(), temp_view_plus_text_length - step, y, paint);
        if (!isStarting) {
            return;
        }
        step += speed;
        if (step > temp_view_plus_two_text_length)
            step = textLength;
        invalidate();

    }

    @Override
    public void onClick(View v) {
        if (isStarting)
            stopScroll();
        else
            startScroll();

    }


      public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }

            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }
        };
        public boolean isStarting = false;
        public float step = 0.0f;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {

            super(in);

            if(in != null){
            	boolean[] b = null;

                try {
					in.readBooleanArray(b);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                if (b != null && b.length > 0)
                    isStarting = b[0];
                step = in.readFloat();
        	}

        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeBooleanArray(new boolean[] { isStarting });
            out.writeFloat(step);
        }
    }
 }
