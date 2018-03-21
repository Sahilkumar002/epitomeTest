package devil.epitomecodetest.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import devil.epitomecodetest.R;
import devil.epitomecodetest.webServices.Pojos.Users;

/**
 * Created by devil on 3/20/18.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Users> list;
    private UserOnClick userOnClick;

    public UsersListAdapter(Context mContext, List<Users> list) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.list = list;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserHolder(mLayoutInflater.inflate(R.layout.row_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.imageView.setImageURI(Uri.parse("res:/" + R.drawable.user));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setUserOnClick(UserOnClick userOnClick) {
        this.userOnClick = userOnClick;
    }

    class UserHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView imageView;

        public UserHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(view -> {
                if (null != userOnClick) {
                    userOnClick.onClickUser(list.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface UserOnClick {
        void onClickUser(int userId);
    }

}
