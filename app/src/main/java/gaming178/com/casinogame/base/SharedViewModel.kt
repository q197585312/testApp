/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gaming178.com.casinogame.base

import androidx.lifecycle.ViewModel
import gaming178.com.baccaratgame.BuildConfig
import java.util.*

class SharedViewModel : ViewModel() {
    val flavor = UnPeekLiveData<String>()
    val isMainFlavor = UnPeekLiveData<Boolean>()

    init {
        flavor.value = BuildConfig.FLAVOR
        isMainFlavor.value = (flavor.value == "gd88" || flavor.value == "liga365")
    }

}