package gaming178.com.casinogame.base

import androidx.lifecycle.LiveData
import gaming178.com.casinogame.Bean.Liga365AgentBean
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    /**
     * 玩安卓轮播图
     */
    @GET
    suspend fun getData(@Url url: String): String

    @GET
    suspend fun getSiteMap(@Url url: String): Liga365AgentBean
}