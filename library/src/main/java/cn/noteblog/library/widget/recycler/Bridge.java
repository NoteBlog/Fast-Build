package cn.noteblog.library.widget.recycler;

/**
 * Author: FynnHan(18330080926@163.com)
 * Date: 2016/11/14
 * Time: 10:47
 */
interface Bridge {

    void doSomething(RefreshLoadLayout host);

    class Loading implements Bridge {

        @Override
        public void doSomething(RefreshLoadLayout host) {
            host.displayLoadingAndResetStatus();
        }
    }

    class Empty implements Bridge {

        @Override
        public void doSomething(RefreshLoadLayout host) {
            host.displayEmptyAndResetStatus();
        }
    }

    class Content implements Bridge {

        @Override
        public void doSomething(RefreshLoadLayout host) {
            host.displayContentAndResetStatus();
        }
    }

    class Error implements Bridge {

        @Override
        public void doSomething(RefreshLoadLayout host) {
            host.displayErrorAndResetStatus();
        }
    }

    class NoMore implements Bridge {

        @Override
        public void doSomething(RefreshLoadLayout host) {
            host.showNoMoreIfEnabled();
        }
    }

    class LoadMoreFailed implements Bridge {

        @Override
        public void doSomething(RefreshLoadLayout host) {
            host.showLoadMoreFailedIfEnabled();
        }
    }

    class ResumeLoadMore implements Bridge {

        @Override
        public void doSomething(RefreshLoadLayout host) {
            host.resumeLoadMoreIfEnabled();
        }
    }

    class AutoLoadMore implements Bridge {

        @Override
        public void doSomething(RefreshLoadLayout host) {
            host.autoLoadMoreIfEnabled();
        }
    }

    class ManualLoadMore implements Bridge {

        @Override
        public void doSomething(RefreshLoadLayout host) {
            host.manualLoadMoreIfEnabled();
        }
    }

    class SwipeConflicts implements Bridge {

        private boolean enabled;

        SwipeConflicts(boolean enabled) {
            this.enabled = enabled;
        }

        @Override
        public void doSomething(RefreshLoadLayout host) {
            host.resolveSwipeConflicts(enabled);
        }
    }
}
