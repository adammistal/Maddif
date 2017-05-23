package adammistal.maddif.domain.interactors;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;


public abstract class BaseUseCase<Result> implements UseCase<DisposableObserver<Result>> {
    //region VARIABLE

    private final CompositeDisposable disposable = new CompositeDisposable();
    private Scheduler workScheduler;
    private Scheduler mainSchduler;
    //endregion

    //region CONSTRUCTOR
    public BaseUseCase(Scheduler workScheduler, Scheduler mainSchduler) {
        this.mainSchduler = mainSchduler;
        this.workScheduler = workScheduler;
    }
    //endregion
    //region SUBSCRIBE
    @Override
    public void subscribe(DisposableObserver<Result> observer) {

        //endregion
        disposable.add(createObservable()
                .subscribeOn(workScheduler)
                .observeOn(mainSchduler)
                .subscribeWith(observer));

    }

    //endregion
    //region ABSTARCT METHODS
    protected abstract Observable<Result> createObservable();
    //endregion

    //region UNSUBSCRIBE
    @Override
    public void unsubscribe() {
        if (!disposable.isDisposed()) {
            disposable.clear();
        }
    }
    //endregion
    public Observable<Result> getObservable() {
        return createObservable();
    }


}
