package gaming178.com.mylibrary.base.quick;

import java.io.Serializable;

public class QuickBaseResponse<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Integer pageCount;
    Integer recordCount;
    String reason;
    String success;
    T result;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
