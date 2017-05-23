package adammistal.maddif.presentation.home;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.koushikdutta.ion.Ion;

import java.util.List;

import adammistal.maddif.R;
import adammistal.maddif.databinding.GifRowBinding;

class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.EntryHolder> {
    //region VARIABLES
    private List<GifViewModel> items;
    private HomeContract.Presenter presenter;
    //endregion

    //region CONSTRUCTOR
    HomeRecyclerAdapter(HomeContract.Presenter presenter, List<GifViewModel> items) {
        this.presenter = presenter;
        this.items = items;
    }

    //endregion
    //region CREATE HOLDER
    @Override
    public EntryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GifRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_gif, parent, false);
        return new EntryHolder(binding);
    }

    //endregion
    //region BINDING
    @Override
    public void onBindViewHolder(EntryHolder holder, int position) {
        holder.binding.setPresenter(presenter);
        holder.binding.setModel(items.get(position));
        Ion.with(holder.binding.image).load(items.get(position).getUrl());
        holder.binding.executePendingBindings();
    }

    //endregion
    //region ITEMS SIZE
    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        return 0;
    }

    //endregion
    //region RELEASE
    public void clear() {
        presenter = null;
        items = null;
        notifyDataSetChanged();
    }

    //endregion
    //region VIEW HOLDER
    static class EntryHolder extends RecyclerView.ViewHolder {
        private GifRowBinding binding;

        EntryHolder(GifRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    //endregion
}