package com.kultur.futbolfiksturuygulamasi.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.kultur.futbolfiksturuygulamasi.Model.DataModel
import com.kultur.futbolfiksturuygulamasi.Model.FixtureModel
import com.kultur.futbolfiksturuygulamasi.R

class ViewPagerAdapter(internal val context : Context,
                       internal val data:List<FixtureModel>): PagerAdapter() {
    internal var layoutInflater: LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context) }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view=layoutInflater.inflate(R.layout.fikstur_vp, container,false)

        val cardView=view.findViewById<View>(R.id.cardView) as CardView
        val takim1=view.findViewById<View>(R.id.takim1) as TextView
        val ligHafta=view.findViewById<View>(R.id.ligHafta) as TextView

        takim1.setText(data[position].teams)
        ligHafta.setText(data[position].week)


        container.addView(view)
        return  view
    }

}