package com.example.criminalintent

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CrimeListFragment : Fragment() {
    private lateinit var crimeRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view)
        crimeRecyclerView.layoutManager = LinearLayoutManager(activity)
        udpateUI()
        return view
        
    }

    private inner class CrimeHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_crime, parent, false)), View.OnClickListener {
        private var titleTextView : TextView
        private var dateTextView : TextView
        private lateinit var crime : Crime
        private lateinit var solvedImageView : ImageView

        init {
            itemView.setOnClickListener(this)
            titleTextView = itemView.findViewById(R.id.crime_title)
            dateTextView = itemView.findViewById(R.id.crime_date)
            solvedImageView = itemView.findViewById(R.id.crime_solved)
        }
        fun bind(cr:Crime) {
            crime = cr
            titleTextView.text = crime.title
            dateTextView.text = DateFormat.format("EEE, MMM dd, yyyy", crime.date)
            solvedImageView.visibility = if (crime.solved) View.VISIBLE else View.GONE
        }

        override fun onClick(v: View?) {
            Toast.makeText(activity, "${crime.title} clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class CrimeAdapter(private var crimes: ArrayList<Crime>) : RecyclerView.Adapter<CrimeHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val layoutInflater = LayoutInflater.from(activity)
            return CrimeHolder(layoutInflater, parent)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            var crime = crimes[position]
            holder.bind(crime)
        }

        override fun getItemCount(): Int {
            return crimes.size
        }

    }
    fun udpateUI() {
        var crimeLab = CrimeLab.get(activity!!)
        var crimes = crimeLab.crimes
        var adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter
    }
}