package com.example.myapplicationt.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.myapplicationt.R
import com.example.myapplicationt.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container,false)
        val view = binding.root



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()

        // Burada addMenuProvider fonksiyonu bizden MenuProvider interface'i bekliyor. Biz de bunun için
        // object kullanarak anonymous class oluşturmuş olduk.
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.update_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == android.R.id.home) {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}