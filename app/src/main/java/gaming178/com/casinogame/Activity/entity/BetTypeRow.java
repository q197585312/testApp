package gaming178.com.casinogame.Activity.entity;

import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public class BetTypeRow {
    List<Integer> numbers;
    FrameLayout frameLayout;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public BetTypeRow(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public FrameLayout getFrameLayout() {
        return frameLayout;
    }

    public void setFrameLayout(FrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }
}
