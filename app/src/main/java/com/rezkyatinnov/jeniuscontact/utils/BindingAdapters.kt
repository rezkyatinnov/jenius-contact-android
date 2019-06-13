package com.rezkyatinnov.jeniuscontact.utils

import android.os.Build
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.text.TextWatcher
import android.webkit.WebView
import android.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Created by rezkyatinnov on 13/06/2019.
 */


@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("mutableTextHtml")
fun setMutableTextHtml(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.text = Html.fromHtml(value ?: "", Html.FROM_HTML_MODE_COMPACT)
            } else view.text = Html.fromHtml(value ?: "")
        })
    }
}

@BindingAdapter("mutableUrlWebview")
fun setMutableText(view: WebView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            view.loadUrl(value ?: "")
        })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("layout_manager")
fun setLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    view.layoutManager = layoutManager
}

@BindingAdapter("onScrollListener")
fun addOnScrollListener(view: RecyclerView, onScrollListener: RecyclerView.OnScrollListener) {
    view.addOnScrollListener(onScrollListener)
}

@BindingAdapter("mutableImageUrl")
fun loadMutableImage(view: ImageView, mutableUrl: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && mutableUrl != null) {
        mutableUrl.observe(parentActivity, Observer { value ->
            val url = value ?: ""
            if (!url.isBlank()) {
                Glide.with(view.context).load(value).into(view)
            }
        })
    }
}

@BindingAdapter("textChangedListener")
fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher) {
    editText.addTextChangedListener(textWatcher)
}

@BindingAdapter("editorActionListener")
fun bindOnEditorActionListener(editText: EditText, onEditorActionListener: TextView.OnEditorActionListener) {
    editText.setOnEditorActionListener(onEditorActionListener)
}

@BindingAdapter("checkChangedListener")
fun bindCheckChangeListener(checkBox: CheckBox, onCheckedChangeListener: CompoundButton.OnCheckedChangeListener) {
    checkBox.setOnCheckedChangeListener(onCheckedChangeListener)
}

@BindingAdapter("onClickListener")
fun bindOnClickListener(view: View, onClickListener: View.OnClickListener) {
    view.setOnClickListener(onClickListener)
}

@BindingAdapter("onRefreshListener")
fun bindOnRefreshListener(
    swipeRefreshLayout: SwipeRefreshLayout,
    onRefreshListener: SwipeRefreshLayout.OnRefreshListener
) {
    swipeRefreshLayout.setOnRefreshListener(onRefreshListener)
}

@BindingAdapter("isRefreshing")
fun bindIsRefreshLayoutRefreshing(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: MutableLiveData<Boolean>?) {
    val parentActivity: AppCompatActivity? = swipeRefreshLayout.getParentActivity()
    if (parentActivity != null && isRefreshing != null) {
        isRefreshing.observe(parentActivity, Observer { value -> swipeRefreshLayout.isRefreshing = value })
    }
}
