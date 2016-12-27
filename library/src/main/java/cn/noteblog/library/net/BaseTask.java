package cn.noteblog.library.net;

//public class BaseTask<T> {
//
//    private final String TAG = "response";
//    private final int SUCCESS = 0;
//
//    private Context mContext;
//    private Observable<BaseEntity<T>> mObservable;
//
//    public BaseTask(Context context, Observable<BaseEntity<T>> observable) {
//        mObservable = observable;
//        mContext = context;
//    }
//
//    public void handleResponse(final ResponseListener listener) {
//        // 1. 判断网络是否连接
//
//        // 2. 显示加载数据的progress dialog
//
//        // 3. 联网请求数据
//        mObservable
//                .subscribeOn(Schedulers.io()) // 分线程执行请求
//                .unsubscribeOn(Schedulers.io()) // 分线程执行请求
//                .observeOn(AndroidSchedulers.mainThread()) // 主线程处理界面
//                .subscribe(new Subscriber<BaseEntity<T>>() {
//
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(mContext, "网络请求出现异常！", Toast.LENGTH_LONG).show();
//                        listener.onFail();
//                    }
//
//                    @Override
//                    public void onNext(BaseEntity<T> baseEntity) {
//                        if (baseEntity != null && baseEntity.getCode() == SUCCESS) {
//                            listener.onSuccess(baseEntity.getData());
//                        } else {
//                            Toast.makeText(mContext, baseEntity.getMessage(), Toast.LENGTH_LONG).show();
//                            listener.onFail();
//                        }
//                    }
//                });
//    }
//
//    public interface ResponseListener<E> {
//        void onSuccess(E t);
//
//        void onFail();
//    }
//}
