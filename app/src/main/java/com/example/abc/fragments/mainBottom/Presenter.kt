package com.example.abc.fragments.mainBottom

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class Presenter(private val view: HomeView) : HomePresenter {
    override fun fetchData() {
        view.showProgress()
        doAsync {
            Thread.sleep(3000)
            val random = Random()
            val randomInt = random.nextInt(20)
            if (randomInt < 11) {
                uiThread {
                    view.showInfo()
                }
            } else {
                uiThread {
                    view.showNoInfo()
                }
            }
        }
    }
}