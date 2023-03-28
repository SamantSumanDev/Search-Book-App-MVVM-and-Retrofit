package com.example.searchbookapplication


import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.searchbookapplication.databinding.ActivityMainBinding
import com.example.searchbookapplication.viewModel.MyViewModel


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var tabBooks: LinearLayoutCompat
    private lateinit var tabFavourite: LinearLayoutCompat
    private lateinit var txtTabBooks: AppCompatTextView
    private lateinit var viewTabBooks: View
    private lateinit var txtTabFav: AppCompatTextView
    private lateinit var viewTabFav: View
    private lateinit var edtSearch: AppCompatEditText
    private lateinit var imgSearch: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        supportActionBar?.hide()
        val MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        tabBooks = binding.tabBooks
        tabFavourite = binding.tabFavourite
        txtTabBooks = binding.txtTabBooks
        viewTabBooks = binding.viewTabBooks
        txtTabFav = binding.txtTabFav
        viewTabFav = binding.viewTabFav
        edtSearch = binding.edtSearch
        imgSearch = binding.imgSearch

        val fragment = homeFragment()
        loadFragment(fragment,"homeFragment")

        binding.imgSearch.setOnClickListener() {
            edtSearch.text = null
        }
        edtSearch.setOnEditorActionListener { _, keyCode, event ->
            if (((event?.action ?: -1) == KeyEvent.ACTION_DOWN)
                || keyCode == EditorInfo.IME_ACTION_DONE) {
                if (!homeFragment().equals(FavoriteFragment())) {
                    loadFragment(fragment,"homeFragment")
                    supportFragmentManager.popBackStack("FavoriteFragment",
                        FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    txtTabBooks.setTextColor(resources.getColor(R.color.md_orange_900))
                    viewTabBooks.visibility = View.VISIBLE
                    txtTabFav.setTextColor(resources.getColor(R.color.dark_grey))
                    viewTabFav.visibility = View.GONE
                }
                val edtValue = edtSearch.text.toString()
                MyViewModel.setMyData(edtValue)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                imgSearch.setImageResource(R.drawable.search)
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    imgSearch.setImageResource(R.drawable.search)
                } else {
                    imgSearch.setImageResource(R.drawable.close)

                    edtSearch.imeOptions = EditorInfo.IME_ACTION_SEARCH
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })

        tabFavourite.setOnClickListener() {
                val fragment = FavoriteFragment()
                loadFragment(fragment,"FavoriteFragment")
                supportFragmentManager.popBackStack("HomeFragment",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
                Toast.makeText(this, "Favorite Fragment Loaded", Toast.LENGTH_SHORT).show()
                txtTabBooks.setTextColor(resources.getColor(R.color.dark_grey))
                viewTabBooks.visibility = View.GONE
                txtTabFav.setTextColor(resources.getColor(R.color.md_orange_900))
                viewTabFav.visibility = View.VISIBLE

        }

        tabBooks.setOnClickListener() {

                val fragment = homeFragment()
                loadFragment(fragment,"homeFragment")
                supportFragmentManager.popBackStack("FavoriteFragment",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
                Toast.makeText(this, "Home Fragment Loaded", Toast.LENGTH_SHORT).show()
                txtTabBooks.setTextColor(resources.getColor(R.color.md_orange_900))
                viewTabBooks.visibility = View.VISIBLE
                txtTabFav.setTextColor(resources.getColor(R.color.dark_grey))
                viewTabFav.visibility = View.GONE

        }
    }
    fun loadFragment(fragment: Fragment, tag: String) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentContainer1.id, fragment)
        fragmentTransaction.addToBackStack(tag)
        Toast.makeText(this, "Fragment Loaded", Toast.LENGTH_LONG).show()
        fragmentTransaction.commit()
    }
}