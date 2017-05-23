package adammistal.maddif.domain.interactors;


public interface UseCase<OBSERVER> {

    void subscribe(OBSERVER observer);

    void unsubscribe();

}
