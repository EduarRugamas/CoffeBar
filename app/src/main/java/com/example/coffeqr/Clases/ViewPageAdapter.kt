package com.example.coffeqr.Clases

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.coffeqr.Fragments.Coffe_Fragment
import com.example.coffeqr.Fragments.desserts_Fragment

class ViewPageAdapter (fm : FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->{
                "Cafes"
            }
            1->{
                "Postres"
            }
            else-> {
                "Cafes"
            }
        }
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0-> {
                Coffe_Fragment()
            }
            1-> {
                    desserts_Fragment()
            }
            else-> {
                Coffe_Fragment()
            }
        }
    }
}