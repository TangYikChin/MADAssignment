package my.com.madassignment.staffManagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import kotlinx.coroutines.launch
import my.com.madassignment.databinding.ActivityMainBinding
import my.com.madassignment.R
import my.com.madassignment.managerLogIn.Manager
import my.com.madassignment.managerLogIn.ManagerViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val nav by lazy { supportFragmentManager.findFragmentById(R.id.host)!!.findNavController() }

    private lateinit var abc: AppBarConfiguration
    private val auth: ManagerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        abc = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.managerLogin),
            binding.drawerLayout
        )

        setupActionBarWithNavController(nav,abc)
        binding.navView.setupWithNavController(nav)

        auth.getUserLiveData().observe(this){ user->
            binding.navView.menu.clear()
            val h =binding.navView.getHeaderView(0)
            binding.navView.removeHeaderView(h)

            if(user==null){
                // no login
                binding.navView.inflateMenu(R.menu.main)
                binding.navView.inflateHeaderView(R.layout.header_non_login)
            }
            else{
                // already login
                binding.navView.inflateMenu(R.menu.main_login)
                binding.navView.inflateHeaderView(R.layout.header)
                setHeader(user)
            }

            binding.navView.menu.findItem(R.id.logout)?.setOnMenuItemClickListener { logout() }
        }

        lifecycleScope.launch{auth.loginFromPreferences(this@MainActivity)}
    }

    private fun setHeader(user: Manager){
        val h = binding.navView.getHeaderView(0)

        val txtname     :TextView = h.findViewById(R.id.nameUI)
        val textemail   :TextView = h.findViewById(R.id.emailUI)

        txtname.text    = user.name
        textemail.text  = user.email

    }

    private fun logout():Boolean{

        auth.logout(this)
        nav.popBackStack(R.id.homeFragment,false)
        nav.navigateUp()

        binding.drawerLayout.close()
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        return nav.navigateUp(abc) || super.onSupportNavigateUp()
    }





}