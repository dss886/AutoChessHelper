package com.dss886.dotaautochess.feature.news.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Outline
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.TypedValue
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.app.BaseActivity
import com.dss886.dotaautochess.network.Api
import com.dss886.dotaautochess.network.data.Article
import com.dss886.dotaautochess.network.data.Paragraph
import com.dss886.dotaautochess.utils.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by dss886 on 2019/2/2.
 */
class NewsDetailActivity: BaseActivity() {

    companion object {
        fun startActivity(context: Context, title: String?, id : Long) {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(Constants.BUNDLE_TITLE, title)
            intent.putExtra(Constants.BUNDLE_ID, id)
            context.startActivity(intent)
        }
    }

    private var mContainer: LinearLayout? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(Constants.BUNDLE_TITLE) ?: getString(R.string.title_news_detail)
        mContainer = findViewById(R.id.container)
        val scrollView = findViewById<NestedScrollView>(R.id.scroll_view)
        toolbar.setOnClickListener { scrollView?.smoothScrollTo(0, 0) }

        val id = intent.getLongExtra(Constants.BUNDLE_ID, 0)
        if (id <= 0) {
            finish()
            return
        }

        Api.get(Constants.HOST_NEWS_DETAIL + id, success = { response ->
            doAsync {
                val article = Article()
                article.parseData(response.body()?.string())
                uiThread {
                    onDetailLoaded(article)
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onDetailLoaded(article: Article) {
        addUser(article)
        article.paragraphList.forEach { paragraph ->
            when (paragraph.type) {
                Paragraph.TYPE_TEXT -> addText(paragraph.content)
                Paragraph.TYPE_IMAGE -> addImage(paragraph.imageUrl)
            }
        }
    }

    private fun addUser(article: Article) {
        val view = LayoutInflater.from(this)
                .inflate(R.layout.news_detail_item_user, mContainer, false)
                .apply {
                    findViewById<ImageView>(R.id.user_avatar).apply {
                        outlineProvider = object : ViewOutlineProvider() {
                            override fun getOutline(view: View, outline: Outline) {
                                outline.setOval(0, 0, view.width, view.height)
                            }
                        }
                        clipToOutline = true
                        loadImage(article.userAvatarUrl)
                    }
                    findViewById<TextView>(R.id.user_name).apply {
                        text = article.userName
                    }
                    findViewById<TextView>(R.id.create_time).apply {
                        text = article.createTime.toFullTime()
                    }
                }
        mContainer?.addView(view, LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
//            topMargin = 16.dpInt
        })
    }

    @Suppress("DEPRECATION")
    private fun addText(content : String?) {
        content ?: return
        val textView = TextView(this).apply {
            movementMethod = LinkMovementMethod.getInstance()
            text = Html.fromHtml(content).trimTrailingWhitespace()
            setTextColor(R.color.white_a0.toColor())
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
            setLineSpacing(4.dp, 1F)
        }
        mContainer?.addView(textView, LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topMargin = 16.dpInt
        })
    }

    private fun addImage(url : String?) {
        url ?: return
        val imageView = ImageView(this).apply {
            setBackgroundColor(R.color.white_22.toColor())
            adjustViewBounds = true
            loadImage(url)
        }
        mContainer?.addView(imageView, LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            topMargin = 16.dpInt
        })
    }

}
