package adammistal.maddif.presentation.home;
//region IMPORTS

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

import javax.inject.Inject;

import adammistal.maddif.MyApp;
import adammistal.maddif.R;
import adammistal.maddif.databinding.HomeRootBinding;
//endregion

public class Home extends Fragment implements HomeContract.View {
    //region VARIABLES
    @Inject
    HomeContract.Presenter presenter;
    private HomeRootBinding binding;
    private HomeRecyclerAdapter adapter;
    //endregion
    //region FACTORY METHOD
    public static Fragment create() {
        return new Home();
    }
    //endregion

    //region CREATE FRAGMENT
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getActivity().getApplication()).getPresenterComponent().inject(this);
    }
    //endregion
    //region CREATE VIEW
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //region BINDING
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        presenter.bindView(this);
        //endregion
        //region REFRESHER
        binding.refresher.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    presenter.refresh();
                }
            }
        });
        //endregion
        return binding.getRoot();
    }
    //endregion

    @Override
    public void onDestroyView() {
        //region UNBIND
        presenter.unbindView();
        binding = null;
        //endregion
        //region CLEAR ADAPTER
        if (adapter != null) {
            adapter.clear();
            adapter = null;
        }
        //endregion
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        //region RELEASE
        presenter.release();
        presenter = null;
        //endregion
        super.onDetach();
    }

    //region RECYCLER ADAPTER UPDATE
    @Override
    public void setAdapterData(List<GifViewModel> items) {
        if (adapter == null) {
            adapter = new HomeRecyclerAdapter(presenter, items);
            binding.recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            binding.recycler.setAdapter(adapter);
        }
    }

    @Override
    public void refreshAdapter() {
        adapter.notifyDataSetChanged();
    }
    //endregion

    //region PRELODER
    @Override
    public void showLargePreloader() {
        binding.preloader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePreloader() {
        if (binding.preloader.getVisibility() != View.GONE) {
            binding.preloader.setVisibility(View.GONE);
        }
        binding.refresher.setRefreshing(false);
    }

    @Override
    public void showError(String error) {

    }
    //endregion
}