package com.example.web_api_use

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EmojiAddViewModel: ViewModel()
{
    var name = MutableLiveData<String>()
    var emoji = MutableLiveData<String>()
}