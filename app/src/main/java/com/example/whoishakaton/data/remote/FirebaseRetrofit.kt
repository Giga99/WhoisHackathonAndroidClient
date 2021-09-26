package com.example.whoishakaton.data.remote

import com.example.whoishakaton.data.remote.responses.PushNotificationRequest
import com.example.whoishakaton.utils.CONTENT_TYPE
import com.example.whoishakaton.utils.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FirebaseRetrofit {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotificationRequest
    ): Response<ResponseBody>
}
