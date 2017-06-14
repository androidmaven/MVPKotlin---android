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

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast


import com.padipady.mvpkotlin.androidmvpkotlin.R

import android.view.View.GONE

class MainActivity : Activity(), MainView, AdapterView.OnItemClickListener {

    private var listView: ListView? = null
    private var progressBar: ProgressBar? = null
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.list) as ListView
        listView?.onItemClickListener = this
        progressBar = findViewById(R.id.progress) as ProgressBar
        presenter = MainPresenterImpl(this, FindItemsInteractorImpl())
        listView?.visibility = GONE
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }


    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
        listView?.visibility = View.INVISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.INVISIBLE
        listView?.visibility = View.VISIBLE
    }

    override fun setItems(items: List<String>) {
        listView?.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        presenter?.onItemClicked(position)
    }
}
