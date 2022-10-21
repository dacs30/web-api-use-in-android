package com.example.web_api_use

import CustomAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolisttutorial.NewEmojiSheet
import com.example.web_api_use.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

//    companion object {
//        const val URL_COUNTRY_API = "http://10.0.2.2:8888"
//    }

    lateinit var allEmojiRendered: List<EmojiViewModel>

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: EmojiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // getting the recyclerview by its id
        val recyclerview = binding.recyclerview

        binding.newTaskButton.setOnClickListener {
            NewEmojiSheet(null).show(supportFragmentManager, "newTaskTag")
        }

        updateEmojiList(recyclerview)

        // this creates a vertical layout Manager
//        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
//        val data = ArrayList<EmojiViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
//        for (i in 1..20) {
//            data.add(EmojiViewModel( "Item " + i, "name") )
//        }
//
//        // This will pass the ArrayList to our Adapter
//        val adapter = CustomAdapter(data)
//
//        // Setting the Adapter with the recyclerview
//        recyclerview.adapter = adapter

//        val retrofit = ServiceBuilder.buildService(EmojiService::class.java)
//
//        retrofit.listEmojis().enqueue(
//            object: Callback<List<EmojiViewModel>> {
//                override fun onResponse(call: Call<List<EmojiViewModel>>, response: Response<List<EmojiViewModel>>) {
//                    val allEmoji = response.body()
//                    for (c in allEmoji!!) {
//                        Log.v("HEY THERE", "we are here")
//                        Log.v(
//                            MainActivity::class.simpleName,
//                            c.emoji
//                        )
//                        data.add(c)
//                        // This will pass the ArrayList to our Adapter
//                        val adapter = CustomAdapter(data)
//
//                        // Setting the Adapter with the recyclerview
//                        recyclerview.adapter = adapter
//                    }
//                }
//                override fun onFailure(call: Call<List<EmojiViewModel>>, t: Throwable) {
//                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
//                }
//            }
//        )

    }

    fun openFragmentEdit(name: String){
        NewEmojiSheet(name).show(supportFragmentManager, "newTaskTag")
    }

    public fun updateEmojiList(recyclerView: RecyclerView){
        val retrofit = ServiceBuilder.buildService(EmojiService::class.java)

        val data = ArrayList<EmojiViewModel>()

        recyclerView.layoutManager = LinearLayoutManager(this)

        retrofit.listEmojis().enqueue(
            object: Callback<List<EmojiViewModel>> {
                override fun onResponse(call: Call<List<EmojiViewModel>>, response: Response<List<EmojiViewModel>>) {
                    val allEmoji = response.body()
                    for (c in allEmoji!!) {
                        Log.v("HEY THERE", "we are here")
                        Log.v(
                            MainActivity::class.simpleName,
                            c.emoji
                        )
                        data.add(c)
                        // This will pass the ArrayList to our Adapter
                    }
                    val adapter = CustomAdapter(data)
                    adapter.setOnItemClickListener(object: onItemClickListener{
                        override fun onItemClick(name: String) {
                            NewEmojiSheet(name).show(supportFragmentManager, "newTaskTag")
                        }
                    })

                    // Setting the Adapter with the recyclerview
                    recyclerView.adapter = adapter
                }
                override fun onFailure(call: Call<List<EmojiViewModel>>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                }
            }
        )
    }
}