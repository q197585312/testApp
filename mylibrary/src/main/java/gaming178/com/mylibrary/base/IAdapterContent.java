package gaming178.com.mylibrary.base;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.util.List;

interface IAdapterContent<T> {
    void setAdapterView(Context context, AdapterView<ListAdapter> view);

    void setData(List<T> obj);

    <A extends PageThreadhandler>void setThread(A thread);

}