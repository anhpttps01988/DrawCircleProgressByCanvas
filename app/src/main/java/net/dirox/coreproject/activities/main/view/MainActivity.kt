package net.dirox.coreproject.activities.main.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.dirox.coreproject.R
import net.dirox.coreproject.activities.main.viewmodel.MainActivityContract
import net.dirox.coreproject.activities.main.viewmodel.MainActivityViewModel
import net.dirox.coreproject.common.activity.BaseActivity
import net.dirox.coreproject.common.di.scoped.ActivityScoped
import net.dirox.coreproject.common.viewmodel.ViewModelFactory
import net.dirox.coreproject.databinding.ActivityMainBinding
import java.util.*
import javax.inject.Inject
import kotlin.math.round

@ActivityScoped
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityContract.Navigator {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding

    private var percent: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        viewModel = ViewModelProviders.of(this@MainActivity, viewModelFactory).get(MainActivityViewModel::class.java)

        lifecycle.addObserver(viewModel)

        binding.appBar.viewModel = viewModel

        binding.appBar.contentMain.viewModel = viewModel

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )



        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        seekBar.max = 90

        val endAngle = 172f

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                val max = seekBar.max.toFloat()

                percent = (p1 / max) * endAngle

                progress_circular.increaseEndAngle(percent)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

    }

    override fun onStart() {
        super.onStart()
        onStartTimeline()
    }

    private fun onStartTimeline() {

        val seconds = 30

        val endAngle = 172f

        val millisSeconds = 30000L

        val countdownInterval = 1000L

        val cdTimer = object : CountDownTimer(millisSeconds, countdownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val remainedSecs = millisUntilFinished / 1000
                tvTime.let {
                    val timeFormat = String.format(
                        Locale.US, "%d:%02d:%02d",
                        remainedSecs / 3600,
                        (remainedSecs % 3600) / 60,
                        remainedSecs % 60
                    )
                    tvTime.text = timeFormat

                    //quy doi nguoc
                    val inverseSec = 1 - remainedSecs.toFloat() / seconds

                    percent = inverseSec * endAngle

                    progress_circular.increaseEndAngle(percent)

                }
            }

            override fun onFinish() {
                tvTime.text = "0:00:00"
                Toast.makeText(this@MainActivity, "Done !", Toast.LENGTH_SHORT).show()
                cancel()
            }
        }
        cdTimer.start()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
