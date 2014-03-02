package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.viewpagerandtabdemo.R;

public class PictureFragment extends SherlockFragment{

	private Button picturebtn;
	private ImageView image;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contextView = inflater.inflate(R.layout.picturefragment, container, false);
		picturebtn=(Button) contextView.findViewById(R.id.picturebtn);
	
		image=(ImageView) contextView.findViewById(R.id.image);
		image.setImageResource(R.drawable.shendun);
		return contextView;
	}
}
