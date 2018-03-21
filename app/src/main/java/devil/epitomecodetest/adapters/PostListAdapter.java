package devil.epitomecodetest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import devil.epitomecodetest.R;
import devil.epitomecodetest.webServices.Pojos.Post;

/**
 * Created by devil on 3/20/18.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Post> list;

    public PostListAdapter(Context mContext, List<Post> list) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.list = list;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostHolder(mLayoutInflater.inflate(R.layout.row_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.tvPost.setText(list.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return list.size() > 3 ? 3 : list.size();
    }

    class PostHolder extends RecyclerView.ViewHolder {
        TextView tvPost;

        public PostHolder(View itemView) {
            super(itemView);
            tvPost = itemView.findViewById(R.id.tvPost);
        }
    }
}
