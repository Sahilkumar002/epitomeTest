package devil.epitomecodetest;

import android.app.ProgressDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import devil.epitomecodetest.adapters.AlbumListAdapter;
import devil.epitomecodetest.adapters.PostListAdapter;
import devil.epitomecodetest.adapters.UsersListAdapter;
import devil.epitomecodetest.webServices.Pojos.Albums;
import devil.epitomecodetest.webServices.Pojos.Post;
import devil.epitomecodetest.webServices.Pojos.Users;
import devil.epitomecodetest.webServices.api.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UsersListAdapter.UserOnClick {

    RecyclerView rvUsers, rvPosts, rvAlbums;
    private UsersListAdapter mUserAdapter;
    LinearLayout llUserData;
    TextView tvSelectUser;
    private List<Users> usersList = new ArrayList<>();
    private PostListAdapter mPostListAdapter;
    private List<Post> postList = new ArrayList<>();
    private AlbumListAdapter mAlbumAdapter;
    private List<Albums> albumsList = new ArrayList<>();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvUsers = findViewById(R.id.rvUsers);
        rvPosts = findViewById(R.id.rvPosts);
        rvAlbums = findViewById(R.id.rvAlbums);
        llUserData = findViewById(R.id.llUserData);
        tvSelectUser = findViewById(R.id.tvSelectUser);
        rvUsers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mUserAdapter = new UsersListAdapter(this, usersList);
        mUserAdapter.setUserOnClick(this);
        rvUsers.setAdapter(mUserAdapter);

//        Albums
        rvAlbums.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAlbumAdapter = new AlbumListAdapter(this, albumsList);
        rvAlbums.setAdapter(mAlbumAdapter);


/* Item Decoration*/
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        rvPosts.addItemDecoration(itemDecorator);
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        mPostListAdapter = new PostListAdapter(this, postList);
        rvPosts.setAdapter(mPostListAdapter);

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.setTitle("Loading");
        getUsersList();
    }

    private void getUsersList() {
        dialog.show();
        RestClient.getClient().getUsersList().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                dialog.dismiss();
                try {
                    if (response.code() == 200 && response.body() != null) {
                        usersList.clear();
                        usersList.addAll(response.body());
                        mUserAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "API ERROR", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onClickUser(int userId) {
        dialog.show();
        RestClient.getClient().getPosts(userId).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                dialog.dismiss();
                try {
                    if (response.code() == 200 && response.body() != null) {
                        tvSelectUser.setVisibility(View.GONE);
                        llUserData.setVisibility(View.VISIBLE);
                        postList.clear();
                        postList.addAll(response.body());
                        mPostListAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "API ERROR", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RestClient.getClient().getAlbums(userId).enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                dialog.dismiss();
                try {
                    if (response.code() == 200 && response.body() != null) {
                        tvSelectUser.setVisibility(View.GONE);
                        llUserData.setVisibility(View.VISIBLE);
                        albumsList.clear();
                        albumsList.addAll(response.body());
                        mAlbumAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "API ERROR", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
