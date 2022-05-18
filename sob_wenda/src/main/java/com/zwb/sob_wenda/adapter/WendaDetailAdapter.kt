package com.zwb.sob_wenda.adapter

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.youth.banner.util.BannerUtils
import com.zwb.lib_base.ktx.gone
import com.zwb.lib_base.ktx.visible
import com.zwb.lib_base.utils.DateUtils
import com.zwb.lib_base.utils.UIUtils
import com.zwb.lib_common.bean.TitleMultiBean
import com.zwb.lib_common.constant.Constants
import com.zwb.lib_common.view.AvatarDecorView
import com.zwb.lib_common.view.HtmlImageGetter
import com.zwb.sob_wenda.R
import com.zwb.sob_wenda.bean.AnswerBean
import com.zwb.sob_wenda.bean.WendaBean

class WendaDetailAdapter(data: MutableList<MultiItemEntity>?) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {

    private val w26dp = UIUtils.dp2px(26f)

    init {
        addItemType(Constants.MultiItemType.TYPE_TITLE, R.layout.common_adapter_title)
        addItemType(Constants.MultiItemType.TYPE_COMMENT, R.layout.wenda_detail_answer_adapter)
        addItemType(Constants.MultiItemType.TYPE_RECOMMEND, R.layout.wenda_detail_answer_adapter)
    }

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity?) {
        item?.let {
            when (helper.itemViewType) {
                Constants.MultiItemType.TYPE_TITLE -> {
                    val title = it as TitleMultiBean
                    helper.setText(R.id.tv_title, title.title)
                }
                Constants.MultiItemType.TYPE_COMMENT -> {
                    val comment = it as AnswerBean
                    helper.setText(R.id.tv_comment_nickname, comment.nickname)
                    helper.setText(R.id.tv_publishTime, DateUtils.timeFormat(comment.publishTime))
                    val tvContent: TextView = helper.getView(R.id.tv_comment)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        tvContent.text = Html.fromHtml(
                            comment.content, Html.FROM_HTML_MODE_LEGACY,
                            HtmlImageGetter(tvContent),
                            null
                        )
                    } else {
                        tvContent.text = Html.fromHtml(comment.content)
                    }
                    helper.setText(R.id.tv_reply, "${comment.wendaSubComments.size} 评论")
                    val ivAvatar = helper.getView<AvatarDecorView>(R.id.iv_comment_avatar)
                    ivAvatar.loadAvatar(comment.isVip,comment.avatar)
                    if(comment.bestAs=="1"){
                        helper.getView<View>(R.id.tv_solved).visible()
                    }else{
                        helper.getView<View>(R.id.tv_solved).gone()
                    }
                    helper.addOnClickListener(R.id.tv_comment_nickname, R.id.iv_comment_avatar)
                }
                Constants.MultiItemType.TYPE_RECOMMEND -> {
                    val wenda = it as WendaBean
                    helper.setText(R.id.tv_comment_nickname, wenda.nickname)
                    helper.setText(R.id.tv_publishTime,  DateUtils.timeFormat(wenda.createTime))
                    helper.setText(R.id.tv_comment, wenda.title)
                    val ivAvatar = helper.getView<AvatarDecorView>(R.id.iv_comment_avatar)
                    ivAvatar.loadAvatar(wenda.isVip=="1",wenda.avatar)
                    helper.addOnClickListener(R.id.tv_comment_nickname, R.id.iv_comment_avatar)
                    helper.getView<View>(R.id.tv_more).gone()
                }
                else ->{}
            }
        }
    }
}