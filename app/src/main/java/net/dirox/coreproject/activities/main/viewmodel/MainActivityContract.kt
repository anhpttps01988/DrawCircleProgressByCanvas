package net.dirox.coreproject.activities.main.viewmodel


interface MainActivityContract {

    interface ViewModelContract {
        fun increaseCount()
        fun loadCountryList()
    }

    interface Navigator {

    }

}