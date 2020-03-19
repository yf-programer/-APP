package com.example.kk.util;

import java.io.File;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.kk.soundrecording.R;


public class RecordPlayer {

	private static MediaPlayer mediaPlayer;

	private Context mcontext;

	public RecordPlayer(Context context) {
		this.mcontext = context;
	}

	public void playRecordFile(File file) {
		if (file.exists() && file != null) {
			if (mediaPlayer == null) {
				Uri uri = Uri.fromFile(file);
				mediaPlayer = MediaPlayer.create(mcontext, uri);
			}
			mediaPlayer.start();


			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer paramMediaPlayer) {
					// TODO Auto-generated method stub
					//������ʾ
					Toast.makeText(mcontext,
							mcontext.getResources().getString(R.string.ok),
							Toast.LENGTH_SHORT).show();
				}
			});

		}
	}


	public void stopPalyer() {

		if (mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			mediaPlayer.seekTo(0);
			Log.e("TAG", "停止播放");
		}
	}
}
