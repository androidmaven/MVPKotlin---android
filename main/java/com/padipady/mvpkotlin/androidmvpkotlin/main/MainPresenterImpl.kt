/*
 *
 Copyright 2017 Oluwatobi Akinpelu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 *
 */

package com.padipady.mvpkotlin.androidmvpkotlin.main

class MainPresenterImpl(mainView: MainView, private val findItemsInteractor: FindItemsInteractor) : MainPresenter, FindItemsInteractor.OnFinishedListener {

    var mainView: MainView? = null
        private set

    init {
        this.mainView = mainView
    }

    override fun onResume() {
            mainView?.showProgress()

        findItemsInteractor.findItems(this)
    }

    override fun onItemClicked(position: Int) {
            mainView?.showMessage(String.format("Position %d clicked", position + 1))

    }

    override fun onDestroy() {
        mainView = null
    }

    override fun onFinished(items: List<String>) {
            mainView?.setItems(items)
            mainView?.hideProgress()
    }
}
