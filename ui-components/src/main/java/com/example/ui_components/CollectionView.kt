package com.example.ui_components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.getResourceIdOrThrow
import com.airbnb.lottie.LottieAnimationView

@Suppress("MemberVisibilityCanBePrivate")
class CollectionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val title by lazy<TextView> { findViewById(R.id.textviewTitle) }
    val subtitle by lazy<TextView> { findViewById(R.id.textviewSubTitle) }
    val anim by lazy<LottieAnimationView> { findViewById(R.id.animationView) }

    init {

        inflate(context, R.layout.collection_view, this)

        attrs?.let {

            val attributes = context.obtainStyledAttributes(it, R.styleable.CollectionView)

            // Apply attributes
            with(attributes) {

                // Title / description
                getString(R.styleable.CollectionView_title)?.run { title.text = this }
                getString(R.styleable.CollectionView_subtitle)?.run { subtitle.text = this }

                // Drawable or Lottie
                with(anim) {
                    setAnimation(getResourceIdOrThrow(R.styleable.CollectionView_animSrc))
                    visibility = View.VISIBLE
                    playAnimation()
                }
                recycle()
            }
        }
    }

}