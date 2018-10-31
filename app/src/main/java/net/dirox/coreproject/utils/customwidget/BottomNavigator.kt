package net.dirox.coreproject.utils.customwidget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import net.dirox.coreproject.R
import net.dirox.coreproject.models.NavigationItem
import java.util.jar.Attributes

class BottomNavigator : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        bindAttributes(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        bindAttributes(context, attributeSet)
    }


    private var items = ArrayList<NavigationItem>()
    private var textSize: Float = 0f
    private var iconSize: Int = 0


    private fun bindAttributes(context: Context, attributeSet: AttributeSet?) {

        val typeArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.BottomNavigator, 0, 0)

        try {
            textSize = typeArray.getDimension(R.styleable.BottomNavigator_textSize, 12f)
            iconSize = typeArray.getDimensionPixelSize(R.styleable.BottomNavigator_iconSize, 16)
        } finally {
            typeArray.recycle()
        }

    }

    fun setItems(items: ArrayList<NavigationItem>) {
        this.items = items
        initView()
    }

    private fun initView() {
        for (i in 0 until this.items.size) {
            addItemView(i, items[i])
        }
        invalidate()
    }

    private fun addItemView(position: Int, item: NavigationItem) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val navItem = inflater.inflate(R.layout.item_navigationbar, this, false)

        bindView(navItem, item, position)

    }

    private fun bindView(navItem: View?, item: NavigationItem, position: Int) {
        val tvTitle = navItem?.findViewById<TextView>(R.id.navText)
        tvTitle?.let {
            tvTitle.textSize = textSize
            tvTitle.text= resources.getString(item.title)
        }
    }


}
























