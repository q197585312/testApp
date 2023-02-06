package gaming178.com.casinogame.Bean;

import android.view.View;

public class TableMaintenanceBean {
    private int tableId;
    private View view;

    public TableMaintenanceBean(int tableId, View view) {
        this.tableId = tableId;
        this.view = view;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
