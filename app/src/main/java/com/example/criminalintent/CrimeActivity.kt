package com.example.criminalintent

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import java.util.*


class CrimeActivity : SingleFragmentActivity() {


    override fun createFragment(): Fragment {
        var crimeId = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID
        return CrimeFragment.newIstance(crimeId)
    }

    companion object {
        private  val EXTRA_CRIME_ID = "com.example.android.criminalintent.crime_id"

        fun newIntent(packageContext: Context, crimeId: UUID): Intent {
            val intent = Intent(packageContext, CrimeActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeId)
            return intent
        }
    }

}