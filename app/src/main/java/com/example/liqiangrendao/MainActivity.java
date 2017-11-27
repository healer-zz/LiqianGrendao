package com.example.liqiangrendao;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private Button start;
    private Button pause;
    private Button bf;

    private TextView total;
    private int max;
    private DownloadUtil mDownloadUtil;
    private VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total = (TextView) findViewById(R.id.textView);
        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.delete);
        bf = (Button) findViewById(R.id.bf);
        vv = (VideoView) findViewById(R.id.vv);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
         String urlString = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";
         String localPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/local2";
        mDownloadUtil = new DownloadUtil(2, localPath, "adc.mp4", urlString,
                this);
        mDownloadUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {

            @Override
            public void downloadStart(int fileSize) {
                Log.w(TAG, "fileSize::" + fileSize);
                max = fileSize;
                mProgressBar.setMax(fileSize);
            }

            @Override
            public void downloadProgress(int downloadedSize) {
                // TODO Auto-generated method stub
                Log.w(TAG, "Compelete::" + downloadedSize);
                mProgressBar.setProgress(downloadedSize);
                total.setText((int) downloadedSize * 100 / max + "%");
            }

            @Override
            public void downloadEnd() {
              vv.setVideoPath(Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/local2/adc.mp4");
                vv.start();

            }
        });
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                mDownloadUtil.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                mDownloadUtil.pause();
            }
        });
    }
}
