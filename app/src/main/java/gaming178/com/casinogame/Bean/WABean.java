package gaming178.com.casinogame.Bean;

import java.util.List;

public class WABean {

    /**
     * result : Success
     * WA : https://api.whatsapp.com/send?phone=6281361892152
     */

    private String result;
    private String WA;
    private String bocoranRtp;
    private String layananVIP;
    private String popup;
    private String popupLink;
    private String pemenang;
    private String Telegram;
    private String LiveChat;
    private String Promo;
    private String bonusSpecial;
    private String BonusSpesial;
    private String LayananVIP;
    private String LuckyDragon;
    private String PopUP;
    private String SuperBonusVIP;
    private String BonusKhusus;
    private String FBMessenger;
    private String LayananVip;
    /**
     * code : 0
     * data : [{"name":"RTP","URL":"/http://202.36.58.165/images/RTP.webp","link":"https://rebrand.ly/RTPdoacasino/"}]
     */

    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWA() {
        return WA;
    }

    public void setWA(String WA) {
        this.WA = WA;
    }

    public String getBocoranRtp() {
        return bocoranRtp;
    }

    public void setBocoranRtp(String bocoranRtp) {
        this.bocoranRtp = bocoranRtp;
    }

    public String getLayananVIP() {
        return layananVIP;
    }

    public void setLayananVIP(String layananVIP) {
        this.layananVIP = layananVIP;
    }

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }

    public String getPopupLink() {
        return popupLink;
    }

    public void setPopupLink(String popupLink) {
        this.popupLink = popupLink;
    }

    public String getPemenang() {
        return pemenang;
    }

    public void setPemenang(String pemenang) {
        this.pemenang = pemenang;
    }

    public String getTelegram() {
        return Telegram;
    }

    public void setTelegram(String telegram) {
        Telegram = telegram;
    }

    public String getLiveChat() {
        return LiveChat;
    }

    public void setLiveChat(String liveChat) {
        LiveChat = liveChat;
    }

    public String getPromo() {
        return Promo;
    }

    public void setPromo(String promo) {
        Promo = promo;
    }

    public String getBonusSpecial() {
        return bonusSpecial;
    }

    public void setBonusSpecial(String bonusSpecial) {
        this.bonusSpecial = bonusSpecial;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getBonusSpesial() {
        return BonusSpesial;
    }

    public void setBonusSpesial(String bonusSpesial) {
        BonusSpesial = bonusSpesial;
    }

    public String getLuckyDragon() {
        return LuckyDragon;
    }

    public void setLuckyDragon(String luckyDragon) {
        LuckyDragon = luckyDragon;
    }

    public String getVIP() {
        return LayananVIP;
    }

    public void setVIP(String LayananVIP) {
        this.LayananVIP = LayananVIP;
    }

    public String get365KasinoPopup() {
        return PopUP;
    }

    public void set365KasinoPopup(String popup) {
        this.PopUP = popup;
    }

    public String getSuperBonusVIP() {
        return SuperBonusVIP;
    }

    public void setSuperBonusVIP(String superBonusVIP) {
        SuperBonusVIP = superBonusVIP;
    }

    public String getBonusKhusus() {
        return BonusKhusus;
    }

    public void setBonusKhusus(String bonusKhusus) {
        BonusKhusus = bonusKhusus;
    }

    public String getFBMessenger() {
        return FBMessenger;
    }

    public void setFBMessenger(String FBMessenger) {
        this.FBMessenger = FBMessenger;
    }

    public String getMainVIP() {
        return LayananVip;
    }

    public void setMainVIP(String LayananVIP) {
        this.LayananVip = LayananVip;
    }

    public static class DataBean {
        /**
         * name : RTP
         * URL : /http://202.36.58.165/images/RTP.webp
         * link : https://rebrand.ly/RTPdoacasino/
         */

        private String name;
        private String URL;
        private String link;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
