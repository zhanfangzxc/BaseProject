package gift.makemoney.network;

import java.util.List;

import gift.makemoney.model.Unsplash;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("/photos") Observable<List<Unsplash>> getUnsplash(@Query("page")int page,
                                                           @Query("per_page")int per_page,
                                                           @Query("order_by")String order_by,
                                                           @Query("client_id")String client_id);
}