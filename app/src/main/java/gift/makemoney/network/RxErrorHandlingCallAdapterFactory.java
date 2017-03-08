package gift.makemoney.network;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 错误处理的拦截器
 */
public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJavaCallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory() {
        original = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    }
    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }
    @Override
    public CallAdapter<?,?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper<R> implements CallAdapter<R,Observable<?>> {
        private final Retrofit retrofit;
        private final CallAdapter<R,Observable<?>> wrapped;

        public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<R,Observable<?>> wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public  Observable<?> adapt(Call<R> call) {
            return ((Observable) wrapped.adapt(call)).onErrorResumeNext(new Func1<Throwable, Observable>() {
                @Override
                public Observable call(Throwable throwable) {
                    Log.e("RxErrorHandling", throwable.getMessage());

                    if (throwable instanceof ApiError) {
                        return Observable.error(throwable);
                    }
                    return Observable.error(asRetrofitException(throwable));
                }
            });
        }

        private RetrofitException asRetrofitException(Throwable throwable) {
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit);
            }
            if (throwable instanceof IOException) {
                return RetrofitException.networkError((IOException) throwable);
            }
            Log.e("RxErrorHandling", throwable.getMessage());
            return RetrofitException.unexpectedError(throwable);
        }
    }
}