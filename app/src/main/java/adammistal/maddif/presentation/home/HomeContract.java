package adammistal.maddif.presentation.home;

import java.util.List;

public interface HomeContract {

    interface View {

        void setAdapterData(List<GifViewModel> items);

        void refreshAdapter();

        void showLargePreloader();

        void hidePreloader();


        void showError(String error);
    }

    interface Presenter {


        void refresh();


        void bindView(View view);

        void unbindView();

        void release();

    }
}