package com.enid.eijkplayer;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.enid.eijkplayer.activity.LiveActivity;
import com.enid.eijkplayer.activity.PermissionActivity;
import com.enid.eijkplayer.activity.VideoActivity;
import com.enid.eijkplayer.bean.Lives;
import com.enid.eijkplayer.http.HttpUtils;
import com.enid.eijkplayer.http.URLConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ProtocolFamily;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class MainActivity extends PermissionActivity {
    private List<Lives> mLivesList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PlayAdapter mPlayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mPlayAdapter = new PlayAdapter();
        mRecyclerView.setAdapter(mPlayAdapter);

        new GetLiveDataTask().execute(URLConstant.URL);
    }

    public void clickStart(View view) {
        requestRuntimePermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionListener() {
            @Override
            public void onGranted() {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }

            @Override
            public void onDenied(List<String> permissions) {

            }
        });

    }

    class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_live,null);
            final ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Lives  lives = mLivesList.get(position);
            Glide.with(MainActivity.this)
                    .load(lives.getCreator().getPortrait())
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .crossFade()
                    .into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LiveActivity.actionStart(MainActivity.this,lives.getStream_addr());
                }
            });
        }

        @Override
        public int getItemCount() {
            return mLivesList == null ? 0 : mLivesList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            public ViewHolder(View itemView) {
                super(itemView);
                this.imageView = (ImageView) itemView.findViewById(R.id.imageViewCover);
            }
        }
    }

    /**
     * 获取数据任务
     */
    class GetLiveDataTask extends AsyncTask<String,Void,List<Lives>>{

        private ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.show();
        }

        @Override
        protected List<Lives> doInBackground(String... strings) {
            String url = strings[0];
            List<Lives> livesList = new ArrayList<>();
            try {
                String jsonString = HttpUtils.getInstance().get(url);
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.optJSONArray("lives");
                JSONObject liveJsonObject = null;
                for (int i = 0; i< jsonArray.length(); i++) {
                    liveJsonObject = jsonArray.optJSONObject(i);
                    livesList.add(new Lives(liveJsonObject));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return livesList;
        }

        @Override
        protected void onPostExecute(List<Lives> lives) {
            super.onPostExecute(lives);
            mLivesList.addAll(lives);
            mPlayAdapter.notifyDataSetChanged();
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }
    }
}
