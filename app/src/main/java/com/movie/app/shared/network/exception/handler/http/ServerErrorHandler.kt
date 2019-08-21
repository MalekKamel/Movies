package com.movie.app.shared.network.exception.handler.http

import com.movie.app.R

class ServerErrorHandler : HttpExceptionHandler() {

    override fun supportedExceptions(): List<Int> {
        return listOf(500)
    }

    override fun handle() {
        presenter.showMessageInFlashBar(R.string.oops_something_went_wrong)
    }

}