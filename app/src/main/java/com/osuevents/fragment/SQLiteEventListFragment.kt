package com.osuevents.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.osuevents.R
import com.osuevents.data.Event
import com.osuevents.db.DatabaseHandler

class SQLiteEventListFragment : EventListFragment() {
    val TAG: String = javaClass.simpleName

    var recyclerAdapter: RecyclerView.Adapter<EventListItemViewHolder>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerAdapter = object : RecyclerView.Adapter<EventListItemViewHolder>() {
            var eventList: ArrayList<Event>? = null
            init {
                var dbHandler = DatabaseHandler(context)
                eventList = dbHandler.getAllEvents()
            }
            override fun onBindViewHolder(holder: EventListItemViewHolder?, position: Int) {
                holder?.bind(eventList!![position])
            }

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EventListItemViewHolder {
                var view = LayoutInflater.from(context).inflate(R.layout.fragment_event_list_item, parent, false)
                return EventListItemViewHolder(view)
            }

            override fun getItemCount(): Int {
                return eventList!!.size
            }

        }
        recyclerView?.adapter = recyclerAdapter
    }
}
