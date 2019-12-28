package com.lalitpawar.shaadicomexample.view_model


import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.lalitpawar.shaadicomexample.R
import com.lalitpawar.shaadicomexample.base.BaseViewModel
import com.lalitpawar.shaadicomexample.model.BaseData
import com.lalitpawar.shaadicomexample.model.Result
import com.lalitpawar.shaadicomexample.network.ApiService
import com.lalitpawar.shaadicomexample.view.adapter.ProfileAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var apiService: ApiService

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadProfile() }
    lateinit  var profileAdapter : ProfileAdapter

    private lateinit var subscription: Disposable


    fun adapterInit(activity: AppCompatActivity){
        profileAdapter = ProfileAdapter(activity)
        loadProfile()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadProfile() {

        subscription = apiService.getMatchList("10")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { error -> onRetrievePostListError(error) }
            )

    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(baseData: BaseData) {
        var listItem: List<Result>? = baseData.results
        profileAdapter.updatePostList(listItem!!)
    }

    private fun onRetrievePostListError(throwable: Throwable) {
        Log.v("onRetrievePostListError", throwable.message)
        errorMessage.value = R.string.profile_error
    }



}