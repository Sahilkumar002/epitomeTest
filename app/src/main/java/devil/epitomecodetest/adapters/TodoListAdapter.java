package devil.epitomecodetest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import devil.epitomecodetest.R;
import devil.epitomecodetest.webServices.Pojos.PojoTodos;

/**
 * Created by devil on 3/21/18.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoAdapter> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<PojoTodos> list;

    public TodoListAdapter(Context context, List<PojoTodos> list) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public TodoAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoAdapter(mLayoutInflater.inflate(R.layout.row_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter holder, int position) {
        holder.tvTodos.setText(list.get(position).getTitle());
        holder.tvTodos.setBackgroundColor(ContextCompat.getColor(context,
                list.get(position).getCompleted() ? android.R.color.white : R.color.colorTextBack));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TodoAdapter extends RecyclerView.ViewHolder {

        TextView tvTodos;

        public TodoAdapter(View itemView) {
            super(itemView);
            tvTodos = itemView.findViewById(R.id.tvPost);
            tvTodos.setOnClickListener(view -> {

                list.get(getAdapterPosition()).setCompleted(!list.get(getAdapterPosition()).getCompleted());
                notifyDataSetChanged();
            });
        }
    }
}
