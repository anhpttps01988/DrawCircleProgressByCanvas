package net.dirox.coreproject.activities.main.view


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_timeline.*
import net.dirox.coreproject.R
import net.dirox.coreproject.activities.main.viewmodel.TimelineViewModel
import net.dirox.coreproject.common.di.scoped.ActivityScoped
import net.dirox.coreproject.common.fragment.BaseFragment
import net.dirox.coreproject.common.viewmodel.ViewModelFactory
import net.dirox.coreproject.databinding.FragmentTimelineBinding
import java.util.*
import javax.inject.Inject

@ActivityScoped
class TimelineFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mViewModel: TimelineViewModel
    private lateinit var mBinding: FragmentTimelineBinding
    private var mCountDownTimer: CountDownTimer? = null

    companion object {
        private var INSTANCE: TimelineFragment = TimelineFragment()

        fun newInstance() : TimelineFragment {
            return INSTANCE
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false)

        mBinding.setLifecycleOwner(this)

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(TimelineViewModel::class.java)

        lifecycle.addObserver(mViewModel)

        startTimeline()

        return mBinding.root
    }

    private fun startTimeline() {

        val max = 360

        val endAngle = 172f

        val milliseconds = 360000L

        val interval = 1000L

        mCountDownTimer = object : CountDownTimer(milliseconds, interval) {

            override fun onTick(p0: Long) {

                val remainedSecs = p0 / 1000

                val hours = remainedSecs / 3600

                val minutes = (remainedSecs % 3600) / 60

                val seconds = remainedSecs % 60

                val timeFormat = String.format(Locale.US, "%d:%02d:%02d", hours, minutes, seconds)

                tvTime.text = timeFormat

                val perSec = remainedSecs.toFloat()

                val percent = (1 - perSec / max) * endAngle

                mBinding.progressCircular.increaseEndAngle(percent)

            }

            override fun onFinish() {
                Toast.makeText(activity, "Done!", Toast.LENGTH_SHORT).show()
                cancel()
            }

        }

        mCountDownTimer?.start()
    }

}