package com.lalitpawar.shaadicomexample.view_model

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.lalitpawar.shaadicomexample.base.BaseViewModel
import com.lalitpawar.shaadicomexample.model.ProfilePref
import com.lalitpawar.shaadicomexample.model.Result
import com.lalitpawar.shaadicomexample.model.dao.ProfileDao
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class ProfileItemViewModel (private val profileDao: ProfileDao): BaseViewModel() {


    val Tag = ProfileItemViewModel::class.java.simpleName

    private val name = MutableLiveData<String>()
    private var cover_image = MutableLiveData<String>()
    private var address = MutableLiveData<String>()
    private var id = MutableLiveData<String>()
    private var profile_state = MutableLiveData<String>()
    private var message = MutableLiveData<String>()
    private var actionState = MutableLiveData<Int>()

    private lateinit var subscription: CompositeDisposable

    @SuppressLint("CheckResult")
    fun bind(results: Result) {

        id.value = results.id.name
        name.value = fullName(results)
        cover_image.value = results.picture.large
        address.value = fullAddress(results)
        actionState.value = View.VISIBLE


    }

    fun getName(): MutableLiveData<String> {
        return name
    }


    fun getCoverImage(): MutableLiveData<String> {
        return cover_image
    }

    fun getAddress(): MutableLiveData<String> {
        return address
    }

    fun fullName(results: Result): String {
        return "${results.name.title}. ${results.name.first} ${results.name.last}, ${results.dob.age}"
    }

    fun fullAddress(results: Result): String {

        return "${results.location.city}, ${results.location.state}-${results.location.country}"
    }

    fun getActionState(): MutableLiveData<Int> {
        return actionState
    }

    fun getMessage(): MutableLiveData<String> {
        return message
    }


    @SuppressLint("CheckResult")
    fun profileStatePref(flag: Boolean) {
        var profilePref = ProfilePref(id.value!!, flag)

        Completable.fromAction { profileDao.insertProfilePref(profilePref) }
            .observeOn(mainThread())
            .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {
                    profile_state.value = if (flag) "Accept" else "decline"

                    message.value = "member ${profile_state.value}"

                    actionState.value = View.GONE
                }

                override fun onError(e: Throwable) {
                    Log.e(Tag, "Unable to update username", e)
                }
            })

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}