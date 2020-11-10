package com.example.criminalintent

import android.content.Context
import java.util.*
import kotlin.collections.ArrayList

class CrimeLab private constructor(context: Context){
    companion object {
        private var crimeLab : CrimeLab? = null
        var crimes = ArrayList<Crime>()
            private set
        fun get(context: Context) : CrimeLab{
            if(crimeLab == null) {
                crimeLab = CrimeLab(context)
            }
            return crimeLab!!
        }
        init {
            for (i in 0..99) {
                val crime = Crime()
                crime.title = "Crime #$i"
                crime.solved = i % 2 == 0 // Для каждого второго объекта
                crimes.add(crime)
            }
        }
        fun getCrime(id: UUID) : Crime  = crimes.first { crime -> crime.id == id  }
    }
}