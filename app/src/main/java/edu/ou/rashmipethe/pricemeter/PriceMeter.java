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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class PriceMeter extends ConstraintLayout {
    private String priceValue;
    private int priceColor = Color.RED;
    private int priceHeight = 0;
    private float pricemeter = 0;

    TextView priceText = null;
    Button plusBtn = null;
    Button minusBtn = null;

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
        pricemeter = Float.parseFloat(getPriceValue());
        priceText.setText(getPriceValue());
        plusBtn = container.findViewById(R.id.plusButton);
        minusBtn = container.findViewById(R.id.minusButton);

        plusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                priceText.setText(Float.toString(++pricemeter));
            }
        });

        minusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                priceText.setText(Float.toString(--pricemeter));
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