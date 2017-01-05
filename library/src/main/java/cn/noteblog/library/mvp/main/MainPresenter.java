package cn.noteblog.library.mvp.main;

/**
 * 操作业务层逻辑，减少与view和model的耦合度
 * -
 * 注意：如果需要对改变的界面显示，则直接调用View层的方法即可
 */
//class MainPresenter implements Contract.Presenter {
//
//    private final Contract.View mView;
//
//    MainPresenter(Contract.View view) {
//        this.mView = view;
//
//        mView.setPresenter(this);
//    }
//
//    @Override
//    public void start() {
//        // 处理数据加载与展示，注意数据获取的方式在model层实现
//        // model.newTask();
//    }
//
//    @Override
//    public void loadPosts(long date, boolean clearing) {
//        mView.showLoading();
//
////        model.load(new Model.GetDataCallback() {
////            @Override
////            public void onSuccess(ArrayList<String> result) {
////                mView.showResults(result);
////                mView.stopLoading();
////            }
////
////            @Override
////            public void onError(String errorCode) {
////                mView.stopLoading();
////                mView.showError();
////            }
////        });
//    }
//
//    @Override
//    public void refresh() {
//
//    }
//
//    @Override
//    public void loadMore() {
//        // model.load(date + 1, this);
//    }
//
//    @Override
//    public void startReading(int position) {
//
//    }
//
//    @Override
//    public void goToSettings() {
//
//    }
//}
