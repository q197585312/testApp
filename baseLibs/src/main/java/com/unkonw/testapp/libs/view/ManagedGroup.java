package com.unkonw.testapp.libs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;

import java.util.HashSet;
import java.util.Set;


public class ManagedGroup extends Group {
    private final Set<Integer> mRemovedRefIds = new HashSet<>();

    public ManagedGroup(Context context) {
        super(context);
    }

    public ManagedGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ManagedGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Set the reference ids for the group and clear the removed id array.
     *
     * @param ids All identifiers in the group.
     */
    @Override
    public void setReferencedIds(@NonNull int[] ids) {
        super.setReferencedIds(ids);
        mRemovedRefIds.clear();
    }

    /**
     * Set visibility for  view and remove the view's id from the group.
     *
     * @param view       View for visibility change
     * @param visibility View.VISIBLE, View.INVISIBLE or View.GONE.
     */
    public void setVisibility(@NonNull View view, int visibility) {
        removeReferencedIds(view.getId());
        view.setVisibility(visibility);
    }

    /**
     * Add all removed views back into the group.
     */
    public void resetGroup() {
        setReferencedIds(getAllReferencedIds());
    }

    /**
     * Remove reference ids from the group. This is done automatically when
     * setVisibility(View view, int visibility) is called.
     *
     * @param idsToRemove All the ids to remove from the group.
     */
    public void removeReferencedIds(int... idsToRemove) {
        for (int id : idsToRemove) {
            mRemovedRefIds.add(id);
        }

        int[] refIds = getReferencedIds();
        Set<Integer> newRefIdSet = new HashSet<>();

        for (int id : refIds) {
            if (!mRemovedRefIds.contains(id)) {
                newRefIdSet.add(id);
            }
        }
        super.setReferencedIds(copySetToIntArray(newRefIdSet));
    }

    /**
     * Add reference ids to the group.
     *
     * @param idsToAdd Identifiers to add to the group.
     */
    public void addReferencedIds(int... idsToAdd) {
        for (int id : idsToAdd) {
            mRemovedRefIds.remove(id);
        }
        super.setReferencedIds(joinArrays(getReferencedIds(), idsToAdd));
    }

    /**
     * Return int[] of all ids in the group plus those removed.
     *
     * @return All current ids in group plus those removed.
     */
    @NonNull
    public int[] getAllReferencedIds() {
        return joinArrays(getReferencedIds(), copySetToIntArray(mRemovedRefIds));
    }

    @NonNull
    private int[] copySetToIntArray(Set<Integer> fromSet) {
        int[] toArray = new int[fromSet.size()];
        int i = 0;

        for (int id : fromSet) {
            toArray[i++] = id;
        }

        return toArray;
    }

    @NonNull
    private int[] joinArrays(@NonNull int[] array1, @NonNull int[] array2) {
        int[] joinedArray = new int[array1.length + array2.length];

        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }
}