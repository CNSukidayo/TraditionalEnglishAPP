package com.gitee.cnsukidayo.traditionalenglish.activity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gitee.cnsukidayo.traditionalenglish.R;
import com.gitee.cnsukidayo.traditionalenglish.activity.adapter.listener.MoveAndSwipedListener;
import com.gitee.cnsukidayo.traditionalenglish.activity.adapter.listener.StateChangedListener;
import com.gitee.cnsukidayo.traditionalenglish.entity.WordCategory;
import com.gitee.cnsukidayo.traditionalenglish.handler.StartFunctionHandler;
import com.gitee.cnsukidayo.traditionalenglish.utils.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author cnsukidayo
 * @date 2023/1/7 17:35
 */
public class StartSingleCategoryAdapter extends RecyclerView.Adapter<StartSingleCategoryAdapter.RecyclerViewHolder> implements MoveAndSwipedListener {

    private Context context;
    // 用于存储所有所有的element
    private List<StartSingleCategoryAdapter.RecyclerViewHolder> cacheElement = new ArrayList<>(15);
    // 用于回调startDrag方法的接口,该接口耦合了,定义方式不好.
    private Consumer<RecyclerView.ViewHolder> startDragListener;
    // 用于处理单词收藏功能的Handler
    private StartFunctionHandler startFunctionHandler;

    private boolean isFirst = true;

    public StartSingleCategoryAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public StartSingleCategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StartSingleCategoryAdapter.RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_word_credit_start_single_category, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull StartSingleCategoryAdapter.RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // 重置改变，防止由于复用而导致的显示问题
        holder.itemView.scrollTo(0,0);
        WordCategory wordCategory = startFunctionHandler.getWordCategoryByPosition(position);
        StringBuilder defaultNameRule = new StringBuilder();
        for (int i = 0; i < 10 && i < wordCategory.getWords().size(); i++) {
            defaultNameRule.append(wordCategory.getWords().get(i).getWordOrigin());
        }
        if (wordCategory.isDefaultTitleRule() && !Strings.notEmpty(wordCategory.getTitle())) {
            holder.title.setText(defaultNameRule.toString());
        } else {
            holder.title.setText(wordCategory.getTitle());
        }
        if (wordCategory.isDefaultDescribeRule() && !Strings.notEmpty(wordCategory.getDescribe())) {
            holder.describe.setText(defaultNameRule.toString());
        } else {
            holder.describe.setText(wordCategory.getDescribe());
        }
    }

    @Override
    public int getItemCount() {
        return startFunctionHandler.categoryListSize();
    }

    public void onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        startFunctionHandler.removeCategory(position);
        notifyItemRemoved(position);
    }

    public void addNewCategory(WordCategory wordCategory) {
        if (startFunctionHandler == null) {
            Log.e("no Handler", "caller want to use StartFunction,but no settings startFunctionHandler");
            return;
        }
        startFunctionHandler.addNewCategory(wordCategory);
        notifyItemInserted(startFunctionHandler.categoryListSize() - 1);
    }


    public void setStartFunctionHandler(StartFunctionHandler startFunctionHandler) {
        this.startFunctionHandler = startFunctionHandler;
    }

    public void setStartDragListener(Consumer<RecyclerView.ViewHolder> startDragListener) {
        this.startDragListener = startDragListener;
    }


    protected class RecyclerViewHolder extends RecyclerView.ViewHolder implements StateChangedListener, View.OnTouchListener, View.OnClickListener {
        public View itemView;
        public TextView title, describe, edit, delete;
        public LinearLayout categoryLinearLayout;
        public ImageButton openList, move;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.title = itemView.findViewById(R.id.fragment_word_credit_start_single_category_title);
            this.describe = itemView.findViewById(R.id.fragment_word_credit_start_single_category_detail);
            this.categoryLinearLayout = itemView.findViewById(R.id.fragment_word_credit_start_single_category_linear_layout);
            this.openList = itemView.findViewById(R.id.fragment_word_credit_start_open_list);
            this.edit = itemView.findViewById(R.id.fragment_word_credit_start_edit);
            this.delete = itemView.findViewById(R.id.fragment_word_credit_start_delete);
            this.move = itemView.findViewById(R.id.fragment_word_credit_start_move);
            this.move.setOnTouchListener(this);
            this.delete.setOnClickListener(this);
        }

        @Override
        public void onItemSelected() {
            categoryLinearLayout.setAlpha(0.5f);
        }

        @Override
        public void onItemClear() {
            categoryLinearLayout.setAlpha(1.0f);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.fragment_word_credit_start_move:
                    if (event.getAction() == MotionEvent.ACTION_DOWN && startDragListener != null) {
                        startDragListener.accept(this);
                    }
                    break;
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fragment_word_credit_start_delete:
                    // 删除当前Item
                    startFunctionHandler.removeCategory(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    break;
                case R.id.fragment_word_credit_start_edit:
                    break;
            }
        }
    }

}
