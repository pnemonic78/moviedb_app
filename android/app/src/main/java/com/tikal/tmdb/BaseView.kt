package com.tikal.tmdb

interface BaseView<P : BasePresenter> {

    var presenter: P

}