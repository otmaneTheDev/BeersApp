package com.otmanethedev.ui_components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.otmanethedev.ui_components.Constants.EMPTY_STRING
import com.otmanethedev.ui_components.Constants.UNDEFINED_RES
import com.otmanethedev.ui_components.databinding.BeerSpecificationBinding

class BeerSpecification(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    private val binding: BeerSpecificationBinding = BeerSpecificationBinding.inflate(LayoutInflater.from(context), this, true)

    var icon: Int = UNDEFINED_RES
        set(value) {
            if (value != UNDEFINED_RES) {
                binding.imgIcon.setImageResource(value)
                binding.imgIcon.visibility = View.VISIBLE
            } else {
                binding.imgIcon.visibility = View.GONE
            }
            field = value
        }

    var label: String = EMPTY_STRING
        set(value) {
            if (value != EMPTY_STRING) {
                binding.txtLabel.text = value
                binding.txtLabel.visibility = View.VISIBLE
            } else {
                binding.txtLabel.visibility = View.GONE
            }
            field = value
        }

    var value: String = EMPTY_STRING
        set(value) {
            if (value != EMPTY_STRING) {
                binding.txtValue.text = value
                binding.txtValue.visibility = View.VISIBLE
            } else {
                binding.txtValue.visibility = View.GONE
            }
            field = value
        }

    init {
        attrs?.let { setUpAttrs(it) }
    }

    private fun setUpAttrs(attrs: AttributeSet) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BeerSpecification, 0, 0)

        runCatching {
            icon = typedArray.getResourceId(R.styleable.BeerSpecification_icon, UNDEFINED_RES)
            label = typedArray.getString(R.styleable.BeerSpecification_label) ?: EMPTY_STRING
        }

        typedArray.recycle()
    }

}