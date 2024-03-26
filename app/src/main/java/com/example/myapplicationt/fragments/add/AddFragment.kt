package com.example.myapplicationt.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.myapplicationt.R
import com.example.myapplicationt.data.models.ToDoData
import com.example.myapplicationt.data.viewmodel.ToDoViewModel
import com.example.myapplicationt.databinding.FragmentAddBinding
import com.example.myapplicationt.fragments.SharedViewModel

class AddFragment : Fragment() {


    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentAddBinding? = null

    /*

    View Binding yaparken fragment binding sınıfımızın nullable nesnesi non-null bir tür döndüren
    get fonksiyonuna eşitlenir. get() fonksiyonu yerine klasik atama yapmış olsaydık (private val binding = _binding!!)
    "_binding" nesnesi fragment yaşam döngüsü boyunca değişmiş olsaydı (fragmentın destroy edildiği durumda _binding null olur)
    bu değişikliği alamazdık. Bu, fragment view'ı yok edildikten sonra bile "binding" üzerinden erişilebileceği ve
    potansiyel memory leaklere neden olabileceği anlamına gelir.

    _binding değiştiğinde yeni değerini alabilmek, onCreateView ve onDestroyView arasındaki süreçte
    _binding in null olmadığından emin olmak için null olmayan bir getter kullanırız. Bu yaklaşım binding
    propertysinin her zaman geçerli ve güncel bir view referansı döndürmesini sağlar. Eğer _binding null ise
    (yani view yok edilmişse), bu NullPointerException hatasına neden olur ve bu da genellikle beklenen bir
    davranıştır çünkü bir view'ın yok edilmesinden sonra ona erişim olmamalıdır. Bu yaklaşım memory leakleri
    önler çünkü fragmentın view'ı yok edildiğinde _binding null olur ve böylece garbage collecter tarafından
    temizlenebilir.

     */
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.add_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.menu_add) {
                    insertDataToDb()
                } else if (menuItem.itemId == android.R.id.home) { // Back button'un id'sini belirtir.
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun insertDataToDb() {
        val title = binding.titleEt.text.toString()
        val priority = binding.prioritiesSpinner.selectedItem.toString()
        val description = binding.descriptionEt.text.toString()

        val allFieldsFull = sharedViewModel.verifyDataFromUser(title, description)

        if (allFieldsFull) {

            // Inserting data to database
            val newData = ToDoData(
                0,
                title,
                sharedViewModel.parsePriority(priority),
                description
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireActivity(), "Successfully added!", Toast.LENGTH_SHORT).show()

            // Navigating back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireActivity(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}