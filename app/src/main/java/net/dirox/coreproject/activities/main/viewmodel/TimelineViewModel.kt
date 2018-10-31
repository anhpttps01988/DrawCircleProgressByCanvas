package net.dirox.coreproject.activities.main.viewmodel

import android.arch.lifecycle.LifecycleObserver
import net.dirox.coreproject.common.viewmodel.BaseViewModel
import net.dirox.coreproject.data.source.DataRepository
import javax.inject.Inject

class TimelineViewModel @Inject constructor(var dataRepository: DataRepository): BaseViewModel(), LifecycleObserver {



}