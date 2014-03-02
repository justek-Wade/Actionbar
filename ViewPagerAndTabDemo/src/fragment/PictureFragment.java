package fragment;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.viewpagerandtabdemo.R;

public class PictureFragment extends SherlockFragment {

	private Button picturebtn;
	private ImageView image;
	private ProgressDialog progressDialog;
	private String picUrl = "http://c.hiphotos.baidu.com/image/w%3D2048/sign=f236ae37347adab43dd01c43bfecb21c/503d269759ee3d6d5fd49ad441166d224f4ade9a.jpg";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contextView = inflater.inflate(R.layout.picturefragment,
				container, false);
		picturebtn = (Button) contextView.findViewById(R.id.picturebtn);
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setTitle("提示");
		progressDialog.setMessage("正在下载");
		picturebtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new DownloadPicture().execute(picUrl);
			}
		});
		image = (ImageView) contextView.findViewById(R.id.image);
		image.setImageResource(R.drawable.shendun);
		return contextView;
	}

	class DownloadPicture extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected void onPreExecute() {
			progressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(params[0]);
			Bitmap bitmap = null;
			try {
				HttpResponse httpResponse = httpClient.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					HttpEntity httpEntity = httpResponse.getEntity();
					byte[] data = EntityUtils.toByteArray(httpEntity);
					bitmap = BitmapFactory
							.decodeByteArray(data, 0, data.length);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			image.setImageBitmap(result);
		}
	}

}
