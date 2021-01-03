package com.tmdbcodlab.android

interface BaseView<P: BasePresenter> {

    var presenter: P?

}