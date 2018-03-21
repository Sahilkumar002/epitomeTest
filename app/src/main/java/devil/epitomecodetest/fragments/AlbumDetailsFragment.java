package devil.epitomecodetest.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import devil.epitomecodetest.R;
import devil.epitomecodetest.adapters.ImagesAdapter;
import devil.epitomecodetest.utils.GeneralFunctions;
import devil.epitomecodetest.webServices.Pojos.PojoPhoto;
import devil.epitomecodetest.webServices.api.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by devil on 3/21/18.
 */

public class AlbumDetailsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressDialog mDialog;
    private List<PojoPhoto> list = new ArrayList<>();
    ImagesAdapter adapter;

    public static AlbumDetailsFragment newInstance(Integer albumId) {
        AlbumDetailsFragment fragment = new AlbumDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("albumId", albumId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvImages);
        view.findViewById(R.id.ivClose).setOnClickListener(view1 -> getFragmentManager().popBackStackImmediate());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDialog = GeneralFunctions.progressDialog(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImagesAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        if (getArguments() != null) {
            loadAlbumPhotos(getArguments().getInt("albumId"));
        }
    }

    private void loadAlbumPhotos(int albumId) {
        mDialog.show();
        RestClient.getClient().getPhotos(albumId).enqueue(new Callback<List<PojoPhoto>>() {
            @Override
            public void onResponse(Call<List<PojoPhoto>> call, Response<List<PojoPhoto>> response) {
                mDialog.dismiss();
                try {
                    if (response.code() == 200 && response.body() != null) {
                        list.clear();
                        list.addAll(response.body());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "API ERROR", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<PojoPhoto>> call, Throwable t) {
                mDialog.dismiss();
                Toast.makeText(getActivity(), "API ERROR", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
