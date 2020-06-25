package gaming178.com.casinogame.base

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

interface IViewModelStoreOwner {
    fun getViewModelStore(): ViewModelStore
    fun getViewModelFactory(): ViewModelProvider.Factory
}