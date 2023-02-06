package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class GameStatusBean {
    /**
     * code : 1
     * msg : 1
     * data : [{"gametype":"SPORTS","provider_id":"52","status":"1","provider":"AFB Sports"},{"gametype":"SPORTS","provider_id":"61","status":"0","provider":"DMB Sports"},{"gametype":"SPORTS","provider_id":"98","status":"1","provider":"IBC Sports"},{"gametype":"SPORTS","provider_id":"100","status":"1","provider":"SBO Sports"},{"gametype":"CASINO","provider_id":"53","status":"1","provider":"GD Casino "},{"gametype":"CASINO","provider_id":"60","status":"1","provider":"CT Casino"},{"gametype":"CASINO","provider_id":"90","status":"1","provider":"W88 Casino"},{"gametype":"CASINO","provider_id":"91","status":"1","provider":"GOLD Casino"},{"gametype":"CASINO","provider_id":"95","status":"1","provider":"GDRAGON Casino"},{"gametype":"CASINO","provider_id":"96","status":"1","provider":"AG Casino"},{"gametype":"CASINO","provider_id":"129","status":"1","provider":"All Bet"},{"gametype":"SLOTS","provider_id":"51","status":"1","provider":"MICRO GAMING"},{"gametype":"SLOTS","provider_id":"93","status":"1","provider":"U1888 Slots"},{"gametype":"SLOTS","provider_id":"94","status":"1","provider":"BestGamer"},{"gametype":"SLOTS","provider_id":"99","status":"1","provider":"Haba Slots"},{"gametype":"POKER","provider_id":"75","status":"1","provider":"GP POKER"},{"gametype":"LOTTERY","provider_id":"1","status":"1","provider":"SINGAPORE"},{"gametype":"LOTTERY","provider_id":"2","status":"1","provider":"TAIPEI"},{"gametype":"LOTTERY","provider_id":"3","status":"1","provider":"KUALA LUMPUR"},{"gametype":"LOTTERY","provider_id":"4","status":"1","provider":"HONGKONG"},{"gametype":"LOTTERY","provider_id":"5","status":"1","provider":"MALAYSIA"},{"gametype":"LOTTERY","provider_id":"6","status":"0","provider":"CHINA"},{"gametype":"LOTTERY","provider_id":"7","status":"0","provider":"HK4D"},{"gametype":"LOTTERY","provider_id":"8","status":"0","provider":"CAMBODIA"},{"gametype":"LOTTERY","provider_id":"9","status":"0","provider":"MACAU"},{"gametype":"LOTTERY","provider_id":"56","status":"0","provider":"BEIJING4D"},{"gametype":"LOTTERY","provider_id":"57","status":"0","provider":"CANADA4D"},{"gametype":"LOTTERY","provider_id":"58","status":"1","provider":"HONGKONGPOOL"},{"gametype":"LOTTERY","provider_id":"62","status":"0","provider":"SYDNEY"},{"gametype":"LOTTERY","provider_id":"63","status":"0","provider":"HIROSHIMA"},{"gametype":"LOTTERY","provider_id":"64","status":"0","provider":"NAGASAKI"},{"gametype":"LOTTERY","provider_id":"65","status":"0","provider":"CHENGDU"},{"gametype":"LOTTERY","provider_id":"67","status":"0","provider":"Philippine"},{"gametype":"LOTTERY","provider_id":"68","status":"0","provider":"Damacai"},{"gametype":"LOTTERY","provider_id":"69","status":"0","provider":"Sportstoto"},{"gametype":"LOTTERY","provider_id":"70","status":"0","provider":"CHREY THORM"},{"gametype":"LOTTERY","provider_id":"71","status":"0","provider":"GENTING 4D"},{"gametype":"LOTTERY","provider_id":"72","status":"0","provider":"MACAU SUPER LOTTO"},{"gametype":"LOTTERY","provider_id":"73","status":"0","provider":"OSAKA"},{"gametype":"LOTTERY","provider_id":"74","status":"0","provider":"MAGNUM 4D"},{"gametype":"LOTTERY","provider_id":"76","status":"0","provider":"OKINAWA"},{"gametype":"LOTTERY","provider_id":"77","status":"0","provider":"LANGKAWI"},{"gametype":"LOTTERY","provider_id":"78","status":"0","provider":"MALADEWA"},{"gametype":"LOTTERY","provider_id":"84","status":"0","provider":"4D MANILA"},{"gametype":"LOTTERY","provider_id":"85","status":"0","provider":"4D TOKYO"},{"gametype":"LOTTERY","provider_id":"86","status":"0","provider":"4D SEOUL"},{"gametype":"LOTTERY","provider_id":"87","status":"0","provider":"HANOITOTO"},{"gametype":"LOTTERY","provider_id":"88","status":"0","provider":"CAMBODIA4D"},{"gametype":"LOTTERY","provider_id":"89","status":"0","provider":"SEOUL4D"},{"gametype":"KENO","provider_id":"13","status":"1","provider":"CHINA"},{"gametype":"KENO","provider_id":"14","status":"1","provider":"HONGKONG"},{"gametype":"KENO","provider_id":"15","status":"1","provider":"MACAU"},{"gametype":"KENO","provider_id":"16","status":"1","provider":"TAIPEI"},{"gametype":"KENO","provider_id":"17","status":"1","provider":"KOREA"},{"gametype":"KENO","provider_id":"22","status":"1","provider":"JAPAN"},{"gametype":"KENO","provider_id":"23","status":"1","provider":"SINGAPORE"},{"gametype":"KENO","provider_id":"24","status":"1","provider":"VIETNAM"},{"gametype":"KENO","provider_id":"25","status":"1","provider":"THAILAND"},{"gametype":"KENO","provider_id":"26","status":"1","provider":"CAMBODIA"},{"gametype":"KENO","provider_id":"27","status":"1","provider":"KUALA LUMPUR"},{"gametype":"KENO","provider_id":"28","status":"1","provider":"JAKARTA"},{"gametype":"LIVE NUMBER","provider_id":"10","status":"1","provider":"42 Balls"},{"gametype":"LIVE NUMBER","provider_id":"11","status":"0","provider":"36 Balls"},{"gametype":"LIVE NUMBER","provider_id":"12","status":"1","provider":"12 Balls"},{"gametype":"LIVE NUMBER","provider_id":"47","status":"1","provider":"48 Balls"},{"gametype":"LIVE NUMBER","provider_id":"48","status":"1","provider":"Sicbo Balls"},{"gametype":"LIVE NUMBER","provider_id":"49","status":"1","provider":"Roulette Balls"},{"gametype":"LIVE NUMBER","provider_id":"50","status":"1","provider":"Multiple 36Balls"},{"gametype":"AUTO NUMBER","provider_id":"18","status":"1","provider":"42 Balls"},{"gametype":"AUTO NUMBER","provider_id":"19","status":"1","provider":"36 Balls"},{"gametype":"AUTO NUMBER","provider_id":"20","status":"1","provider":"24 Balls"},{"gametype":"AUTO NUMBER","provider_id":"21","status":"1","provider":"12 Balls"},{"gametype":"AUTO NUMBER","provider_id":"81","status":"1","provider":"18 Balls"},{"gametype":"AUTO NUMBER","provider_id":"82","status":"1","provider":"30 Balls"},{"gametype":"VideoPoker","provider_id":"59","status":"0","provider":"Tangkas"},{"gametype":"VideoPoker","provider_id":"66","status":"1","provider":"JokerPoker500"},{"gametype":"VideoPoker","provider_id":"79","status":"1","provider":"JacksOrBetter"},{"gametype":"VideoPoker","provider_id":"80","status":"1","provider":"CaribbeanPoker"},{"gametype":"FOREX","provider_id":"83","status":"1","provider":"855 FOREX"},{"gametype":"INNOXOFT","provider_id":"92","status":"1","provider":"Beijing Race"},{"gametype":"THAI LOTTERY","provider_id":"97","status":"1","provider":"THAILAND"},{"gametype":"VN LOTTERY","provider_id":"101","status":"1","provider":" Hanoi"},{"gametype":"VN LOTTERY","provider_id":"102","status":"1","provider":"Quang Ninh"},{"gametype":"VN LOTTERY","provider_id":"103","status":"1","provider":"Bac Ninh"},{"gametype":"VN LOTTERY","provider_id":"104","status":"1","provider":"Haiphong"},{"gametype":"VN LOTTERY","provider_id":"105","status":"1","provider":"Nam Dinh"},{"gametype":"VN LOTTERY","provider_id":"106","status":"1","provider":"Thái Bình"},{"gametype":"VN LOTTERY","provider_id":"107","status":"1","provider":"TP HCM"},{"gametype":"VN LOTTERY","provider_id":"108","status":"1","provider":"Ca Mau"},{"gametype":"VN LOTTERY","provider_id":"109","status":"1","provider":"Dong Thap"},{"gametype":"VN LOTTERY","provider_id":"110","status":"1","provider":"Bac Lieu"},{"gametype":"VN LOTTERY","provider_id":"111","status":"1","provider":"Ben tre"},{"gametype":"VN LOTTERY","provider_id":"112","status":"1","provider":"Vung Tau"},{"gametype":"VN LOTTERY","provider_id":"113","status":"1","provider":"Can Tho"},{"gametype":"VN LOTTERY","provider_id":"114","status":"1","provider":"Dong Nai"},{"gametype":"VN LOTTERY","provider_id":"115","status":"1","provider":"Soc Trang"},{"gametype":"VN LOTTERY","provider_id":"116","status":"1","provider":"An Giang"},{"gametype":"VN LOTTERY","provider_id":"117","status":"1","provider":"Binh Thuan"},{"gametype":"VN LOTTERY","provider_id":"118","status":"1","provider":"Tây Ninh"},{"gametype":"VN LOTTERY","provider_id":"119","status":"1","provider":"Binh Duong"},{"gametype":"VN LOTTERY","provider_id":"120","status":"1","provider":"Tra Vinh"},{"gametype":"VN LOTTERY","provider_id":"121","status":"1","provider":"Vinh Long"},{"gametype":"VN LOTTERY","provider_id":"122","status":"1","provider":"Binh Phuoc"},{"gametype":"VN LOTTERY","provider_id":"123","status":"1","provider":"Hau Giang"},{"gametype":"VN LOTTERY","provider_id":"124","status":"1","provider":"Long An"},{"gametype":"VN LOTTERY","provider_id":"125","status":"1","provider":"Kien Giang"},{"gametype":"VN LOTTERY","provider_id":"126","status":"1","provider":"Lam Dong"},{"gametype":"VN LOTTERY","provider_id":"127","status":"1","provider":"Tien Giang"},{"gametype":"POKER","provider_id":"130","status":"1","provider":"W88 Mahjong"},{"gametype":"","provider_id":"38","status":"1","provider":"CHINA BJ"},{"gametype":"","provider_id":"40","status":"0","provider":"CANADA 1"},{"gametype":"","provider_id":"41","status":"0","provider":"SLOVAKIA"},{"gametype":"","provider_id":"43","status":"0","provider":"CANADA 2"},{"gametype":"","provider_id":"44","status":"0","provider":"AUSTRALIA"},{"gametype":"","provider_id":"45","status":"0","provider":"MALTA"},{"gametype":"","provider_id":"55","status":"0","provider":"KOREA"},{"gametype":"SLOTS","provider_id":"131","status":"1","provider":"W88 Slots"},{"gametype":"SLOTS","provider_id":"132","status":"1","provider":"PlayTech Slots"},{"gametype":"SLOTS","provider_id":"133","status":"1","provider":"Gold Slots"},{"gametype":"SLOTS","provider_id":"134","status":"1","provider":"Fishing"},{"gametype":"CASINO","provider_id":"136","status":"1","provider":"DG99 Casino"},{"gametype":"LOTTERY","provider_id":"135","status":"0","provider":"Macau 45 Toto"},{"gametype":"CASINO","provider_id":"138","status":"1","provider":"CF88"},{"gametype":"LOTTERY","provider_id":"137","status":"0","provider":"4D BARCELONA"},{"gametype":"LOTTERY","provider_id":"139","status":"0","provider":"Qatar"},{"gametype":"LOTTERY","provider_id":"140","status":"0","provider":"SENTOSA  4D"},{"gametype":"LOTTERY","provider_id":"141","status":"0","provider":"SENTOSA TOTO"},{"gametype":"LOTTERY","provider_id":"142","status":"0","provider":"PASARAN SYDNEY"},{"gametype":"LOTTERY","provider_id":"143","status":"0","provider":"PASARAN MACAO"},{"gametype":"LOTTERY","provider_id":"144","status":"0","provider":"PASARAN MAGNUM4D"},{"gametype":"LOTTERY","provider_id":"145","status":"0","provider":"PASARAN SEOUL"},{"gametype":"LOTTERY","provider_id":"146","status":"0","provider":"PASARAN QATAR"},{"gametype":"LOTTERY","provider_id":"147","status":"0","provider":"PASARAN HONGKONG"},{"gametype":"LOTTERY","provider_id":"148","status":"0","provider":"KUCHING"},{"gametype":"LOTTERY","provider_id":"149","status":"0","provider":"WALES"},{"gametype":"LOTTERY","provider_id":"150","status":"0","provider":"SERBIA"},{"gametype":"LOTTERY","provider_id":"151","status":"0","provider":"TaiWan"},{"gametype":"LOTTERY","provider_id":"152","status":"1","provider":""},{"gametype":"LOTTERY","provider_id":"153","status":"0","provider":"OKE77-Genting"},{"gametype":"LOTTERY","provider_id":"154","status":"0","provider":"OKE77-HONGKONG"},{"gametype":"LOTTERY","provider_id":"155","status":"0","provider":"42s-MILAN"},{"gametype":"LOTTERY","provider_id":"156","status":"0","provider":"42s-PATTAYA"},{"gametype":"LOTTERY","provider_id":"157","status":"0","provider":"42s-Genting"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * gametype : SPORTS
         * provider_id : 52
         * status : 1
         * provider : AFB Sports
         */

        private String gametype;
        private String provider_id;
        private String status;
        private String provider;

        public String getGametype() {
            return gametype;
        }

        public void setGametype(String gametype) {
            this.gametype = gametype;
        }

        public String getProvider_id() {
            return provider_id;
        }

        public void setProvider_id(String provider_id) {
            this.provider_id = provider_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }
    }
}
