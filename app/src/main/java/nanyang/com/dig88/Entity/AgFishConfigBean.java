package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/12/4.
 */

public class AgFishConfigBean {

    /**
     * code : 0
     * msg : No Error
     * gamebalance : 0
     * login_data : Ez0+tlFUr0XUNCIlx+ooC6KtxhJ9uYEyqvp74F+k+2WcPLMKHpTIXelFABO5QvDiaJNS7s3GDBhInAY7x1kFjnc4OC1YDdrxWmBlPHZyWcCAfEVX/7ZuCrVjx/bRk8tgWPEXGoezp/wXxAsSAHUxW5h1LJPUvNgZuMiUUxLi1xGT25sv71ZP1cGBTWyPcN6/oAQb9yMlXYj8krthViwtXOzkXJeuf79c3b1evs44rz6YZZs0sfo9fxy5y0h+cLOOg0idZjprgmU=
     * md5_login : 33ac9261c0aaec76a1bcdd62b85dd65b
     */

    private int code;
    private String msg;
    private double gamebalance;
    private String login_data;
    private String md5_login;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public double getGamebalance() {
        return gamebalance;
    }

    public void setGamebalance(double gamebalance) {
        this.gamebalance = gamebalance;
    }

    public String getLogin_data() {
        return login_data;
    }

    public void setLogin_data(String login_data) {
        this.login_data = login_data;
    }

    public String getMd5_login() {
        return md5_login;
    }

    public void setMd5_login(String md5_login) {
        this.md5_login = md5_login;
    }
}
