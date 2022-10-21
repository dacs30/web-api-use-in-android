import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.web_api_use.EmojiViewModel
import com.example.web_api_use.R
import com.example.web_api_use.onItemClickListener

class CustomAdapter(private val mList: List<EmojiViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view, mListener)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val EmojiViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = EmojiViewModel.emoji

        holder.name.text = EmojiViewModel.name

//        holder.itemView.setOnClickListener { onItemClicked(EmojiViewModel.name) }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val name: TextView = itemView.findViewById(R.id.name)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(name.text.toString())
            }
        }

    }
}
