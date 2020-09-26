package ar.edu.utn.frba.mobile.clases_2020c2.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.utn.frba.mobile.clases_2020c2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image_list_item.view.*

class ImagesAdapter(private val mValues: List<Uri>) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener = View.OnClickListener {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_image_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUri = mValues[position]

        Picasso.get().load(imageUri).into(holder.image)

        with(holder.mView) {
            tag = imageUri.toString()
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val image: ImageView = mView.edited_image as ImageView
    }
}
