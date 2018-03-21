package devil.epitomecodetest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import devil.epitomecodetest.R;
import devil.epitomecodetest.webServices.Pojos.Albums;

/**
 * Created by devil on 3/21/18.
 */

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Albums> list;

    public AlbumListAdapter(Context mContext, List<Albums> list) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.list = list;
    }

    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumHolder(mLayoutInflater.inflate(R.layout.row_album, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class AlbumHolder extends RecyclerView.ViewHolder {

        public AlbumHolder(View itemView) {
            super(itemView);
        }
    }
}
