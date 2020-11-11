package com.example.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.*
import kotlin.collections.ArrayList

class CrimePagerActivity: AppCompatActivity() {

    private lateinit var viewPager :ViewPager
    private lateinit var crimes : ArrayList<Crime>

    companion object {
        private  val EXTRA_CRIME_ID = "com.example.android.criminalintent.crime_id"

        fun newIntent(packageContext: Context, crimeId: UUID): Intent {
            val intent = Intent(packageContext, CrimePagerActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeId)
            println(intent)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_pager)
        viewPager = findViewById(R.id.crime_view_pager)
        crimes = CrimeLab.get(this).crimes
        var fragmentManager = supportFragmentManager
        viewPager.adapter = object : FragmentStatePagerAdapter(fragmentManager) {
            override fun getCount(): Int {
                return crimes.size
            }

            override fun getItem(position: Int): Fragment {
                return CrimeFragment.newIstance(crimes[position].id)
            }

        }
        var crime_id = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID

        for (i in 0..99) {
            if (crimes[i].id.equals(crime_id)) {
                viewPager.currentItem = i
                break
            }
        }
    }

}