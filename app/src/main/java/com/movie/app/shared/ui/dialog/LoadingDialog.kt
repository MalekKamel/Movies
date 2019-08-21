package com.movie.app.shared.ui.dialog

import com.movie.app.R
import com.movie.app.shared.data.DataManager
import com.movie.app.shared.ui.frag.BaseDialogFrag
import com.movie.app.shared.util.ThreadUtil
import com.movie.app.shared.vm.BaseViewModel

class LoadingDialog(var isCancellable: Boolean = false) : BaseDialogFrag<LoadingVm>() {

     override var layoutId: Int = R.layout.frag_dialog_loading

    companion object {
        fun newInstance(isCancellable: Boolean = false): LoadingDialog {
            return LoadingDialog(isCancellable)
        }
    }


}

class LoadingVm(dataManager: DataManager) : BaseViewModel(dataManager)

object LoadingDialogHelper {
    var instances: MutableList<LoadingDialog?> = mutableListOf()

    fun add(dialog: LoadingDialog){
        ThreadUtil.runOnUiThread {
            instances.forEach {
                it?.dismiss()
            }
            instances.clear()
            instances.add(dialog)
        }
    }

    fun hide() {
        ThreadUtil.runOnUiThread {
            instances.forEach { it?.dismiss() }
        }
    }

}
