package com.unkonw.testapp.libs.view;

import android.content.Context;
import android.os.Build;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
 
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
 

 
/**
 * @ClassName ：CustomGroup
 * @Description ：
 */
public class CustomGroup extends Group {
 
    public CustomGroup(Context context) {
        this(context, null);
    }
 
    public CustomGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
 
    public CustomGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
 
    @Override
    public void setVisibility(int visibility) {
        setReferenceViewsVisibility(visibility);
    }
 
    /**
     * 设置constraint_referenced_ids数组中的view的可见性
     *
     * @param visibility
     */
    private void setReferenceViewsVisibility(int visibility) {
        ViewParent parent = this.getParent();
        if (parent != null && parent instanceof ConstraintLayout) {
            ConstraintLayout container = (ConstraintLayout) parent;
            for (int i = 0; i < this.mCount; ++i) {
                int id = this.mIds[i];
                View view = container.getViewById(id);
                if (view != null) {
                    view.setVisibility(visibility);
                }
            }
        }
    }
 
    @Override
    protected void applyLayoutFeatures(ConstraintLayout container) {
        float elevation = 0.0F;
        if (Build.VERSION.SDK_INT >= 21) {
            elevation = this.getElevation();
        }
 
        for (int i = 0; i < this.mCount; ++i) {
            int id = this.mIds[i];
            View view = container.getViewById(id);
            if (view != null) {
                if (elevation > 0.0F && Build.VERSION.SDK_INT >= 21) {
                    view.setTranslationZ(view.getTranslationZ() + elevation);
                }
            }
        }
    }
}