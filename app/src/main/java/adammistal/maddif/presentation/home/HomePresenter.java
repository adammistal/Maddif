package adammistal.maddif.presentation.home;
//region IMPORTS

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import adammistal.maddif.domain.interactors.GetGifsInteractor;
import io.reactivex.observers.DisposableObserver;

//endregion

public class HomePresenter implements HomeContract.Presenter {
    //region VARIABLES
    private static final String TAG = "Home";
    private final List<GifViewModel> items;
    private HomeContract.View view;
    private boolean isLoading;
    private GetGifsInteractor interactor;
    //endregion
    //region LIFECYCLE METHODS

    //region CONSTRUCTOR
    @Inject
    public HomePresenter( GetGifsInteractor interactor) {
        items = new ArrayList<>();
        this.interactor = interactor;

    }
    //endregion

    //region BIND
    @Override
    public void bindView(HomeContract.View view) {
        this.view = view;
        this.view.setAdapterData(items);
        if (items.size() == 0) {
            loadData();
        }
    }
    //endregion

    //region UNBIND
    @Override
    public void unbindView() {
        interactor.unsubscribe();
        view = null;
        isLoading = false;

    }

    //endregion
    //region RELEASE
    @Override
    public void release() {
        items.clear();
        interactor = null;
    }
    //endregion
    //endregion

    //region REFRESH

    @Override
    public void refresh() {
        loadData();
    }

    private void loadData() {
        Log.d(TAG, "loadData:" + isLoading);
        if (!isLoading) {
            if (items.size() == 0) {
                this.view.showLargePreloader();
            }
            isLoading = true;
            interactor.subscribe(new DisposableObserver<List<String>>() {
                @Override
                public void onNext(List<String> strings) {
                    items.clear();
                    for(String item : strings){
                        GifViewModel m = new GifViewModel();
                        m.setUrl(item);
                        items.add(m);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    error(e);
                }

                @Override
                public void onComplete() {
                    onDataLoaded();
                }
            });
        }
    }
    //endregion

    private void error(Throwable e) {
        Log.e("Home", "onError:" + e);
        view.hidePreloader();
        isLoading = false;
        if (e != null) {
            view.showError("" + e);
        }
    }

    private void onDataLoaded() {
        Log.d("Home", "onDataLoaded:");
        isLoading = false;
        view.hidePreloader();
        view.refreshAdapter();
    }

}