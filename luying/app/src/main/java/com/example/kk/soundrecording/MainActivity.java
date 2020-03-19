package com.example.kk.soundrecording;

import java.io.File;
import java.io.IOException;
import com.example.kk.util.RecordPlayer;
import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {

	private Button start;

	private Button stop;

	private Button paly;


	private MediaRecorder mediaRecorder;

	private File recordFile;

	private RecordPlayer player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recordFile = new File("/mnt/sdcard", "yf.mp3");

		initView();
		Listener();
	}

	private void initView() {
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
		paly = (Button) findViewById(R.id.paly);

	}

	private void Listener() {
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		paly.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		player = new RecordPlayer(MainActivity.this);
		int Id = v.getId();

		switch (Id) {
			case R.id.start:
				startRecording();
				break;
			case R.id.stop:
				stopRecording();
				break;
			case R.id.paly:
				playRecording();
				break;
		}
	}


	private void startRecording() {
		mediaRecorder = new MediaRecorder();

		if (recordFile.exists()) {
			recordFile.delete();
		}
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		mediaRecorder.setOutputFile(recordFile.getAbsolutePath());

		try {

			mediaRecorder.prepare();

			mediaRecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void stopRecording() {
		if (recordFile != null) {
			mediaRecorder.stop();
			mediaRecorder.release();
		}
	}

	private void playRecording() {
		player.playRecordFile(recordFile);
	}
}
	

