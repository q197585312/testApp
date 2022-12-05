package gaming178.com.casinogame.Bean;

import android.view.View;
import android.widget.TextView;

public class PopNewMusicContentBean {
    private TextView tvName;
    private View viewLine;
    private View viewContent;

    public PopNewMusicContentBean(TextView tvName, View viewLine, View viewContent) {
        this.tvName = tvName;
        this.viewLine = viewLine;
        this.viewContent = viewContent;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public View getViewLine() {
        return viewLine;
    }

    public void setViewLine(View viewLine) {
        this.viewLine = viewLine;
    }

    public View getViewContent() {
        return viewContent;
    }

    public void setViewContent(View viewContent) {
        this.viewContent = viewContent;
    }
}
