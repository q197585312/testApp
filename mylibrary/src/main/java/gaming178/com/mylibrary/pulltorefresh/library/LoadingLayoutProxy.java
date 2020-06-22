package gaming178.com.mylibrary.pulltorefresh.library;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import java.util.HashSet;

import gaming178.com.mylibrary.pulltorefresh.library.internal.LoadingLayout;

public class LoadingLayoutProxy implements ILoadingLayout {

    private final HashSet<LoadingLayout> mTopLoadingLayouts;
    private final HashSet<LoadingLayout> mBottomLoadingLayouts;
    private int theType;

    LoadingLayoutProxy() {
        mTopLoadingLayouts = new HashSet<LoadingLayout>();
        mBottomLoadingLayouts = new HashSet<LoadingLayout>();
    }

    /**
     * This allows you to add extra LoadingLayout instances to this proxy. This
     * is only necessary if you keep your own instances, and want to have them
     * included in any
     * {@link PullToRefreshBase#createLoadingLayoutProxy(boolean, boolean)
     * createLoadingLayoutProxy(...)} calls.
     *
     * @param layout - LoadingLayout to have included.
     */
    public void addLayout(LoadingLayout layout) {
        if (null != layout) {
            switch (layout.getTheType()) {
                case 0:
                    mTopLoadingLayouts.add(layout);
                    break;
                case 1:
                    mBottomLoadingLayouts.add(layout);
                    break;
                default:
                    break;
            }
        }
    }

    public int getTheType() {
        return theType;
    }

    public void setTheType(int theType) {
        this.theType = theType;
    }

    @Override
    public void setLastUpdatedLabel(CharSequence label) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setLastUpdatedLabel(label);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setLastUpdatedLabel(label);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setLoadingDrawable(Drawable drawable) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setLoadingDrawable(drawable);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setLoadingDrawable(drawable);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setRefreshingLabel(CharSequence refreshingLabel) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setRefreshingLabel(refreshingLabel);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setRefreshingLabel(refreshingLabel);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setPullLabel(CharSequence label) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setPullLabel(label);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setPullLabel(label);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setReleaseLabel(CharSequence label) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setReleaseLabel(label);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setReleaseLabel(label);
                }
                break;
            default:
                break;
        }
    }

    public void setTextTypeface(Typeface tf) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setTextTypeface(tf);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setTextTypeface(tf);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setPullImageResource(int resourceId) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setPullImageResource(resourceId);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setPullImageResource(resourceId);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setRefreshingImageResource(int resourceId) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setRefreshingImageResource(resourceId);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setRefreshingImageResource(resourceId);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setReleaseImageResource(int resourceId) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setReleaseImageResource(resourceId);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setReleaseImageResource(resourceId);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setPullLoadingDrawableVisibility(int visibility) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setPullLoadingDrawableVisibility(visibility);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setPullLoadingDrawableVisibility(visibility);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setRefreshingLoadingDrawableVisibility(int visibility) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setReleaseImageResource(visibility);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setRefreshingLoadingDrawableVisibility(visibility);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setReleaseLoadingDrawableVisibility(int visibility) {
        switch (getTheType()) {
            case 0:
                for (LoadingLayout layout : mTopLoadingLayouts) {
                    layout.setReleaseLoadingDrawableVisibility(visibility);
                }
                break;
            case 1:
                for (LoadingLayout layout : mBottomLoadingLayouts) {
                    layout.setReleaseLoadingDrawableVisibility(visibility);
                }
                break;
            default:
                break;
        }
    }

}
