package nanyang.com.dig88.Table.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/13.
 */
public class LeagueBean implements Serializable {
    String moduleTitle;
    String moduleId;

    public LeagueBean(String moduleId, String moduleTitle) {
        this.moduleTitle = moduleTitle;
        this.moduleId = moduleId;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj == null) {
            result = false;
        }

        if (this == obj) {
            result = true;
        }

        if (obj instanceof LeagueBean) {
            LeagueBean stu = (LeagueBean) obj;
            if (stu.getModuleId().equals(this.moduleId)) {
                result = true;
            }
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(moduleId);
    }
}
