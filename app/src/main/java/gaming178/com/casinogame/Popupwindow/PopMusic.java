package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Arrays;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.BackgroudMuzicService;
import gaming178.com.casinogame.Util.FrontMuzicService;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2020/5/21.
 */

public class PopMusic extends BasePopupWindow {
    BaseActivity baseActivity;
    CheckBox currentBox;
    private ImageView img_exit;

    public PopMusic(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_music;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        baseActivity = (BaseActivity) context;
        img_exit = view.findViewById(R.id.gd__img_exit);
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        CheckBox checkBox1 = view.findViewById(R.id.gd__rb_set_muzic1);
        CheckBox checkBox2 = view.findViewById(R.id.gd__rb_set_muzic2);
        CheckBox checkBox3 = view.findViewById(R.id.gd__rb_set_muzic3);
        CheckBox checkBox4 = view.findViewById(R.id.gd__rb_set_muzic4);
        CheckBox checkBox5 = view.findViewById(R.id.gd__rb_set_muzic5);
        CheckBox checkBox6 = view.findViewById(R.id.gd__rb_set_muzic6);
        List<CheckBox> boxList = Arrays.asList(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        switch (baseActivity.mAppViewModel.getMuzicIndex()) {
            case 1:
                currentBox = checkBox1;
                break;
            case 2:
                currentBox = checkBox2;
                break;
            case 3:
                currentBox = checkBox3;
                break;
            case 4:
                currentBox = checkBox4;
                break;
            case 5:
                currentBox = checkBox5;
                break;
            case 6:
                currentBox = checkBox6;
                break;
        }
        currentBox.setChecked(true);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseMusic(boxList, (CheckBox) v);
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseMusic(boxList, (CheckBox) v);
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseMusic(boxList, (CheckBox) v);
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseMusic(boxList, (CheckBox) v);
            }
        });
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseMusic(boxList, (CheckBox) v);
            }
        });
        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseMusic(boxList, (CheckBox) v);
            }
        });
        SeekBar sb_front_voice = (SeekBar) view.findViewById(R.id.gd__sb_front_voice);
        SeekBar sb_background_voice = (SeekBar) view.findViewById(R.id.gd__sb_background_voice);
        sb_front_voice.setProgress(baseActivity.mAppViewModel.getFrontVolume());
        sb_background_voice.setProgress(baseActivity.mAppViewModel.getBackgroudVolume());
        sb_front_voice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                baseActivity.mAppViewModel.setFrontVolume(seekBar.getProgress());
                baseActivity.mAppViewModel.changeMuzicVolumeService(baseActivity.componentFront, context, seekBar.getProgress(), FrontMuzicService.PLAY_CHANGE_VOLUME);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_background_voice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                baseActivity.mAppViewModel.setBackgroudVolume(seekBar.getProgress());
                baseActivity.mAppViewModel.changeMuzicVolumeService(baseActivity.componentBack, context, seekBar.getProgress(), BackgroudMuzicService.PLAY_CHANGE_VOLUME);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void chooseMusic(List<CheckBox> boxList, CheckBox chooseCheckBox) {
        if (currentBox.equals(chooseCheckBox)) {
            chooseCheckBox.setChecked(true);
            return;
        } else {
            currentBox = chooseCheckBox;
        }
        for (int i = 0; i < boxList.size(); i++) {
            CheckBox checkBox = boxList.get(i);
            if (checkBox.equals(chooseCheckBox)) {
                checkBox.setChecked(true);
                baseActivity.mAppViewModel.setMuzicIndex(i + 1);
                if (baseActivity.mAppViewModel.isbLobby() == false) {
                    baseActivity.mAppViewModel.startBackgroudMuzicService(baseActivity.mAppViewModel.getMuzicIndex(), baseActivity.componentBack, context, baseActivity.mAppViewModel.getBackgroudVolume());
                }
            } else {
                checkBox.setChecked(false);
            }
        }
    }
}
