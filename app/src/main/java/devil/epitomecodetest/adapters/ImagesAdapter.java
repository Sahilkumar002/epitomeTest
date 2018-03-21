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
import devil.epitomecodetest.webServices.Pojos.PojoPhoto;

/**
 * Created by devil on 3/21/18.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<PojoPhoto> list;

    public ImagesAdapter(Context mContext, List<PojoPhoto> list) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.list = list;
    }

    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageHolder(mLayoutInflater.inflate(R.layout.row_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.ivImage.setImageURI(Uri.parse(list.get(position).getUrl()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView ivImage;

        public ImageHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
