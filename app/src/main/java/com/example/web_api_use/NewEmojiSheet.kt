package com.example.todolisttutorial

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.web_api_use.*
import com.example.web_api_use.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class NewEmojiSheet(private var emojiName: String?) : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var emojiViewModel: EmojiAddViewModel
    private lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        emojiViewModel = ViewModelProvider(activity).get(EmojiAddViewModel::class.java)

        if (emojiName != null){
            binding.saveButton.setOnClickListener {
                updateEmoji()
            }
        } else {
            binding.saveButton.setOnClickListener {
                saveAction()
            }
        }
        binding.deleteButton.setOnClickListener {
            deleteEmoji()
        }

        if(emojiName != null){
            val activity: MainActivity? = activity as MainActivity?
            binding.emojiTitle.text = "Edit Emoji"
            binding.name.setText(emojiName)
            binding.deleteButton.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun deleteEmoji()
    {
        val retrofit = ServiceBuilder.buildService(EmojiService::class.java)

        emojiViewModel.name.value = binding.name.text.toString()
        emojiViewModel.emoji.value = binding.desc.text.toString()

        val emojiInfo = DeleteEmojiInfo (
            name= emojiViewModel.name.value.toString()
        )

        retrofit.deleteEmoji(emojiInfo).enqueue(
            object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Toast.makeText(activity, response.body(), Toast.LENGTH_SHORT)
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                }
            }
        )
        val activity: MainActivity? = activity as MainActivity?
        activity?.updateEmojiList(activity.findViewById(R.id.recyclerview))
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

    private fun updateEmoji()
    {
        val retrofit = ServiceBuilder.buildService(EmojiService::class.java)

        emojiViewModel.name.value = binding.name.text.toString()
        emojiViewModel.emoji.value = binding.desc.text.toString()

        val emojiInfo = EmojiInfo (
            name= emojiViewModel.name.value.toString(),
            emoji = emojiViewModel.emoji.value.toString())

        retrofit.updateEmoji(emojiInfo).enqueue(
            object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Toast.makeText(activity, response.body(), Toast.LENGTH_SHORT)
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                }
            }
        )
        val activity: MainActivity? = activity as MainActivity?
        activity?.updateEmojiList(activity.findViewById(R.id.recyclerview))
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

    private fun saveAction()
    {
        val retrofit = ServiceBuilder.buildService(EmojiService::class.java)

        emojiViewModel.name.value = binding.name.text.toString()
        emojiViewModel.emoji.value = binding.desc.text.toString()

        val emojiInfo = EmojiInfo (
            name= emojiViewModel.name.value.toString(),
            emoji = emojiViewModel.emoji.value.toString())

        retrofit.addEmoji(emojiInfo).enqueue(
            object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Toast.makeText(activity, response.body(), Toast.LENGTH_SHORT)
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
                }
            }
        )
        val activity: MainActivity? = activity as MainActivity?
        activity?.updateEmojiList(activity.findViewById(R.id.recyclerview))
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

}

data class EmojiInfo (
    @SerializedName("name") val name: String?,
    @SerializedName("emoji") val emoji: String?
)

data class DeleteEmojiInfo (
    @SerializedName("name") val name: String?
)
