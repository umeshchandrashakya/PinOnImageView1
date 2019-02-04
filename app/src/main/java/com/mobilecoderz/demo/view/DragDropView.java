package com.mobilecoderz.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


public class DragDropView extends FrameLayout {
    private onDragDropListener Listener;
    private float x, y;
    static ImageView ivDrag;
    private Context context;
    private onDragDropListener dragListener;
    private boolean ismove;
    private int width;
    private int height;
    public DragDropView(Context context) {
        super(context);
        this.ismove = false;
        this.context = context;
    }

    public DragDropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ismove = false;
        this.context = context;
    }


    public DragDropView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ismove = false;
        this.context = context;
    }

    /**
     * Adding draggable object to the dragView
     *
     * @param  - object to be dragged
     * @param -             x horizontal position of the view
     * @param -             y vertical position of the view
     * @param -             width width of the view
     * @param -             height height of the view
     */
    public void AddDraggableView(View draggableObject, float x, float y, int i, int j, onDragDropListener onDragDropListener,int width,int height) {
        dragListener = onDragDropListener;
        android.widget.FrameLayout.LayoutParams layoutParams = new android.widget.FrameLayout.LayoutParams(i, j);
        layoutParams.leftMargin = (int) x;
        layoutParams.topMargin = (int) y;
        this.width=width;
        this.height=height;

        if (draggableObject instanceof ImageView) {
            ivDrag = (ImageView) draggableObject;
            ivDrag.setLayoutParams(layoutParams);
            ivDrag.setOnTouchListener(OnTouchToDrag);
            addView(ivDrag);
        }
        //TODO implement to do other type of view
//		else if(draggableObject instanceof TextView) {
//			TextView tvDrag = (TextView) draggableObject;
//			tvDrag.setLayoutParams(lpDraggableView);
//			tvDrag.setOnTouchListener(OnTouchToDrag);
//			this.addView(tvDrag);
//		}
//		else if(draggableObject instanceof Button) {
//			Button btnDrag = (Button) draggableObject;
//			btnDrag.setLayoutParams(lpDraggableView);
//			btnDrag.setOnTouchListener(OnTouchToDrag);
//			this.addView(btnDrag);
//		}

    }

    /**
     * Draggable object ontouch listener
     * Handle the movement of the object when dragged and dropped
     */
    private View.OnTouchListener OnTouchToDrag = new View.OnTouchListener() {


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            FrameLayout.LayoutParams dragParam = (LayoutParams) v.getLayoutParams();
            int h=v.getHeight();
            int w=v.getWidth();
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE: {
                    ivDrag.setMaxHeight(150);
                    // dragParam.height = 100;
                    dragParam.width = 160;
                    Toast.makeText(getContext(),"moving"+event.getRawX()+""+event.getRawY(),Toast.LENGTH_SHORT).show();
                    dragParam.topMargin = (int) event.getRawY() - (v.getHeight()+300);
                    dragParam.leftMargin = (int) event.getRawX() - (v.getWidth() / 2);
                    v.setLayoutParams(dragParam);
                    v.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    //ivDrag.setMaxHeight(150);
                    // dragParam.height = 100;
                    dragParam.width = 160;
                    dragParam.topMargin = (int) event.getRawY() - (v.getHeight() +300);
                    dragParam.leftMargin = (int) event.getRawX() - (v.getWidth());
                    v.setLayoutParams(dragParam);
                    int[] posXY = new int[2];
                    ivDrag.getLocationOnScreen(posXY);
                    int x = posXY[0];
                    int y = posXY[1];
                    if(y>=(height+300)||x>=width||y<=0||x<=-60){
                        ivDrag.setVisibility(INVISIBLE);
                    }else  if(y>=height+300||y<=300||x>=width||x<-60) {
                        ivDrag.setVisibility(INVISIBLE);
                    }else{
                        dragListener.onDrop((int) event.getRawX(), (int) event.getRawY()-300);
                        ivDrag.setVisibility(INVISIBLE);
                        v.invalidate();
                    }
                    break;
                }case MotionEvent.ACTION_DOWN: {
                    dragParam.height = 100;
                    dragParam.width = 160;
                    v.setLayoutParams(dragParam);
                    v.invalidate();
                    break;
                }
            }

            return true;
        }

    };



}













