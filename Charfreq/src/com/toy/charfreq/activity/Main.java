package com.toy.charfreq.activity;

import com.toy.charfreq.R;
import com.toy.charfreq.ui.CFListAdapter;
import com.toy.charfreq.utility.CFFrequencyCounter;
import com.toy.charfreq.utility.CFHttp;
import com.toy.charfreq.utility.CFHttp.CFHttpDelegate;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class Main extends Activity implements CFHttpDelegate {
	
	private ListView mListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Find our inflated UI control
        mListLayout = (ListView)findViewById(R.id.listLayoutId);
        
        // Make an HTTP request for content
        CFHttp httpRequest = new CFHttp(this);
        httpRequest.execute("http://www.classicshorts.com/stories/tec.html");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void downloadComplete(byte[] downloaded) {
		
		// Count character frequencies and update the UI
		CFFrequencyCounter freqCounter = new CFFrequencyCounter(new String(downloaded));
		CFListAdapter gridAdapter = new CFListAdapter(this, freqCounter.generateFrequencyMap());
		mListLayout.setAdapter(gridAdapter);
	}


	@Override
	public void downloadFailed(String errorDescription) {
		
		// Tell the user there was a critical mission failure
		Toast.makeText(this, errorDescription, Toast.LENGTH_LONG).show();
	}
    
}
