package com.zwb.lib_common.view

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.zwb.lib_common.R

object CommonViewUtils {
    
    fun setFollowState(btn: TextView, state: Int){
        when (state) {
            0 -> {
                btn.text = "+ 关注"
                btn.setTextColor(ContextCompat.getColor(btn.context, R.color.colorAccent))
                btn.setBackgroundResource(R.drawable.blue_hollow_btn_selector)
            }
            1 -> {
                btn.text = "回粉"
                btn.setTextColor(ContextCompat.getColor(btn.context, R.color.white))
                btn.setBackgroundResource(R.drawable.red_solid_btn_selector)
            }
            2 -> {
                btn.text = "已关注"
                btn.setTextColor(ContextCompat.getColor(btn.context, R.color.white))
                btn.setBackgroundResource(R.drawable.green_solid_btn_selector)
            }
            3 -> {
                btn.text = "相互关注"
                btn.setTextColor(ContextCompat.getColor(btn.context, R.color.white))
                btn.setBackgroundResource(R.drawable.blue_solid_btn_selector)
            }
        }
    }
}