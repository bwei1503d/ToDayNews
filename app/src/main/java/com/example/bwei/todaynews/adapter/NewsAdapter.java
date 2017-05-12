package com.example.bwei.todaynews.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import com.example.bwei.todaynews.R;
import com.example.bwei.todaynews.bean.TuijianBean;
import com.example.bwei.todaynews.bean.TuijianBean1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class NewsAdapter extends BaseAdapter{
	List<TuijianBean1.DataBean> newsList;
	Activity activity;
	LayoutInflater inflater = null;
	/** 弹出的更多选择框  */
	private PopupWindow popupWindow;
	public NewsAdapter(Activity activity, List<TuijianBean1.DataBean> newsList) {
		this.activity = activity;
		this.newsList = newsList;
		inflater = LayoutInflater.from(activity);
		initPopWindow();
		initDateHead();
	}
	
	private List<Integer> mPositions;
	private List<String> mSections;
	
	private void initDateHead() {
		mSections = new ArrayList<String>();
		mPositions= new ArrayList<Integer>();
		for(int i = 0; i <newsList.size();i++){
			if(i == 0){
//				mSections.add(DateTools.getSection(String.valueOf(newsList.get(i).getPublishTime())));
				mPositions.add(i);
				continue;
			}
			if(i != newsList.size()){
//				if(!newsList.get(i).getPublishTime().equals(newsList.get(i - 1).getPublishTime())){
//					mSections.add(DateTools.getSection(String.valueOf(newsList.get(i).getPublishTime())));
//					mPositions.add(i);
//				}
			}
		}
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
			view = inflater.inflate(R.layout.list_item, null);
			mHolder = new ViewHolder();
//			mHolder.item_layout = (LinearLayout)view.findViewById(R.id.item_layout);
//			mHolder.comment_layout = (RelativeLayout)view.findViewById(R.id.comment_layout);
//			mHolder.item_title = (TextView)view.findViewById(R.id.item_title);
//			mHolder.item_source = (TextView)view.findViewById(R.id.item_source);
//			mHolder.list_item_local = (TextView)view.findViewById(R.id.list_item_local);
//			mHolder.comment_count = (TextView)view.findViewById(R.id.comment_count);
//			mHolder.publish_time = (TextView)view.findViewById(R.id.publish_time);
//			mHolder.item_abstract = (TextView)view.findViewById(R.id.item_abstract);
//			mHolder.alt_mark = (ImageView)view.findViewById(R.id.alt_mark);
//			mHolder.right_image = (ImageView)view.findViewById(R.id.right_image);
//			mHolder.item_image_layout = (LinearLayout)view.findViewById(R.id.item_image_layout);
//			mHolder.item_image_0 = (ImageView)view.findViewById(R.id.item_image_0);
//			mHolder.item_image_1 = (ImageView)view.findViewById(R.id.item_image_1);
//			mHolder.item_image_2 = (ImageView)view.findViewById(R.id.item_image_2);
//			mHolder.large_image = (ImageView)view.findViewById(R.id.large_image);
//			mHolder.popicon = (ImageView)view.findViewById(R.id.popicon);
//			mHolder.comment_content = (TextView)view.findViewById(R.id.comment_content);
//			mHolder.right_padding_view = (View)view.findViewById(R.id.right_padding_view);

			
			view.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) view.getTag();
		}
		//获取position对应的数据
//		TuijianBean.DataBean news = getItem(position);
//		mHolder.item_title.setText(news.getTitle()+"");
//		mHolder.item_source.setText(news.getSource());
//		mHolder.comment_count.setText("评论" + news.getComment_count());
//		mHolder.publish_time.setText(news.getPublish_time() + "小时前");
//		List<String> imgUrlList = (List<String>) news.getImage_list();
//		mHolder.popicon.setVisibility(View.VISIBLE);
//		mHolder.comment_count.setVisibility(View.VISIBLE);
//		mHolder.right_padding_view.setVisibility(View.VISIBLE);
//		if(imgUrlList !=null && imgUrlList.size() !=0){
//			if(imgUrlList.size() == 1){
//				mHolder.item_image_layout.setVisibility(View.GONE);
//				//是否是大图
//				if(news.getLarge_image_list().size() > 0){
//					mHolder.large_image.setVisibility(View.VISIBLE);
//					mHolder.right_image.setVisibility(View.GONE);
////					imageLoader.displayImage(imgUrlList.get(0), mHolder.large_image, options);
//					mHolder.popicon.setVisibility(View.GONE);
//					mHolder.comment_count.setVisibility(View.GONE);
//					mHolder.right_padding_view.setVisibility(View.GONE);
//				}else{
//					mHolder.large_image.setVisibility(View.GONE);
//					mHolder.right_image.setVisibility(View.VISIBLE);
////					imageLoader.displayImage(imgUrlList.get(0), mHolder.right_image, options);
//				}
//			}else{
//				mHolder.large_image.setVisibility(View.GONE);
//				mHolder.right_image.setVisibility(View.GONE);
//				mHolder.item_image_layout.setVisibility(View.VISIBLE);
////				imageLoader.displayImage(imgUrlList.get(0), mHolder.item_image_0, options);
////				imageLoader.displayImage(imgUrlList.get(1), mHolder.item_image_1, options);
////				imageLoader.displayImage(imgUrlList.get(2), mHolder.item_image_2, options);
//			}
//		}else{
//			mHolder.right_image.setVisibility(View.GONE);
//			mHolder.item_image_layout.setVisibility(View.GONE);
//		}
//		int markResID = getAltMarkResID(news.getMark(),news.getCollectStatus());
//		if(markResID != -1){
//			mHolder.alt_mark.setVisibility(View.VISIBLE);
//			mHolder.alt_mark.setImageResource(markResID);
//		}else{
//			mHolder.alt_mark.setVisibility(View.GONE);
//		}
//		//判断该新闻概述是否为空
//		if (!TextUtils.isEmpty(news.getNewsAbstract())) {
//			mHolder.item_abstract.setVisibility(View.VISIBLE);
//			mHolder.item_abstract.setText(news.getNewsAbstract());
//		} else {
//			mHolder.item_abstract.setVisibility(View.GONE);
//		}
//		//判断该新闻是否是特殊标记的，推广等，为空就是新闻
//		if(!TextUtils.isEmpty(news.getLocal())){
//			mHolder.list_item_local.setVisibility(View.VISIBLE);
//			mHolder.list_item_local.setText(news.getLocal());
//		}else{
//			mHolder.list_item_local.setVisibility(View.GONE);
//		}
//		//判断评论字段是否为空，不为空显示对应布局
//		if(!TextUtils.isEmpty(news.getComment())){
//			//news.getLocal() != null &&
//			mHolder.comment_layout.setVisibility(View.VISIBLE);
//			mHolder.comment_content.setText(news.getComment());
//		}else{
//			mHolder.comment_layout.setVisibility(View.GONE);
//		}
//		//判断该新闻是否已读
//		if(!news.getReadStatus()){
//			mHolder.item_layout.setSelected(true);
//		}else{
//			mHolder.item_layout.setSelected(false);
//		}
//		//设置+按钮点击效果
//		mHolder.popicon.setOnClickListener(new popAction(position));
//		//头部的相关东西
//		int section = getSectionForPosition(position);
//		if (getPositionForSection(section) == position) {
//			mHolder.layout_list_section.setVisibility(View.VISIBLE);
////			head_title.setText(news.getDate());
//			mHolder.section_text.setText(mSections.get(section));
//			mHolder.section_day.setText("今天");
//		} else {
//			mHolder.layout_list_section.setVisibility(View.GONE);
//		}
		return view;
	}

	static class ViewHolder {
		LinearLayout item_layout;
		//title
		TextView item_title;
		//图片源
		TextView item_source;
		//类似推广之类的标签
		TextView list_item_local;
		//评论数量
		TextView comment_count;
		//发布时间
		TextView publish_time;
		//新闻摘要
		TextView item_abstract;
		//右上方TAG标记图片
		ImageView alt_mark;
		//右边图片
		ImageView right_image;
		//3张图片布局
		LinearLayout item_image_layout; //3张图片时候的布局
		ImageView item_image_0;
		ImageView item_image_1;
		ImageView item_image_2;
		//大图的图片的话布局
		ImageView large_image;
		//pop按钮
		ImageView popicon;
		//评论布局
		RelativeLayout comment_layout;
		TextView comment_content;
		//paddingview
		View right_padding_view;
		
		//头部的日期部分
		LinearLayout layout_list_section;
		TextView section_text;
		TextView section_day;
	}

	
	/** popWindow 关闭按钮 */
	private ImageView btn_pop_close;
	
	/**
	 * 初始化弹出的pop
	 * */
	private void initPopWindow() {

	}
	
	/** 
	 * 显示popWindow
	 * */
	public void showPop(View parent, int x, int y,int postion) {

	}
	

}
