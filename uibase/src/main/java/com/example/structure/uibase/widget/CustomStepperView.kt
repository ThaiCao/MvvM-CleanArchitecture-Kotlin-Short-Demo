package com.example.structure.uibase.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.structure.model.presentation.*
import com.example.structure.uibase.R
import com.example.structure.uibase.extend.getThemeColor
import com.example.structure.uibase.extend.toPx

class CustomStepperView  : View {

    var stepperData: SingleSelectableList<StepperData> = SingleSelectableList()
        set(value) {
            field = value
            requestLayout()
        }

    private val sizeState: Int
        get() {
            return stepperData.size
        }

    private val lineThickness = 1.toPx().toFloat()
    private val borderThickness = 1.toPx().toFloat()
    private var uncheckColor: Int = 0
    private var checkColor: Int = 0

    private val circleSize = 24f
    private val descriptionTextSize = 18f
    private val marginTextCircle = 16
    private val textStyle: TextView = TextView(context).apply {
        setTextAppearance(R.style.Paragraph_Small_OnSurface)
    }
    private val paddingLeftInternal = 26.toPx()
    private val paddingRightInternal = 26.toPx()

    private val circles = mutableListOf<Circle>()
    private val lines = mutableListOf<Line>()
    private val texts = mutableListOf<Text>()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        setBackgroundResource(0)
        setPadding(0, 0, 0, 0)
        checkColor = getThemeColor(context, R.attr.colorLink)
        uncheckColor = getThemeColor(context, R.attr.colorAnchor)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth: Int = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight: Int =
            circleSize.toInt()
                .toPx() + marginTextCircle.toPx() + descriptionTextSize.toInt()
                .toPx() + 2 * borderThickness.toInt()
                .toPx()
        setMeasuredDimension(
            measureDimension(desiredWidth, widthMeasureSpec),
            desiredHeight
        )
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (specMode == MeasureSpec.AT_MOST) {
                result = result.coerceAtMost(specSize)
            }
        }

        return result
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        prepare()
    }

    private fun prepare() {
        circles.clear()
        lines.clear()
        texts.clear()

        val spaceTwoCircle = if (sizeState <= 1) {
            0f
        } else {
            val totalStepperSpace = width - paddingRightInternal * 2f - paddingLeftInternal
            val lineSpace = totalStepperSpace / (sizeState - 1f)
            lineSpace
        }
        var startX = paddingLeftInternal.toFloat()
        val startY = circleSize
        val endX: Float = startX + circleSize
        val endY: Float = circleSize

        stepperData.mapIndexed { index, data ->
            texts.add(
                Text(
                    text = data.value,
                    pointF = PointF(
                        startX + circleSize.toInt().toPx() / 2,
                        startY + circleSize.toInt()
                            .toPx() + marginTextCircle.toPx() + borderThickness * 2
                    ),
                    paint = Paint().apply {
                        textSize = textStyle.textSize
                        color = textStyle.currentTextColor
                        typeface = textStyle.typeface
                        textAlign = Paint.Align.CENTER
                        isAntiAlias = true
                    }
                )
            )

            if (index != 0) {
                lines.add(
                    Line(
                        start = PointF(
                            circles[index - 1].start.x + circleSize * 2,
                            circles[index - 1].start.y + circleSize.toInt().toPx() / 2.0f
                        ),
                        end = PointF(
                            startX + borderThickness * 2,
                            circles[index - 1].end.y + circleSize.toInt().toPx() / 2.0f
                        ),
                        paint = Paint().apply {
                            this.strokeWidth = lineThickness
                            this.color = when {
                                data == stepperData.itemSelected -> checkColor
                                data.state is StepperState.Uncheck -> uncheckColor
                                else -> checkColor
                            }
                            this.style = Paint.Style.FILL_AND_STROKE
                            this.isAntiAlias = true
                        },
                    )
                )
            }

            if (stepperData.itemSelected === data) {
                circles.add(
                    Circle(
                        start = PointF(startX, startY),
                        end = PointF(endX, endY),
                        bitmap = getCircleBitmap(R.drawable.ic_nested_green_circle)
                    )
                )
            } else if (data.state is StepperState.Check) {
                circles.add(
                    Circle(
                        start = PointF(startX, startY),
                        end = PointF(endX, endY),
                        bitmap = getCircleBitmap(R.drawable.ic_green_check_thin_mark)
                    )
                )
            } else if (data.state is StepperState.Uncheck) {
                circles.add(
                    Circle(
                        start = PointF(startX, startY),
                        end = PointF(endX, endY),
                        bitmap = getCircleBitmap(R.drawable.ic_grey_circle)
                    )
                )
            }

            startX += spaceTwoCircle
        }
    }

    private fun getCircleBitmap(resourceId: Int): Bitmap {
        return ContextCompat.getDrawable(context, resourceId)!!.toBitmap()
    }

    override fun onDraw(canvas: Canvas) {
        drawLines(canvas)
        drawCircles(canvas)
        drawTexts(canvas)
    }

    private fun drawLines(canvas: Canvas) {
        lines.forEach {
            it.draw(canvas)
        }
    }

    private fun drawCircles(canvas: Canvas) {
        circles.forEach {
            it.draw(canvas)
        }
    }

    private fun drawTexts(canvas: Canvas) {
        texts.forEach {
            it.draw(canvas)
        }
    }

    data class Circle(
        val start: PointF,
        val end: PointF,
        val bitmap: Bitmap
    ) {

        fun draw(canvas: Canvas) {
            canvas.drawBitmap(
                bitmap,
                start.x,
                start.y,
                Paint().apply {
                    this.isAntiAlias = true
                }
            )
        }
    }

    data class Line(
        val start: PointF,
        val end: PointF,
        val paint: Paint
    ) {

        fun draw(canvas: Canvas) {
            canvas.drawLine(
                start.x,
                start.y,
                end.x,
                end.y,
                paint
            )
        }
    }

    data class Text(
        val text: String,
        val pointF: PointF,
        val paint: Paint
    ) {

        fun draw(canvas: Canvas) {
            canvas.drawText(
                text,
                pointF.x,
                pointF.y,
                paint
            )
        }
    }
}
