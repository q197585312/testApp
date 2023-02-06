package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/26.
 */
public class DigGameTypeBean implements Serializable {
    private int image;
    private String name;
    private String intrduce;
    private String comein;

    public DigGameTypeBean(int image,String name,String intrduce,String comein) {
        this.image=image;
        this.name=name;
        this.intrduce=intrduce;
        this.comein=comein;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntrduce() {
        return intrduce;
    }

    public void setIntrduce(String intrduce) {
        this.intrduce = intrduce;
    }

    public String getComein() {
        return comein;
    }

    public void setComein(String comein) {
        this.comein = comein;
    }

    @Override
    public String toString() {
        return "DigGameTypeBean{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", intrduce='" + intrduce + '\'' +
                ", comein='" + comein + '\'' +
                '}';
    }
}
