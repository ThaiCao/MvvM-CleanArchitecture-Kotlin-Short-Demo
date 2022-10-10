package com.example.structure.presentation.model

class SingleSelectableList <T> : ArrayList<T> {
    private var mItemSelected: T? = null

    val itemSelected: T? get() = mItemSelected

    val itemSelectedPosition: Int get() = indexOf(mItemSelected)

    fun setSelected(item: T?) {
        mItemSelected = item
    }

    private fun selectFirst() {
        mItemSelected = firstOrNull()
    }

    fun setSelected(position: Int) {
        if (position >= size) return
        mItemSelected = get(position)
    }

    fun getSelectedIndex(): Int {
        return this.indexOf(mItemSelected)
    }

    constructor(c: Collection<T>, selectFirst: Boolean = false) : super(c) {
        if (selectFirst) selectFirst()
    }

    constructor() : super()
}

fun <T> Collection<T>.toSingleSelectableList(selectFirst: Boolean = false): SingleSelectableList<T> {
    return SingleSelectableList(this, selectFirst).apply { }
}
