package net.dirox.coreproject.models

import net.dirox.coreproject.R

class NavigationItem(val icon: Int?, val title: Int, val selectable: Boolean){
    companion object {
        fun getValues(): ArrayList<NavigationItem> {
            val items = ArrayList<NavigationItem>()
            items.add(NavigationItem(R.drawable.ic_add, R.string.home, true))
            return items
        }
    }
}