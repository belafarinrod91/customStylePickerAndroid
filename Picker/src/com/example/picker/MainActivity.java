package com.example.picker;


import java.util.ArrayList;

import android.os.Bundle;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
 
public class MainActivity extends Activity {

CustomPicker cp;
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	ArrayList<CustomPickerElement> elements = new ArrayList<CustomPickerElement>();
	elements.add(new CustomPickerElement(this,"1"));



	
	
	cp = new CustomPicker(this, elements);
	
 }
 

 public void up(){
	 cp.up();
	 
 }
 
 public void down(){
	 cp.down();
 }
 
 
 
 class CustomPicker{
	 
	 private MainActivity mMainActivity;
	 private ArrayList<CustomPickerElement> mPickerElements;
	 private TextView center, center1, center2, centerMin1, centerMin2;
	 private ImageView ivCenter, ivCenter1, ivCenter2, ivCenterMin1, ivCenterMin2;
	 private int mCurrentIndex;
	 private Bitmap mDefaultBitmap;
	 int nStart = 5;
	 int nEnd = 250;
	 
	 
	 public CustomPicker(MainActivity mainActivity, ArrayList<CustomPickerElement> pickerElements){
		mMainActivity = mainActivity;
		mPickerElements = pickerElements;
		initTextViews();
		initGestureDetector();
		
		mDefaultBitmap =  BitmapFactory.decodeResource(mMainActivity.getResources(),R.drawable.ic_launcher);
		
		initPicker();
		mCurrentIndex = 0;
				
	 }
	 
	 private void initTextViews(){
		 centerMin2 = (TextView) findViewById(R.id.centerMin2);
		 centerMin1 = (TextView) findViewById(R.id.centerMin1);
		 center = (TextView) findViewById(R.id.center);
		 center1 = (TextView) findViewById(R.id.center1);
		 center2 = (TextView) findViewById(R.id.center2);
		 
		 ivCenterMin2 = (ImageView) findViewById(R.id.ivCenterMin2);
		 ivCenterMin1 = (ImageView) findViewById(R.id.ivCenterMin1);
		 ivCenter = (ImageView) findViewById(R.id.ivCenter);
		 ivCenter1 = (ImageView) findViewById(R.id.ivCenter1);
		 ivCenter2 = (ImageView) findViewById(R.id.ivCenter2);


		 
		 centerMin2.setOnClickListener(new OnDownClickListener(this));
		 centerMin1.setOnClickListener(new OnDownClickListener(this));
		 
		 center2.setOnClickListener(new OnUpClickListener(this));
		 center1.setOnClickListener(new OnUpClickListener(this));

	 }
	 
	 private void initGestureDetector(){
		SimpleGestureFilter activitySwipeDetector = new SimpleGestureFilter(mMainActivity);
		LinearLayout lowestLayout = (LinearLayout)mMainActivity.findViewById(R.id.linearLayoutForPicker);
		
		lowestLayout.setOnTouchListener(activitySwipeDetector);
	 }
	 
	 private void initPicker(){
		 if(mPickerElements.size() >= 3){
			 changeTextFields("", "", mPickerElements.get(0).getText(), mPickerElements.get(1).getText(), mPickerElements.get(2).getText());
		 } else if(mPickerElements.size() == 2) {
			 changeTextFields("", "", mPickerElements.get(0).getText(), mPickerElements.get(1).getText(), "");
		 } else if(mPickerElements.size() == 1) {
			 changeTextFields("", "", mPickerElements.get(0).getText(), "", "");
		 }
		 
	 }
	 
	 private void changeTextFields(String sCenterMin2, String sCenterMin1, String sCenter, String sCenter1, String sCenter2){
		 if(sCenterMin2.equals("")){
			 ivCenterMin2.setVisibility(View.INVISIBLE);
		 } else {
			 ivCenterMin2.setVisibility(View.VISIBLE);
		 }
		 
		 if(sCenterMin1.equals("")){
			 ivCenterMin1.setVisibility(View.INVISIBLE);
		 } else {
			 ivCenterMin1.setVisibility(View.VISIBLE);
		 }
		 
		 if(sCenter.equals("")){
			 ivCenter.setVisibility(View.INVISIBLE);
		 } else {
			 ivCenter.setVisibility(View.VISIBLE);
		 }
		 
		 if(sCenter1.equals("")){
			 ivCenter1.setVisibility(View.INVISIBLE);
		 } else {
			 ivCenter1.setVisibility(View.VISIBLE);
		 }
		 
		 if(sCenter2.equals("")){
			 ivCenter2.setVisibility(View.INVISIBLE);
		 } else {
			 ivCenter2.setVisibility(View.VISIBLE);
		 }
		 
		 centerMin2.setText(sCenterMin2);
		 centerMin1.setText(sCenterMin1);
		 center.setText(sCenter);
		 center1.setText(sCenter1);
		 center2.setText(sCenter2);
	 }
	 
	 private void changeImageViews(Bitmap min2, Bitmap min1, Bitmap center, Bitmap plus1, Bitmap plus2){
		 
		 ivCenterMin2.setImageBitmap(min2);
		 ivCenterMin1.setImageBitmap(min1);
		 ivCenter.setImageBitmap(center);
		 ivCenter1.setImageBitmap(plus1);
		 ivCenterMin2.setImageBitmap(plus2);
	 }
	 
	 private String getTextFromListElement(int index){
		 String result;
		 try{
			  result = mPickerElements.get(index).getText();
		 } catch(IndexOutOfBoundsException e){
			 result = "";
		 } catch(NullPointerException e){
			 result = "";
		 }
		 return result;
	 }
	 
	 private Bitmap getIconFromListElement(int index){
		 Bitmap icn;
		 try{
			 icn = mPickerElements.get(index).getIcn();
		 } catch(IndexOutOfBoundsException e){
			 icn = mDefaultBitmap;
		 } catch(NullPointerException e){
			 icn = mDefaultBitmap;
		 }
		 return icn;
	 }
	 
	 public void up(){

		if(!(mCurrentIndex+1 == mPickerElements.size())){
			mCurrentIndex++;
		}
		
		
		
			changeTextFields(
					getTextFromListElement(mCurrentIndex-2),
					getTextFromListElement(mCurrentIndex-1),
					getTextFromListElement(mCurrentIndex),
					getTextFromListElement(mCurrentIndex+1),
					getTextFromListElement(mCurrentIndex+2));
			changeImageViews(
					getIconFromListElement(mCurrentIndex-2), 
					getIconFromListElement(mCurrentIndex-1), 
					getIconFromListElement(mCurrentIndex), 
					getIconFromListElement(mCurrentIndex+1), 
					getIconFromListElement(mCurrentIndex+2));
	 }
	 
	 public void down(){
		 if(mCurrentIndex-1 >= 0){
				mCurrentIndex--;
			}	
		 
		 changeTextFields(
					getTextFromListElement(mCurrentIndex-2),
					getTextFromListElement(mCurrentIndex-1),
					getTextFromListElement(mCurrentIndex),
					getTextFromListElement(mCurrentIndex+1),
					getTextFromListElement(mCurrentIndex+2));
		 changeImageViews(
					getIconFromListElement(mCurrentIndex-2), 
					getIconFromListElement(mCurrentIndex-1), 
					getIconFromListElement(mCurrentIndex), 
					getIconFromListElement(mCurrentIndex+1), 
					getIconFromListElement(mCurrentIndex+2));
	 
 }
 
 }
 
 class CustomPickerElement{
	 
	 private int mId;
	 private String mText;
	 private Bitmap mIcn;
	 private Context ctx;
	 
	 public CustomPickerElement(Context context, String text){
		 mText = text;
		 ctx = context;
	 }
	 
	 public String getText(){
		 return mText;
	 }
	 
	 public Bitmap getIcn(){
		 return BitmapFactory.decodeResource(ctx.getResources(),R.drawable.ic_launcher);
	 }
	 
 }
 
 class OnUpClickListener implements OnClickListener {

	CustomPicker mCustomPicker;
	public OnUpClickListener(CustomPicker parent) {
		mCustomPicker = parent;
	}
	 
	@Override
	public void onClick(View v) {
		mCustomPicker.up();
	}
	 
 }
 
 class OnDownClickListener implements OnClickListener {

	CustomPicker mCustomPicker;
	public OnDownClickListener(CustomPicker parent) {
		mCustomPicker = parent;
	}
	 
	@Override
	public void onClick(View v) {
		mCustomPicker.down();
	}
 }
 
 
 
}
