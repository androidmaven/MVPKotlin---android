/*
 *
 *  * Copyright (C) 2017 Oluwatobi Akinpelu.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.padipady.mvpkotlin.androidmvpkotlin.login

class LoginPresenterImpl(private var loginView: LoginView?) : LoginPresenter, LoginInteractor.OnLoginFinishedListener {
    private val loginInteractor: LoginInteractor

    init {
        this.loginInteractor = LoginInteractorImpl()
    }

    override fun validateCredentials(username: String, password: String) {
            loginView?.showProgress()

        loginInteractor.login(username, password, this)
    }

    override fun onDestroy() {
        loginView = null
    }

    override fun onUsernameError() {
            loginView?.setUsernameError()
            loginView?.hideProgress()
    }

    override fun onPasswordError() {
            loginView?.setPasswordError()
            loginView?.hideProgress()

    }

    override fun onSuccess() {
            loginView?.navigateToHome()
    }
}
