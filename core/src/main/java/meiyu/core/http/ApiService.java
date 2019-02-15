package meiyu.core.http;

import io.reactivex.Observable;
import meiyu.core.module.response.ArticleModel;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: ApiService
 * Date: 2018/11/27 17:01.
 */
public interface ApiService {

    /**
     * 首页文章列表
     */

    @GET("article/list/{page}/json")
    Observable<ArticleModel> getArticleList(@Path("page") int page);
}
