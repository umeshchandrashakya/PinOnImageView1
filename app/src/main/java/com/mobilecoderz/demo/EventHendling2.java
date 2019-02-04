package com.mobilecoderz.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.mobilecoderz.demo.view.DragDropView;
import com.mobilecoderz.demo.view.PinView;
import com.mobilecoderz.demo.view.onDragDropListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lenovo on 23-03-2017.
 */

public class EventHendling2  extends Activity implements View.OnClickListener, onDragDropListener

    {

        private static final String BUNDLE_POSITION = "position";
        int i = 0;

        private int position;
        public ArrayList<PointF> pointsList = new ArrayList<PointF>();
        ArrayList<String> sidcriptiopn = new ArrayList<String>();
        private List<Note> notes;
        RelativeLayout llContainerMain1;
        SubsamplingScaleImageView imageView;
        PinView pinView;
        DragDropView dragDropView;
        ImageView imageView1;
        PointF currentPoint;

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_handling);
        // getActionBar().setTitle("Advanced event handling");
        // getActionBar().setDisplayHomeAsUpEnabled(true);


        initialiseImage();

    }

        @Override
        protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_POSITION, position);
    }


        @Override
        public boolean onOptionsItemSelected (MenuItem item){
        finish();
        return true;
    }

    private void initialiseImage() {

        imageView = (SubsamplingScaleImageView) findViewById(R.id.imageView);


        //imageView.getScaleX();
        //imageView.getScaleY();


        //llContainerMain.se(dragDropView);

        currentPoint = new PointF(190, 450);



        pinView = (PinView) findViewById(R.id.imageView);

        /// sidcriptiopn.add("umesh chandra"+i++);
        pinView.setPin(currentPoint);



            final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    if (imageView.isReady()) {
                        final PointF sCoord = imageView.viewToSourceCoord(e.getX(), e.getY());
                        PointF center;
                        PinView pinView;
                        if (imageView.isReady()) {
                            // PointF sCoord = imageView.viewToSourceCoord(e.getX(), e.getY());
                            pinView = (PinView) findViewById(R.id.imageView);
                            Random random = new Random();
                             float maxScale = pinView.getMaxScale();
                            float minScale = pinView.getMinScale();
                            float scale = (sCoord.x * (maxScale - minScale)) + minScale;
                            center = new PointF(1660, 400);
                            ////pointsList.add(center);
                            //sidcriptiopn.add("umesh chandra" + i++);
                           // pinView.setPin(center);
                            for(int i=0;i<10;i++)
                            dragDropView(202+10+1,202+20+i);

                        }
                        Toast.makeText(getApplicationContext(), "Single tap: " + ((int) sCoord.x) + ", " + ((int) sCoord.y), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Single tap: Image not ready", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent motionEvent) {
                   /* if (imageView.isReady()) {
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        PointF vPin = imageView.sourceToViewCoord(currentPoint.x, currentPoint.y);
                        System.out.println(currentPoint.x);
                        System.out.println(currentPoint.y);
                        Toast.makeText(EventHendling2.this, "long press", Toast.LENGTH_LONG).show();
                        float x1 = vPin.x;
                        float y1 = vPin.y;
                        if (x > vPin.x && x < vPin.x + 30 && y > vPin.y && y < vPin.y + 50) {
                            Toast.makeText(EventHendling2.this, "bitmap touched", Toast.LENGTH_LONG).show();
                            dragDropView(x - 50f, y - 80f);
                        }
                        if (x > vPin.x - 50 && x < vPin.x + 20 && y > vPin.y - 100 && y < vPin.y) {
                            dragDropView(x - 50f, y - 80f);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Long press: Image not ready", Toast.LENGTH_SHORT).show();
                    }*/
                }

                @Override
                public boolean onDoubleTap(MotionEvent event) {
                    if (imageView.isReady()) {
                        //PointF sCoord = imageView.viewToSourceCoord(pointsList.get(0));
                        //Toast.makeText(getApplicationContext(), "Double tap: " + ((int)sCoord.x) + ", " + ((int)sCoord.y), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Double tap: Image not ready", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });


            imageView.setImage(ImageSource.resource(R.drawable.floor_plan));
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return gestureDetector.onTouchEvent(motionEvent);
                }
            });

        }



    private void openPopup() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText input = new EditText(EventHendling2.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alert.setView(input);
        alert.show();


    }

    @Override
    public void onClick(View v) {

    }


    private static final class Note {
        private final String text;
        private final String subtitle;

        private Note(String subtitle, String text) {
            this.subtitle = subtitle;
            this.text = text;
        }
    }

    private void dragDropView(float f, float f1) {
        llContainerMain1 = (RelativeLayout) findViewById(R.id.content);
        int width = llContainerMain1.getWidth();
        int height = llContainerMain1.getHeight();
        llContainerMain1.setVisibility(View.VISIBLE);
        dragDropView = new DragDropView(EventHendling2.this);
        dragDropView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        ImageView imageview = new ImageView(EventHendling2.this);
        imageview.setImageDrawable(getResources().getDrawable(R.drawable.pushpin_blue));
        dragDropView.AddDraggableView(imageview, (int) f, (int) f1, 120, 160, this, width, height);
        llContainerMain1.addView(dragDropView);

    }

    @Override
    public void onDragStart() {

    }

    public void onDrop(float f, float f1) {
        llContainerMain1.setVisibility(View.VISIBLE);
        currentPoint = imageView.viewToSourceCoord(f, f1);
        pinView.setPin(currentPoint);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int width = imageView.getWidth();
        //llContainerMain1.getHeight();
        //llContainerMain1.getWidth();
        int higt = imageView.getHeight();
        int a = imageView.getTop();
        int b = imageView.getBottom();
        int h = pxToDp(higt);
        int w = pxToDp(width);

        int pinH = pinView.getHeight();
        int pinW = pinView.getWidth();
        pinView.getWidth();
        float xscale = imageView.getScaleX();
        float yscale = imageView.getScaleY();
        float x = imageView.getX();
        float y = imageView.getY();
        int finalHeight = imageView.getMeasuredHeight();
        int finalWidth = imageView.getMeasuredWidth();

        System.out.println(width);
        int height = imageView.getHeight();
        System.out.print(height);
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = EventHendling2.this.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}