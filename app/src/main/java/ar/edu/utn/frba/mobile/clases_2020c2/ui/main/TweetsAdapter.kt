package ar.edu.utn.frba.mobile.clases_2020c2.ui.main


import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.utn.frba.mobile.clases_2020c2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_image.view.*
import kotlinx.android.synthetic.main.item_post.view.*

class TweetsAdapter(private val listener: MainFragment.OnFragmentInteractionListener?, private val tweets: List<Tweet>): RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) R.layout.item_post
        else {
            val itemIndex = position - 1 // el primer item es el encabezado
            val hasPicture = tweets[itemIndex].image != null
            if (hasPicture) R.layout.item_image
            else R.layout.item_simple
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        when (viewType) {
            R.layout.item_post -> {
                view.postButton.setOnClickListener {
                    listener?.showFragment(StatusUpdateFragment())
                }
            }
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_simple, R.layout.item_image -> {
                val itemIndex = position - 1 // el primer item es el encabezado

                holder.itemView.nameText.text = tweets[itemIndex].name
                holder.itemView.certifiedIcon.visibility = if (tweets[itemIndex].certified) View.VISIBLE else View.GONE
                holder.itemView.usernameText.text = tweets[itemIndex].username
                holder.itemView.tweetContent.text =tweets[itemIndex].content
                holder.itemView.commentCount.text = tweets[itemIndex].commentCount.toString()
                holder.itemView.retweetCount.text = tweets[itemIndex].retweetCount.toString()
                holder.itemView.likeCount.text = tweets[itemIndex].likeCount.toString()

                if (tweets[itemIndex].image != null){
                    Picasso.get().load(Uri.parse(tweets[itemIndex].image)).into(holder.itemView.image)
                }
            }
            else -> {}
        }
    }

    override fun getItemCount(): Int = tweets.size + 1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}