package com.zwb.sob_login

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.hjq.toast.ToastUtils
import com.zwb.lib_base.ktx.initiateRequest
import com.zwb.lib_base.mvvm.vm.BaseViewModel
import com.zwb.lib_base.net.BaseResponse
import com.zwb.lib_base.utils.LogUtils
import com.zwb.lib_base.utils.SpUtils
import com.zwb.lib_common.constant.SpKey
import com.zwb.sob_login.bean.*

class LoginViewModel : BaseViewModel() {
    private val repository by lazy {
        LoginRepo(loadState)
    }

    fun login(captcha: String, query: LoginInBean, key: String): MutableLiveData<BaseResponse<String?>> {
        val response: MutableLiveData<BaseResponse<String?>> = MutableLiveData()
        initiateRequest({
            val res = repository.login(captcha, query)
            if(res.success){
                val token = repository.checkToken(key)
                token?.let {
                    //登录成功后保存用户信息
                    SpUtils.putString(SpKey.USER_ID, token.id)
                    SpUtils.putString(SpKey.USER_AVATAR, if(TextUtils.isEmpty(token.avatar))"" else token.avatar!! )
                    SpUtils.putString(SpKey.USER_NICKNAME, if(TextUtils.isEmpty(token.nickname))"" else token.nickname!! )
                }
            }
            //将返回监听结果
            response.value = res
        }, loadState, key)
        return response
    }

    fun newLogin(captcha: String, query: LoginInBean2, key: String):MutableLiveData<BaseResponse<String?>>{
        val response:MutableLiveData<BaseResponse<String?>> = MutableLiveData()
        initiateRequest({
            val res=repository.newLogin(captcha,query, glideKey)
            if(res.success){
                LogUtils.d("cyr","登录成功-----"+res.code+"-----"+res.message)
            }
            //将返回监听结果
            response.value = res
        },loadState,key)
        return response
    }

    fun getUserInfo():MutableLiveData<BaseResponse<UserInfoBean?>>{
        val response:MutableLiveData<BaseResponse<UserInfoBean?>> = MutableLiveData()
        initiateRequest({
            val res=repository.getUserInfo()
            if(res.success){
                LogUtils.d("cyr","获取用户信息数据成功----"+res.data)
            }
        },loadState)
        return response
    }



    fun registerSms(query: SendSmsBean, key: String): MutableLiveData<BaseResponse<String?>> {
        val response: MutableLiveData<BaseResponse<String?>> = MutableLiveData()
        initiateRequest({
            response.value = repository.registerSms(query)
        }, loadState, key)
        return response
    }

    fun register(smsCode: String, query: RegisterBean, key: String): MutableLiveData<BaseResponse<String?>> {
        val response: MutableLiveData<BaseResponse<String?>> = MutableLiveData()
        initiateRequest({
            val res = repository.checkSmsCode(query.phoneNum, smsCode)
            if (res.success) {
                response.value = repository.register(smsCode, query)
            } else {
                response.value = res
            }
        }, loadState, key)
        return response
    }

    fun forgetSms(query: SendSmsBean, key: String): MutableLiveData<BaseResponse<String?>> {
        val response: MutableLiveData<BaseResponse<String?>> = MutableLiveData()
        initiateRequest({
            response.value = repository.forgetSms(query)
        }, loadState, key)
        return response
    }

    fun forget(smsCode: String, query: LoginInBean, key: String): MutableLiveData<BaseResponse<String?>> {
        val response: MutableLiveData<BaseResponse<String?>> = MutableLiveData()
        initiateRequest({
            val res = repository.checkSmsCode(query.phoneNum, smsCode)
            if (res.success) {
                response.value = repository.forget(smsCode, query)
            } else {
                response.value = res
            }
        }, loadState, key)
        return response
    }
}