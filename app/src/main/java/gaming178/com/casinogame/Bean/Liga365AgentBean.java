package gaming178.com.casinogame.Bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/12.
 */

public class Liga365AgentBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * web : liga365.com
         * agent : @G
         */

        private String web;
        private String agent;

        public String getWeb() {
            return web;
        }

        public void setWeb(String web) {
            this.web = web;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }
    }
}
