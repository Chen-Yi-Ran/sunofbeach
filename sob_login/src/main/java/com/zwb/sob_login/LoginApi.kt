package com.zwb.sob_login

import com.zwb.lib_base.net.BaseResponse
import com.zwb.lib_common.bean.TokenBean
import com.zwb.lib_common.constant.Constants
import com.zwb.sob_login.bean.*
import retrofit2.http.*

interface LoginApi {

    @POST("${LOGIN_URL}/{captcha}")
    suspend fun login(
        @Path("captcha") captcha: String,
        @Body query: LoginInBean
    ): BaseResponse<String?>


    @POST("${NEW_LOGIN_URL}/{captcha}/{glide_key}")
    suspend fun newLogin(
        @Path("captcha") captcha: String,
        @Path("glide_key") glide_key:String,
        @Body query: LoginInBean2
    ):BaseResponse<String?>

    @GET("http://192.168.1.200:2020/user/user_info/1230644350879268864")
    suspend fun getUserInfo():BaseResponse<UserInfoBean?>

    @GET(Constants.URL.CHECK_TOKEN_URL)
    suspend fun checkToken(): BaseResponse<TokenBean?>


    @POST(REGISTER_SMS_URL)
    suspend fun registerSms(@Body query: SendSmsBean): BaseResponse<String?>

    @POST(FORGET_SMS_URL)
    suspend fun forgetSms(@Body query: SendSmsBean): BaseResponse<String?>

    @GET("${CHECK_SMS_CODE_URL}/{phoneNumber}/{smsCode}")
    suspend fun checkSmsCode(
        @Path("phoneNumber") phoneNumber: String,
        @Path("smsCode") smsCode: String
    ): BaseResponse<String?>



    @POST("${REGISTER_URL}/{smsCode}")
    suspend fun register(
        @Path("smsCode") smsCode: String,
        @Body query: RegisterBean
    ): BaseResponse<String?>


    @PUT("${FORGET_URL}/{smsCode}")
    suspend fun forget(
        @Path("smsCode") smsCode: String,
        @Body query: LoginInBean
    ): BaseResponse<String?>

    @PUT(MODIFY_PSD_URL)
    suspend fun modifyPsd(
        @Body query: ModifyPwdBean
    ): BaseResponse<String?>

    companion object {
        const val BASE_URL = "https://api.sunofbeaches.com/"
        const val NEW_BASE_URL="http://192.168.1.200:2020"

        // 登录
        const val LOGIN_URL = "${BASE_URL}uc/user/login"
        const val NEW_LOGIN_URL="${NEW_BASE_URL}/user/login"

        // 获取注册的手机验证码（注册）
        const val REGISTER_SMS_URL = "${BASE_URL}uc/ut/join/send-sms"

        // 获取找回密码的手机验证码（找回密码）
        const val FORGET_SMS_URL = "${BASE_URL}uc/ut/forget/send-sms"

        // 检查手机验证码是否正确
        const val CHECK_SMS_CODE_URL = "${BASE_URL}uc/ut/check-sms-code"

        // 注册账号
        const val REGISTER_URL = "${BASE_URL}uc/user/register"

        // 找回密码（通过短信找回）
        const val FORGET_URL = "${BASE_URL}uc/user/forget"

        // 修改密码（通过旧密码修改）
        const val MODIFY_PSD_URL = "${BASE_URL}uc/user/modify-pwd"

    }
}