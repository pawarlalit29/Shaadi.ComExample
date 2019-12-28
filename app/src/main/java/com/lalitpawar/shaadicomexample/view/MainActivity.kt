package com.lalitpawar.shaadicomexample.view

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.snackbar.Snackbar
import com.lalitpawar.shaadicomexample.R
import com.lalitpawar.shaadicomexample.databinding.ActivityMainBinding
import com.lalitpawar.shaadicomexample.model.Result
import com.lalitpawar.shaadicomexample.view.adapter.ProfileAdapter
import com.lalitpawar.shaadicomexample.view_model.MainViewModel
import com.yuyakaido.android.cardstackview.*

class MainActivity : AppCompatActivity(), CardStackListener, ProfileAdapter.setOnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var errorSnackbar: Snackbar? = null

    private val manager by lazy { CardStackLayoutManager(this, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.adapterInit(this)
        binding.viewmodel = viewModel


        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        initialize()
    }


    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.Bottom)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(10.0f)
        manager.setScaleInterval(0.90f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        binding.cardStackView.layoutManager = manager
        binding.cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

        binding.viewmodel?.profileAdapter?.setOnItemClick(this)
    }

    override fun onClickItem(view: View, results: Result) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardSwiped(direction: Direction?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardCanceled() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardAppeared(view: View?, position: Int) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardRewound() {
        //To change body of created functions use File | Settings | File Templates.
    }
}
