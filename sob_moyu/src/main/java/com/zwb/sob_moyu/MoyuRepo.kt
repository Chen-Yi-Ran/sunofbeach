package com.zwb.sob_moyu

import androidx.lifecycle.MutableLiveData
import com.zwb.lib_base.ktx.dataConvert
import com.zwb.lib_base.mvvm.m.BaseRepository
import com.zwb.lib_base.net.RetrofitFactory
import com.zwb.lib_base.net.State
import com.zwb.lib_base.bean.ListData
import com.zwb.lib_common.bean.TokenBean
import com.zwb.lib_common.bean.MoyuItemBean
import com.zwb.sob_moyu.bean.MomentCommentBean
import com.zwb.sob_moyu.bean.TopicIndexBean

class MoyuRepo(private val loadState: MutableLiveData<State>) : BaseRepository() {
    private val apiService by lazy {
        RetrofitFactory.instance.getService(MoyuApi::class.java, MoyuApi.BASE_URL)
    }

    suspend fun checkToken(key: String): TokenBean? {
        return apiService.checkToken().dataConvert(loadState, key)
    }

    suspend fun topicIndex(key: String): List<TopicIndexBean>? {
        return apiService.topicIndex().dataConvert(loadState, key)
    }

    suspend fun getList(topicId: String, page: Int, key: String): ListData<MoyuItemBean>? {
        return apiService.getList(topicId, page).dataConvert(loadState, key)
    }

    suspend fun getRecommendList(page: Int, key: String): ListData<MoyuItemBean>? {
        return apiService.getRecommendList(page).dataConvert(loadState, key)
    }

    suspend fun getFollowList(page: Int, key: String): ListData<MoyuItemBean>? {
        return apiService.getFollowList(page).dataConvert(loadState, key)
    }

    suspend fun getFollowList(
        momentId: String,
        page: Int,
        key: String
    ): ListData<MomentCommentBean>? {
        return apiService.getCommentList(momentId, page).dataConvert(loadState, key)
    }


}