package com.example.searchbookapplication


import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.searchbookapplication.databinding.ActivityMainBinding
import com.example.searchbookapplication.viewModel.MyViewModel


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var btnClicked : String = ""
    private lateinit var tabBooks: LinearLayoutCompat
    private lateinit var tabFavourite : LinearLayoutCompat
    private lateinit var txtTabBooks: AppCompatTextView
    private lateinit var viewTabBooks: View
    private lateinit var txtTabFav: AppCompatTextView
    private lateinit var viewTabFav: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        val MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        tabBooks = binding.tabBooks
        tabFavourite = binding.tabFavourite
        txtTabBooks = binding.txtTabBooks
        viewTabBooks = binding.viewTabBooks
        txtTabFav = binding.txtTabFav
        viewTabFav = binding.viewTabFav



        val fragment = homeFragment()
        loadFragment(fragment)
        binding.imgSearch.setOnClickListener(){
            val edtValue = binding.edtSearch.text.toString()
            MyViewModel.setMyData(edtValue)
        }

        tabFavourite.setOnClickListener(){
            if (!fragment.equals(FavoriteFragment())){
                val fragment = FavoriteFragment()
                loadFragment(fragment)
                Toast.makeText(this,"Favorite Fragment Loaded",Toast.LENGTH_SHORT).show()

                txtTabBooks.setTypeface(null, Typeface.NORMAL)
                viewTabBooks.visibility = View.GONE
                txtTabFav.setTypeface(null,Typeface.BOLD)
                viewTabFav.visibility = View.VISIBLE
            }




        }

        tabBooks.setOnClickListener(){
            if (!fragment.equals(homeFragment())){
                val fragment = homeFragment()
                loadFragment(fragment)
                Toast.makeText(this,"Home Fragment Loaded",Toast.LENGTH_SHORT).show()

                //btnClicked = "homeFragmentClicked"
                txtTabBooks.setTypeface(null, Typeface.BOLD)
                viewTabBooks.visibility = View.VISIBLE
                txtTabFav.setTypeface(null,Typeface.NORMAL)
                viewTabFav.visibility = View.GONE
            }

        }







        when (btnClicked) {
            "homeFragmentClicked" -> {

            }
           "favoriteFragmentClicked" -> {
               val fragment = FavoriteFragment()
               loadFragment(fragment)
               Toast.makeText(this,"Favorite Fragment Loaded",Toast.LENGTH_SHORT).show()
            }

        }


    }

   fun loadFragment(fragment: Fragment) {
        // create a FragmentManager object
        val fragmentManager: FragmentManager = supportFragmentManager

        // create a FragmentTransaction object
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // replace the fragment_container view with the passed-in fragment
        fragmentTransaction.replace(binding.fragmentContainer1.id, fragment)

        // add the fragment to the back stack so the user can navigate back to the previous fragment
        fragmentTransaction.addToBackStack(fragment.toString())

        // commit the transaction
       Toast.makeText(this, "Fragment Loaded", Toast.LENGTH_LONG).show()
        fragmentTransaction.commit()
    }
}