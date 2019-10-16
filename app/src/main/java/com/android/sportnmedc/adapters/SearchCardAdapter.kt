package com.android.sportnmedc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import com.android.sportnmedc.R
import com.android.sportnmedc.exdata.cards.CardModel


class SearchCardAdapter(context: Context, resource: Int, textViewResourceId: Int,val items: ArrayList<CardModel>)
    : ArrayAdapter<CardModel>(context, resource, textViewResourceId, items){

    private var tempItems: ArrayList<CardModel> = ArrayList(items)
    private var suggestions: ArrayList<CardModel> = ArrayList()


    fun getItemPosition(position:Int):CardModel{
        return items[position]
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view =  if (convertView == null) {
            LayoutInflater.from(context).inflate(R.layout.item_search, parent, false)
        }else{
            convertView
        }
        val profileImg = view?.findViewById(R.id.profile_img) as ImageView
        val nameTxt = view?.findViewById(R.id.name_txt) as TextView
        val typeTxt = view?.findViewById(R.id.type_txt) as TextView
        items[position].apply {
            profileImg.setImageResource(image)
            nameTxt.text = name
            typeTxt.text = sport
        }


        return view
    }
    override fun getFilter(): Filter {
        return nameFilter
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    var nameFilter: Filter = object : Filter() {
        override fun convertResultToString(resultValue: Any): CharSequence {
            return (resultValue as CardModel).name
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            if (constraint != null) {
                suggestions.clear()
                for (card in tempItems) {
                    if (card.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(card)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = suggestions
                filterResults.count = suggestions.size
                return filterResults
            } else {
                return null
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.also {
                val filterList = it.values as? ArrayList<CardModel>
                filterList?.also {
                    if (it.size > 0) {
                        clear()
                        for (card in it) {
                            add(card)
                            notifyDataSetChanged()
                        }
                    }
                }
            }

        }
    }
}