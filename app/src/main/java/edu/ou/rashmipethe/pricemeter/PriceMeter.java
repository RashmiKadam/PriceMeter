package edu.ou.rashmipethe.pricemeter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.constraint.ConstraintLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * TODO: document your custom view class.
 */
public class PriceMeter extends ConstraintLayout {
    private String priceValue;
    private int priceColor = Color.RED;
    private int priceHeight = 0;
    private float priceMeter = 0;
    private float curPriceChange = 0;

    TextView priceText = null;
    Button plusBtn = null;
    Button minusBtn = null;
    Button oneCentBtn = null;
    Button fiveCentBtn = null;
    Button tfCentBtn = null;
    Button oneDollarBtn = null;
    Button fiveDollarBtn = null;

    private float oneCentValue = 0;
    private float fiveCentValue = 0;
    private float tfCentValue = 0;
    private float oneDollarValue = 0;
    private float fiveDollarValue = 0;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }

    public int getPriceColor() {
        return priceColor;
    }

    public void setPriceColor(int priceColor) {
        this.priceColor = priceColor;
    }

    public float getPriceHeight() {
        return priceHeight;
    }

    public void setPriceHeight(int priceHeight) {
        this.priceHeight = priceHeight;
    }

    public PriceMeter(Context context) {
        super(context);
        init(null, 0);
    }

    public PriceMeter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);

        //Inflate the view
        LayoutInflater inflater = LayoutInflater.from(context);
        ConstraintLayout container = (ConstraintLayout) inflater.inflate(R.layout.sample_price_meter, this);
        //Find the components within container
        priceText = container.findViewById(R.id.priceTextValue);
        priceMeter = Float.parseFloat(getPriceValue());
        priceText.setText(getPriceValue());
        plusBtn = container.findViewById(R.id.plusButton);
        minusBtn = container.findViewById(R.id.minusButton);
        oneCentBtn = container.findViewById(R.id.oneCent);
        oneCentValue = Float.parseFloat(oneCentBtn.getText().toString());
        fiveCentBtn = container.findViewById(R.id.fiveCent);
        fiveCentValue = Float.parseFloat(fiveCentBtn.getText().toString());
        tfCentBtn = container.findViewById(R.id.tfCent);
        tfCentValue = Float.parseFloat(tfCentBtn.getText().toString());
        oneDollarBtn = container.findViewById(R.id.oneDollar);
        oneDollarValue = Float.parseFloat(oneDollarBtn.getText().toString());
        fiveDollarBtn = container.findViewById(R.id.fiveDollar);
        fiveDollarValue = Float.parseFloat(fiveDollarBtn.getText().toString());

        //Set default priceChnage value to 0.01f
        oneCentBtn.setPressed(true);
        curPriceChange = 0.01f;

        plusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                priceMeter = Float.parseFloat(getPriceValue());
                float priceChange = priceMeter + curPriceChange;
                setPriceValue(String.valueOf(priceChange));
                priceText.setText(currencyFormat.format(priceChange));
            }
        });

        minusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                priceMeter = Float.parseFloat(getPriceValue());
                float priceChange = priceMeter - curPriceChange;
                setPriceValue(String.valueOf(priceChange));
                priceText.setText(currencyFormat.format(priceChange));
            }
        });

        oneCentBtn.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Clear the state of all buttons
                oneCentBtn.setPressed(false);
                fiveCentBtn.setPressed(false);
                tfCentBtn.setPressed(false);
                oneDollarBtn.setPressed(false);
                fiveDollarBtn.setPressed(false);

                curPriceChange = 0.01f;
                oneCentBtn.setPressed(true);
                return true;
            }
        }) ;

        fiveCentBtn.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Clear the state of all buttons
                oneCentBtn.setPressed(false);
                fiveCentBtn.setPressed(false);
                tfCentBtn.setPressed(false);
                oneDollarBtn.setPressed(false);
                fiveDollarBtn.setPressed(false);

                curPriceChange = 0.05f;
                fiveCentBtn.setPressed(true);
                return true;
            }
        });

        tfCentBtn.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Clear the state of all buttons
                oneCentBtn.setPressed(false);
                fiveCentBtn.setPressed(false);
                tfCentBtn.setPressed(false);
                oneDollarBtn.setPressed(false);
                fiveDollarBtn.setPressed(false);

                curPriceChange = 0.25f;
                tfCentBtn.setPressed(true);
                return true;
            }
        });

        oneDollarBtn.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Clear the state of all buttons
                oneCentBtn.setPressed(false);
                fiveCentBtn.setPressed(false);
                tfCentBtn.setPressed(false);
                oneDollarBtn.setPressed(false);
                fiveDollarBtn.setPressed(false);

                curPriceChange = 1f;
                oneDollarBtn.setPressed(true);
                return true;
            }
        });

        fiveDollarBtn.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Clear the state of all buttons
                oneCentBtn.setPressed(false);
                fiveCentBtn.setPressed(false);
                tfCentBtn.setPressed(false);
                oneDollarBtn.setPressed(false);
                fiveDollarBtn.setPressed(false);

                curPriceChange = 5f;
                fiveDollarBtn.setPressed(true);
                return true;
            }
        });
    }

    public PriceMeter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PriceMeter, defStyle, 0);

        priceValue = a.getString(R.styleable.PriceMeter_priceValue);
        priceColor = a.getColor(R.styleable.PriceMeter_priceColor, Color.BLUE);
        priceHeight = (int) a.getDimension(R.styleable.PriceMeter_priceHeight, (int)64);

        Drawable circleDrawable = getResources().getDrawable(R.drawable.circle);
        if(circleDrawable instanceof ShapeDrawable){
            ShapeDrawable drawableShape = (ShapeDrawable) circleDrawable;
            drawableShape.getPaint().setColor(priceColor);
        } else if(circleDrawable instanceof GradientDrawable){
            GradientDrawable gd = (GradientDrawable) circleDrawable;
            gd.setColor(priceColor);
        }

        circleDrawable.setBounds(0,0,priceHeight,priceHeight);

        a.recycle();
    }


}