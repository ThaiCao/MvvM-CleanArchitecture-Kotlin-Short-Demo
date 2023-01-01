package com.example.structure.model.presentation//package com.example.structure.presentation.model
//
//abstract class ItemHome(override val type: Int) : ItemDifferent {
//    companion object {
//        const val ITEM_HOME_MENU_ROW = 1
//        const val ITEM_HOME_NEW_ROW = ITEM_HOME_MENU_ROW + 1
//        const val ITEM_HOME_HOT_ROW = ITEM_HOME_NEW_ROW + 1
//    }
//}
//
//data class ItemHomeMenuRow(var id: String, var img: String, var name: String) :
//    ItemHome(ITEM_HOME_MENU_ROW) {
//    override fun areItemsTheSame(new: ItemDifferent): Boolean {
//        return this.type == new.type
//    }
//
//    override fun areContentsTheSame(new: ItemDifferent): Boolean {
//        return this == new
//    }
//}
//
//data class ItemNewRow(val id: String, val name: String, val categoryId: String?= null, val img: String) :
//    ItemHome(ITEM_HOME_NEW_ROW) {
//    override fun areItemsTheSame(new: ItemDifferent): Boolean {
//        return this.type == new.type
//    }
//
//    override fun areContentsTheSame(new: ItemDifferent): Boolean {
//        return this == new
//    }
//}
//
//data class ItemHotRow(val id: String, val name: String, val categoryId: String? = null, val img: String) :
//    ItemHome(ITEM_HOME_HOT_ROW) {
//    override fun areItemsTheSame(new: ItemDifferent): Boolean {
//        return this.type == new.type
//    }
//
//    override fun areContentsTheSame(new: ItemDifferent): Boolean {
//        return this == new
//    }
//}
