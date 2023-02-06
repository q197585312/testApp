package nanyang.com.dig88.Table.fragment;

import android.widget.ListView;

import java.util.ArrayList;

import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Imp.IClickListener;
import nanyang.com.dig88.Table.entity.TableModuleBean;
import nanyang.com.dig88.Table.utils.TableDataHelper;

/**
 * 游戏
 */
public class OtherGamesFragment extends TableBaseFragment{


	@Override
	protected int getFragmentLayoutRes() {
		return R.layout.fragment_ballgame_test;
	}

	@Override
	public void notifyBetTypeChange() {

	}

	@Override
	protected void initBallData() {

	}

	@Override
	protected ListView getListView() {
		return new ListView(mContext);
	}

	@Override
	protected void getNetTableData(TableDataHelper dataHelper,String params) {

	}

	@Override
	protected void endList(ArrayList<TableModuleBean> result) {

	}

	@Override
	public void updateFromSelected() {

	}

	@Override
	public void setModelClickLisenter(IClickListener lisenter) {

	}

}
