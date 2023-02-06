package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2016/1/8.
 */
public class DiscountBean extends RecommendGameBean {
    boolean isOpen;
    public DiscountBean(String name, int res, String description, String action) {
        super(name, res, description, action);
    }
    public DiscountBean(String name, int res, String description, String action,boolean isOpen) {
        this(name, res, description, action);
        this.isOpen=isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
