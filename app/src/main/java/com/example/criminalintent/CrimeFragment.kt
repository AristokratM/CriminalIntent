package com.example.criminalintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.util.*


class CrimeFragment : Fragment() {


    private lateinit var crime :Crime
    private lateinit var titleField : EditText
    private lateinit var dateButton : Button
    private lateinit var solvedCheckBox : CheckBox

    companion object {
        private val ARG_CRIME_ID = "crime_id"
        fun newIstance(crimeId: UUID) : CrimeFragment {
            var args = Bundle()
            var crimeFragment = CrimeFragment()
            args.putSerializable(ARG_CRIME_ID, crimeId)
            crimeFragment.arguments = args
            return  crimeFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val crimeId = arguments!!.getSerializable(ARG_CRIME_ID) as UUID
        crime = CrimeLab.get(context!!).getCrime(crimeId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_crime, container, false)
        titleField = view.findViewById(R.id.crime_title)
        titleField.setText(crime.title)
        titleField.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    crime.title = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )
        dateButton = view.findViewById(R.id.crime_date)
        dateButton.text = crime.date.toString()
        dateButton.isEnabled = false
        solvedCheckBox = view.findViewById(R.id.crime_solved)
        solvedCheckBox.isChecked = crime.solved
        solvedCheckBox.setOnCheckedChangeListener(
            object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    crime.solved = isChecked
                }

            }
        )
        return view
    }

    fun returnResult() {
        activity!!.setResult(Activity.RESULT_OK, null)
    }

}