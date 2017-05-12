package com.example.bwei.todaynews.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bwei.todaynews.R;
import com.example.bwei.todaynews.bean.TuijianBean;
import com.example.bwei.todaynews.bean.TuijianBean1;

import org.xutils.x;

import java.util.List;



public class NewsListAdapter extends BaseAdapter{

	List<TuijianBean1.DataBean> newsList;
	//ImageLoader imageLoader = ImageLoader.getInstance();
	Context mcontext;

	public NewsListAdapter(Context mcontext, List<TuijianBean1.DataBean> newsList) {
		this.newsList = newsList;
		this.mcontext = mcontext;

	}

	


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList == null ? 0 : newsList.size();
	}

	@Override
	public TuijianBean1.DataBean getItem(int position) {
		if (newsList != null && newsList.size() != 0) {
			return newsList.get(position);
		}
		return null;
	}




	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mHolder;
		View view = convertView;
		if (view == null) {
			view = View.inflate(mcontext, R.layout.list_news_item, null);
			mHolder = new ViewHolder();
			mHolder.item_title = (TextView) view.findViewById(R.id.item_title);
			mHolder.item_lable = (TextView) view.findViewById(R.id.item_lable);
			mHolder.item_media_name = (TextView) view.findViewById(R.id.item_media_name);
			mHolder.item_comment_count = (TextView) view.findViewById(R.id.item_comment_count);
			mHolder.right_image = (ImageView) view.findViewById(R.id.item_middle_image);
			mHolder.item_image_0 = (ImageView) view.findViewById(R.id.item_image01);
			mHolder.item_image_1 = (ImageView) view.findViewById(R.id.item_image02);
			mHolder.item_image_2 = (ImageView) view.findViewById(R.id.item_image03);
			mHolder.item_image_layout =(LinearLayout) view.findViewById(R.id.item_image_layout);
			view.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) view.getTag();
		}
		//获取position对应的数据
		TuijianBean1.DataBean news = getItem(position);



		mHolder.item_title.setText(news.getTitle()+"");
		mHolder.item_lable.setText(news.getSource());
		mHolder.item_comment_count.setText("评论" + news.getComment_count());
		List<TuijianBean1.DataBean.ImageBean> imgUrlList = news.getImage_list();
		mHolder.item_comment_count.setVisibility(View.VISIBLE);

		if(imgUrlList !=null && imgUrlList.size() !=0){
			if(imgUrlList.size() == 3){
				mHolder.right_image.setVisibility(View.GONE);
				mHolder.item_image_layout.setVisibility(View.VISIBLE);
				final String tag1 = (String) mHolder.item_image_0.getTag(R.id.imageloader_uri);
				final String tag2 = (String) mHolder.item_image_1.getTag(R.id.imageloader_uri);
				final String tag3 = (String) mHolder.item_image_2.getTag(R.id.imageloader_uri);

				loadImage(imgUrlList.get(0).getUrl(),mHolder.item_image_0,tag1);
				loadImage(imgUrlList.get(1).getUrl(),mHolder.item_image_1,tag2);
				loadImage(imgUrlList.get(2).getUrl(),mHolder.item_image_2,tag3);

			}
		}else if (newsList.get(position).getMiddle_image()!=null){
			mHolder.right_image.setVisibility(View.VISIBLE);
			mHolder.item_image_layout.setVisibility(View.GONE);
			final String tag = (String) mHolder.right_image.getTag(R.id.imageloader_uri);

			loadImage(newsList.get(position).getMiddle_image().getUrl(),mHolder.right_image,tag);

		}
		return view;
	}


	private void loadImage(String path,ImageView imageView,String tag){
//		if(!path.equals(tag)){
			imageView.setTag(R.id.imageloader_uri,path);
			Glide.with(mcontext).load(path).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(imageView);

//		}
		


	}

	static class ViewHolder {
		//title
		TextView item_title;
		//新闻源
		TextView item_media_name;
		//类似推广之类的标签
		TextView item_lable;
		//评论数量
		TextView item_comment_count;
		//右边图片
		ImageView right_image;
		//3张图片布局
		LinearLayout item_image_layout; //3张图片时候的布局
		ImageView item_image_0;
		ImageView item_image_1;
		ImageView item_image_2;
		//大图的图片的话布局
		ImageView large_image;
		//评论布局
		RelativeLayout comment_layout;
		TextView comment_content;
	}
}
