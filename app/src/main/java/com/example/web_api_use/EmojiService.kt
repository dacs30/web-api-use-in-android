package com.example.web_api_use

import com.example.todolisttutorial.DeleteEmojiInfo
import com.example.todolisttutorial.EmojiInfo
import retrofit2.Call
import retrofit2.http.*

interface EmojiService {

    @GET("/")
    fun listEmojis(): Call<List<EmojiViewModel>>

    @POST("/create")
    fun addEmoji(@Body emojiData: EmojiInfo): Call<String>

    @HTTP(method = "DELETE", path = "/delete", hasBody = true)
    fun deleteEmoji(@Body deleteEmojiInfo: DeleteEmojiInfo): Call<String>

    @PUT("/put")
    fun updateEmoji(@Body emojiData: EmojiInfo): Call<String>
}