package devil.epitomecodetest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import devil.epitomecodetest.R;

/**
 * Created by devil on 3/21/18.
 */

public class PostDetailsFragment extends Fragment {

    TextView tvTitle, tvBody;
    ImageView ivClose;

    public static PostDetailsFragment newInstance(String title, String body) {
        PostDetailsFragment fragment = new PostDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("body", body);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvBody = view.findViewById(R.id.tvBody);
        ivClose = view.findViewById(R.id.ivClose);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            tvTitle.setText(getArguments().getString("title"));
            tvBody.setText(getArguments().getString("body"));
        }
        ivClose.setOnClickListener(view -> getFragmentManager().popBackStackImmediate());
    }
}
